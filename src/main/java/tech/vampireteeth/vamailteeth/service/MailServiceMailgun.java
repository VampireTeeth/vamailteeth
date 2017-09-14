package tech.vampireteeth.vamailteeth.service;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service("mailServiceMailgun")
public class MailServiceMailgun implements MailService {

    private static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
    private static final String MAILGUN_EMAIL_FROM= "mailgun@email.vampireteeth.tech";
    private static final String MAIL_API_URI = "https://api.mailgun.net/v3/email.vampireteeth.tech/messages";
    private static final String SUCCESS = "SUCCESS";

    @Override
    public MailResponse sendMail(MailRequest mailRequest, String apiKey) {
        byte[] encodeApiKeyBytes = Base64.encodeBase64(apiKey.getBytes());
        final String ENCODED_API_KEY = new String(encodeApiKeyBytes);
        final String to = mailRequest.to();
        final String cc = mailRequest.cc();
        final String bcc = mailRequest.bcc();
        final String text = mailRequest.text();
        final String subject = mailRequest.subject();
        Form form = Form.form()
                .add("to", to)
                .add("cc", cc)
                .add("bcc", bcc)
                .add("from", MAILGUN_EMAIL_FROM);
        if (text != null) {
            //TODO Should return failed mailResponse with message saying that text is missing on null
            form.add("text", text);
        }
        if (subject != null) {
            //TODO Should return failed mailResponse with message saying that subject is missing on null
            form.add("subject", subject);
        }

        MailResponse mailResponse = new MailResponse();
        try {
            Request.Post(MAIL_API_URI)
                    .addHeader(HttpHeaders.AUTHORIZATION, String.format("Basic %s", ENCODED_API_KEY))
                    .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString())
                    .addHeader(HttpHeaders.EXPECT, "100-continue")
                    .bodyForm(form.build())
                    .execute();

            //No error means response returned with 200 status code
            mailResponse.setFailed(false);
            mailResponse.setMessage(SUCCESS);
        } catch (ClientProtocolException e) {
            mailResponse.setMessage(e.getMessage());
            mailResponse.setFailed(true);
        } catch (IOException e) {
            mailResponse.setMessage(INTERNAL_SERVER_ERROR);
            mailResponse.setFailed(true);
        }
        return mailResponse;
    }

}
