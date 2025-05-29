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
        // Movie creating can fail, so it is recommended to put them in a try catch
        // block
        try {
            // Creates a movie and automatically adds it to the global movie list
            // (Movie.allMovies)
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
        System.out.println("--- User creation example ---");
        User user;
        try {
            user = new User("alice");
            System.out.println("User " + user.username + " was created successfully!");
        } catch (Exception e) {
            System.out.println("Exception when creating alice: " + e);
            return;
        }
        System.out.println();

        // The printDetails method can be used to display relevant information
        System.out.println("--- Movie details example ---");
        movie.printDetails();
        System.out.println();

        System.out.println("--- User details example ---");
        user.printDetails();
        System.out.println();

        // Create a VerifiedUser and a VerifiedReview from that user.
        // The constructors of VerifiedUser and VerifiedReview may throw an exception
        // so they need to be in a try catch block
        // We initialize them before the try catch block so that they can be used
        // afterwards
        System.out.println("--- Verified Review and Verified User example ---");
        VerifiedUser verifiedUser = null;
        VerifiedReview verifiedReview = null;
        try {
            // A VerifiedUser takes in a VerificationMethod enum in its constructor.
            // It is then used in a verify() method which should be implemented by the users
            // of this framework
            verifiedUser = new VerifiedUser("bob", VerifiedUser.VerificationMethod.Password);
            System.out.println("VerifiedUser " + verifiedUser.username + " was created succesfully!");
        } catch (Exception e) {
            System.out.println("Exception when creating VerifiedUser: " + e.getMessage());
        }
        try {
            // VerifiedReviews take in a VerifiedUser in their constructor and cannot be
            // created otherwise

            // When a review is created, verified or not, it is automatically added to the
            // reviews list of
            // the user that is passed in the constructor, as well as the reviews list of
            // the movie that is passed in the constructor
            verifiedReview = new VerifiedReview(verifiedUser, 3, movie);
            System.out.println("VerifiedReview created successfully!");
        } catch (Exception e) {
            System.out.println("Exception when creating VerifiedReview: " + e.getMessage());
        }

        // If the verifiedReview has been created, the printDetails method can be used
        // to display relevant information
        if (verifiedReview != null) {
            verifiedReview.printDetails();
            System.out.println();
        }

        // Similarly, we initialize the BasicReviews in order to have access to them
        // after the try catch blocks
        System.out.println("--- BasicReview example ---");
        BasicReview basicReview = null;
        BasicReview basicReview2 = null;
        try {
            basicReview = new BasicReview(user, 9, movie);
            System.out.println("BasicReview by " + basicReview.user.username + " created successfully!");

            basicReview2 = new BasicReview(user, 8, Movie.getSpecificMovie("The Dark Knight"));
            System.out.println("Second BasicReview by " + basicReview2.user.username + " created successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // If they have been created, we print out the relevant information
        if (basicReview != null) {
            basicReview.printDetails();
        }
        if (basicReview2 != null) {
            basicReview2.printDetails();
        }
        System.out.println();

        // General usage of methods provided by the framework
        System.out.println("--- General method usage examples ---");
        int padding = 57;

        System.out.printf("%-" + padding + "s | Inception total rating: %.2f%n",
                "Average Rating of a movie", movie.getAverageRating());

        System.out.printf("%-" + padding + "s | The highest rated movies for each genre are: %s%n",
                "Highest Rated movies by a genre", Movie.getHighestRatedByGenre());

        System.out.printf("%-" + padding + "s | %s%n",
                "Related movies for " + movie + ":", movie.getRelatedMovies());

        System.out.printf("%-" + padding + "s | %s%n",
                "Reviewers of " + movie + ":", movie.getReviewers());

        System.out.printf("The highest rated movies for each genre\n" + "%-" + padding + "s | %s%n",
                "with at least two reviews and a minimum rating of 5 are:",
                Movie.getHighestRatedByGenre(2, 5));

        // Get all the movies
        List<Movie> moviesSortedByYear = new ArrayList<>(Movie.getAllMovies());

        // Sort them by year with the byYear Comparator
        moviesSortedByYear.sort(Movie.byYear);
        System.out.printf("%-" + padding + "s | %s%n%n",
                "Movies sorted by year", moviesSortedByYear);

        // NEW FUNCTIONS

        // Load more reviews from a CSV file
        // The first argument is the filepath of the csv file
        // The second is the delimiter used to separate information
        DataLoader.loadReviewsFromCSV("reviews.csv", ",");

        // Print movies by genre
        System.out.println("--- Print movies by genre example ---");
        Movie.printMoviesByGenre();
        System.out.println();

        // Get a user to demonstrate the Recommender class
        User john = User.getSpecificUser("john_d");

        // Get a movie to demonstrate the genreWeightCalculation
        System.out.println("--- Genre weight demonstration ---");
        Movie pulp = Movie.getSpecificMovie("Pulp Fiction");
        System.out.printf("%s: %.1f%n", "Average genre weight for pulp fiction based on reviews by john: ",
                Recommender.getAverageGenreWeightByUser(pulp, john)); /*
                                                                       * It's 0.5 because that's the default value when
                                                                       * a person hasn't reviewed any movies in the
                                                                       * genres of the given movie
                                                                       */
        System.out.printf("%s: %.1f%n%n", "Average genre weight for pulp fiction based on reviews by maria: ",
                Recommender.getAverageGenreWeightByUser(pulp, User.getSpecificUser("maria89")));

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
        highestRatedMovies.sort(Movie.byAverageRating.reversed());
        for (Movie m : highestRatedMovies) {
            System.out.printf("%s: %.2f%n", m, m.getAverageRating());
        }

    }
}