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
