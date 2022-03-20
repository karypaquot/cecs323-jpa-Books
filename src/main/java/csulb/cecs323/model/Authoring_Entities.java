package csulb.cecs323.model;

import javax.persistence.*;

@MappedSuperclass
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
    

}
