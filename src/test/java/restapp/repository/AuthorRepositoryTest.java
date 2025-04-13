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
import restapp.model.Author;

import java.util.List;

@WebAppConfiguration
@ContextConfiguration(classes = SpringConfig.class)
@ExtendWith(SpringExtension.class)
@Testcontainers
class AuthorRepositoryTest {
    private static final int containerPort = 5432;
    private static final int localPort = 5432;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres")
            .withDatabaseName("java_spring_rest_db")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(containerPort)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding
                            (Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
            )).withInitScript("sql/sql_script.sql");

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void findAllShouldReturnAllEntriesFromDb() {
        List<Author> authors = (List<Author>) authorRepository.findAll();

        Assertions.assertEquals(11, authors.size());
        Assertions.assertEquals("Herbert Schildt", authors.get(0).getName());
        Assertions.assertEquals("Erich Gamma", authors.get(1).getName());
    }

    @Test
    void findByIdShouldReturnEntryWithSpecifiedIdFromDb() {
        Author author = authorRepository.findById(3).get();

        Assertions.assertEquals(3, author.getId());
        Assertions.assertEquals("Richard Helm", author.getName());
    }

    @Test
    void saveShouldCreateNewEntryInDb() {
        Author newAuthor1 = new Author(null, "Author 5555", null);
        Author newAuthor2 = new Author(null, "Author 9999", null);

        authorRepository.save(newAuthor1);
        authorRepository.save(newAuthor2);

        Author author1 = authorRepository.findById(10).get();
        Author author2 = authorRepository.findById(11).get();

        Assertions.assertEquals(10, author1.getId());
        Assertions.assertEquals("Author 5555", author1.getName());

        Assertions.assertEquals(11, author2.getId());
        Assertions.assertEquals("Author 9999", author2.getName());
    }

    @Test
    void saveShouldChangeEntryInDb() {
        Author newAuthor = new Author(1, "Author 3333", null);

        authorRepository.save(newAuthor);

        Author author = authorRepository.findById(1).get();

        Assertions.assertEquals(1, author.getId());
        Assertions.assertEquals("Author 3333", author.getName());
    }

    @Test
    void deleteShouldRemoveEntryFromDb() {
        authorRepository.deleteById(8);
        authorRepository.deleteById(9);

        List<Author> authors = (List<Author>) authorRepository.findAll();

        Assertions.assertEquals(9, authors.size());
    }
}
