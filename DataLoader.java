import java.io.BufferedReader;
import java.io.FileReader;

public class DataLoader {
    public static void loadFromCsv(String filePath, String svSplitBy) {
        String line;
        User user;
        Movie movie;

        // Read the CSV file and process each line
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Split the line by the specified delimiter
                String[] values = line.split(svSplitBy);

                String movieTitle = values[0];
                int year = Integer.parseInt(values[1]);
                String genre = values[2];
                String username = values[3];
                int rating = Integer.parseInt(values[4]);
                String comment = values[5];

                // Find or create the user
                if (User.getSpecificUser(username) == null) {
                    user = new User(username);
                } else {
                    user = User.getSpecificUser(username);
                }

                // Find or create the movie
                if (Movie.getSpecificMovie(movieTitle) == null) {
                    movie = new Movie(movieTitle, year, genre);
                } else {
                    movie = Movie.getSpecificMovie(movieTitle);
                }

                // Create review if it doesn't exist
                if (Review.getSpecificReview(user, movie) == null) {
                    new BasicReview(user, rating, comment, movie);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
