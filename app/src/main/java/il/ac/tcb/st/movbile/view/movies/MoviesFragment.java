package il.ac.tcb.st.movbile.view.movies;

import android.content.res.Configuration;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.adapters.MovieAdapter;
import il.ac.tcb.st.movbile.databinding.FragmentMoviesBinding;
import il.ac.tcb.st.movbile.model.Movie;

public class MoviesFragment extends Fragment {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private MoviesViewModel moviesViewModel;
    private FragmentMoviesBinding fragmentMoviesBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(inflater, container, false);
        View view = fragmentMoviesBinding.getRoot();

        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        getPopularMovies();

        return view;
    }

    private void getPopularMovies() {
        moviesViewModel.getAllMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
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
        movieAdapter = new MovieAdapter(getContext(), movies, navController);
        recyclerView.setAdapter(movieAdapter);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        movieAdapter.notifyDataSetChanged();
    }
}