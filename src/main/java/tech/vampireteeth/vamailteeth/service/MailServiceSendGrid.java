package tech.vampireteeth.vamailteeth.service;

import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service("mailServiceSendGrid")
public class MailServiceSendGrid implements MailService {

    @Override
    public MailResponse sendMail(MailRequest mailRequest, String apiKey) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Request request(MailRequest mailRequest, String apiKey) {
        // TODO Auto-generated method stub
        return null;
    }

}
