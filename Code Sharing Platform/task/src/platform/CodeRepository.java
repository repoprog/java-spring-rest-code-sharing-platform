package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    List<Code> findAll();
    Optional<Code> findByUuid(String uuid);
}
