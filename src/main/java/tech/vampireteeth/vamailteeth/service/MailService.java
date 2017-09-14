package tech.vampireteeth.vamailteeth.service;

import org.apache.http.client.fluent.Request;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

public interface MailService {

    MailResponse sendMail(MailRequest mailRequest, String apiKey);

    Request request(MailRequest mailRequest, String apiKey);
}
