package il.ac.tcb.st.movbile.view.mylist;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
import il.ac.tcb.st.movbile.view.mylistmovies.MyListMoviesFragment;
import il.ac.tcb.st.movbile.view.searchmovies.SearchMoviesFragment;

public class MyListFragment extends Fragment {

    private MyListViewModel mViewModel;
    MoviesDatabase db;

    private RecyclerView recyclerView;

    MyListAdapter movieAdapter;

    FragmentMyListBinding fragmentMyListBinding;


    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentMyListBinding = FragmentMyListBinding.inflate(inflater, container, false);
        View view = fragmentMyListBinding.getRoot();

        db = MoviesDatabase.getInstance(requireContext());
        List<MoviesTable> movies = db.moviesDao().getAll();

        LoadList();

        return view;
    }

    private void LoadList() {

        // Load SearchMoviesFragment
        MyListMoviesFragment myListMoviesFragment = new MyListMoviesFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, myListMoviesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
