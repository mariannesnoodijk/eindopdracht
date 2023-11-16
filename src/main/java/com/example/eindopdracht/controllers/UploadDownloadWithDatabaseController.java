package com.example.eindopdracht.controllers;

import com.example.eindopdracht.fileUploadResponse.FileUploadResponse;
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

    // Constructor to inject the DatabaseService dependency
    public UploadDownloadWithDatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // Endpoint for single file upload to the database
    @PostMapping("single/uploadDB")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // Upload the file to the database and retrieve the associated FileDocument
        FileDocument fileDocument = databaseService.uploadFileDocument(file);

        // Build the download URL for the uploaded file
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFromDB/")
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();
        // Get the content type of the uploaded file
        String contentType = file.getContentType();
        // Return the response with the uploaded file details
        return new FileUploadResponse(fileDocument.getFileName(), url, contentType);
    }

    // Endpoint for downloading a single file from the database
    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        // Download the file from the database using the specified file name
        FileDocument document = databaseService.singleFileDownload(fileName, request);

        // Return the file in the response with the appropriate headers
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName())
                .body(document.getDocFile());
    }

    // TODO: DETERMINE WHETHER A MULTI UPLOAD IS POSSIBLE AND DESIRED
    //    post for multiple uploads to database
//    // Endpoint for multiple file upload to the database
//    @PostMapping("/multiple/uploadDB")
//    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {
//
//        if(files.length > 7) {
//            throw new RuntimeException("too many files selected");
//        }
//
//        return databaseService.createMultipleUpload(files);
//
//    }

    // TODO: HERACTIVEREN ALS MULTI UPLOAD TE DOEN IS
//    // Endpoint for downloading all files from the database
//    @GetMapping("/downloadAllFromDB")
//    public Collection<FileDocument> getAllFromDB(){
//        return databaseService.getALlFromDB();
//    }
}
