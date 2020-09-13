package ru.omsu.imit.main.server.config;

import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig {
    public MyResourceConfig() {
        packages("ru.omsu.imit.main.resources",
                "ru.omsu.imit.main.rest.mappers");
    }
}
