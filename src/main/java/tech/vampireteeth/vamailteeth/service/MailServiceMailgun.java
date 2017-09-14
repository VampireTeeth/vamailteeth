package tech.vampireteeth.vamailteeth.service;

import static tech.vampireteeth.vamailteeth.service.Constants.MAILGUN_MAIL_API_URI;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service("mailServiceMailgun")
public class MailServiceMailgun implements MailService {

    private static final Log LOG = LogFactory.getLog(MailServiceMailgun.class);

    @Resource
    private MailSender mailSender;

    @Override
    public MailResponse sendMail(MailRequest mailRequest, String apiKey) {
        return mailSender.send(this, mailRequest, apiKey);
    }

    @Override
    public Request request(MailRequest mailRequest, String apiKey) {
        byte[] encodeApiKeyBytes = Base64.encodeBase64(apiKey.getBytes());
        final String ENCODED_API_KEY = new String(encodeApiKeyBytes);
        Form form = Form.form().add("to", mailRequest.to()).add("text", mailRequest.text())
                .add("subject", mailRequest.subject()).add("from", Constants.MAILGUN_EMAIL_FROM);

        if (mailRequest.cc() != null) {
            form.add("cc", mailRequest.cc());
        }
        if (mailRequest.bcc() != null) {
            form.add("bcc", mailRequest.bcc());
        }
        return Request.Post(MAILGUN_MAIL_API_URI)
                .addHeader(HttpHeaders.AUTHORIZATION, String.format("Basic %s", ENCODED_API_KEY))
                .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                .addHeader(HttpHeaders.EXPECT, "100-continue").bodyForm(form.build());
    }

}
