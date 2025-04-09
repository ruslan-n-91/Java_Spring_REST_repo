package restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapp.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
