package com.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileService {

    public static final String UPLOAD_DIR = "uploads";
    private static final List<String> AllowedTypes = Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp");

    public String store(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        String contentType = file.getContentType();
        if (contentType == null || !AllowedTypes.contains(contentType)) {
            throw new IllegalArgumentException("El formato de archivo es inválido");
        }
        try {
            Path dir = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            Files.createDirectories(dir); // crea la carpeta si no existe
            String original = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
            String ext = "";
            int dot = original.lastIndexOf('.');
            if (dot > 0)
                ext = original.substring(dot);
            String filename = UUID.randomUUID() + ext;
            Path target = dir.resolve(filename);
            file.transferTo(target);
            return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el archivo", e);
        }
    }

}