package csulb.cecs323.model;
import javax.persistence.*;

@Entity
public class Writing_Group extends Authoring_Entities{
    @Id
    @Column (nullable = false, length = 80)
    private String head_writer;

    @Column(nullable = false)
    private int year_formed;

    public Writing_Group(){}

    public Writing_Group(String head_writer, int year_formed){
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }
}
