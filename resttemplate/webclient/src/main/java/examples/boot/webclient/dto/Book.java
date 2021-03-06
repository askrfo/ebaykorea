package examples.boot.webclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Book {
    private Long id;
    private String title;
    private LocalDateTime regdate;

    public Book(){
        regdate = LocalDateTime.now();
    }

    public Book(Long id, String title){
        this.id = id;
        this.title = title;
        regdate = LocalDateTime.now();
    }
}
