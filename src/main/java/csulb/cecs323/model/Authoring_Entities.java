package csulb.cecs323.model;

import javax.persistence.*;

@Entity
//@Table(name = "AUTHYORING_ENTITIES")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "email", discriminatorType = DiscriminatorType.STRING, length = 30)
public class Authoring_Entities {
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

    @Override
    public String toString () {
        return "Authoring name:  " + name + " Email: " + email;
    }

}

