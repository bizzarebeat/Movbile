package il.ac.tcb.st.movbile.view.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import il.ac.tcb.st.movbile.model.Movie;
import il.ac.tcb.st.movbile.model.MovieRepository;

public class MoviesViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getMutableLiveData(1, null);
    }

    public LiveData<List<Movie>> getSearchMovies(String query) {
        return movieRepository.getMutableLiveData(2, query);
    }
}
