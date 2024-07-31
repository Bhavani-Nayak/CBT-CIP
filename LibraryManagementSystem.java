import java.util.*;

public class LibraryManagementSystem {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("......Library Management System......");
            System.out.println("1. Add New Book");
            System.out.println("2. Display All The Books");
            System.out.println("3. Add New Member");
            System.out.println("4. Display All The Members");
            System.out.println("5. Borrow A Book");
            System.out.println("6. Return A Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    addMember();
                    break;
                case 4:
                    displayMembers();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    System.out.println("Thank you for using the Library Management System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();

        Book book = new Book(title, author, isbn);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    private static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void addMember() {
        System.out.print("Enter member name: ");
        String name = sc.nextLine();
        System.out.print("Enter member ID: ");
        String id = sc.nextLine();

        Member member = new Member(name, id);
        members.add(member);
        System.out.println("Member added successfully.");
    }

    private static void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in the library.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    private static void borrowBook() {
        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = sc.nextLine();

        Member member = findMember(memberId);
        Book book = findBook(isbn);

        if (member != null && book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
            member.borrowBook(book);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Unable to borrow the book. Please check member ID, ISBN, and availability.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = sc.nextLine();

        Member member = findMember(memberId);
        Book book = findBook(isbn);

        if (member != null && book != null && book.isBorrowed()) {
            book.setBorrowed(false);
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Unable to return the book. Please check member ID and ISBN.");
        }
    }

    private static Member findMember(String id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    private static Book findBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean borrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowed = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getBorrowedStatus() {
        return borrowed ? "Yes" : "No";
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', isbn='" + isbn + "', borrowed=" + getBorrowedStatus() + "}";
    }
}

class Member {
    private String name;
    private String id;
    private List<Book> borrowedBooks;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member{name='" + name + "', id='" + id + "', borrowedBooks=" + borrowedBooks.size() + "}";
    }
}