package cz.enehano.training.demoapp.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @CreatedDate
    private Date creationDate;// = new Date();
    @CreatedBy
    private String created_by;
    private String firstName;
    private String lastName;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String tel;

    UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String username,
                      String password, String email, String tel) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
    }


    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] nameParts = name.split(" ");
        this.firstName = nameParts[0];
        this.lastName = nameParts[1];
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setGetCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreated_by() {
        return this.created_by;
    }

    public void setCreated_by(String user) {
        this.created_by = user;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof UserEntity))
            return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(this.id, user.id)
                && Objects.equals(this.firstName, user.firstName)
                && Objects.equals(this.lastName, user.lastName)
                && Objects.equals(this.username, user.username)
                && Objects.equals(this.email, user.email)
                && Objects.equals(this.tel, user.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName,
                this.username, this.email, this.tel, this.created_by, this.creationDate, this.password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id
                + ", firstName='" + this.firstName + '\''
                + ", lastName='" + this.lastName + '\''
                + ", username='" + this.username + '\''
                + ", email='" + this.email + '\''
                + ", tel='" + this.tel + '\'' + '}';
    }
}