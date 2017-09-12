package tech.vampireteeth.vamailteeth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.vampireteeth.vamailteeth.model.Person;

@RestController
@EnableAutoConfiguration
public class Vamailteeth {

    @RequestMapping("/")
    public String home() {
        return "Hello, world!";
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public Person person() {
        return new Person("Steven Liu", 34);
    }

    public static void main(String[] args) {
        SpringApplication.run(Vamailteeth.class, args);
    }
}
