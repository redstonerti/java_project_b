import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//Custom Comparator to sort movies based on a user's genre weight and their rating
class ContentRecommendorComparator implements Comparator<Movie> {
    private User user;

    public ContentRecommendorComparator(User user) {
        this.user = user;
    }

    public int compare(Movie m1, Movie m2) {
        return m1.getAverageRating() * Recommender.getAverageGenreWeightByUser(m1, user) > m2.getAverageRating()
                * Recommender.getAverageGenreWeightByUser(m2, user) ? -1
                        : 1;
    }
}

public class Recommender {
    public static List<Movie> recommendByUserSimilarity(User user) {
        List<User> similarUsers = new ArrayList<>();
        for (User u : User.getAllUsers()) {
            if (u.equals(user)) {
                continue;
            }
            if (arePreferencesSimilar(user, u)) {
                similarUsers.add(u);
            }
        }
        List<Movie> moviesToRecommend = new ArrayList<>();
        for (User u : similarUsers) {
            for (Movie movie : u.getReviewedMovies()) {
                if (!moviesToRecommend.contains(movie) && !user.getReviewedMovies().contains(movie) &&
                        Review.getSpecificReview(u, movie).getRating() > 7) {
                    moviesToRecommend.add(movie);
                }
            }
        }
        moviesToRecommend.sort(Movie.byAverageRating);
        return moviesToRecommend;
    }

    // Get a hashmap with keys all genres and values the movies passed as an
    // argument that are in that genre
    private static HashMap<String, List<Movie>> getHashMapByGenre(List<Movie> movieList) {
        HashMap<String, List<Movie>> hashMapByGenre = new HashMap<>();
        for (Movie movie : movieList) {
            for (String genre : movie.getGenres()) {
                List<Movie> moviesWithThisGenre = hashMapByGenre.get(genre);
                if (moviesWithThisGenre == null) {
                    moviesWithThisGenre = new ArrayList<>();
                }
                if (!moviesWithThisGenre.contains(movie)) {
                    moviesWithThisGenre.add(movie);
                }
                hashMapByGenre.put(genre, moviesWithThisGenre);
            }
        }
        return hashMapByGenre;
    }

    public static double getAverageGenreWeightByUser(Movie movie, User user) {
        if (movie.getGenres().size() == 0) {
            return 0.;
        }
        if (user.getReviewedMovies().size() == 0) {
            return 0.;
        }

        HashMap<String, List<Movie>> reviewedMoviesByGenre = getHashMapByGenre(user.getReviewedMovies());
        HashMap<String, Double> genreRatings = new HashMap<>();

        // Calculate average rating for each genre according to the user's reviews
        for (String genre : reviewedMoviesByGenre.keySet()) {
            Double sum = 0.;
            Double movieNumber = 0.;
            Double averageRatingForThisGenre = 0.;
            for (Review review : user.getReviews()) {
                if (review.movie.getGenres().contains(genre)) {
                    sum += review.rating;
                    movieNumber += 1;
                }
            }
            averageRatingForThisGenre = movieNumber == 0 ? null : sum / movieNumber;
            if (averageRatingForThisGenre != null) {
                genreRatings.put(genre, averageRatingForThisGenre);
            }
        }

        double sum = 0;
        int total = 0;
        for (String genre : movie.getGenres()) {
            Double userRatingForGenre = genreRatings.get(genre);
            if (userRatingForGenre != null) {
                sum += userRatingForGenre / 10.;
                total += 1;
            }
        }
        return total == 0 ? 0.5 : sum / (double) total;
    }

    public static List<Movie> recommendByContent(User user, int numberOfMovies) {
        if (user == null) {
            return null;
        }
        List<Movie> moviesToRecommend = Movie.getAllMovies();
        moviesToRecommend.sort(new ContentRecommendorComparator(user));
        if (moviesToRecommend.size() > numberOfMovies) {
            moviesToRecommend.subList(5, moviesToRecommend.size()).clear();
        }
        return moviesToRecommend;
    }

    public static double calculateMSE(User user1, User user2) {
        double sum = 0;
        int commonMovies = 0;
        for (Review review1 : user1.getReviews()) {
            for (Review review2 : user2.getReviews()) {
                if (review1.getMovie().equals(review2.getMovie())) {
                    sum += Math.pow(review1.getRating() - review2.getRating(), 2);
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
