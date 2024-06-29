package il.ac.tcb.st.movbile.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.database.MoviesTable;
import il.ac.tcb.st.movbile.databinding.MyListItemBinding;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListViewHolder> {

    private Context context;
    private List<MoviesTable> movies;
    private NavController navController;

    public MyListAdapter(Context context, List<MoviesTable> movies, NavController navController) {
        this.context = context;
        this.movies = movies;
        this.navController = navController;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyListItemBinding itemBinding = MyListItemBinding.inflate(layoutInflater, parent, false);
        return new MyListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        MoviesTable movie = movies.get(position);
        holder.bind(movie);

        holder.itemView.setOnClickListener(v -> {
            // Handle item click
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movie);
            navController.navigate(R.id.action_myListFragment_to_moviePreviewFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void removeItem(int position) {
        movies.remove(position);
        notifyItemRemoved(position);
    }

    public static class MyListViewHolder extends RecyclerView.ViewHolder {
        private final MyListItemBinding binding;

        public MyListViewHolder(MyListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MoviesTable movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}