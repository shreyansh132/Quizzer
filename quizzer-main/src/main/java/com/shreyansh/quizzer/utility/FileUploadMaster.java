package com.shreyansh.quizzer.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class FileUploadMaster {

    public final String UPLOAD_DIR = "C:\\Users\\Shreyansh Jain\\Desktop\\Quizzer-Files";

    public String uploadFile(MultipartFile multipartFile)
    {
        String fileUrl = "";

        try{
            InputStream inputStream = multipartFile.getInputStream();
            byte fileData[] = new byte[inputStream.available()];
            inputStream.read(fileData);

            FileOutputStream fileOutputStream = new FileOutputStream
                    (UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename());

            fileOutputStream.write(fileData);
            fileOutputStream.flush();
            fileOutputStream.close();
            //f = true;
            //Alternative way
            // Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR), StandardCopyOption.REPLACE_EXISTING);

             fileUrl = UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename()+"";
        }
        catch (Exception e){

        }
        return fileUrl;
    }



}
