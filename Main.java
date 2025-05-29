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

public class Main {
    public static void main(String[] args) {
        // Creates a movie and automatically adds it to the global movie list
        // (Movie.allMovies)

        try {
            new Movie("Inception", 2010, List.of("Sci-Fi", "Action"), "Christopher Nolan");

            // More movie creation examples
            new Movie("The Matrix", 1999, List.of("Sci-Fi", "Action"), "Lana Wachowski, Lilly Wachowski");
            new Movie("Interstellar", 2014, List.of("Sci-Fi", "Drama"), "Christopher Nolan");
            new Movie("Mad Max: Fury Road", 2015, List.of("Action", "Adventure"), "George Miller");
            new Movie("Blade Runner 2049", 2017, List.of("Sci-Fi", "Mystery"), "Denis Villeneuve");
            new Movie("The Dark Knight", 2008, List.of("Action", "Crime"), "Christopher Nolan");
            new Movie("Family", 2009, List.of("Comedy", "Action"), "Jason Statham");
        } catch (Exception e) {
            System.out.println("Exception when creating movies: " + e);
            return;
        }

        // Get a specific movie from its name
        Movie movie = Movie.getSpecificMovie("Family");

        // Create a User.
        // The constructor of User throw an exception so it need to be in a try catch
        // block. We initialize it before the try catch block so that it can be used
        // afterwards
        User user;
        try {
            user = new User("alice");
        } catch (Exception e) {
            System.out.println("Exception when creating alice: " + e);
            return;
        }

        // The printDetails method can be used to display relevant information
        movie.printDetails();
        System.out.println();
        user.printDetails();
        System.out.println();

        // Create a VerifiedUser and a VerifiedReview from that user.
        // The constructors of VerifiedUser and VerifiedReview may throw an exception
        // so they need to be in a try catch block
        // We initialize them before the try catch block so that they can be used
        // afterwards
        VerifiedUser user2 = null;
        VerifiedReview verifiedReview = null;
        try {
            // A VerifiedUser takes in a VerificationMethod enum in its constructor.
            // It is then used in a verify() method which should be implemented by the users
            // of this framework
            user2 = new VerifiedUser("bob", VerifiedUser.VerificationMethod.Password);
            System.out.println("User created: " + user2.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            // VerifiedReviews take in a VerifiedUser in their constructor and cannot be
            // created otherwise

            // When a review is created, verified or not, it is automatically added to the
            // reviews list of
            // the user that is passed in the constructor, as well as the reviews list of
            // the movie that is passed in the constructor
            verifiedReview = new VerifiedReview(user2, 3, movie);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // If the verifiedReview has been created, the printDetails method can be used
        // to display relevant information
        if (verifiedReview != null) {
            System.out.println("VerifiedReview created successfully!");
            verifiedReview.printDetails();
            System.out.println();
        }

        // Similarly, we initialize the BasicReviews in order to have access to them
        // after the try catch blocks
        BasicReview basicReview2 = null;
        BasicReview basicReview = null;
        try {
            basicReview = new BasicReview(user, 9, movie);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            basicReview2 = new BasicReview(user, 8, Movie.getSpecificMovie("The Dark Knight"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // If they have been created, we print out the relevant information
        if (basicReview != null) {
            System.out.println("BasicReview created successfully!");
            basicReview.printDetails();
            System.out.println();
        }

        if (basicReview2 != null) {
            System.out.println("Second BasicReview created successfully!");
            basicReview2.printDetails();
            System.out.println();
        }

        // General usage of methods provided by the framework
        System.out.printf("Inception total rating: %.2f%n", movie.getAverageRating());
        System.out.println("The highest rated movies for each genre are: " +
                Movie.getHighestRatedByGenre());
        System.out.println("Related movies for " + movie + ": " +
                movie.getRelatedMovies());
        System.out.println("Reviewers of " + movie + ": " + movie.getReviewers());
        System.out.println(
                "The highest rated movies for each genre with at least two reviews and a minimum rating of 5 are: "
                        + Movie.getHighestRatedByGenre(2, 5));

        // Get all the movies
        List<Movie> movies = new ArrayList<>(Movie.getAllMovies());

        // Sort them by year with the byYear Comparator
        movies.sort(Movie.byYear);
        System.out.println("Movies sorted by year: " + movies);

        // NEW FUNCTIONS

        // Load more reviews from a CSV file
        // The first argument is the filepath of the csv file
        // The second is the delimiter used to separate information
        DataLoader.loadReviewsFromCSV("reviews.csv", ",");

        // Print movies by genre
        Movie.printMoviesByGenre();

        // Get a user to demonstrate the Recommender class
        User john = User.getSpecificUser("john_d");
        john.printDetails();

        // Get a movie to demonstrate the genreWeightCalculation
        Movie pulp = Movie.getSpecificMovie("Pulp Fiction");
        System.out.println(Recommender.getAverageGenreWeightByUser(pulp, john));

        // Get a list of movies recommended by the content a specific user watches
        System.out.println("--- Movies recommended by content for john ---");
        for (Movie m : Recommender.recommendByContent(john, 5)) {
            System.out.printf(
                    "%s: %.2f%n", m.getTitle(),
                    Recommender.getAverageGenreWeightByUser(m, john) * m.getAverageRating());
        }
        System.out.println();

        // Get a list of movies recommended by finding movies in genres that other users
        // rated similarly to the one given
        List<Movie> moviesRecommendedBySimilarity = Recommender.recommendByUserSimilarity(john);
        System.out.println("--- Movies recommended by similarity for john ---");
        System.out.println(moviesRecommendedBySimilarity);
        System.out.println();

        // Print a list of the Top 5 movies by genre
        System.out.println("--- Top 5 movies by genre ---");
        Movie.printTop5MoviesPerGenre();

        // Get a list of the highest rated movies across the site (Movies must have a
        // rating of >= 7 to be considered highly rated)
        System.out.println("--- Highest rated movies ---");
        List<Movie> highestRatedMovies = Movie.getHighRatedMovies();
        System.out.println(highestRatedMovies);

    }
}