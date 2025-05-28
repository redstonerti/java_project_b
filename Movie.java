import java.util.*;
import java.util.stream.Collectors;

public class Movie implements Printable {
    // attributes
    private String title;
    private int year;
    private List<String> genres;
    private String director;
    private List<Review> reviews;
    private List<Movie> relatedMovies;
    private List<User> reviewers;
    private static List<Movie> allMovies = new ArrayList<>();
    private static TreeMap<String, List<Movie>> moviesByGenre = new TreeMap<>();

    // constructors
    public Movie(String title, int year, List<String> genres, String director) throws Exception {
        if (Movie.getSpecificMovie(title) != null) {
            throw new Exception("The movie " + title + " already exists.");
        }
        this.title = title;
        this.year = year;
        this.genres = new ArrayList<>(genres);
        this.director = director;
        this.reviews = new ArrayList<Review>();
        this.relatedMovies = new ArrayList<Movie>();
        this.reviewers = new ArrayList<User>();
        for (String genre : genres) {
            List<Movie> currentList = moviesByGenre.get(genre);
            if (currentList == null) {
                currentList = new ArrayList<>();
            }
            currentList.add(this);
            moviesByGenre.put(genre, currentList);
        }
        allMovies.add(this);
    }

    public Movie(String title, int year, String genre) throws Exception {
        this(title, year, List.of(genre), "Unknown Director");
    }

    public Movie(String title, int year, List<String> genres) throws Exception {
        this(title, year, genres, "Unknown Director");
    }

    public static void printMoviesByGenre() {
        for (String genre : moviesByGenre.keySet()) {
            System.out.print(genre + ": ");
            for (Movie movie : moviesByGenre.get(genre)) {
                System.out.print(movie + ", ");
            }
            System.out.println();
        }
    }

    public static void printTop5MoviesPerGenre() {
        Map<String, List<Movie>> top5MoviesByGenre = getTop5MoviesPerGenre();
        for (String genre : top5MoviesByGenre.keySet()) {
            System.out.println("Top 5 movies in: " + genre);
            for (Movie movie : top5MoviesByGenre.get(genre)) {
                System.out.printf("- %s (%.2f)\n", movie.getTitle(), movie.getAverageRating());
            }
            System.out.println();
        }
    }

    // methods
    public void addReview(Review r) {
        reviews.add(r);
    }

    public void addReviewer(User u) {
        if (!reviewers.contains(u)) {
            reviewers.add(u);
        }
    }

    public void addRelatedMovie(Movie m) {
        relatedMovies.add(m);
    }

    public double getAverageRating() {
        if (reviews.isEmpty())
            return 0;
        int total = 0;
        double totalWeight = 0;
        for (Review r : reviews) {
            total += r.getWeightedRating();
            totalWeight += r.getWeight();
        }

        double finalRating = (double) total / totalWeight;

        // For good measure
        return finalRating > 10 ? 10 : finalRating < 1 ? 1 : finalRating;
    }

    public static Map<String, Movie> getHighestRatedByGenre() {
        Map<String, Movie> highestRatedByGenre = new HashMap<>();
        for (Movie movie : getAllMovies()) {
            for (String genre : movie.getGenres()) {
                Movie currentBest = highestRatedByGenre.get(genre);
                if (currentBest == null || movie.getAverageRating() > currentBest.getAverageRating()) {
                    highestRatedByGenre.put(genre, movie);
                }
            }
        }
        return highestRatedByGenre;
    }

    public static Map<String, Movie> getHighestRatedByGenre(int mininumReviewerAmount, double minimumRating) {
        Map<String, Movie> highestRatedByGenre = new HashMap<>();
        for (Movie movie : getAllMovies()) {
            for (String genre : movie.getGenres()) {
                Movie currentBest = highestRatedByGenre.get(genre);
                if ((currentBest == null || movie.getAverageRating() > currentBest.getAverageRating())
                        && movie.getAverageRating() > minimumRating &&
                        movie.getReviews().size() >= mininumReviewerAmount) {
                    highestRatedByGenre.put(genre, movie);
                }
            }
        }
        return highestRatedByGenre;
    }



    // print details
    public void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Genres: " + genres);
        System.out.println("Director: " + director);
        System.out.println("Average Rating: " + getAverageRating());
    }

    // getters
        public List<User> getReviewers() {
            return new ArrayList<>(reviewers);
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<String> getGenres() {
        return new ArrayList<>(genres);
    }

    public String getDirector() {
        return director;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public List<Movie> getRelatedMovies() {
        for (Movie movie : getAllMovies()) {
            for (String genre : movie.getGenres()) {
                if (this.getGenres().contains(genre) && !this.equals(movie)) {
                    this.addRelatedMovie(movie);
                }
            }
        }
        return new ArrayList<>(relatedMovies);
    }

    public static List<Movie> getAllMovies() {
        return new ArrayList<>(allMovies);
    }

    public static Movie getSpecificMovie(String title) {
        for (Movie movie : getAllMovies()) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    // toString
    @Override
    public String toString() {
        return title;
    }

    // comparators
    public static Comparator<Movie> byYear = (m1, m2) -> Integer.compare(m1.getYear(), m2.getYear());

    public static Comparator<Movie> byAverageRating = (m1, m2) -> Double.compare(m1.getAverageRating(),
            m2.getAverageRating());

    public static Comparator<Movie> byTitle = (m1, m2) -> m1.getTitle().compareTo(m2.getTitle());

    public static Comparator<Movie> byReviewCount = (m1, m2) -> Integer.compare(m1.getReviews().size(),
            m2.getReviews().size());

    // searchers
    public static List<Movie> searchByYear(int year) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : getAllMovies()) {
            if (movie.getYear() == year) {
                result.add(movie);
            }
        }
        return result;
    }

    public static List<Movie> searchByDirector(String director) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : getAllMovies()) {
            if (movie.getDirector().equalsIgnoreCase(director)) {
                result.add(movie);
            }
        }
        return result;
    }

    public static List<Movie> searchByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : getAllMovies()) {
            if (movie.getGenres().contains(genre)) {
                result.add(movie);
            }
        }
        return result;
    }

    // top 5
    public static Map<String, List<Movie>> getTop5MoviesPerGenre() {
        Map<String, List<Movie>> top5MoviesPerGenre = new HashMap<>();
        for (String genre : moviesByGenre.keySet()) {
            top5MoviesPerGenre.put(genre, new ArrayList<Movie>());
            moviesByGenre.get(genre).sort(byAverageRating.reversed());
            if(moviesByGenre.get(genre).size() > 5) {
                top5MoviesPerGenre.get(genre).addAll(moviesByGenre.get(genre).subList(0, 5));
            } else {
                top5MoviesPerGenre.get(genre).addAll(moviesByGenre.get(genre));
            }
        }
        return top5MoviesPerGenre;
    }

    public static List<Movie> getHighRatedMovies() {
        List<Movie> highRatedMovies = new ArrayList<>();
        for (Movie movie : getAllMovies()) {
            double count = 0.0;
            for (Review review : movie.getReviews()) {
                if (review.getRating() > 7) {
                    count ++;
            }
            if (count/ movie.getReviews().size() >= 0.8) {
                highRatedMovies.add(movie);
            }
            }
        }
        return highRatedMovies;
    }
}
