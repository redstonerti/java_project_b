public class VerifiedReview extends Review {
    public VerifiedReview(VerifiedUser verifiedUser, int rating, Movie movie) throws Exception {
        super((User) verifiedUser, rating, movie);
        this.weight = 1.3;
    }

    public VerifiedReview(User user, int rating, String comment, Movie movie) throws Exception {
        super(user, rating, comment, movie);
        this.weight = 1.3;
    }
}