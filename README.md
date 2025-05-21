# JAVA PROJECT A AUEB

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

### Day 1

- Created the necessary `java` files. One for each of the classes that are said to implement.
- Copied the given code template to the files so that we have a guide.
- From the `User` class we changed the `ArrayList` which was assigned to the attribute `reviews` to accept `Review` type objects instead of `String`s.
- Added getters to all the classes that have `protected`/`private` attributes.
- Overridden the `toString()` method in each class so it returns a string with all the attributes of the class.
- Changed the implementation of the `printDetails()` method by using `System.out.println()`, which uses the now-overridden `toString()`.
- Added comments to each part of the class.

---

### Day 2

- Duplicated `Review` constructor to optionally take `comments`.
- Made `Review` add itself to the `Movie` and `User` it takes as arguments.
- Changed `getWeightedRating()` of `VerifiedReview` so that it multiplies the rating by `1.3`.
- Changed `getAverageRating()` of `Movie` so that it clamps the rating value to `10`.

---

### Day 3

- Added a new attribute `allMovies` to the class `Movie` which stores all the movies in an array.
  - Made a getter for it and also made the constructor add each new movie object created to `allMovies`.
- Changed the `printDetails()` methods to work like they used to, without the `toString()`.
- Changed the `toString()` method for each class to display less information so it’s easier to understand what is displayed to the terminal.
- Created the static method `getHighestRatedByGenre()` in the class `Movie`, which returns a `HashMap<String, Movie>` mapping genres to their highest rated movie.
- Used the getters of the attributes instead of accessing the attributes directly in some methods.
- Changed the method `getRelatedMovies()` so now it fills the array `relatedMovies` with movies of the same genre and returns it.
- Created the method `getReviewers()` which returns an array of `User`s who have reviewed the movie that called the method.

---

### Day 4

- Overloaded the method `getHighestRatedByGenre()` so the new method also accepts an `int N` and a `float X`.
  - This overloaded method returns a `HashMap<String, Movie>` with genres as keys and the highest rated movies as values that have **at least** `N` reviews and a rating **higher** than `X`.
- Added some `Comparator<Movie>`s.
- Added searchers for the movies.

---

### Day 5

- Added a small run script
- Changed method `getHighestRatedByGenre(int N, double X)` to `getHighestRatedByGenre(int mininumReviewerAmount, double minimumRating)`
- Added method `getSpecificMovie(String title)` to get movies by their name
- Added **VerifiedUser** class and made **VerifiedReview** only accept VerifiedUsers. It contains an enum with several authentication methods, as well as a `verify` method that can be overriden by the users of the framework.
- Changed README formatting
- Added more movies
- Changed user to user2 in the `System.out.println()` of the try block in the `Main` class
- Removed the declaration of the attributes in the `VeridiedUser` class as they are inherited from the class User
- Removed the initialization of the attributes in the `VerifiedUser` constructor as they are already initialized from `super(username)`
- Removed the method `searchByTitle(String title)` from the class `Movie` as it is now implemented by the method `getSpecificMovie(String title)`
- Removed `import java.util.*` from `VerifiedUser` as it is now not used
- The constructor of the class `VerifiedUser` now throws exceptions
- Added try catch block in `Main` so that it handles the new exception

---

### Day 6

- Made `getWeightedRating()` a non abstract class in **Review** that multiplies the review's rating with its weight and removed the overrides in **BasicReview** and **Verified Review**
- Added the `weight` attribute to the **Review** class as well as a `getWeight()` getter
- Improved the `getAverageRating()` method in the **Movie** class
- The constructors of the classes `Review`, `VerifiedReview`, `BasicReview` now throw exceptions
- The constructor of the class `Review` now check if `rating` is between 1 and 10 and if it is not it throws exception
- Added try catch blocks in `Main` so that it handles the new exceptions
- Reorganized main and added comments
- Added Demo and Design sections in README