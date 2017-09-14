package tech.vampireteeth.vamailteeth;

public class ApiKeys {

    private final String mailgun;
    private final String sendgrid;

    public ApiKeys(String mailgun, String sendgrid) {
        this.mailgun = mailgun;
        this.sendgrid = sendgrid;
    }

    public final String getMailgun() {
        return mailgun;
    }

    public final String getSendgrid() {
        return sendgrid;
    }



}
