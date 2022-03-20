package csulb.cecs323.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AHT")
public class Ad_Hoc_Teams_Member extends Authoring_Entities{

    public Ad_Hoc_Teams_Member(String email, String name){
        super(email,name);
    }
    public Ad_Hoc_Teams_Member(){
        super();
    }

}
