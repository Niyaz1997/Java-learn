import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Student {
    private String name;
    private List<Book> books;

    public Student(String name, List<Book> books) {
        if (name == null) {
            throw new IllegalArgumentException("Student name cannot be null");
        }
        this.books = (books == null) ? new ArrayList<>() : new ArrayList<>(books);
        this.name = name;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(name, student.name) && Objects.equals(books, student.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, books);
    }

    @Override
    public String toString() {
        return String.format("Student name: %s, Books: %d", name, books.size());
    }
}

class Book {
    private String nameBook;
    private String author;
    private int year;
    private int pages;

    public Book(String nameBook, String author, int pages, int year) {
        if (nameBook == null) {
            throw new IllegalArgumentException();
        }
        this.nameBook = nameBook;
        this.author = (author == null) ? "" : author;
        this.pages = Math.max(0, pages);
        this.year = year;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return year == book.year && pages == book.pages && Objects.equals(nameBook, book.nameBook) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameBook, author, year, pages);
    }

    @Override
    public String toString() {
        return String.format("Book: %s, Author: %s, %d year, %d pages", nameBook, author, year, pages);
    }
}