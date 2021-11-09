package de.db.webapp.spielwiese;

import de.db.webapp.repositories.entities.PersonEntity;
import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.spielwiese.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.beans.beancontext.BeanContext;

//@Component
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Demo {

    @Autowired
    private PersonenRepository repo;
    private final Translator translator;
    public Demo(final Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Hallo"));
    }

    @PostConstruct
    public void foo() {
        repo.ganzDollKomplizierteMethode();

        PersonEntity p = PersonEntity.builder().id("123").vorname("John").nachname("Doe").build();
        repo.save(p);
        System.out.println(translator.translate("Hier ist Foo"));
    }
}
