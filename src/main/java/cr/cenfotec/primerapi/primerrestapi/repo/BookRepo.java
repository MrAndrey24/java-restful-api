package cr.cenfotec.primerapi.primerrestapi.repo;

import cr.cenfotec.primerapi.primerrestapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
