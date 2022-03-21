package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Ad Hoc Team")
public class Ad_Hoc_Team extends Authoring_Entities{

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
