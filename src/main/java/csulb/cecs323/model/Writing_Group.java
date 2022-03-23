package csulb.cecs323.model;

import javax.persistence.*;
import javax.persistence.NamedNativeQueries;

@Entity
@NamedNativeQuery(
        name = "ReturnWritingGroup",
        query = "SELECT * " +
                "FROM AUTHORING_ENTITIES " +
                "WHERE name = ? AND AUTHORING_ENTITY_TYPE = 'Writing Groups'",
        resultClass = Writing_Group.class
)
@DiscriminatorValue("Writing Groups")
public class Writing_Group extends Authoring_Entities{

    @Column(length = 80)
    private String headWriter;

    @Column
    private int yearFormed;

    public Writing_Group(){

    }

    public Writing_Group(String email, String name, String headWriter, int yearFormed){
        super(email, name);
        this.headWriter = headWriter;
        this.yearFormed = yearFormed;
    }

    public void setHeadWriter(String headWriter) {
        this.headWriter = headWriter;
    }

    public String getHeadWriter(){
        return this.headWriter;
    }

    public void setYearFormed(int yearFormed) {
        this.yearFormed = yearFormed;
    }

    public int getYearFormed() {
        return yearFormed;
    }

    @Override
    public String toString(){
        return super.toString() + "\nHead Writer: " + getHeadWriter() + "\nYear Group Formed: " + this.getYearFormed();
    }
}
