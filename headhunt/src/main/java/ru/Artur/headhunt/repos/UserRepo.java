package ru.Artur.headhunt.repos;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.Artur.headhunt.domain.User;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);

    User findById(long id);

    void deleteById(long id);


    @Query(                  //my own query
            "select u from User u " +
                    "where upper(u.username) like upper(concat('%', ?1, '%'))"
    )
    Page<User> findByUsernameIgnoreCase(String filter, Pageable pageable);

    Page<User> findAll(Pageable pageable);
}