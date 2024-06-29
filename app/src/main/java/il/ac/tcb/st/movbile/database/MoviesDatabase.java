package il.ac.tcb.st.movbile.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {MoviesTable.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    private static MoviesDatabase instance;
    public static synchronized MoviesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MoviesDatabase.class,
                            "movies_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract MoviesDao moviesDao();
}

