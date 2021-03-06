package br.com.leucotron.livre.util;

import br.com.leucotron.livre.core.dto.ModelDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class RestMessage extends ModelDTO{

    private String message;
    private List<String> messages;

    /**
     * Constructor.
     *
     * @param messages
     * 		Messages Code.
     */
    public RestMessage(List<String> messages) {
        if (messages.size() > 0) {
            this.message = messages.get(0);
        }
        this.messages = messages;
    }

    /**
     * Constructor.
     *
     * @param message
     * 		Message Code.
     */
    public RestMessage(String message) {
        this.message = message;
    }


}