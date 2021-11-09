package de.db.webapp.repositories.entities;

import de.db.webapp.repositories.PersonCustomRepository;

public class PersonCustomRepositoryImpl implements PersonCustomRepository {
    @Override
    public boolean ganzDollKomplizierteMethode() {
        System.out.println("Yepp");
        return false;
    }
}
