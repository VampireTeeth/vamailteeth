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

@Service("mailServiceMailgun")
public class MailServiceMailgun implements MailService {

    private static final Log LOG = LogFactory.getLog(MailServiceMailgun.class);

    @Resource
    private MailRequestValidateService mailRequestValidateService;

    @Resource
    private MailRequestTransformer mailRequestTransformer; 

    @Override
    public MailResponse sendMail(MailRequest mailRequest, String apiKey) {
        MailResponse mailResponse = new MailResponse();
        if (!mailRequestValidateService.validate(mailRequest, mailResponse)) {
            return mailResponse;
        }

        try {
            mailRequestTransformer.transform(this, mailRequest, apiKey).execute();
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
