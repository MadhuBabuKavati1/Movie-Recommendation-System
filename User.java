import java.util.*;

public class User {
    private String userId;
    private Map<String, Double> movieRatings; // Movie ID -> Rating

    public User(String userId) {
        this.userId = userId;
        this.movieRatings = new HashMap<>();
    }

    public void addRating(String movieId, double rating) {
        movieRatings.put(movieId, rating);
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Double> getMovieRatings() {
        return movieRatings;
    }
}
