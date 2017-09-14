package tech.vampireteeth.vamailteeth.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service("mailServiceMailgun")
public class MailServiceMailgun implements MailService {

    private static final Log LOG = LogFactory.getLog(MailServiceMailgun.class);

    @Resource
    private MailRequestValidateService mailRequestValidateService;

    @Resource
    private MailRequestTransformer mailRequestTransformer; 
    
    @Resource
    private MailSender mailSender;

    @Override
    public MailResponse sendMail(MailRequest mailRequest, String apiKey) {
        return mailSender.send(this, mailRequest, apiKey);
    }

    @Override
    public Request request(MailRequest mailRequest, String apiKey) {
        return mailRequestTransformer.transform(this, mailRequest, apiKey);
    }

}
