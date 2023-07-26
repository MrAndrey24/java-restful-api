package cr.cenfotec.primerapi.primerrestapi.service;

import cr.cenfotec.primerapi.primerrestapi.entities.Book;
import cr.cenfotec.primerapi.primerrestapi.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo repo;

    public  void saveBook(Book book){
        repo.save(book);
    }

    public List<Book> getAll(){
        return repo.findAll();
    }

    public Book get(Long id){
        Optional<Book> book = repo.findById(id);
        return book.orElse(null);

    }

    public void updateBook(Book newData){
        repo.save(newData);
    }

    public  void deleteBook(Long id){
        repo.deleteById(id);
    }
}
