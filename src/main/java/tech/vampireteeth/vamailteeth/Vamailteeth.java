package tech.vampireteeth.vamailteeth;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;
import tech.vampireteeth.vamailteeth.service.MailService;

@RestController
@EnableAutoConfiguration
@ComponentScan
public class Vamailteeth {

    @Resource(name = "mailServiceMailgun")
    private MailService mailServiceMailgun;

    @Resource(name = "mailServiceSendGrid")
    private MailService mailServiceSendGrid;



    @RequestMapping(value = "/mail", method = RequestMethod.POST, consumes = {"application/json"})
    public MailResponse mail(@RequestBody MailRequest mailRequest) {
        MailResponse mailResponse = mailServiceMailgun.sendMail(mailRequest);
        if (mailResponse.isFailed()) {
            mailResponse = mailServiceSendGrid.sendMail(mailRequest);
        }
        return mailResponse;
    }

    public static void main(String[] args) {
        SpringApplication.run(Vamailteeth.class, args);
    }
}
