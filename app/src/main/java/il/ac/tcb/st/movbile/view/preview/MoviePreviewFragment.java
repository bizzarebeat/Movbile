package il.ac.tcb.st.movbile.view.preview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.database.MoviesTable;
import il.ac.tcb.st.movbile.databinding.FragmentMovieBinding;
import il.ac.tcb.st.movbile.databinding.FragmentMoviePreviewBinding;

public class MoviePreviewFragment extends Fragment {

    private MoviesTable movie;

    private FragmentMoviePreviewBinding fragmentMovieBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_preview, container, false);
        View view = fragmentMovieBinding.getRoot();

        if (getArguments() != null) {
            movie = (MoviesTable) getArguments().getSerializable("movie");
            fragmentMovieBinding.setMovie(movie);
        }

        return view;
    }
}