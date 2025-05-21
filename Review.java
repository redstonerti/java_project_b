public abstract class Review implements Printable {
    // attributes
    protected User user;
    protected int rating;
    protected String comment;
    protected Movie movie;
    protected double weight;

    // constructor
    public Review(User user, int rating, String comment, Movie movie) throws Exception {
        if (rating < 1 || rating > 10) {
            throw new Exception("Rating must be between 0 and 10");
        }
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.movie = movie;
        user.addReview(this);
        movie.addReview(this);
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

    // toString
    @Override
    public String toString() {
        return user.getUsername() + " rated " + movie.getTitle() + " with " + rating + "/10"
                + ((comment != null && !comment.isEmpty()) ? "\nComment: " + comment : "");
    }
}