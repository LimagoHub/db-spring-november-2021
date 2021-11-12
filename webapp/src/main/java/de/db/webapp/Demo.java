package de.db.webapp;


import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Demo {

   private final Map<String,String> demoValues;


    public Demo(Map<String, String> demoValues) {
        this.demoValues = demoValues;
        System.out.println(demoValues);
    }
}
