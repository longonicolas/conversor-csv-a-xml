package csvtoxml.repositories;

import csvtoxml.entities.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RowRepository extends JpaRepository<Row, Integer> {

    @Query("SELECT r FROM Row r")
    List<Row> findAllRows();

}
