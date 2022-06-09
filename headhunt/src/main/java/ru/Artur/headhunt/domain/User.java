package ru.Artur.headhunt.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")   //need to change table name because postgres doesn't like tables wich are equals with key words
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 55, message = "Не более 55 символов")
    private String username;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 55, message = "Не более 55 символов")
    private String firstname;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 55, message = "Не более 55 символов")
    private String lastname;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String password;
    private boolean active;
    @Email(message="Некорректный Email")
    @NotBlank(message = "Поле не должно быть пустым!")
    @Length(max = 55, message = "Не более 55 символов")
    private String email;
    private String activationCode;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)   // lets us preserve enum without creating new table
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))    //this means our roles preserves in table user_role which hasn't got any description
    @Enumerated(EnumType.STRING) //this means we want to preserve enum as string
    private Set<Role> roles;    // eager fetch means we  need to load this field either we need it or not



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "resume_likes",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "resume_id")}
    )
    private Set<Resume> likes = new HashSet<>();




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = { @JoinColumn(name = "channel_id") },
            inverseJoinColumns = { @JoinColumn(name = "subscriber_id") }
    )
    private Set<User> subscribers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = { @JoinColumn(name = "subscriber_id") },
            inverseJoinColumns = { @JoinColumn(name = "channel_id") }
    )
    private Set<User> subscriptions = new HashSet<>();


    public User() {};

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);   //check if out user is admin
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<Resume> getLikes() {
        return likes;
    }

    public void setLikes(Set<Resume> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}