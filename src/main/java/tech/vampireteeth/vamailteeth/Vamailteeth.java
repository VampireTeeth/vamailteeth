package tech.vampireteeth.vamailteeth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Vamailteeth {

    @RequestMapping("/")
    public String home() {
        return "Hello, world!";
    }
    public static void main(String[] args) {
        SpringApplication.run(Vamailteeth.class, args);
    }
}
