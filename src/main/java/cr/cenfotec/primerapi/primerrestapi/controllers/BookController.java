package cr.cenfotec.primerapi.primerrestapi.controllers;

import cr.cenfotec.primerapi.primerrestapi.controllers.response.BookResponse;
import cr.cenfotec.primerapi.primerrestapi.controllers.response.GenericResponse;
import cr.cenfotec.primerapi.primerrestapi.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cr.cenfotec.primerapi.primerrestapi.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<GenericResponse> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(service.getAll()));
    }

    @PostMapping()
    public ResponseEntity<GenericResponse> insertBook(@RequestBody Book newBook){
        try{
            Long newId = service.saveBook(newBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(new BookResponse(newId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponse> getBook(@PathVariable("id") Long id){
        try{
            Book book = service.get(id);
            if(book != null){
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(book));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Book not found"));
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GenericResponse> deleteBook(@PathVariable("id") Long id){
        try{
            if (service.get(id) != null){
                service.deleteBook(id);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Book deleted"));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Book not found"));
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenericResponse> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        try{
            if (service.get(id) != null) {
                updatedBook.setId(id);
                service.updateBook(updatedBook);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Book updated"));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Book not found"));
            }

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }
}
