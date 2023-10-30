import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void checkout() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + (available ? "Available" : "Checked Out") + ")";
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                return book;
            }
        }
        return null;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        library.addBook(new Book("Book 1", "Author 1"));
        library.addBook(new Book("Book 2", "Author 2"));
        library.addBook(new Book("Book 3", "Author 3"));

        while (true) {
            System.out.println("Library Menu:");
            System.out.println("1. List Books");
            System.out.println("2. Check Out Book");
            System.out.println("3. Return Book");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Books:");
                    library.listBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book to check out: ");
                    String checkoutTitle = scanner.nextLine();
                    Book checkoutBook = library.findBook(checkoutTitle);
                    if (checkoutBook != null) {
                        checkoutBook.checkout();
                        System.out.println(checkoutBook.getTitle() + " has been checked out.");
                    } else {
                        System.out.println("Book not found or already checked out.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.findBook(returnTitle);
                    if (returnBook != null) {
                        returnBook.returnBook();
                        System.out.println(returnBook.getTitle() + " has been returned.");
                    } else {
                        System.out.println("Book not found or already available.");
                    }
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
