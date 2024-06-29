package il.ac.tcb.st.movbile.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.databinding.ActivityMovieBinding;
import il.ac.tcb.st.movbile.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie);

        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        Intent i = getIntent();

        if (i.hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);
        }
    }
}