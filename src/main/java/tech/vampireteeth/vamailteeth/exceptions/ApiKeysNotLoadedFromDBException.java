package tech.vampireteeth.vamailteeth.exceptions;

public class ApiKeysNotLoadedFromDBException extends Exception {

    private static final long serialVersionUID = 1L;

    public ApiKeysNotLoadedFromDBException() {
        super();
    }

    public ApiKeysNotLoadedFromDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiKeysNotLoadedFromDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiKeysNotLoadedFromDBException(String message) {
        super(message);
    }

    public ApiKeysNotLoadedFromDBException(Throwable cause) {
        super(cause);
    }
    
    
    

}
