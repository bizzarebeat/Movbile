package il.ac.tcb.st.movbile.view.searchmovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.adapters.MovieSearchAdapter;
import il.ac.tcb.st.movbile.databinding.FragmentMoviesBinding;
import il.ac.tcb.st.movbile.model.Movie;
import il.ac.tcb.st.movbile.view.movies.MoviesViewModel;

public class SearchMoviesFragment extends Fragment {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieSearchAdapter movieAdapter;
    private MoviesViewModel moviesViewModel;
    private FragmentMoviesBinding fragmentMoviesBinding;

    private String query;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false);
        View view = fragmentMoviesBinding.getRoot();

        // Retrieve the query from the arguments
        if (getArguments() != null) {
            query = getArguments().getString("query");
        }

        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        SearchMovies();

        return view;
    }

    private void SearchMovies() {
        moviesViewModel.getSearchMovies(query).observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                ShowOnRecyclerView();
            }
        });
    }

    private void ShowOnRecyclerView() {
        recyclerView = fragmentMoviesBinding.rvMovies;
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        movieAdapter = new MovieSearchAdapter(getContext(), movies, navController);
        recyclerView.setAdapter(movieAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieAdapter.notifyDataSetChanged();
    }
}

