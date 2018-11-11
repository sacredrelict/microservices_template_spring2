package com.github.sacredrelict.api.common.component.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Message component.
 * All messages read from messages.properties file.
 */
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    /**
     * Init the component with locale.
     */
    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    /**
     * Base method, that return message by code from file.
     * @param code - code in messages.properties file.
     * @return message value.
     */
    public String get(String code) {
        return accessor.getMessage(code);
    }

}
