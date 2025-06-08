package com.assessment.adapter.starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@Component
@Profile({"dev", "test"})
public class JsonDataLoader implements CommandLineRunner {

    private final JsonService jsonService;
    private final ResourceLoader resourceLoader;

    public JsonDataLoader(JsonService jsonService, ResourceLoader resourceLoader) {
        this.jsonService = jsonService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] files = {
                "db/orders.json",
                "db/payments.json",
                "db/products.json"
        };

        for (String filePath : files) {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            if (resource.exists()) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                    String jsonContent = reader.lines().collect(Collectors.joining("\n"));
                    jsonService.saveJsonArrayAsIndividualFiles(jsonContent, resource.getFilename());
                    System.out.println("saved JSON: " + filePath);
                }
            } else {
                System.out.println("File not found: " + filePath);
            }
        }
    }
}