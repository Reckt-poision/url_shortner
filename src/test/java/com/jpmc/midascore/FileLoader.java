package com.jpmc.midascore;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class FileLoader {
    public String[] loadStrings(String path) {
        try {
            var resource = new ClassPathResource(path);
            var bytes = resource.getInputStream().readAllBytes();
            String contents = new String(bytes, StandardCharsets.UTF_8);
            contents = contents.replace("\r\n", "\n").replace("\r", "\n");
            return contents.split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
