package org.alonst.configuration;

public class ConfigMapsLoadingException extends RuntimeException {
    public ConfigMapsLoadingException(String message) {
        super(message);
    }

    public ConfigMapsLoadingException(Throwable cause) {
        super(cause);
    }
}
