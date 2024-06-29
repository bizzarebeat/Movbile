package il.ac.tcb.st.movbile.view.movie;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.database.MoviesDatabase;
import il.ac.tcb.st.movbile.database.MoviesTable;
import il.ac.tcb.st.movbile.databinding.FragmentMovieBinding;
import il.ac.tcb.st.movbile.model.Movie;

public class MovieFragment extends Fragment {

    private Movie movie;
    private FragmentMovieBinding fragmentMovieBinding;

    private boolean beenAdded = false;

    MoviesDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        View view = fragmentMovieBinding.getRoot();
        db = MoviesDatabase.getInstance(requireContext());

        if (getArguments() != null) {
            movie = getArguments().getParcelable("movie");
            fragmentMovieBinding.setMovie(movie);
        }

        // Set long press listener on the poster image
        fragmentMovieBinding.ivMovieLarge.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AddToDB(movie);
                if (!beenAdded) {
                    Toast.makeText(getContext(), "Movie added to list", Toast.LENGTH_SHORT).show();
                    beenAdded = true;
                }
                return true; // Returning true indicates the event is consumed
            }
        });

        return view;
    }

    private void AddToDB(Movie movie) {
        MoviesTable movieEntry = new MoviesTable();
        movieEntry.id = movie.getId();
        movieEntry.posterPath = movie.getPosterPath();
        movieEntry.title = movie.getTitle();
        movieEntry.releaseDate = movie.getReleaseDate();
        movieEntry.voteAverage = movie.getVoteAverage();
        movieEntry.overview = movie.getOverview();
        db.moviesDao().insertMovie(movieEntry);

    }
}
