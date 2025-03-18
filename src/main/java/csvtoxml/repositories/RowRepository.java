package csvtoxml.repositories;

import csvtoxml.entities.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowRepository extends JpaRepository<Row, Integer> {


}
