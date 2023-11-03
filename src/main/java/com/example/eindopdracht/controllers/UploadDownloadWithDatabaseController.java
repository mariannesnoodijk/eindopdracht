package com.example.eindopdracht.controllers;

import com.example.eindopdracht.FileUploadResponse.FileUploadResponse;
import com.example.eindopdracht.models.FileDocument;
import com.example.eindopdracht.services.DatabaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin
@RestController
public class UploadDownloadWithDatabaseController {

    private final DatabaseService databaseService;

    public UploadDownloadWithDatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("single/uploadDB")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {


        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        FileDocument fileDocument = databaseService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType );
    }

    //    get for single download
    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        FileDocument document = databaseService.singleFileDownload(fileName, request);


        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());
    }

    // TODO: DETERMINE WHETHER A MULTI UPLOAD IS POSSIBLE AND DESIRED
    //    post for multiple uploads to database
//    @PostMapping("/multiple/upload/db")
//    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {
//
//        if(files.length > 7) {
//            throw new RuntimeException("to many files selected");
//        }
//
//        return databaseService.createMultipleUpload(files);
//
//    }

    // TODO: HERACTIVEREN ALS MULTI UPLOAD TE DOEN IS
//    @GetMapping("/getAll/db")
//    public Collection<FileDocument> getAllFromDB(){
//        return databaseService.getALlFromDB();
//    }
}
