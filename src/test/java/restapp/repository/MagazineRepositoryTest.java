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
import restapp.model.Magazine;

import java.util.List;

@WebAppConfiguration
@ContextConfiguration(classes = SpringConfig.class)
@ExtendWith(SpringExtension.class)
@Testcontainers
class MagazineRepositoryTest {
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
    MagazineRepository magazineRepository;

    @Test
    void findAllShouldReturnAllEntriesFromDb() {
        List<Magazine> magazines = (List<Magazine>) magazineRepository.findAll();

        Assertions.assertEquals(8, magazines.size());
        Assertions.assertEquals("National Geographic", magazines.get(0).getTitle());
        Assertions.assertEquals("National Trust Magazine", magazines.get(1).getTitle());
    }

    @Test
    void findByIdShouldReturnEntryWithSpecifiedIdFromDb() {
        Magazine magazine = magazineRepository.findById(1).get();

        Assertions.assertEquals(1, magazine.getId());
        Assertions.assertEquals("National Geographic", magazine.getTitle());
    }

    @Test
    void saveShouldCreateNewEntryInDb() {
        Magazine newMagazine1 = new Magazine(null, "Magazine 5555", 10, null);
        Magazine newMagazine2 = new Magazine(null, "Magazine 9999", 15, null);

        magazineRepository.save(newMagazine1);
        magazineRepository.save(newMagazine2);

        Magazine magazine1 = magazineRepository.findById(7).get();
        Magazine magazine2 = magazineRepository.findById(8).get();

        Assertions.assertEquals(7, magazine1.getId());
        Assertions.assertEquals("Magazine 5555", magazine1.getTitle());
        Assertions.assertEquals(10, magazine1.getQuantity());

        Assertions.assertEquals(8, magazine2.getId());
        Assertions.assertEquals("Magazine 9999", magazine2.getTitle());
        Assertions.assertEquals(15, magazine2.getQuantity());
    }

    @Test
    void saveShouldChangeEntryInDb() {
        Magazine newMagazine = new Magazine(6, "Magazine 3333", 25, null);

        magazineRepository.save(newMagazine);

        Magazine magazine = magazineRepository.findById(6).get();

        Assertions.assertEquals(6, magazine.getId());
        Assertions.assertEquals("Magazine 3333", magazine.getTitle());
        Assertions.assertEquals(25, magazine.getQuantity());
    }

    @Test
    void deleteShouldRemoveEntryFromDb() {
        magazineRepository.deleteById(7);
        magazineRepository.deleteById(8);

        List<Magazine> magazines = (List<Magazine>) magazineRepository.findAll();

        Assertions.assertEquals(6, magazines.size());
    }
}
