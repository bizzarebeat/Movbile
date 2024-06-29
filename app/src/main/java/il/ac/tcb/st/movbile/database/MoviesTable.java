package il.ac.tcb.st.movbile.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movies",
        indices = {@Index(value = {"id"}, unique = true)})
public class MoviesTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "poster_path")
    public String posterPath;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "release_date")
    public String releaseDate;
    @ColumnInfo(name = "vote_average")
    public double voteAverage;
    @ColumnInfo(name = "overview")
    public String overview;

    @Override
    public String toString() {
        return "MoviesTable{" +
                "id=" + id +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAverage=" + voteAverage +
                ", overview='" + overview + '\'' +
                '}';
    }
}
