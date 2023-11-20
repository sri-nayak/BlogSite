package com.prodevans.BlogSite.service.impl;

import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private Path path;

    /**
     * @param filePath
     */

    public FileUploadServiceImpl(@Value("${file.store.path:src/main/resources/document}") String filePath) {
        System.out.println("excuting");
        this.path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println(e.getCause() + "Try to re run the program error creating file");
            }
        }
    }

    /**
     * @param file
     * @return
     */

    @Override
    public String save(MultipartFile file) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            int value = (int) (Math.random() * 1000);
            String fileName = file.getOriginalFilename();
            fileName = fileName.replaceAll(" ", "-");
            fileName = fileName.substring(0, fileName.indexOf(".")).concat(String.valueOf(value)).concat(fileName.substring(fileName.indexOf("."), fileName.length()));
            File file1 = new File(fileName);
            Files.copy(file.getInputStream(), this.path.resolve(fileName));
            return fileName;
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public Resource load(String filename) {
        try {
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    /**
     * @param fileName
     * @throws CustomException
     */
    @Override
    public void deleteFile(String fileName) throws CustomException {
        try {
            Path file = path.resolve(fileName);
            Files.delete(path);
        } catch (IOException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List loadAll() throws IOException {
        File file1=new File(path.toUri());

        List files=Files.list(path).toList();
        List fileName=files.stream().map(file->path.getFileName()).toList();
        return Arrays.stream(file1.list()).toList() ;
    }
}
