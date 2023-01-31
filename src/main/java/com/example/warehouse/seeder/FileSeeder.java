package com.example.warehouse.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileSeeder implements CommandLineRunner {
    private final Path ProductImagesDir = Paths.get("./src/main/resources/static/images/ProductImages/");

    public void init() {
        try {
            Files.createDirectories(ProductImagesDir);
        } catch (
                IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void run(String... arg) throws Exception {
        init();
    }
}
