package cr.cenfotec.primerapi.primerrestapi.controllers;

import cr.cenfotec.primerapi.primerrestapi.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cr.cenfotec.primerapi.primerrestapi.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getAllBooks(){
        return service.getAll();
    }

    @PostMapping()
    public void insertBook(@RequestBody Book newBook){
        service.saveBook(newBook);
    }

    @GetMapping(value = "/{id}")
    public Book getBook(@PathVariable("id") Long id){
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        service.deleteBook(id);
    }

    @PutMapping(value = "/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        updatedBook.setId(id);
        service.updateBook(updatedBook);
    }
}
