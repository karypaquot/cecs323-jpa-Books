package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Ad Hoc Team")
public class Ad_Hoc_Team extends Authoring_Entities{

    @ManyToMany
    @JoinTable (
            name = "individual_on_team",
            joinColumns = @JoinColumn(name = "team_name"),
            inverseJoinColumns = @JoinColumn(name = "individual_name")
    )
    Set<Individual_Author> individual_authors;

    public Ad_Hoc_Team(){}

    public Ad_Hoc_Team(String email, String name)
    {
        super(email, name);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
