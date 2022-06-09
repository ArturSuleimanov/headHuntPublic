package ru.Artur.headhunt.service.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public abstract class FileSaver {




    public static String createFileAndFilename(String uploadPath, MultipartFile file) throws IOException {
        System.out.println(uploadPath);

        String resultFilename = null;
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            Date curDate = new Date();
            SimpleDateFormat getDate = new SimpleDateFormat("yyyy.MM.dd");
            File uploadDir = new File(uploadPath + "/" + getDate.format(curDate));

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();    // makes upload dir if it doesn't exist
            }

            String uuidFile = UUID.randomUUID().toString();    // this is how we generate filename
            resultFilename = getDate.format(curDate) + "/" + uuidFile + "." + Instant.now().toString();
            file.transferTo(new File(uploadPath + "/" + resultFilename));    //downloading file


        }
        return resultFilename;
    }
}
