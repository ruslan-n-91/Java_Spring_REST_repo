package restapp.repository;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import restapp.config.SpringConfig;
import restapp.model.Book;

import java.util.List;

@WebAppConfiguration
@ContextConfiguration(classes = SpringConfig.class)
@ExtendWith(SpringExtension.class)
@Testcontainers
class BookRepositoryTest {
    private static final int containerPort = 5432;
    private static final int localPort = 5432;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine")
            .withDatabaseName("java_spring_rest_db")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(containerPort)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding
                            (Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
            )).withInitScript("sql/sql_script.sql");

    @Autowired
    BookRepository bookRepository;

    @Test
    void findAllShouldReturnAllEntriesFromDb() {
        List<Book> books = (List<Book>) bookRepository.findAll();

        Assertions.assertEquals(8, books.size());
        Assertions.assertEquals("Java The Complete Reference 9th Edition", books.get(0).getTitle());
        Assertions.assertEquals("Grokking Algorithms", books.get(2).getTitle());
    }

    @Test
    void findByIdShouldReturnEntryWithSpecifiedIdFromDb() {
        Book book = bookRepository.findById(3).get();

        Assertions.assertEquals(3, book.getId());
        Assertions.assertEquals("Grokking Algorithms", book.getTitle());
    }

    @Test
    void saveShouldCreateNewEntryInDb() {
        Book newBook1 = new Book(null, "Book 5555", 10, null);
        Book newBook2 = new Book(null, "Book 9999", 15, null);

        bookRepository.save(newBook1);
        bookRepository.save(newBook2);

        Book book1 = bookRepository.findById(7).get();
        Book book2 = bookRepository.findById(8).get();

        Assertions.assertEquals(7, book1.getId());
        Assertions.assertEquals("Book 5555", book1.getTitle());
        Assertions.assertEquals(10, book1.getQuantity());

        Assertions.assertEquals(8, book2.getId());
        Assertions.assertEquals("Book 9999", book2.getTitle());
        Assertions.assertEquals(15, book2.getQuantity());
    }

    @Test
    void saveShouldChangeEntryInDb() {
        Book newBook = new Book(1, "Book 3333", 20, null);

        bookRepository.save(newBook);

        Book book = bookRepository.findById(1).get();

        Assertions.assertEquals(1, book.getId());
        Assertions.assertEquals("Book 3333", book.getTitle());
        Assertions.assertEquals(20, book.getQuantity());
    }

    @Test
    void deleteShouldRemoveEntryFromDb() {
        bookRepository.deleteById(5);
        bookRepository.deleteById(7);
        bookRepository.deleteById(8);

        List<Book> books = (List<Book>) bookRepository.findAll();

        Assertions.assertEquals(5, books.size());
    }
}
