package restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restapp.model.Magazine;

@Repository
public interface MagazineRepository extends CrudRepository<Magazine, Integer> {

}
