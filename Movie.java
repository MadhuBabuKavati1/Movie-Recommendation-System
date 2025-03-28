import java.util.*;

public class Movie {
    private String movieId;
    private String title;
    private List<String> genres;

    public Movie(String movieId, String title, List<String> genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }
}
