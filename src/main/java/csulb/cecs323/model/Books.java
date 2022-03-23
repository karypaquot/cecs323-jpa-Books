package csulb.cecs323.model;
import javax.persistence.*;

/**
 * A physical book that a person can read. A book has a Title, Authoring entity, Publisher, year published,
 * and an ISBN to uniquely identify it from other books. This class creates a book with these attributes.
 */
@Entity
public class Books {

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name", nullable = false)
    private Publishers Publishers;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private Authoring_Entities Authoring_Entities;


    @Id
    @Column(nullable = false, length = 17)
    private String ISBN;

    @Column (nullable = false, length = 80)
    private String title;

    @Column (nullable = false)
    private int year_published;

    /**
     * The constructor for the Books class. It stashes a books ISBN, title, year_published,
     * authoring_Entities, and publisher to the Entity.
     * @param ISBN      String
     * @param title     String
     * @param year_published int
     * @param authoring_Entities Authoring_entities
     * @param publisher Publishers
     */
    public Books(String ISBN, String title, int year_published,Authoring_Entities authoring_Entities, Publishers publisher){
        this.ISBN = ISBN;
        this.title = title;
        this.year_published = year_published;
        this.Authoring_Entities = authoring_Entities;
        this.Publishers = publisher;

    }

    /**
     * The default constructor for the Books class.
     */
    public Books(){
    }

    public void setAuthoring_Entity(Authoring_Entities ae)
    {
        this.Authoring_Entities = ae;
    }

    public void setTitle(String t){
        this.title = t;
    }

    public String getTitle(){
        return title;
    }

    public csulb.cecs323.model.Publishers getPublishers() {
        return Publishers;
    }

    public String getISBN(){
        return ISBN;
    }





    /**
     * The toString class returns a String with the books ISBN, Title, Year Published, and publisher.
     * @return ISBN String
     * @return title String
     * @return year_published int
     * @return publisher.getName() String
     */
    @Override
    public String toString () {
        return "\nISBN: " + this.ISBN + "\nTitle: " + this.title +
                "\nYear Published: " + this.year_published + "\nPublisher: " + this.Publishers.getName() +"\nAuthor: " + this.Authoring_Entities.getName() + "\nAuthor Email: " + this.Authoring_Entities.getEmail();
    }


}

