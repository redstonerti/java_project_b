import java.util.*;

public class User implements Printable {
    // attributes
    protected String username;
    protected List<Review> reviews;
    protected List<Movie> reviewedMovies;
    protected static List<User> allUsers = new ArrayList<>();

    // constructor
    public User(String username) throws Exception {
        if (User.getSpecificUser(username) != null) {
            throw new Exception("user" + username + "  already exists ");
        }
        this.username = username;
        this.reviews = new ArrayList<Review>();
        allUsers.add(this);
        this.reviewedMovies = new ArrayList<Movie>();
    }

    // methods
    // adders
    public void addReview(Review r) {
        reviews.add(r);
    }

    public void addReviewedMovie(Movie m) {
        reviewedMovies.add(m);
    }

    // toString
    @Override
    public String toString() {
        return username;
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

    // print details
    public void printDetails() {
        System.out.println("User: " + username);
        System.out.println("Reviews submitted: " + reviews.size());
        System.out.printf("Average rating: %.2f%n", getAverageRating());
    }

    // getters
    public List<Movie> getReviewedMovies() {
        return new ArrayList<>(reviewedMovies);
    }

    public String getUsername() {
        return username;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public static List<User> getAllUsers() {
        return new ArrayList<>(allUsers);
    }

    //other methods
    public static User getSpecificUser(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        return totalRating / reviews.size();
    }
}