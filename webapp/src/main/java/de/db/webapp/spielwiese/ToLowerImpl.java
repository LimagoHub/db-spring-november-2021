package de.db.webapp.spielwiese;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class ToLowerImpl implements  Translator{
    @Override
    public String translate(String message) {
        return message.toLowerCase();
    }
}
