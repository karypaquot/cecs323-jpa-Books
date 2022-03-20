package csulb.cecs323.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IA")
public class Individual_Author extends Authoring_Entities{

    public Individual_Author(String email, String name){
        super(email, name);
    }

    public Individual_Author(){
        super();
    }
}
