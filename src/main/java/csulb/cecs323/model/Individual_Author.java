package csulb.cecs323.model;

import javax.persistence.*;
@Entity
@DiscriminatorValue("Individual_Author")
public class Individual_Author extends Authoring_Entities{
    public Individual_Author(String name, String email){
        super(name,email);
    }
    public Individual_Author(){
    }

    @Override
    public String toString(){
        return super.toString() + "\nIndividual Writer: " + getName() + "\nIndividual Writer's Email " + this.getEmail();
    }

}

