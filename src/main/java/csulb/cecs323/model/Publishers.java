package csulb.cecs323.model;

import javax.persistence.*;
@Entity
public class Publishers {

    @Id
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 24)
    private String phone;

    @Column (nullable = false, length = 80)
    private String email;


    public Publishers (String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public Publishers(){}


    public String getName() {

        return name;
    }

    public void setName(String Name) {

        this.name = name;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }
    @Override
    public String toString () {
        return "(Publisher - ID: " + ", Name: " + this.getName() +
                ", Phone #: " + this.getPhone() + ", Email: " + this.getEmail() + ")";
    }





}
