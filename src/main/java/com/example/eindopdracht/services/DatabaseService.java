package com.example.eindopdracht.services;

import com.example.eindopdracht.models.FileDocument;
import com.example.eindopdracht.repositories.DocFileRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class DatabaseService {

    private final DocFileRepository doc;

    public DatabaseService(DocFileRepository doc){
        this.doc = doc;
    }

//    public Collection<FileDocument> getALlFromDB() {
//        return doc.findAll();
//    }

    public FileDocument uploadFileDocument(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(name);
        fileDocument.setDocFile(file.getBytes());

        doc.save(fileDocument);

        return fileDocument;

    }

    public FileDocument singleFileDownload(String fileName, HttpServletRequest request){

        FileDocument document = doc.findByFileName(fileName);

//        this mediaType decides witch type you accept if you only accept 1 type
//        MediaType contentType = MediaType.IMAGE_JPEG;
//        this is going to accept multiple types

        String mimeType = request.getServletContext().getMimeType(document.getFileName());

//        for download attachment use next line
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return document;

    }
}
