package ru.Artur.headhunt.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import ru.Artur.headhunt.domain.util.MessageHelper;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity //this annotation tells spring that it's an entity which we need to save in database
public class Resume {
    @Id  // tells spring this field is unic id
    @GeneratedValue(strategy = GenerationType.AUTO) // tells spring to generate this field automatically
    private long id;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER) // everytime we get information about resume we also want to get user information
    @JoinColumn(name = "author_id")
    private User author;
    @Length(max = 55, message = "Не более 55 символов")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String firstname;
    @Length(max = 55, message = "Не более 55 символов")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String lastname;
    private Timestamp dateCreate;
    private Timestamp dateUpdate;
    private String filename;   //photo
    @Length(max = 1048, message = "Не более 1048 символов")
    private String aboutMe;
//    @NotNull(message="Поле не должно быть пустым!")
    private java.sql.Date birthday;
    @Length(max = 55, message = "Не более 55 цифр")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String mobileNumber;
    @Email(message="Некорректный Email")
    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 55, message = "Не более 55 символов")
    private String email;
    @Length(max = 255, message = "Не более 255 символов")
    private String hobby;
    @Length(max = 1048, message = "Не более 1048 символов")
    private String education;
    @Length(max = 1048, message = "Не более 1048 символов")
    private String workingExperience;
    @Length(max = 1048, message = "Не более 1048 символов")
    private String skills;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 55, message = "Не более 55 символов")
    private String profession;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resume_likes",
            joinColumns = { @JoinColumn(name = "resume_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> likes = new HashSet<>();





    public Resume() {   // also create empty constructor so that spring can create user objects, always create it when using @Entity
    }

    public Resume(
            String firstname,
            String lastname,
            String aboutMe,
            java.sql.Date birthday,
            String mobileNumber,
            String email,
            String hobby,
            String education,
            String workingExperience,
            String skills,
            String profession,
            User user
    ) {
        this.firstname = firstname;
        this.lastname = lastname;

        this.aboutMe = aboutMe;
        this.birthday = birthday;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.hobby = hobby;
        this.education = education;
        this.workingExperience = workingExperience;
        this.skills = skills;
        this.profession = profession;
        this.author = user;
        java.util.Date utilDate = new java.util.Date();
        this.dateCreate = new Timestamp(utilDate.getTime());
        this.dateUpdate = new Timestamp(utilDate.getTime());
    }

    public void updateDate() {
        java.util.Date utilDate = new java.util.Date();
        this.dateUpdate = new Timestamp(utilDate.getTime());
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }



    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(String workingExperience) {
        this.workingExperience = workingExperience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void creationDate() {
        java.util.Date utilDate = new java.util.Date();
        this.dateCreate = new Timestamp(utilDate.getTime());
        this.dateUpdate = new Timestamp(utilDate.getTime());
    }

    public String getAurhorName() {
        return MessageHelper.getAuthorName(author);
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        return id == resume.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
