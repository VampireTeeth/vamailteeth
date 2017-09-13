package tech.vampireteeth.vamailteeth.model;

public class MailResponse {

    private String message;
    private boolean isFailed;

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed(boolean isFailed) {
        this.isFailed = isFailed;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
