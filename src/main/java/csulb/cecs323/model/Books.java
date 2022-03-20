package csulb.cecs323.model;
import javax.persistence.*;

@Entity
public class Books {

    @ManyToOne
    @JoinColumn(nullable = false, name = "name")
    private Publishers Publishers;

    @ManyToOne
    @JoinColumn(nullable = false, name = "email")
    private Authoring_Entities Authoring_Entities;


    @Id
    @Column(nullable = false, length = 17)
    private String ISBN;

    @Column (nullable = false, length = 80)
    private String title;

    @Column (nullable = false)
    private int year_published;





    public Books(String ISBN, String title, int year_published,Authoring_Entities authoring_Entities, Publishers publisher){
        this.ISBN = ISBN;
        this.title = title;
        this.year_published = year_published;
        this.Authoring_Entities = authoring_Entities;
        this.Publishers = publisher;

    }
    public Books(){

    }



    @Override
    public String toString () {
        return "Books - ISBN: " + this.ISBN + " Title: " + this.title +
                " Year Published: " + this.year_published + " Publisher: " + this.Publishers.getName();
    }


}

