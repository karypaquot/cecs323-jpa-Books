package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedNativeQuery(
        name = "ReturnIndividualAuthor",
        query = "SELECT * " +
                "FROM AUTHORING_ENTITIES " +
                "WHERE name = ? AND AUTHORING_ENTITY_TYPE = 'Individual_Author'",
        resultClass = Individual_Author.class
)
@DiscriminatorValue("Individual_Author")
public class Individual_Author extends Authoring_Entities{

    @ManyToMany (mappedBy = "individual_authors")
    Set<Ad_Hoc_Team> ad_hoc_teams;

    public Individual_Author(String email, String name){
        super(email,name);
    }
    public Individual_Author(){
    }

    @Override
    public String toString(){
        return "Individual Writer's Email: " + this.getEmail() + "\nIndividual Writer: " + this.getName();
    }

}

