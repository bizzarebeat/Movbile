package il.ac.tcb.st.movbile.view.search;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import il.ac.tcb.st.movbile.R;
import il.ac.tcb.st.movbile.view.searchmovies.SearchMoviesFragment;

public class SearchFragment extends Fragment {

    private EditText searchField;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        searchField = view.findViewById(R.id.search_field);
        searchButton = view.findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchField.getText().toString();

                ((InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0); // Hiding the on-screen keyboard after hit "search"

                SearchItems(query);

            }
        });

        return view;
    }

    private void SearchItems(String query) {

        // Load SearchMoviesFragment
        SearchMoviesFragment searchMoviesFragment = new SearchMoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        searchMoviesFragment.setArguments(bundle);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, searchMoviesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

