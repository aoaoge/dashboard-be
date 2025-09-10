package org.example.librarybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LibraryBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryBeApplication.class, args);
    }


}
