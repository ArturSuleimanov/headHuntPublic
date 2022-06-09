package ru.Artur.headhunt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.ResumeDto;
import ru.Artur.headhunt.repos.ResumeRepo;
import ru.Artur.headhunt.repos.UserRepo;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubscribeService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ResumeRepo resumeRepo;



    public void subscribe(User user, User curUser) {
        curUser.getSubscribers().add(user);
        userRepo.save(curUser);
    }

    public void unsubscribe(User user, User curUser) {
        System.out.println(curUser.getSubscribers());
        curUser.getSubscribers().remove(user);
        System.out.println(curUser.getSubscribers());

        userRepo.save(curUser);
    }



    public Page<ResumeDto> findByAuthorIdOrFilter(Pageable pageable, long id, String filter, User authUser) {
        User user = userRepo.findById(id);
        List<Long> ids= new ArrayList<>();
        for (User temUser: user.getSubscriptions()) {
            ids.add(temUser.getId());
        };
        if (filter != null && !filter.isEmpty()) {
            return resumeRepo.findByFilterIgnoreCaseAndAuthorId(filter, ids, pageable, authUser);
        }
        return resumeRepo.findByAuthorIdsDto(ids, pageable, authUser);
    }



}

