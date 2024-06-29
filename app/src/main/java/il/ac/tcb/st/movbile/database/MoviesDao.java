package il.ac.tcb.st.movbile.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
@Dao
public interface MoviesDao {
    @Query("SELECT * FROM movies")
    List<MoviesTable> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MoviesTable movie);
    @Delete
    void deleteMovie(MoviesTable movie);

}

