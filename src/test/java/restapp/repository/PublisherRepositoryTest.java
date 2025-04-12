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
import restapp.model.Publisher;

import java.util.List;

@WebAppConfiguration
@ContextConfiguration(classes = SpringConfig.class)
@ExtendWith(SpringExtension.class)
@Testcontainers
class PublisherRepositoryTest {
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
    PublisherRepository publisherRepository;

    @Test
    void findAllShouldReturnAllEntriesFromDb() {
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();

        Assertions.assertEquals(5, publishers.size());
        Assertions.assertEquals("National Geographic Society", publishers.get(0).getName());
    }

    @Test
    void findByIdShouldReturnEntryWithSpecifiedIdFromDb() {
        Publisher publisher = publisherRepository.findById(1).get();

        Assertions.assertEquals(1, publisher.getId());
        Assertions.assertEquals("National Geographic Society", publisher.getName());
    }

    @Test
    void saveShouldCreateNewEntryInDb() {
        Publisher newPublisher1 = new Publisher(null, "Publisher 5555", null);
        Publisher newPublisher2 = new Publisher(null, "Publisher 9999", null);

        publisherRepository.save(newPublisher1);
        publisherRepository.save(newPublisher2);

        Publisher publisher1 = publisherRepository.findById(4).get();
        Publisher publisher2 = publisherRepository.findById(5).get();

        Assertions.assertEquals(4, publisher1.getId());
        Assertions.assertEquals("Publisher 5555", publisher1.getName());

        Assertions.assertEquals(5, publisher2.getId());
        Assertions.assertEquals("Publisher 9999", publisher2.getName());
    }

    @Test
    void saveShouldChangeEntryInDb() {
        Publisher newPublisher = new Publisher(3, "Publisher 3333", null);

        publisherRepository.save(newPublisher);

        Publisher publisher = publisherRepository.findById(3).get();

        Assertions.assertEquals(3, publisher.getId());
        Assertions.assertEquals("Publisher 3333", publisher.getName());
    }

    @Test
    void deleteShouldRemoveEntryFromDb() {
        publisherRepository.deleteById(4);
        publisherRepository.deleteById(5);

        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();

        Assertions.assertEquals(3, publishers.size());
    }
}
