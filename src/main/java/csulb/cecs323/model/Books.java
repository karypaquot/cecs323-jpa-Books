package csulb.cecs323.model;
import javax.persistence.*;
@Entity
public class Books {
    @Id
    @Column(nullable = false, length = 17)
    private String ISBN;

    @Column (nullable = false, length = 80)
    private String title;

    @Column (nullable = false)
    private int year_published;

//    @Column (nullable = false, length = 30)
//    private String authoring_entity_name;
//
//    @Column (nullable = false, length = 80)
//    private String publisher_name;

    @ManyToOne
    @JoinColumn(name = "authoring_entity_name", referencedColumnName = "name", nullable = false)
    private Authoring_Entities Authoring_Entities;

    @ManyToOne
    @JoinColumn (name = "publisher_name", referencedColumnName = "name", nullable = false)
    private Publisher Publisher;

    public Books(String ISBN, String title, int year_published, Authoring_Entities authoring_entity, Publisher publisher){
        this.ISBN = ISBN;
        this.title = title;
        this.year_published = year_published;
        this.Authoring_Entities= authoring_entity;
        this.Publisher = publisher;

    }
    public Books(){
    }

    @Override
    public String toString () {
        return "Books - ISBN: " + this.ISBN + " Title: " + this.title +
                " Year Published: " + this.year_published + " Authoring Entitiy " + this.Authoring_Entities + "Publisher " + this.Publisher;
    }





}
