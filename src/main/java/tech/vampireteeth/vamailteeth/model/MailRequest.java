package tech.vampireteeth.vamailteeth.model;

import java.util.ArrayList;
import java.util.List;

public class MailRequest {

    private static final String DELIMITER = ";";
    private List<String> tos = new ArrayList<>();
    private List<String> ccs = new ArrayList<>();
    private List<String> bccs = new ArrayList<>();
    private String text = null;

    public MailRequest to(String to) {
        tos.add(to.trim());
        return this;
    }

    public MailRequest cc(String cc) {
        ccs.add(cc.trim());
        return this;
    }

    public MailRequest bcc(String bcc) {
        bccs.add(bcc.trim());
        return this;
    }

    public MailRequest text(String text) {
        this.text = text;
        return this;
    }

    public String to() {
        return String.join(DELIMITER, tos);
    }
    
    public String cc() {
        return String.join(DELIMITER, ccs);
    }
    
    public String bcc() {
        return String.join(DELIMITER, bccs);
    }
    
    public String text() {
        return text;
    }
}
