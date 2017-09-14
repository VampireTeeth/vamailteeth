package tech.vampireteeth.vamailteeth.service;

import static tech.vampireteeth.vamailteeth.service.Constants.RECIPENT_IS_MISSING;
import static tech.vampireteeth.vamailteeth.service.Constants.SUBJECT_IS_MISSING;
import static tech.vampireteeth.vamailteeth.service.Constants.TEXT_CONTENT_IS_MISSING;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service
public class MailRequestValidateService {

    public boolean validate(MailRequest mailRequest, MailResponse mailResponse) {
        boolean failed = false;
        List<String> failMsg = new ArrayList<>();
        final String to = mailRequest.to();
        final String text = mailRequest.text();
        final String subject = mailRequest.subject();
        if (to == null || "".equals(to)) {
            failed = true;
            failMsg.add(RECIPENT_IS_MISSING);
        }
        if (text == null || "".equals(text)) {
            failed = true;
            failMsg.add(TEXT_CONTENT_IS_MISSING);
        }
        if (subject == null || "".equals(subject)) {
            failed = true;
            failMsg.add(SUBJECT_IS_MISSING);
        }
        mailResponse.setFailed(failed);
        mailResponse.setMessage(String.join("|", failMsg));
        return failed;
    }
}
