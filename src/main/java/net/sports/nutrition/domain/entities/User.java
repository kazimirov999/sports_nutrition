package net.sports.nutrition.domain.entities;




import net.sports.nutrition.domain.enumx.Role;
import net.sports.nutrition.utils.EncodeUtil;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 08.01.2016 15:02
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @Email(message = "{error.login.is.not.correct}")
    private String loginEmail;
    private String phoneNumber;
    private  String password;
    private transient String passwordOriginal;
    private transient String passwordDubl;

    private boolean enabled;




    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    private String address;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateRegister;

    public User() {
        this.dateRegister = DateTime.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginEmail='" + loginEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", passwordDubl='" + passwordDubl + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", country=" + country +
                ", address='" + address + '\'' +
                ", dateRegister=" + dateRegister +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncodeUtil.md5Encryption(passwordOriginal);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DateTime getDateRegister() {
        return dateRegister;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public void setDateRegister(DateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getPasswordDubl() {
        return passwordDubl;
    }

    public void setPasswordDubl(String passwordDubl) {
        this.passwordDubl = passwordDubl;
    }

    public String getPasswordOriginal() {
        return passwordOriginal;
    }

    public void setPasswordOriginal(String passwordOriginal) {
        this.passwordOriginal = passwordOriginal;
        this.setPassword(passwordOriginal);
    }
}
