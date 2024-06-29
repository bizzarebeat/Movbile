package il.ac.tcb.st.movbile.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.databinding.SearchListItemBinding;
import il.ac.tcb.st.movbile.model.Movie;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> movieArrayList;
    private NavController navController;

    public MovieSearchAdapter(Context context, ArrayList<Movie> movieArrayList, NavController navController) {
        this.context = context;
        this.movieArrayList = movieArrayList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchListItemBinding searchListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.search_list_item,
                parent,
                false);

        return new MovieViewHolder(searchListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.searchListItemBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private SearchListItemBinding searchListItemBinding;

        public MovieViewHolder(SearchListItemBinding searchListItemBinding) {
            super(searchListItemBinding.getRoot());
            this.searchListItemBinding = searchListItemBinding;

            searchListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        Movie selectedMovie = movieArrayList.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("movie", selectedMovie);

                        NavDestination currentDestination = navController.getCurrentDestination();

                        if (currentDestination != null) {
                            if (currentDestination.getId() == R.id.moviesFragment) {
                                navController.navigate(R.id.action_moviesFragment_to_movieFragment, bundle);
                            } else if (currentDestination.getId() == R.id.searchMoviesFragment) {
                                navController.navigate(R.id.action_searchMoviesFragment_to_movieFragment, bundle);
                            } else if (currentDestination.getId() == R.id.searchFragment) {
                                navController.navigate(R.id.action_searchFragment_to_movieFragment, bundle);
                            }
                        }
                    }
                }
            });
        }
    }
}
