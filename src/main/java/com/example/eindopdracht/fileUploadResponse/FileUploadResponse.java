package com.example.eindopdracht.fileUploadResponse;

import lombok.Data;

@Data
public class FileUploadResponse {

    String fileName;
    String contentType;
    String url;

    // Constructor to initialize the response with file details
    public FileUploadResponse(String fileName, String url, String contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
    }
}
