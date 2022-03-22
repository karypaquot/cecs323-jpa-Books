package csulb.cecs323.model;

import javax.persistence.*;

/**
 * A publisher is a person or a company that publishes books. A publisher has a name, phone
 * and email. This class creates a Publisher with these attributes.
 */
@Entity
public class Publishers {

    @Id
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 24)
    private String phone;

    @Column (nullable = false, length = 80)
    private String email;

    /**
     * The constructor for the Publishers class. It stashes a books ISBN, title, year_published,
     * authoring_Entities, and publisher to the Entity.
     * @param name      String
     * @param phone     String
     * @param email     String
     */
    public Publishers (String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    /**
     * The default constructor for Publishers
     */
    public Publishers(){}


    public String getName() {

        return name;
    }

    public void setName(String Name) {

        this.name = name;
    }
    /**
     * getPhone method returns the phone number of a Publisher.
     * @return phone String
     */
    public String getPhone(){
        return phone;
    }
    /**
     * setPhone method assigns the phone number of a Publisher.
     * @param phone String
     */
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
        return "\nPublisher - ID: " + "\nName: " + this.getName() +
                "\nPhone #: " + this.getPhone() + "\nEmail: " + this.getEmail();
    }





}
