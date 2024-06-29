package il.ac.tcb.st.movbile.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.service.MovieDataService;
import il.ac.tcb.st.movbile.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(int opt, String query) {
        MovieDataService movieDataService = RetrofitInstance.getService();
        Call<Result> call;

        if (opt == 1)
            call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        else
            call = movieDataService.getMovieSearch(query,application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result != null && result.getResults() != null) {
                    movies = (ArrayList<Movie>)  result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });

        return mutableLiveData;
    }
}
