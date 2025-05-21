import java.util.*;

public class User implements Printable {
    // attributes
    protected String username;
    protected List<Review> reviews;

    // constructor
    public User(String username) {
        this.username = username;
        this.reviews = new ArrayList<Review>();
    }

    // methods
    public void addReview(Review r) {
        reviews.add(r);
    }

    // print details
    public void printDetails() {
        System.out.println("User: " + username);
        System.out.println("Reviews submitted: " + reviews.size());
    }

    // getters
    public String getUsername() {
        return username;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    // toString
    @Override
    public String toString() {
        return username;
    }
}