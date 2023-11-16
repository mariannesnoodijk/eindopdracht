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

    // TODO: DETERMINE WHETHER A MULTI UPLOAD IS POSSIBLE AND DESIRED
//    public List<FileUploadResponse> createMultipleUpload(MultipartFile[] files){
//        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
//        Arrays.stream(files).forEach(file -> {
//
//            String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//            FileDocument fileDocument = new FileDocument();
//            fileDocument.setFileName(name);
//            try {
//                fileDocument.setDocFile(file.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            doc.save(fileDocument);
//
////            next line makes url. example "http://localhost:8080/download/naam.jpg"
//            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(name).toUriString();
//
//            String contentType = file.getContentType();
//
//            FileUploadResponse response = new FileUploadResponse(name, contentType, url);
//
//            uploadResponseList.add(response);
//        });
//        return uploadResponseList;
//    }


    // TODO: GEBRUIK IK DIT???
//    public Resource downLoadFileDatabase(String fileName) {
//
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();
//
//        Resource resource;
//
//        try {
//            resource = new UrlResource(url);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Issue in reading the file", e);
//        }
//
//        if(resource.exists()&& resource.isReadable()) {
//            return resource;
//        } else {
//            throw new RuntimeException("the file doesn't exist or not readable");
//        }
//    }


}
