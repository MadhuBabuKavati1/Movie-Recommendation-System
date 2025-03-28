import java.util.*;

public class RecommendationEngine {
    public static List<String> getRecommendations(String userId, Map<String, User> users, Map<String, Movie> movies) {
        if (!users.containsKey(userId)) return Collections.emptyList();

        User targetUser = users.get(userId);
        Map<String, Double> targetRatings = targetUser.getMovieRatings();
        Map<String, Double> recommendations = new HashMap<>();

        for (User otherUser : users.values()) {
            if (otherUser.getUserId().equals(userId)) continue;

            double similarity = computeSimilarity(targetRatings, otherUser.getMovieRatings());
            if (similarity <= 0) continue;

            for (Map.Entry<String, Double> entry : otherUser.getMovieRatings().entrySet()) {
                if (!targetRatings.containsKey(entry.getKey())) {
                    recommendations.put(entry.getKey(), recommendations.getOrDefault(entry.getKey(), 0.0) + similarity * entry.getValue());
                }
            }
        }

        return recommendations.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(5) // Top 5 recommendations
                .map(e -> movies.get(e.getKey()).getTitle())
                .toList();
    }

    private static double computeSimilarity(Map<String, Double> ratings1, Map<String, Double> ratings2) {
        Set<String> commonMovies = new HashSet<>(ratings1.keySet());
        commonMovies.retainAll(ratings2.keySet());

        if (commonMovies.isEmpty()) return 0;

        double sum1 = 0, sum2 = 0, sumSq1 = 0, sumSq2 = 0, productSum = 0;
        for (String movie : commonMovies) {
            double rating1 = ratings1.get(movie);
            double rating2 = ratings2.get(movie);

            sum1 += rating1;
            sum2 += rating2;
            sumSq1 += rating1 * rating1;
            sumSq2 += rating2 * rating2;
            productSum += rating1 * rating2;
        }

        int n = commonMovies.size();
        double numerator = productSum - (sum1 * sum2 / n);
        double denominator = Math.sqrt((sumSq1 - (sum1 * sum1 / n)) * (sumSq2 - (sum2 * sum2 / n)));

        return denominator == 0 ? 0 : numerator / denominator;
    }
}
