package csulb.cecs323.model;

import javax.persistence.*;

/**
 * An Authoring_Entity is a type of person that can vary from the types of: individual author, writing_group, or Ad Hoc team.
 * Has the attribute email and name for it to be uniquely identified from other types of authoring identity
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Authoring_Entity_Type", discriminatorType = DiscriminatorType.STRING)
public abstract class Authoring_Entities {

    @Id
    @Column(nullable = false, length = 30)
    private String email;


    @Column (nullable = false, length = 80)
    private String name;

    public Authoring_Entities (String email, String name){
        this.email = email;
        this.name = name;
    }
    public Authoring_Entities() {}

    /**
     * getEmail returns the email attribute
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail sets the email attribute to the specified email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getName() sets the name attribute to the specified name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setName() sets the name attribute to the specified name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return "\nAuthoring name:  " + name + "\nEmail: " + email;
    }

}

