package tech.vampireteeth.vamailteeth.model;

import java.util.ArrayList;
import java.util.List;

public class MailRequest {

    private static final String DELIMITER = ";";
    private List<String> tos = new ArrayList<>();
    private List<String> ccs = new ArrayList<>();
    private List<String> bccs = new ArrayList<>();
    private String subject = null;
    private String text = null;

    public void setTos(List<String> tos) {
        this.tos = tos;
    }

    public void setCcs(List<String> ccs) {
        this.ccs = ccs;
    }

    public void setBccs(List<String> bccs) {
        this.bccs = bccs;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String to() {
        return tos.isEmpty() ? null : String.join(DELIMITER, tos);
    }

    public String cc() {
        return ccs.isEmpty() ? null : String.join(DELIMITER, ccs);
    }

    public String bcc() {
        return bccs.isEmpty() ? null : String.join(DELIMITER, bccs);
    }

    public String text() {
        return text;
    }

    public String subject() {
        return subject;
    }

}
