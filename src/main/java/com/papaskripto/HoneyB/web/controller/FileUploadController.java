package com.papaskripto.HoneyB.web.controller;

import com.papaskripto.HoneyB.web.service.CompressionService;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping ("/compress")
public class FileUploadController {

    private final CompressionService compressionService;

    public FileUploadController (CompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @PostMapping
    public ResponseEntity<Resource> compressFiles (
            @RequestParam ("files")MultipartFile [] files) throws IOException {

        File zipFile = compressionService.compress (files);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=" + zipFile.getName ())
        .contentType (MediaType.APPLICATION_OCTET_STREAM)
                .body (resource);
    }
}
