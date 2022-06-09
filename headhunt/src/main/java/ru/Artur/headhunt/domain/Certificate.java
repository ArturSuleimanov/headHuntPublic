package ru.Artur.headhunt.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;



@Entity
public class Certificate {



    @Id  // tells spring this field is unic id
    @GeneratedValue(strategy = GenerationType.AUTO) // tells spring to generate this field automatically
    private long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER) // everytime we get information about resume we also want to get user information
    @JoinColumn(name = "author_id")
    private User author;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 255, message = "Название слишком длинное! Не более 255 символов")
    private String title;

    private String filename;

    private Timestamp dateCreate;

    public Certificate() {};

    public Certificate(String title , User author) {
        this.title = title;
        this.author = author;
        java.util.Date utilDate = new java.util.Date();
        this.dateCreate = new Timestamp(utilDate.getTime());
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void creationDate() {
        java.util.Date utilDate = new java.util.Date();
        this.dateCreate = new Timestamp(utilDate.getTime());
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }
}
