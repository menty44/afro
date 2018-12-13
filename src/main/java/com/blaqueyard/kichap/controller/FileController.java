package com.blaqueyard.kichap.controller;


/**
 * Created by admin on 11/3/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

import com.blaqueyard.kichap.model.DBFile;
import com.blaqueyard.kichap.payload.UploadFileResponse;
import com.blaqueyard.kichap.repository.DBFileRepository;
import com.blaqueyard.kichap.service.DBFileStorageService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    //@Autowired
//    private DBFileStorageService DBFileStorageService;

    @Autowired
    private DBFileStorageService dbFileStorageService;


    @Autowired
    private DBFileRepository dbFileRepository;


    @GetMapping("/myfiles")
    public Page<DBFile> getFiles(Pageable pageable) {
        return dbFileRepository.findAll(pageable);
    }

//    @GetMapping("/pdf", produces = "application/json")
    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public Document mypdf() throws IOException, DocumentException {

        Document document = new Document();
//
        return  document;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
//                .path(dbFile.getId())
                .path(String.valueOf(dbFile.getId()))
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
//        DBFile dbFile = DBFileStorageService.getFile(fileId);
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
