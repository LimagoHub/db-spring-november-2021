package de.db.webapp.application;


import de.db.webapp.repositories.PersonenRepository;
import de.db.webapp.services.PersonenService;
import de.db.webapp.services.impl.PersonenServiceImpl;
import de.db.webapp.services.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@Configuration
@EnableAsync
public class PersonConfig {

    @Bean
    @Qualifier("antipathen")
    public List<String> antipathen() {
        return List.of("Attila","Peter","Paul","Mary");
    }
    @Bean
    @Qualifier("fruits")
    public List<String> fruits() {
        return List.of("Apple","Cherry","Strawberry","Raspberry");
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint injectionPoint){
        return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
    }


    @Bean
    public PersonenService createPersonenService(final PersonenRepository repo, final PersonMapper mapper, @Qualifier("antipathen") final List<String> antipathen){
        return new PersonenServiceImpl(repo,mapper,antipathen);
    }


}
