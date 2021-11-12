package de.db.webapp;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:demo.properties")
@ConfigurationProperties(prefix = "demo")
@Setter
public class DemoConfig {

    private String gruss;
    private String stadt;

    @Bean
    public Map<String, String> getDemoValues() {
        Map<String, String> result = new HashMap<>();
        result.put("gruss",gruss);
        result.put("stadt",stadt);
        return result;
    }
}
