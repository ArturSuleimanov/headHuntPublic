package ru.Artur.headhunt.domain.dto;

import ru.Artur.headhunt.domain.Resume;
import ru.Artur.headhunt.domain.User;
import ru.Artur.headhunt.domain.util.MessageHelper;

import java.sql.Date;
import java.sql.Timestamp;

public class ResumeDto {
    private long id;
    private User author;
    private String firstname;
    private String lastname;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
    private String filename;   //photo
    private String aboutMe;
    private java.sql.Date birthday;
    private String mobileNumber;
    private String email;
    private String hobby;
    private String education;
    private String workingExperience;
    private String skills;
    private String profession;
    private long likes;
    private Boolean meLiked;


    public ResumeDto(Resume resume, long likes, Boolean meLiked) {
        this.id = resume.getId();
        this.author = resume.getAuthor();
        this.firstname = resume.getFirstname();
        this.lastname = resume.getLastname();
        this.dateCreate = resume.getDateCreate();
        this.dateUpdate = resume.getDateUpdate();
        this.filename = resume.getFilename();
        this.aboutMe = resume.getAboutMe();
        this.birthday = resume.getBirthday();
        this.mobileNumber = resume.getMobileNumber();
        this.email = resume.getEmail();
        this.hobby = resume.getHobby();
        this.education = resume.getEducation();
        this.workingExperience = resume.getWorkingExperience();
        this.skills = resume.getSkills();
        this.profession = resume.getProfession();
        this.likes = likes;
        this.meLiked = meLiked;
    }



    public long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public String getFilename() {
        return filename;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getHobby() {
        return hobby;
    }

    public String getEducation() {
        return education;
    }

    public String getWorkingExperience() {
        return workingExperience;
    }

    public String getSkills() {
        return skills;
    }

    public String getProfession() {
        return profession;
    }

    public long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    public String getAurhorName() {
        return MessageHelper.getAuthorName(author);
    }

    @Override
    public String toString() {
        return "ResumeDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
