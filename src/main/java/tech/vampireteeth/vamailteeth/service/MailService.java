package tech.vampireteeth.vamailteeth.service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

public interface MailService {

    MailResponse sendMail(MailRequest mailRequest);
}
