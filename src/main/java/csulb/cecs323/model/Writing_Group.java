package csulb.cecs323.model;
import javax.persistence.*;

@Entity
@DiscriminatorValue("WG")
public class Writing_Group extends Authoring_Entities{
    @Id
    @Column (nullable = false, length = 80)
    private String head_writer;

    @Column(nullable = false)
    private int year_formed;


    public Writing_Group(String email, String name, String head_writer, int year_formed){
        super(email, name);
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }

    public Writing_Group(){
        super();
    }
}
