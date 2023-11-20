package com.example.demo.utils.mediaUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utils.generatorUtil.FileNameGenerator;

public class MediaStorageUtil {
    private static final String imageBasePath = "resources/assets/documents/";
    public static String saveImage(MultipartFile file) {
        String fileName = FileNameGenerator.generate();
        File savedImage = new File(imageBasePath + fileName + ".png");
        try {
            savedImage.getParentFile().mkdirs();
            savedImage.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(savedImage);
            outputStream.write(file.getBytes());
            outputStream.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
