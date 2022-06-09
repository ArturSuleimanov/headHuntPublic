package ru.Artur.headhunt.repos;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.Artur.headhunt.domain.Resume;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.dto.ResumeDto;

import java.util.List;


// this repo helps us to do selection from user table
public interface ResumeRepo extends CrudRepository<Resume, Long> {



    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    "   r, " +
                    "   count(rl), " +
                    "   sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where upper(r.lastname) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.firstname) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.profession) like upper(concat('%', :filter, '%'))" +
                    "or upper (r.workingExperience) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.skills) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.education) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.hobby) like upper(concat('%', :filter, '%'))" +
                    "group by r"
    )
    Page<ResumeDto> findByFilterIgnoreCase(@Param("filter") String filter, Pageable pageable, @Param("user") User user);





    @Query("select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Resume m left join m.likes ml " +
            "group by m")
    Page<ResumeDto> findAll(Pageable pageable, @Param("user") User user);

    Resume findById(long id);

    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    "   r, " +
                    "   count(rl), " +
                    "   sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where r.id = :id " +
                    "group by r"
    )
    ResumeDto findByIdDto(@Param("id") long id, @Param("user") User user);


    void deleteById(long id);



    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    "   r, " +
                    "   count(rl), " +
                    "   sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where r.author.id = :id " +
                    "group by r"
    )
    List<ResumeDto> findByAuthorIdDto(@Param("id") long id, @Param("user") User user);


    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    "   r, " +
                    "   count(rl), " +
                    "   sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where r.author.id in :ids and (upper(r.lastname) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.firstname) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.profession) like upper(concat('%', :filter, '%'))" +
                    "or upper (r.workingExperience) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.skills) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.education) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.hobby) like upper(concat('%', :filter, '%')))" +
                    "group by r"
    )
    Page<ResumeDto> findByFilterIgnoreCaseAndAuthorId(
            @Param("filter") String filter,
            @Param("ids") List<Long> ids,
            Pageable pageable,
            @Param("user") User user
    );


    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    " r, " +
                    " count(rl), " +
                    " sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where r.author.id in :ids " +
                    "group by r"
    )
    Page<ResumeDto> findByAuthorIdsDto(@Param("ids") List<Long> ids, Pageable pageable, @Param("user") User user);


    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    " r, " +
                    " count(rl), " +
                    " sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where :user in r.likes " +
                    "group by r"
    )
    Page<ResumeDto> findByAuthorLikesDto(Pageable pageable, @Param("user") User user);



    List<Resume> findAll();





    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    "   r, " +
                    "   count(rl), " +
                    "   sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where r.id in :ids and (upper(r.lastname) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.firstname) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.profession) like upper(concat('%', :filter, '%'))" +
                    "or upper (r.workingExperience) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.skills) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.education) like upper(concat('%', :filter, '%'))" +
                    "or upper(r.hobby) like upper(concat('%', :filter, '%')))" +
                    "group by r"
    )
    Page<Resume> findByIdsAndFilter(
            @Param("filter") String filter,
            @Param("ids") List<Long> ids,
            Pageable pageable,
            @Param("user") User user
    );


    @Query(                  //my own query
            "select new ru.Artur.headhunt.domain.dto.ResumeDto(" +
                    "   r, " +
                    "   count(rl), " +
                    "   sum(case when rl = :user then 1 else 0 end) > 0" +
                    ") " +
                    "from Resume r left join r.likes rl " +
                    "where r.id in :ids " +
                    "group by r"
    )
    Page<Resume> findByIds(
            @Param("ids") List<Long> ids,
            Pageable pageable,
            @Param("user") User user
    );
}

