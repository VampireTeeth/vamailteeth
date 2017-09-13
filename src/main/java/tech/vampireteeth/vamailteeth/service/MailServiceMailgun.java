package tech.vampireteeth.vamailteeth.service;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service("mailServiceMailgun")
public class MailServiceMailgun implements MailService {

    private static final String MAIL_API_URI = "https://api.mailgun.net/v3/email.vampireteeth.tech/messages";


    @Override
    public MailResponse sendMail(MailRequest mailRequest) {
        final String apiKey = "key-f4786f480ac495413417ce279aeff89f";
        final byte[] encodedKey = Base64.encodeBase64(apiKey.getBytes());
        String to = mailRequest.to();
        String cc = mailRequest.cc();
        String bcc = mailRequest.bcc();
        String text = mailRequest.text();
        Form form = Form.form()
                .add("to", to)
                .add("cc", cc)
                .add("bcc", bcc)
                .add("from", "email.vamailteeth.tech@mailgun.com");
        if (text != null) {
            form.add("text", text);
        }
        MailResponse mailResponse = new MailResponse();
        try {
            String response = Request.Post(MAIL_API_URI)
                    .addHeader("Authorization", String.format("Basic %s", new String(encodedKey)))
                    .bodyForm(form.build())
                    .execute()
                    .returnContent()
                    .asString()
                    ;
            System.out.println(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mailResponse;
    }

}
