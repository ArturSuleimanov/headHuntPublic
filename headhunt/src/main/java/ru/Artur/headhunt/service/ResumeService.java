package ru.Artur.headhunt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.Artur.headhunt.domain.Resume;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.ResumeDto;
import ru.Artur.headhunt.repos.ResumeRepo;
import ru.Artur.headhunt.repos.UserRepo;
import ru.Artur.headhunt.service.util.FileSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private UserRepo userRepo;

    @Value("${upload.path}")    // spring takes property upload.path and put it into variable uploadPath
    private String uploadPath;

    public void saveResume(User user, MultipartFile file, Resume resume) throws IOException {
        if(resume.getAboutMe().isEmpty() || resume.getAboutMe()==null) resume.setAboutMe(null);
        if(resume.getHobby().isEmpty() || resume.getHobby()==null) resume.setHobby(null);
        if(resume.getEducation().isEmpty() || resume.getEducation()==null) resume.setEducation(null);
        if(resume.getWorkingExperience().isEmpty() || resume.getWorkingExperience()==null) resume.setWorkingExperience(null);
        if(resume.getSkills().isEmpty() || resume.getSkills()==null)resume.setSkills(null);
        resume.creationDate();
        resume.setAuthor(user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            resume.setFilename(FileSaver.createFileAndFilename(uploadPath, file));
            //also visit mvc config to share this file
        } else { resume.setFilename("default.jpg");};

        resumeRepo.save(resume);    //spring has this method from box, read documentation to know more
    }


    public Resume findResumeById (long id) {
        return resumeRepo.findById(id);
    }


    public boolean editResume(
            long id,
            User user,
            Resume resume,
            MultipartFile file
    ) throws IOException {
        String filename;
        Resume resumeFromDB = resumeRepo.findById(id);
        if(user.getId() != resumeFromDB.getAuthor().getId()) return false;
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            filename = FileSaver.createFileAndFilename(uploadPath, file);
            if (!resumeFromDB.getFilename().equals("default.jpg")) {
                File deleteFile = new File (uploadPath + "/" + resumeFromDB.getFilename());
                deleteFile.delete();
            }
            //also visit mvc config to share this file
        } else { filename = resumeFromDB.getFilename();};

        resumeFromDB.setFirstname(resume.getFirstname());
        resumeFromDB.setLastname(resume.getLastname());
        resumeFromDB.setEmail(resume.getEmail());
        resumeFromDB.setMobileNumber(resume.getMobileNumber());
        resumeFromDB.setBirthday(resume.getBirthday());
        resumeFromDB.setProfession(resume.getProfession());
        resumeFromDB.setFilename(filename);
        resumeFromDB.setAboutMe((resume.getAboutMe().isEmpty() || resume.getAboutMe() == null)?null:resume.getAboutMe());
        resumeFromDB.setHobby((resume.getHobby().isEmpty() || resume.getHobby() == null)?null:resume.getHobby());
        resumeFromDB.setEducation((resume.getEducation().isEmpty() || resume.getEducation() == null)?null:resume.getEducation());
        resumeFromDB.setWorkingExperience((resume.getWorkingExperience().isEmpty() || resume.getWorkingExperience() == null)?null:resume.getWorkingExperience());
        resumeFromDB.setSkills((resume.getSkills().isEmpty() || resume.getSkills() == null)?null:resume.getSkills());
        resumeFromDB.updateDate();
        resumeRepo.save(resumeFromDB);
        return false;

    }

    public void deleteById(long id) {
        resumeRepo.deleteById(id);
    }



    public void resumeUpdate(User user, long id) {
        Resume resume = resumeRepo.findById(id);
        if(user.getId() != resume.getAuthor().getId()) return;
        resume.updateDate();
        resumeRepo.save(resume);

    }


    public ResumeDto findResumeByIdDto(long id, User user) {
        return resumeRepo.findByIdDto(id, user);
    }

    public void save(Resume resume) {
        resumeRepo.save(resume);
    }


    public Page<Resume> chosen(User authUser, Pageable pageable, String filter) {
        List<Long> ids = new ArrayList<>();
        for(Resume resume: authUser.getLikes()) {
            ids.add(resume.getId());
        }
        if (filter != null && !filter.isEmpty()) {
            return resumeRepo.findByIdsAndFilter(filter, ids, pageable, authUser);
        }
        return resumeRepo.findByIds(ids, pageable, authUser);
    }
}
