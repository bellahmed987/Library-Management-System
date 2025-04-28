**Library Management System - Detailed Line-by-Line Documentation**

---

# Overview
This documentation provides a **line-by-line explanation** of the Java-based Library Management System. The project consists of four main files:

1. **Book.java**: Defines a `Book` class representing library books.
2. **Member.java**: Defines a `Member` class representing library members who borrow books.
3. **Library.java**: Manages collections of books and members, and supports borrowing/returning operations.
4. **LibrarySystem.java**: Contains the `main` method and user interaction loop.

Each section below walks through every line of code, explaining its purpose, Java basics involved, and how data structures like lists store and manage data.

---

## 1. Book.java
```java
package org.example;                          // 1

public class Book {                           // 2
    private String title;                     // 3
    private String author;                    // 4
    private String isbn;                      // 5
    private boolean isAvailable;              // 6

    // Constructor: initializes new Book objects
    public Book(String title, String author, String isbn) { // 7
        this.title = title;                   // 8
        this.author = author;                 // 9
        this.isbn = isbn;                     // 10
        this.isAvailable = true;              // 11
    }                                         // 12

    // Getter for title
    public String getTitle() {                // 13
        return title;                        // 14
    }                                         // 15

    // Getter for author
    public String getAuthor() {               // 16
        return author;                       // 17
    }                                         // 18

    // Getter for ISBN
    public String getIsbn() {                 // 19
        return isbn;                         // 20
    }                                         // 21

    // Getter for availability status
    public boolean isAvailable() {            // 22
        return isAvailable;                  // 23
    }                                         // 24

    // Setter to update availability
    public void setAvailable(boolean available) { // 25
        isAvailable = available;             // 26
    }                                         // 27

    // Returns a readable representation of the book
    @Override                                  // 28
    public String toString() {                // 29
        return "Title: " + title           // 30
            + ", Author: " + author        // 31
            + ", ISBN: " + isbn            // 32
            + ", Available: " + isAvailable; // 33
    }                                         // 34
}                                             // 35
```

### Explanation
1. **`package org.example;`** — Declares the package namespace. Helps organize classes.
2. **`public class Book {`** — Begins class definition; `public` makes it accessible outside this package.
3–6. **Private fields** — Encapsulated attributes: `title`, `author`, `isbn`, and `isAvailable`. Used to store book details. Marked `private` to enforce access via getters/setters.
7. **Constructor signature** — `public Book(String title, String author, String isbn)`: invoked with `new Book(...)` to create objects.
8–11. **`this` assignments** — Assign constructor parameters to instance fields. `isAvailable` defaults to `true` (book starts available).
13–24. **Getters** — Public methods to retrieve private field values. Following JavaBean conventions (`getXxx`, `isXxx`).
25–27. **Setter** — `setAvailable(boolean)`: updates `isAvailable`. Controls borrowing logic externally.
28–29. **`@Override public String toString()`** — Overrides base `Object#toString()`. Provides human-readable text when printing a `Book`.
30–33. **Concatenate fields** — Builds the display string showing all key properties.

---

## 2. Member.java
```java
package org.example;                          // 1

import java.util.ArrayList;                  // 2
import java.util.List;                       // 3

public class Member {                        // 4
    private String name;                      // 5
    private String memberId;                  // 6
    private List<Book> borrowedBooks;         // 7

    // Constructor: initialize a Member with empty borrowed-books list
    public Member(String name, String memberId) { // 8
        this.name = name;                     // 9
        this.memberId = memberId;             // 10
        this.borrowedBooks = new ArrayList<>(); // 11
    }                                         // 12

    // Returns the list of books this member has borrowed
    public List<Book> getBorrowedBooks() {     // 13
        return borrowedBooks;                 // 14
    }                                         // 15

    // Adds a book to the borrowedBooks list
    public void borrowBook(Book book) {        // 16
        borrowedBooks.add(book);              // 17
    }                                         // 18

    // Removes a book from the borrowedBooks list
    public void returnBook(Book book) {        // 19
        borrowedBooks.remove(book);           // 20
    }                                         // 21

    // String summary of member
    @Override                                  // 22
    public String toString() {                // 23
        return "Name: " + name              // 24
            + ", Member ID: " + memberId;  // 25
    }                                         // 26
}                                             // 27
```

### Explanation
1. **Package** — Same as `Book`.
2–3. **Imports** — `List` interface and `ArrayList` implementation from Java Collections Framework.
5–7. **Private fields** — `name`, `memberId`, and `borrowedBooks` (a list storing books borrowed by this member).
8–12. **Constructor** — Sets `name`, `memberId`, and creates a new empty `ArrayList` for `borrowedBooks`.
13–15. **`getBorrowedBooks()`** — Returns the list reference, allowing callers to view borrowed books.
16–18. **`borrowBook(Book book)`** — `list.add(...)` appends the passed `Book` object to the end of the list.
19–21. **`returnBook(Book book)`** — `list.remove(...)` finds and removes the first matching `Book` object.
22–23. **`@Override toString()`** — Textual summary for logging or display.
24–25. **Concatenate** — Combines `name` and `memberId` into one string.

**List Basics**: `List<Book>` is an interface type. We instantiate as `new ArrayList<>()`. Internally, an `ArrayList` uses a resizable array, growing automatically when elements are added. `add()` puts the element at the end; `remove()` shifts subsequent elements to fill the gap.

---

## 3. Library.java
```java
package org.example;                          // 1

import java.util.ArrayList;                  // 2
import java.util.List;                       // 3

public class Library {                       // 4
    private List<Book> books;                // 5
    private List<Member> members;            // 6

    // Initialize empty collections for books and members
    public Library() {                        // 7
        this.books = new ArrayList<>();      // 8
        this.members = new ArrayList<>();    // 9
    }                                        // 10

    // Adds a new book to the library
    public void addBook(Book book) {         // 11
        books.add(book);                     // 12
    }                                        // 13

    // Register a new member
    public void registerMember(Member member) { // 14
        members.add(member);                 // 15
    }                                        // 16

    // Member borrows a book if available
    public boolean borrowBook(Member member, String isbn) { // 17
        for (Book book : books) {            // 18
            if (book.getIsbn().equals(isbn) && book.isAvailable()) { // 19
                book.setAvailable(false);   // 20
                member.borrowBook(book);    // 21
                return true;                // 22
            }                                // 23
        }                                    // 24
        return false;                        // 25
    }                                        // 26

    // Member returns a book identified by ISBN
    public boolean returnBook(Member member, String isbn) { // 27
        for (Book book : member.getBorrowedBooks()) { // 28
            if (book.getIsbn().equals(isbn)) { // 29
                book.setAvailable(true);    // 30
                member.returnBook(book);    // 31
                return true;                // 32
            }                                // 33
        }                                    // 34
        return false;                        // 35
    }                                        // 36

    // Display all books in the library
    public void displayBooks() {             // 37
        for (Book book : books) {           // 38
            System.out.println(book);       // 39
        }                                    // 40
    }                                        // 41

    // Display all registered members
    public void displayMembers() {           // 42
        for (Member member : members) {     // 43
            System.out.println(member);     // 44
        }                                    // 45
    }                                        // 46
}                                            // 47
```

### Explanation
1. **Package** — Project namespace.
2–3. **Imports** — Collections interfaces.
5–6. **Fields** — Two lists: `books` and `members` (both dynamic collections).
7–10. **Constructor** — Instantiates both lists as empty `ArrayList`s.
11–13. **`addBook(Book)`** — Adds book to library.
14–16. **`registerMember(Member)`** — Adds member to library.
17–26. **`borrowBook(Member, String)`** —
- 18–24. Loop through `books` list.
- 19. Check matching ISBN and availability (`equals` and `isAvailable`).
- 20. Mark book unavailable.
- 21. Add to member’s `borrowedBooks` list.
- 22. Return `true` on success, `false` if no match found.
27–36. **`returnBook(Member, String)`** — Similar logic on member’s borrowed list.
37–41. **`displayBooks()`** — Iterates `books` and prints each via its `toString()`.
42–46. **`displayMembers()`** — Iterates `members` and prints summary.

---

## 4. LibrarySystem.java
```java
package org.example;                          // 1

import java.util.Scanner;                    // 2

public class LibrarySystem {                  // 3
    public static void main(String[] args) {  // 4
        Scanner scanner = new Scanner(System.in); // 5
        Library library = new Library();      // 6

        // Sample data                       // 7
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565")); // 8
        library.addBook(new Book("1984", "George Orwell", "9780451524935"));              // 9
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084"));// 10

        Member member = new Member("John Doe", "M001"); // 11
        library.registerMember(member);       // 12

        while (true) {                        // 13
            System.out.println("Library Management System"); // 14
            System.out.println("1. Add Book");      // 15
            System.out.println("2. Borrow Book");   // 16
            System.out.println("3. Return Book");   // 17
            System.out.println("4. Display Books"); // 18
            System.out.println("5. Display Members");// 19
            System.out.println("6. Exit");          // 20
            System.out.print("Enter your choice: "); // 21
            int choice = scanner.nextInt();   // 22
            scanner.nextLine(); // consume newline // 23

            switch (choice) {               // 24
                case 1:                     // 25
                    System.out.print("Title: ");      // 26
                    String title = scanner.nextLine(); // 27
                    System.out.print("Author: ");     // 28
                    String author = scanner.nextLine();// 29
                    System.out.print("ISBN: ");       // 30
                    String isbn = scanner.nextLine();  // 31
                    library.addBook(new Book(title, author, isbn)); // 32
                    break;                    // 33

                case 2:                     // 34
                    System.out.print("ISBN to borrow: "); // 35
                    String borrowIsbn = scanner.nextLine(); // 36
                    boolean success = library.borrowBook(member, borrowIsbn); // 37
                    System.out.println(success ? "Borrowed" : "Not available"); // 38
                    break;                    // 39

                case 3:                     // 40
                    System.out.print("ISBN to return: "); // 41
                    String returnIsbn = scanner.nextLine();  // 42
                    boolean returned = library.returnBook(member, returnIsbn); // 43
                    System.out.println(returned ? "Returned" : "Not borrowed"); // 44
                    break;                    // 45

                case 4:                     // 46
                    library.displayBooks();  // 47
                    break;                    // 48

                case 5:                     // 49
                    library.displayMembers();// 50
                    break;                    // 51

                case 6:                     // 52
                    System.out.println("Goodbye!"); // 53
                    scanner.close();       // 54
                    return;                // 55

                default:                    // 56
                    System.out.println("Invalid choice"); // 57
            }                                // 58
        }                                    // 59
    }                                        // 60
}                                            // 61
```

### Explanation
1. **Package** — Organizes classes.
2. **Import Scanner** — For console input.
4. **`main` method** — Java entry point; `String[] args` holds command-line arguments.
5. **`new Scanner(System.in)`** — Reads user input from console.
6. **`new Library()`** — Instantiates the library manager.
8–12. **Sample data** — Creates and registers books/members.
13–21. **Menu loop** — `while(true)` shows options and reads a numeric `choice`.
22–23. **`nextInt()` + `nextLine()`** — Read integer then consume trailing newline.
24–58. **`switch`** — Branch logic for each menu option:
- **Case 1**: Add book (lines 26–33).
- **Case 2**: Borrow book (35–39).
- **Case 3**: Return book (41–45).
- **Case 4**: Display all books (47–48).
- **Case 5**: Display members (50–51).
- **Case 6**: Exit (print message, close scanner, `return` from `main`).
- **Default**: Handle invalid choice.
59–61. **End of loops and class**.

**Key Points**:
- **Scanner** is used for interactive console input.
- **Lists** (`books`, `members`, `borrowedBooks`) dynamically manage collections; no fixed size.
- **Loop + switch** pattern implements a simple text-based menu.
- **Encapsulation**: Private fields with getters/setters, public methods for operations.
- **Method calls** cross between `LibrarySystem`, `Library`, `Member`, and `Book` classes to perform actions.

---

## Conclusion
This detailed walkthrough explains each line, illustrating how the code uses Java fundamentals—classes, objects, constructors, methods, collections, and console I/O—to create a functioning Library Management System.

