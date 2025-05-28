# JAVA PROJECT B AUEB

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

- Commented out the previous `Main` so we can now  the use the new methods that we will create
- Created `allReviews` attribute in the class `Review`
- The constructors now add every review objects that get created to the `allReviews` list
- Created `allUser` attribute in the class `User`
- The constructor now adds every user objects that get created to the `allUser` list
- Created getters for `allReviews` and `allUsers`
- Creted the methods `getSpecificUser` and `getSpecificReview`
- Made a `Movie` constructor that takes as input one genre without an array and also doesnt take a director
- Created the corresponding movie, user and review objects in `Main` according to the given `reviews.csv` file
- Made Review constructor throw exception if a review already exists for the specified user and movie
- Created **DataLoader** class as specified by the UML diagram and moved file input logic there
- Made `loadFromCSV()` split movie genres by this `|` delimiter
- Changed `getSpecificMovie()` to compare with the `.equals()` method
- Changed `.split("|")` to `.split("\\|")` in `loadFromCSV` in order to escape the pipe from regex
- Created `moviesByGenre` hashmap and made the constructor of Movie add itself to it
- Added `printMoviesByGenre()` in the **Movie** class to make it easy to see how the hashmap works
- Changed `moviesByGenre` to a TreeMap so that it is always sorted
- Added `byReviewCount` Comparator to the **Movie** class

---