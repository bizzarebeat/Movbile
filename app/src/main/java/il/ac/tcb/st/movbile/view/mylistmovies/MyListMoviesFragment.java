package il.ac.tcb.st.movbile.view.mylistmovies;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.adapters.MovieSearchAdapter;
import il.ac.tcb.st.movbile.adapters.MyListAdapter;
import il.ac.tcb.st.movbile.database.MoviesDatabase;
import il.ac.tcb.st.movbile.database.MoviesTable;
import il.ac.tcb.st.movbile.databinding.FragmentMoviesBinding;
import il.ac.tcb.st.movbile.databinding.FragmentMyListBinding;
import il.ac.tcb.st.movbile.databinding.FragmentMyListMoviesBinding;
import il.ac.tcb.st.movbile.view.mylist.MyListViewModel;

public class MyListMoviesFragment extends Fragment {

    private MyListViewModel mViewModel;
    MoviesDatabase db;

    private RecyclerView recyclerView;

    MyListAdapter movieAdapter;

    FragmentMyListMoviesBinding fragmentMyListBinding;


    public static il.ac.tcb.st.movbile.view.mylist.MyListFragment newInstance() {
        return new il.ac.tcb.st.movbile.view.mylist.MyListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentMyListBinding = FragmentMyListMoviesBinding.inflate(inflater, container, false);
        View view = fragmentMyListBinding.getRoot();

        db = MoviesDatabase.getInstance(requireContext());
        List<MoviesTable> movies = db.moviesDao().getAll();

        ShowOnRecyclerView(movies);

        return view;
    }


    private void ShowOnRecyclerView(List<MoviesTable> movies) {
        recyclerView = fragmentMyListBinding.rvMovies;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        movieAdapter = new MyListAdapter(getContext(), movies, navController);
        recyclerView.setAdapter(movieAdapter);

        // Add ItemTouchHelper for swipe to delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                MoviesTable movie = movies.get(position);

                // Remove item from database
                RemoveItem(movie);

                // Remove item from adapter
                movieAdapter.removeItem(position);
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void RemoveItem(MoviesTable movie) { // Remove item from database
        db.moviesDao().deleteMovie(movie);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyListViewModel.class);
        // TODO: Use the ViewModel
    }

}