import java.util.List;

public class Recommender {
    public static List<Movie> recommendByUserSimilarity(User user) {
        return null;
    }

    public static List<Movie> recommendByContent(User user) {
        return null;
    }

    public static double calculateMSE(User user1, User user2) {
        double sum = 0;
        int commonMovies = 0;
        for (Review review1 : user1.getReviews()) {
            for (Review review2 : user2.getReviews()) {
                if (review1.movie.equals(review2.movie)) {
                    sum += Math.pow(review1.rating - review2.rating, 2);
                    commonMovies += 1;
                    continue;
                }
            }
        }
        sum = sum / commonMovies;
        if (commonMovies > 0) {
            return sum;
        } else {
            return -1;
        }
    }

    public static boolean arePreferencesSimilar(User user1, User user2) {
        double mse = calculateMSE(user1, user2);
        if (mse < 0) {
            return false;
        } else if (mse < 4) {
            return true;
        } else {
            return false;
        }
    }
}