package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Individual_Author")
public class Individual_Author extends Authoring_Entities{

    @ManyToMany (mappedBy = "individual_authors")
    Set<Ad_Hoc_Team> ad_hoc_teams;

    public Individual_Author(String name, String email){
        super(name,email);
    }
    public Individual_Author(){
    }

    @Override
    public String toString(){
        return "Individual Writer: " + getName() + "\nIndividual Writer's Email " + this.getEmail();
    }

}

