package br.com.leucotron.livre.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RestException extends RuntimeException {

    @Getter
    private String message;

    @Getter
    private Object[] args;

    public RestException() {
    }

    public RestException(String message) {
        this.message = message;
    }

    /**
     * getArgs
     */
    public Object[] getArgs() {
        return this.args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}