package de.db.webapp.spielwiese;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
@Profile("test")
public class ToUpperImpl implements  Translator{
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }
}
