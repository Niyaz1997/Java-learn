import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Student> students = Files.lines(Paths.get("Quest_2/students.txt"))
                .map(line -> {
                    String[] parts = line.split("\\|");
                    String name = parts[0];
                    List<Book> books = new ArrayList<>();
                    for (int i = 1; i < parts.length; i += 4) {
                        if (i + 3 < parts.length) {
                            books.add(new Book(parts[i], parts[i + 1], Integer.parseInt(parts[i + 2]), Integer.parseInt((parts[i + 3]))));
                        }
                    }
                    return new Student(name, books);
                }).toList();

        students.stream()
                .peek(s -> {
                    System.out.println("Student: " + s.getName());
                    s.getBooks().forEach(b -> System.out.println("  - " + b));
                    System.out.println();
                })
                .flatMap(s -> s.getBooks().stream())
                .sorted(Comparator
                        .comparingInt(Book::getPages)
                        .thenComparing(Book::getNameBook)
                        .thenComparing(Book::getAuthor)
                )
                .distinct()
                .peek(b -> System.out.println("Unique book (sorted by pages): " + b))
                .filter(b -> b.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Year of publication of the book: " + year),
                        () -> System.out.println("No books after 2000 year")
                );
    }
}