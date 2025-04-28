package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
library library=new library();
        Scanner scanner=new Scanner(System.in);
library.addBook(new book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
    library.addBook(new book("To Kill a Mockingbird", "Harper Lee", "9780061120084"));
        library.addBook(new book("1984", "George Orwell", "9780451524935"));

        member member = new member("biley rana", "M001");
        library.registerMember(member);
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display All Books");
            System.out.println("7. Display All Members");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
switch (choice){
    case 1:
        // Add a new book
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();
        library.addBook(new book(title, author, isbn));
        break;
    case 2:
        // Remove a book
        System.out.print("Enter Book ISBN to remove: ");
        String removeIsbn = scanner.nextLine();
        library.removeBook(removeIsbn);
        break;
    case 3:
        // Search for a book
        System.out.print("Enter Book Title/Author/ISBN to search: ");
        String searchQuery = scanner.nextLine();
        book foundBook = library.searchbook(searchQuery);
        if (foundBook != null) {
            System.out.println("Book Found: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }
        break;
    case 4:
        // Borrow a book
        System.out.print("Enter ISBN of the book to borrow: ");
        String borrowIsbn = scanner.nextLine();
        boolean borrowed = library.borrowbook(member, borrowIsbn);
        if (borrowed) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is not available.");
        }
        break;case 5:
        // Return a book
        System.out.print("Enter ISBN of the book to return: ");
        String returnIsbn = scanner.nextLine();
        boolean returned = library.returnBook(member, returnIsbn);
        if (returned) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("You did not borrow this book or it's already available.");
        }
        break;

    case 6:
        // Display all books
        library.displayBooks();
        break;

    case 7:
        // Display all members
        library.displayMembers();
        break;

    case 8:
        System.out.println("Exiting... Thank you for using the Library Management System.");
        return; // Exit the program

    default:
        System.out.println("Invalid choice. Try again.");
}
        }
}


        }


