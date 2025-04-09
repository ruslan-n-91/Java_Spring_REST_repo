package restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapp.model.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {

}
