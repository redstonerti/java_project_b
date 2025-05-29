# JAVA PROJECT B AUEB

## Index
- [Demo / Usage](#demo--usage)
- [Design](#design)
- [Documentation of the Development Process](#documentation-of-the-development-process)
- [Sample Output](#sample-output)

## Demo / Usage
To see a complete example of this framework:

Open `Main.java`.
Inside you’ll find:
- Creation of several `Movie` objects.
- Examples of `User`, `BasicReview`, and `VerifiedReview` usage.
- Calls to helper methods like `getAverageRating()`, `getHighestRatedByGenre()`, etc.
- Inline comments explaining each step, for instance:
  ```java
  // Creates a movie and automatically adds it to the global movie list
  // (Movie.allMovies)
  new Movie("Inception", 2010, List.of("Sci-Fi", "Action"), "Christopher Nolan");
  ```

### Running the Demo

#### Using `javac` / `java`
```bash
javac *.java
java Main
```

#### Using the provided `run.bat` (Windows Only)
```bash
./run.bat
```

## Design

**Inheritance**
- `Printable`
  - `Movie`
  - `User`
    - `VerifiedUser`
  - `Review` (abstract)
    - `VerifiedReview`
    - `BasicReview`

**Encapsulation**
- Fields are private or protected.
- Access with public getters/setters.

**Exception Handling**
- Validation in constructors
- Try catch is used to create the objects safely

**Methods**
- `printDetails()` to print relevant details for **Movies**, **Users** and **Reviews**
- `getAverageRating()`
- `getHighestRatedByGenre()` (overload exists with filters for minimum reviewer amount and minimum rating)
- `getRelatedMovies()`
- `getReviewers()`
- Sorting with the Comparators `byYear`, `byAverageRating` or `byTitle`
- Search for movies with `searchByYear()`, `searchByDirector()` or `searchByGenre()`

## Documentation of the Development Process

### DAY 1

- Commented out the previous **Main** so we can now  the use the new methods that we will create
- Created `allReviews` attribute in the class **Review**
- The constructors now add every review objects that get created to the `allReviews` list
- Created `allUser` attribute in the class **User**
- The constructor now adds every user objects that get created to the `allUser` list
- Created getters for `allReviews` and `allUsers`
- Creted the methods `getSpecificUser` and `getSpecificReview`
- Made a **Movie** constructor that takes as input one genre without an array and also doesnt take a director
- Created the corresponding movie, user and review objects in **Main** according to the given `reviews.csv` file
- Made `Review` constructor throw exception if a review already exists for the specified user and movie
- Created **DataLoader** class as specified by the UML diagram and moved file input logic there
- Made `loadReviewsFromCSV()` split movie genres by this `|` delimiter
- Changed `getSpecificMovie()` to compare with the `.equals()` method
- Changed `.split("|")` to `.split("\\|")` in `loadReviewsFromCSV` in order to escape the pipe from regex
- Created `moviesByGenre` treemap and made the constructor of Movie add itself to it
- Added `printMoviesByGenre()` in the **Movie** class to make it easy to see how the hashmap works
- Changed `moviesByGenre` to a TreeMap so that it is always sorted
- Added `byReviewCount` Comparator to the **Movie** class
- Created **Recommender** class with `calculateMSE()` and `arePreferencesSimilar()` functions
- Fleshed out `recommendByUserSimilarity()` in **Recommender** class
- Made **User** constructor throw exception if user already exists with the specified name
- Added the attribute `reviewers` to **Movie**
- Changed the method `getReviewers()` so now it just returns the list of the attribute `reviewers`
- Created `addReviewer(User u)` in **Movie**
- The **Review** constructors now add the user of the review to the movie's `reviewers` list
- Made the other **Review** constructor throw exception if a review already exists for the specified user and movie
- Made the **Movie** constructor throw exception if a movie already exists with the specified title
- Added `reviewedMovies` attribute to **User**
- Created `getReviwedMovies()` method **User**
- Created `addReviewedMovie(Movie m)` method to **User**
- The **Review** constructors now add the movie of the review to the user's `reviewedMovies`
- Created `getTop5MoviesPerGenre()` in the class **Movie** which returns a hashmap with all the gernes as keys and lists of the 5 best rated movies for each coresponding genre as values
- Created the method `printTop5MoviesPerGenre()` in the class **Movie** to make it easy to see how the method `getTop5MoviesPerGenre()` works
- Created `getAverageRating()` in the class **User** which returns the average rating of the user`s reviews
- Added the `getAverageRating()` in the `printDetails()` of **USER**
- Created the `getHighRatedMovies()` method in the class **Movie** which returns a list with all movies that got a rating of more than 7 by at least 80% of the reviewers
- Added private method `getHashMapByGenre()` in the **Recommender** class
- Fleshed out `recommendByContent()` in the **Recommender** class
- Added example of `recommendByContent()` in **Main**
- Changed `calculateMSE()` function to use getters instead of directly accessing attributes
- Overridden the method `equals(Object obj)` in the class **Movies**
- Changed the inside of the `recommendBySimilarity(User user)`, to now only recommend movies that that the similar user have given a rating of more than 7
- Overridden the method `equals(Object obj)` in the class **User**

### DAY 2
- Made `recommendByContent()` check if user has already seen the movie and changed `getHashMapByGenre()` to use the movieList passed as an argument
- Uncommented **Main**
- Renamed `loadFromCSV()` to `loadReviewsFromCSV`
- Put `try_catch` in places where the functions now throw exceptions
- Added comments in main to explain what each function does
- Changed `printDetails()` in **User** and getAverageRating() example for the movie inception to print the average with 2 decimals of accuracy
- Completely reworked the `recommendByContent()` method of the **Recommender** class
- Complete main rework to increase clarity and decrease clutter

## Sample Output
```console
--- User creation example ---
User alice was created successfully!

--- Movie details example ---
Title: Family
Year: 2009
Genres: [Comedy, Action]
Director: Jason Statham
Average Rating: 0.0

--- User details example ---
User: alice
Reviews submitted: 0
Average rating: 0.00

--- Verified Review and Verified User example ---
VerifiedUser bob was created succesfully!
VerifiedReview created successfully!
bob rated Family with 3/10

--- BasicReview example ---
BasicReview by alice created successfully!
Second BasicReview by alice created successfully!
alice rated Family with 9/10
alice rated The Dark Knight with 8/10

--- General method usage examples ---
Average Rating of a movie                                 | Inception total rating: 5.65
Highest Rated movies by a genre                           | The highest rated movies for each genre are: {Sci-Fi=Inception, Action=The Dark Knight, Adventure=Mad Max: Fury Road, Drama=Interstellar, Crime=The Dark Knight, Mystery=Blade Runner 2049, Comedy=Family}
Related movies for Family:                                | [Inception, The Matrix, Mad Max: Fury Road, The Dark Knight]
Reviewers of Family:                                      | [bob, alice]
The highest rated movies for each genre
with at least two reviews and a minimum rating of 5 are:  | {Action=Family, Comedy=Family}
Movies sorted by year                                     | [The Matrix, The Dark Knight, Family, Inception, Interstellar, Mad Max: Fury Road, Blade Runner 2049]

--- Print movies by genre example ---
Action: Inception, The Matrix, Mad Max: Fury Road, The Dark Knight, Family,
Adventure: Mad Max: Fury Road,
Comedy: Family,
Crime: The Dark Knight, The Godfather, Pulp Fiction,
Drama: Interstellar,
Musical: La La Land, The Greatest Showman,
Mystery: Blade Runner 2049,
Sci-Fi: Inception, The Matrix, Interstellar, Blade Runner 2049,

--- Genre weight demonstration ---
Average genre weight for pulp fiction based on reviews by john: : 0.5
Average genre weight for pulp fiction based on reviews by maria: : 0.9

--- Movies recommended by content for john ---
Mad Max: Fury Road: 8.10
Inception: 7.95
The Matrix: 7.95
Interstellar: 7.50
The Dark Knight: 7.20

--- Movies recommended by similarity for john ---
[The Godfather]

--- Top 5 movies by genre ---
Top 5 movies in: Action
- Inception (9.00)
- The Matrix (9.00)
- Mad Max: Fury Road (9.00)
- The Dark Knight (8.00)
- Family (5.65)

Top 5 movies in: Sci-Fi
- Inception (9.00)
- The Matrix (9.00)
- Interstellar (9.00)
- Blade Runner 2049 (0.00)

Top 5 movies in: Adventure
- Mad Max: Fury Road (9.00)

Top 5 movies in: Drama
- Interstellar (9.00)

Top 5 movies in: Crime
- The Godfather (9.00)
- Pulp Fiction (8.67)
- The Dark Knight (8.00)

Top 5 movies in: Comedy
- Family (5.65)

Top 5 movies in: Mystery
- Blade Runner 2049 (0.00)

Top 5 movies in: Musical
- The Greatest Showman (9.00)
- La La Land (8.67)

--- Highest rated movies ---
Inception: 9.00
The Matrix: 9.00
Interstellar: 9.00
Mad Max: Fury Road: 9.00
The Greatest Showman: 9.00
The Godfather: 9.00
Pulp Fiction: 8.67
The Dark Knight: 8.00
```