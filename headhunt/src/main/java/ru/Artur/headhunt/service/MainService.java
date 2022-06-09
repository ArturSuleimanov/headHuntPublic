package ru.Artur.headhunt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.ResumeDto;
import ru.Artur.headhunt.repos.ResumeRepo;

import javax.persistence.EntityManager;

@Service
public class MainService {

    @Autowired
    private EntityManager em;


    @Autowired
    private ResumeRepo resumeRepo;    //this object helps us to do selection from resume table


    public Page<ResumeDto> findResume(
            String filter,
            Pageable pageable,
            User user
    ) {
        if (filter != null && !filter.isEmpty()) {
            return resumeRepo.findByFilterIgnoreCase(filter, pageable, user);
        } else {
            return resumeRepo.findAll(pageable, user);
        }
    }
}
