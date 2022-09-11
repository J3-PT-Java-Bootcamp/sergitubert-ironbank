package com.ironhack.sergitubertironbank.config.keycloak.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ironbank.code")
@Getter
@Setter
public class BankCodeProperty {

    /**
     * IP of foo service used to blah.
     */
    private String bankCode = "";

    // getter & setter
}