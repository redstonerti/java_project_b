//AM:3240177
//email: p3240177@aueb.gr
//onoma: IFIGENEIA
//epitheto: RAPTI

//AM: 3240033
//email: p3240033@aueb.gr
//onoma: VASILEIOS
//epitheto: GRIGOROPOULOS

//AM: 3240244
//email: p3240244@aueb.gr
//onoma: GEORGIOS
//epitheto: FITIKIDES

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        /**
         * // Creates a movie and automatically adds it to the global movie list
         * // (Movie.allMovies)
         * new Movie("Inception", 2010, List.of("Sci-Fi", "Action"), "Christopher
         * Nolan");
         *
         * // More movie creation examples
         * new Movie("The Matrix", 1999, List.of("Sci-Fi", "Action"), "Lana Wachowski,
         * Lilly Wachowski");
         * new Movie("Interstellar", 2014, List.of("Sci-Fi", "Drama"), "Christopher
         * Nolan");
         * new Movie("Mad Max: Fury Road", 2015, List.of("Action", "Adventure"), "George
         * Miller");
         * new Movie("Blade Runner 2049", 2017, List.of("Sci-Fi", "Mystery"), "Denis
         * Villeneuve");
         * new Movie("The Dark Knight", 2008, List.of("Action", "Crime"), "Christopher
         * Nolan");
         * new Movie("Family", 2009, List.of("Comedy", "Action"), "Jason Statham");
         *
         * // Get a specific movie from its name
         * Movie movie = Movie.getSpecificMovie("Family");
         *
         * // Create a User
         * User user = new User("alice");
         *
         * // The printDetails method can be used to display relevant information
         * movie.printDetails();
         * System.out.println();
         * user.printDetails();
         * System.out.println();
         *
         * // Create a VerifiedUser and a VerifiedReview from that user.
         * // The constructors of VerifiedUser and VerifiedReview may throw an
         * exception,
         * // so they need to be in a try catch block
         * // We initialize them before the try catch block so that they can be used
         * // afterwards
         * VerifiedUser user2 = null;
         * VerifiedReview verifiedReview = null;
         * try {
         * // A VerifiedUser takes in a VerificationMethod enum in its constructor.
         * // It is then used in a verify() method which should be implemented by the
         * users
         * // of this framework
         * user2 = new VerifiedUser("bob", VerifiedUser.VerificationMethod.Password);
         * System.out.println("User created: " + user2.getUsername());
         * } catch (Exception e) {
         * System.out.println(e.getMessage());
         * }
         * try {
         * // VerifiedReviews take in a VerifiedUser in their constructor and cannot be
         * // created otherwise
         *
         * // When a review is created, verified or not, it is automatically added to
         * the
         * // reviews list of
         * // the user that is passed in the constructor, as well as the reviews list of
         * // the movie that is passed in the constructor
         * verifiedReview = new VerifiedReview(user2, 3, movie);
         * } catch (Exception e) {
         * System.out.println(e.getMessage());
         * }
         *
         * // If the verifiedReview has been created, the printDetails method can be
         * used
         * // to display relevant information
         * if (verifiedReview != null) {
         * System.out.println("VerifiedReview created successfully!");
         * verifiedReview.printDetails();
         * System.out.println();
         * }
         *
         * // Similarly, we initialize the BasicReviews in order to have access to them
         * // after the try catch blocks
         * BasicReview basicReview2 = null;
         * BasicReview basicReview = null;
         * try {
         * basicReview = new BasicReview(user, 9, movie);
         * } catch (Exception e) {
         * System.out.println(e.getMessage());
         * }
         * try {
         * basicReview2 = new BasicReview(user, 8, Movie.getSpecificMovie("The Dark
         * Knight"));
         * } catch (Exception e) {
         * System.out.println(e.getMessage());
         * }
         *
         * // If they have been created, we print out the relevant information
         * if (basicReview != null) {
         * System.out.println("BasicReview created successfully!");
         * basicReview.printDetails();
         * System.out.println();
         * }
         *
         * if (basicReview2 != null) {
         * System.out.println("Second BasicReview created successfully!");
         * basicReview2.printDetails();
         * System.out.println();
         * }
         *
         * // General usage of methods provided by the framework
         * System.out.println("Inception total rating: " + movie.getAverageRating());
         * System.out.println("The highest rated movies for each genre are: " +
         * Movie.getHighestRatedByGenre());
         * System.out.println("Related movies for " + movie + ": " +
         * movie.getRelatedMovies());
         * System.out.println("Reviewers of " + movie + ": " + movie.getReviewers());
         * System.out.println(
         * "The highest rated movies for each genre with at least two reviews and a
         * minimum rating of 5 are: "
         * + Movie.getHighestRatedByGenre(2, 5));
         *
         * // Get all the movies
         * List<Movie> movies = new ArrayList<>(Movie.getAllMovies());
         *
         * // Sort them by year with the byYear Comparator
         * movies.sort(Movie.byYear);
         * System.out.println("Movies sorted by year: " + movies);
         */

        DataLoader.loadFromCSV("reviews.csv", ",");

        Movie.printMoviesByGenre();
        System.out.println(Recommender.calculateMSE(User.getSpecificUser("singer22"), User.getSpecificUser("maria89")));

        Movie.printTop5MoviesPerGenre();

        User.getSpecificUser("singer22").printDetails();
        System.err.println(Movie.getHighRatedMovies());





    }
}