package restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapp.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    
}
