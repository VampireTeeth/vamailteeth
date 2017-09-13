package tech.vampireteeth.vamailteeth.service;

import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service("mailServiceSendGrid")
public class MailServiceSendGrid implements MailService {

    private static final String API_KEY = "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";

    @Override
    public MailResponse sendMail(MailRequest mailRequest) {
        // TODO Auto-generated method stub
        return null;
    }

}
