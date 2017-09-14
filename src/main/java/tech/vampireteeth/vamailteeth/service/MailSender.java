package tech.vampireteeth.vamailteeth.service;

import static tech.vampireteeth.vamailteeth.service.Constants.INTERNAL_SERVER_ERROR;
import static tech.vampireteeth.vamailteeth.service.Constants.SUCCESS;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import tech.vampireteeth.vamailteeth.model.MailRequest;
import tech.vampireteeth.vamailteeth.model.MailResponse;

@Service
public class MailSender {

    private static final Log LOG = LogFactory.getLog(MailSender.class);

    @Resource
    protected MailRequestValidateService mailRequestValidateService;

    public MailResponse send(MailService mailService, MailRequest mailRequest, String apiKey) {

        MailResponse mailResponse = new MailResponse();
        if (!mailRequestValidateService.validate(mailRequest, mailResponse)) {
            return mailResponse;
        }

        try {
            mailService.request(mailRequest, apiKey).execute();
            // No error means response returned with 200 status code
            mailResponse.setFailed(false);
            mailResponse.setMessage(SUCCESS);
        } catch (ClientProtocolException e) {
            LOG.error(e);
            mailResponse.setFailed(true);
            mailResponse.setMessage(e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            mailResponse.setFailed(true);
            mailResponse.setMessage(INTERNAL_SERVER_ERROR);
        }
        return mailResponse;
    }

}
