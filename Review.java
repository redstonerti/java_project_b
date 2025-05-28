import java.util.*;

public abstract class Review implements Printable {
    // attributes
    protected User user;
    protected int rating;
    protected String comment;
    protected Movie movie;
    protected double weight;
    protected static List<Review> allReviews = new ArrayList<>();

    // constructor
    public Review(User user, int rating, String comment, Movie movie) throws Exception {
        if (rating < 1 || rating > 10) {
            throw new Exception("Rating must be between 0 and 10");
        }
        if (Review.getSpecificReview(user, movie) != null) {
            throw new Exception(user.username + " has already made a review for " + movie.getTitle());
        }
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.movie = movie;
        user.addReview(this);
        movie.addReview(this);
        allReviews.add(this);
    }

    public Review(User user, int rating, Movie movie) throws Exception {
        if (rating < 1 || rating > 10) {
            throw new Exception("Rating must be between 0 and 10");
        }
        this.user = user;
        this.rating = rating;
        this.comment = null;
        this.movie = movie;
        user.addReview(this);
        movie.addReview(this);
        allReviews.add(this);
    }

    // methods
    public int getWeightedRating() {
        return (int) Math.round((float) this.getRating() * this.weight);
    }

    public double getWeight() {
        return this.weight;
    }

    // print details
    public void printDetails() {
        System.out.println(user.getUsername() + " rated " + movie.getTitle() + " with " + rating + "/10"
                + ((comment != null && !comment.isEmpty()) ? "\nComment: " + comment : ""));
    }

    // getters
    public User getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Movie getMovie() {
        return movie;
    }

    public static List<Review> getAllReviews() {
        return new ArrayList<>(allReviews);
    }

    public static Review getSpecificReview(User user, Movie movie) {
        for (Review review : allReviews) {
            if (review.getUser().equals(user) && review.getMovie().equals(movie)) {
                return review;
            }
        }
        return null;
    }

    // toString
    @Override
    public String toString() {
        return user.getUsername() + " rated " + movie.getTitle() + " with " + rating + "/10"
                + ((comment != null && !comment.isEmpty()) ? "\nComment: " + comment : "");
    }
}