package tech.vampireteeth.vamailteeth;

import javax.annotation.Resource;

import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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
    
    
    private static void loadDB() {
        MongoClientURI mongoUri = new MongoClientURI(System.getenv("MONGODB_URI"));
        MongoClient mongoClient = new MongoClient(mongoUri);
        MongoDatabase db = mongoClient.getDatabase(mongoUri.getDatabase());
        MongoCollection<Document> vamailteeth = db.getCollection("vamailteeth");
        FindIterable<Document> find = vamailteeth.find(new Document("name", "api_keys"));
        Document doc = null;
        MongoCursor<Document> it = find.iterator();
        for(;it.hasNext();) {
            doc = it.next();
        }
    }
}
