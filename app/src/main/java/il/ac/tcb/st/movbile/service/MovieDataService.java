package il.ac.tcb.st.movbile.service;

import il.ac.tcb.st.movbile.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    // Base URL
    // https://api.themoviedb.org/3/
    // Endpoint URL
    // movie/popular?api_key=4710bc4db7781ee38ed893769b098a76
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);

    // search/movie?query=Jack+Reacher&api_key=4710bc4db7781ee38ed893769b098a76
    @GET("search/movie")
    Call<Result> getMovieSearch(@Query("query") String movieName, @Query("api_key") String apiKey);
}
