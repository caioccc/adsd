package br.com.leucotron.livre.core.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RestException extends RuntimeException {
    @Getter
    private String message;

    @Getter
    private Object[] args;

    /**
     * getArgs
     */
    public Object[] getArgs() {
        return this.args;
    }
}