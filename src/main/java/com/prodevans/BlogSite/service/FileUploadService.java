package com.prodevans.BlogSite.service;

import com.prodevans.BlogSite.exception.CustomException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    public String save(MultipartFile file);

    public Resource load(String filename);

    public void deleteFile(String filename) throws IOException, CustomException;

    public List loadAll() throws IOException;

}
