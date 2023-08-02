package cr.cenfotec.primerapi.primerrestapi.controllers.response;

public class BookResponse {
    private Long id;

    public BookResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
