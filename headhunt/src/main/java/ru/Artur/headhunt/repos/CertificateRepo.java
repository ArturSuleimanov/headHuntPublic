package ru.Artur.headhunt.repos;

import org.springframework.data.repository.CrudRepository;
import ru.Artur.headhunt.domain.Certificate;

import java.util.List;


public interface CertificateRepo  extends CrudRepository<Certificate, Long> {
    void deleteById(Long id);
    Certificate findById(long id);


    List<Certificate> findByAuthorIdOrderByDateCreate(long id);



}


