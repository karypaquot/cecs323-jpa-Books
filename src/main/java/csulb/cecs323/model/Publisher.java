package csulb.cecs323.model;
//name: String
//phone: String
//email: String
import javax.persistence.*;
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String phone;

    @Column (nullable = false, length = 24)
    private String email;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false)
//    private Books books;

    public Publisher (String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public Publisher(){}


    public long get_publisherID() {

        return ID;
    }

    public String getName() {

        return name;
    }

    public void setName(String Name) {

        this.name = name;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }
    @Override
    public String toString () {
        return "Publisher - ID: " + this.get_publisherID() + " Name: " + this.getName() +
                " Phone #: " + this.getPhone() + "Email " + this.getEmail();
    }





}
