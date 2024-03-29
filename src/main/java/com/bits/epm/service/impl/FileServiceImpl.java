package com.bits.epm.service.impl;

import com.bits.epm.service.FileService;
import com.bits.epm.utils.Constants;
import com.bits.epm.utils.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


@Service("FileService")
public class FileServiceImpl implements FileService {

    @Override
    public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = getUploadPath();

        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path filePath = getUploadPath().resolve(fileName).normalize().toAbsolutePath();

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return Files.exists(filePath) ? filePath.toString() : null;

        }catch (IOException ioException){
            throw  new IOException("File save failed: "+fileName, ioException);
        }

    }

    @Override
    public Path getUploadPath(){
        return Paths.get(Constants.File.UPLOAD_DIR);
    }

    @Override
    public String getUploadDir(){
        return Constants.File.UPLOAD_DIR;
    }

    @Override
    public Path getFilePath(String filename){
        return getUploadPath().resolve(filename);
    }


    @Override
    public Stream<Path> loadAll() throws IOException {
        try {

            Path uploadPath = getUploadPath();

            return Files.walk(uploadPath, 1)
                    .filter(path -> !path.equals(uploadPath))
                    .map(uploadPath::relativize);
        } catch (IOException e) {
            throw new IOException("Failed to read stored files", e);
        }

    }


    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = getFilePath(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw ExceptionUtils.notFoundException("Could not find file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw ExceptionUtils.notFoundException("Could not find file: " + filename, e);
        }
    }

    @Override
    public boolean deleteFile(String filename) {
//        FileSystemUtils.deleteRecursively(getFilePath(filename).toFile());

        if (StringUtils.isBlank(filename)) {
            return false;
        }

        try {
            Path filePath =  getFilePath(filename);

            if (Files.exists(filePath)){
                Files.delete(filePath);
                return true;
            }
        }catch (IOException ignored){
        }

        return false;
    }

}
