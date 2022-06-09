package ru.Artur.headhunt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.Artur.headhunt.domain.Certificate;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.repos.CertificateRepo;
import ru.Artur.headhunt.service.util.FileSaver;

import java.io.IOException;

@Service
public class CertificateService {

    @Value("${upload.path}")    // spring takes property upload.path and put it into variable uploadPath
    private String uploadPath;

    @Autowired
    private CertificateRepo certificateRepo;


    public Iterable<Certificate> findAll() {
        return certificateRepo.findAll();
    }

    public void saveCertificate(User user, MultipartFile file, Certificate certificate) throws IOException {
        certificate.setAuthor(user);


        certificate.setFilename(FileSaver.createFileAndFilename(uploadPath, file));
        //also visit mvc config to share this file
        certificate.creationDate();
        certificateRepo.save(certificate);    //spring has this method from box, read documentation to know more
    }

    public Object findByAuthorIdOrderByDateCreate(long id) {
        return certificateRepo.findByAuthorIdOrderByDateCreate(id);
    }

    public boolean deleteById(long id, User user) {
        Certificate certificate = certificateRepo.findById(id);
        if(user.getId() != certificate.getAuthor().getId()) return false;
        certificateRepo.deleteById(id);
        return true;
    }

    public Certificate findById(long id) {
        return certificateRepo.findById(id);
    }


    public boolean editCertificate(long id, User user, Certificate certificate) {
        Certificate certificateFromDB = certificateRepo.findById(id);
        if(user.getId() != certificateFromDB.getAuthor().getId()) return false;
        certificateFromDB.setTitle(certificate.getTitle());
        certificateRepo.save(certificateFromDB);
        return true;
    }
}
