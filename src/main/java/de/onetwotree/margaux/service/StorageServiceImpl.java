package de.onetwotree.margaux.service;

import de.onetwotree.margaux.appConfig.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by SebUndefined on 21/11/17.
 */
@Service("StorageService")
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());

    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String store(MultipartFile file) {
        //getting the extension
        System.out.println(file.toString());
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String[] fileFrags = file.getOriginalFilename().split("\\.");
        String extension = fileFrags[fileFrags.length-1];
        //Replacing name by current time
        filename = LocalDateTime.now().toString();
        filename = StringUtils.replace(filename, ":", "");
        filename = StringUtils.replace(filename, ".", "");
        //Adding a random number
        int random = (int) (Math.random() * (1024 - 10)) + 10;
        filename = filename + "-" + random + "." + extension;
        filename = StringUtils.cleanPath(filename);
        //Saving the file
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                //throw new StorageFileNotFoundException(
                  //      "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            //throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
