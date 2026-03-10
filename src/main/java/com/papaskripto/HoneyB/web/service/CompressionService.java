package com.papaskripto.HoneyB.web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CompressionService {

    public File compress (MultipartFile [] files) throws IOException {

        File zipFile = File.createTempFile("compressed", ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {

            for (MultipartFile file: files){

                ZipEntry zipEntry = new ZipEntry(file.getOriginalFilename());
                zos.putNextEntry(zipEntry);

                zos.write(file.getBytes());
                zos.closeEntry();
            }
        }

        return zipFile;
    }
}
