package examples.boot.webclient;

import examples.boot.webclient.client.BookAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import examples.boot.webclient.dto.Book;

import java.util.List;


@SpringBootApplication
public class WebclientApplication implements CommandLineRunner {

    @Autowired
    BookAdapter bookAdapter;

    public static void main(String[] args) {
        SpringApplication.run(WebclientApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book = bookAdapter.getBook(1L);
        printBook(book);

        System.out.println("-----------------------");
        List<Book> list = bookAdapter.getBooks();
        for(Book b : list){
            printBook(b);
        }
    }



    private void printBook(Book book) {
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getRegdate());
    }
}
