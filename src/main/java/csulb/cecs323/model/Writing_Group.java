package csulb.cecs323.model;

import javax.persistence.*;
@Entity
public class Writing_Group extends Authoring_Entities{

    @Column
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
        return super.toString() + "\nHead Writer: " + getHeadWriter() + "\nYear Group Form: " + this.getYearFormed();
    }
}
