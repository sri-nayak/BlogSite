package com.prodevans.BlogSite.controller;

import com.prodevans.BlogSite.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    private FileUploadService fileUploadService;
    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestBody MultipartFile file) {
        System.out.println(file.getSize());
        String message = "";
        try {

            message=fileUploadService.save(file);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File douldn't upload");
        }
    }

    /**
     *
     * @param filename
     * @return
     */
    @GetMapping("/getFile/{filename:.+}")
    @ResponseBody
    public ResponseEntity getFile(@PathVariable String filename) {
       try {
           Resource file = fileUploadService.load(filename);
           return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping("/getAllFile")
    public ResponseEntity getAllFiles(){
        try {
            return  new ResponseEntity<>(fileUploadService.loadAll(),HttpStatus.FOUND);
        }catch (IOException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
