package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

import controllers.Accounts;
import play.db.jpa.Model;

@Entity
public class Trainer extends Model {
    public String name;
    public String email;
    public String password;
    public String address;
    public String gender;
    public String specialty;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<Member>();
    public Trainer(String email, String name, String address,
                   String gender, String specialty) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setAddress(address);
        setGender(gender);
        setSpecialty(specialty);
    }

    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if ((gender.equals("")) || (gender.equals("M"))) {
            this.gender = gender;
        }
        else {
            this.gender = "Unspecified";
        }
    }

    public String getSpecialty(String specialty) {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

}
