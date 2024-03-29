package com.bits.epm.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;


public interface FileService {

    String saveFile(String fileName, MultipartFile multipartFile) throws IOException;

    Path getUploadPath();

    String getUploadDir();

    Path getFilePath(String filename);

    Stream<Path> loadAll() throws IOException;

    Resource loadAsResource(String filename);

    boolean deleteFile(String filename);

}
