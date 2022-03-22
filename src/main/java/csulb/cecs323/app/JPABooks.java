/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.


import csulb.cecs323.model.*;

import java.util.concurrent.Flow;
import java.util.logging.Level;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class JPABooks {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(JPABooks.class.getName());

   /**
    * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public JPABooks(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.setLevel(Level.OFF);
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPABooks");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of JPABooks and store our new EntityManager as an instance variable.
      JPABooks JPABooks = new JPABooks(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();
      List<Ad_Hoc_Team> ad_hoc_teams =  new ArrayList<Ad_Hoc_Team>();
      //JPABooks.createEntity(ad_hoc_teams);
      List<Publishers> publishers = new ArrayList<Publishers>();
      //JPABooks.createEntity(publishers);
      List<Books> books = new ArrayList<Books>();
      //JPABooks.createEntity(books);
      List<Writing_Group> writingGroups = new ArrayList<Writing_Group>();
      //JPABooks.createEntity(writingGroups);
      List<Individual_Author> individualAuthors = new ArrayList<Individual_Author>();
      //JPABooks.createEntity(individualAuthors);

      int mainChoice = mainMenu();
      while(mainChoice < 3) {
         if (mainChoice == 1) {
            int authorChoice = authorMenu();
            while (authorChoice < 3) {
               int choice1;
               //select an existing authoring entity or create a new authoring entity
               if (authorChoice == 1) {
                  choice1 = existingAuthorsMenu();
                  if (choice1 == 1) {
                     //choose existing writing group

                  } else if (choice1 == 2) {
                     //choose existing individual author

                  } else if (choice1 == 3) {
                     //choose existing ad hoc team

                  } else if (choice1 == 4) {
                     //exit while loop
                     System.out.println("\n\nExiting program.....");
                     break;
                  }
               }
               //create an authoring entity
               int choice2;
               if (authorChoice == 2) {
                  //get choice for either writing group, individual author, ad hoc team
                  choice2 = createAuthorMenu();
                  //create writing group
                  if (choice2 == 1) {
                     Writing_Group wr = createWritingGroup(writingGroups);
                  }
                  // creates an instance of  individual Author
                  else if (choice2 == 2) {
                     Individual_Author ia = createIndividualAuthor(individualAuthors);
                  }
                  //creates an instance of an ad_hoc_team
                  else if (choice2 == 3) {
                     Ad_Hoc_Team AHT = createAdHocTeam(ad_hoc_teams);
                  }
               }
               //keep for end of while loop
               authorChoice = authorMenu();

            }
         }
         else if (mainChoice == 2)
         {
            Publishers pub = createPublishers(publishers);
         }
         else if(mainChoice == 3){
            //check if publisher list is empty
            if(publishers.size() == 0 ){
               //if publisher list is empty notify user that book cannot be created
               System.out.println("You cannot create a book because you don't have a Publisher");
            }
            //if publisher list is not empty.....
            else{
               //check to make sure none of the Authorint Entity lists are empty
               if(writingGroups.size() == 0 && individualAuthors.size() == 0 && ad_hoc_teams.size() == 0){
                  //if they are empty, notify the user that a book cannot be created
                  System.out.println("You cannot create a book because you don't have any Authors");
                  //if at least 1 list has data...
               } else {
                  //prompt user to enter the name of the publisher they want for the book
                  System.out.println("Please enter the name of the Publisher that will be attached to the this book: ");
                  String pub = getString();

                  int index = 0;
                  Boolean contPub = false; //initialized the contained publisher to false
                     //iterate publishers list to check if the desired publisher exists
                     for(int i = 0; i < publishers.size(); i++){
                       String temp =  publishers.get(i).getName();
                       if(temp.toLowerCase() == pub.toLowerCase()){
                          index = i;
                          contPub = true;
                          break;
                       } else if(i == publishers.size() - 1 && temp.toLowerCase() != pub.toLowerCase()){
                          System.out.println("Publisher entered does not currently exist in the database.....");
                       }
                     }
                  String auth = ""; //initialize author selection
                  int index2 = 0;
                  Boolean contGrp = false;
                  Boolean contGrpLst = false;
                  if(contPub == true){
                     System.out.println("Please enter the name of the Writing Group, Individual Author, or");
//                     if(JPABooks.getGroup(auth.toLowerCase()) == auth.toLowerCase()){
//
//                     }
                  }

               }
               //Books b = createBook();
            }
         }
         mainChoice = mainMenu();

      }
      tx.commit();
      /*Publisher pub = manager.find(Publisher.class, publishers.get(0).get_publisherID());
      manager.getTransaction().begin();
      manager.remove(pub);
      manager.getTransaction().commit();
       */


      LOGGER.fine("End of Transaction");



   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   /**
    * Method utilizes scanner to take in user input, stores the line
    * Then returns a String when method is called
    * @return input String value from scanner
    */
   public static String getString() {
      Scanner in = new Scanner(System.in);
      String input = in.nextLine();
      return input;
   }

   /**
    * getInt prompts the user to enter an intger value and the function will return it
    * @return the integer value of the user
    */
   public static int getInt () {
      Scanner in = new Scanner(System.in);
      int answer = 0;
      boolean valid = false;
      while (!valid) {
         if (in.hasNextInt()) {
            answer = in.nextInt();
            valid = true;
         } else {
            in.next(); //clear invalid string
            System.out.println("Invalid Input.");
         }
      }
      return answer;
   }
   /**
    * Menu method utilizes scanner to take in user input, stores the line
    * Then returns a String when method is called
    * @return input String value from scanner
    */
   public static int authorMenu(){
      System.out.println("Select an option:\n" +
              "1. Select an existing Writing Group, Individual Author, or Ad Hoc Team\n" +
              "2. Create a Writing Group, Individual Author, or Ad Hoc Team\n" +
              "3. Exit");
      int answer = getInt();
      if(answer > 3 || answer < 1){
         System.out.println("\n\nPlease enter a number from 1 - 3" + "\n1. Select an existing Writing Group, Individual Author, or Ad Hoc Team\n" +
                 "2. Create a Writing Group, Individual Author, or Ad Hoc Team" +
                 "\n3. Exit");
         answer = getInt();
      }
      return answer;
   }

   /**
    * existingAuthorsMenu prompts the user to choose an option from the displayed menu
    * @return choice of the user
    */
   public static int existingAuthorsMenu()
   {
      System.out.println("\n\nSelect an option: \n1. Choose an existing Writing Group\n2. Choose an existing Individual Author\n3." +
              "Choose an existing Ad Hoc Team\n4. Exit");
      int choice = getInt();
      if (choice > 4 || choice < 1)
      {
         System.out.println("\nPlease enter a number from 1-4" + "\n1. Choose an existing Writing Group\n2. Choose an existing Individual Author\n3." +
                 "Choose an existing Ad Hoc Team\n4. Exit");
         choice = getInt();
      }
      return choice;
   }

//   public Authoring_Entities displayExistingMenu(int c){
//      Writing_Group a;
//      Individual_Author b;
//      if(c == 1){
//         System.out.println("\n\nPlease enter the name of the Group you'd like to select: ");
//         String n = getString();
//         a = getGroup(n);
//      } else if(c == 2){
//         System.out.println("\n\nPlease enter the name of the Individual Author you'd like to select: ");
//         String n = getString();
//         b = getAuthor(n);
//      }
//   }

   /**
    * mainMenu() prompts the user to choose an option of either creating/choosing an authoring entity, creating a publisher
    * a book
    */
   public static int mainMenu()
   {
      System.out.println("Select an option:\n1. Create or choose an authoring entity \n2. Create a " +
              "publisher \n3. Create a book \n4. Exit");
      int choice = getInt();
      if (choice > 4 || choice < 1)
      {
         System.out.println("Please enter a number from 1-4:" + "\n1. Create or choose an authoring entity \n2. Create a " +
                 "publisher \n3. Create a book \n4. Exit");
         choice = getInt();
      }
      return choice;
   }

   /**
    * createAuthorMenu method displays a menu that prompts the user to value
    * @return choice of user
    */
   public static int createAuthorMenu(){
      System.out.println("Select an option: \n1. Create a Writing Group\n2: Create Individual Author\n3: Create Ad Hoc Team\n 4: Exit");
      int choice = getInt();
      if(choice>4 || choice<1){
         System.out.println("\nPlease enter a number from 1-4 ");
      }
      return choice;
   }

   /**
    * The createPublishers method takes in list of Publishers and prompts the user to enter the
    * publishers name, number, and email. It then creates a new instance of a publisher with those
    * attributes as parameters. The method returns the new Publisher.
    * @param list the List of publishers
    * @return the new instance of publisher that was created
    */
   public static Publishers createPublishers(List<Publishers> list){
      System.out.println("Enter the publisher's name: ");
      String publishers_name = getString();
      System.out.println("Enter the publisher's number: ");
      String publishers_number = getString();
      System.out.println("Enter the publisher's email: ");
      String publishers_email = getString();
      Publishers new_publisher = new Publishers(publishers_name,publishers_number,publishers_email);
      list.add(new_publisher);
      return new_publisher;
   }

   /**
    * The createBook method takes in the list of books b, Authoring_Entities ae, and Publishers p.
    * It prompts the user to enter a books isbn, and title as a String and the year published as an int.
    * A new instance of a book is added to the Books list with the isbn, title, year, ae, p as parameters.
    * @param b The list of books
    * @param ae the authoring entity of the book
    * @param p the publisher of the book
    */
   public static void createBook(List<Books> b, Authoring_Entities ae, Publishers p)
   {
      System.out.println("You are creating a new book. Please enter the ISBN: ");
      String isbn = getString();
      System.out.println("\nEnter the title: ");
      String title = getString();
      System.out.println("\nEnter the year published: ");
      int year = getInt();

      b.add(new Books(isbn, title, year, ae, p));
   }

   /**
    * The createWritingGroup method accepts a writing group list as a parameter and then prompts the user
    * to enter the name, email, thea head writer's name, and the year the group was formed. It then creates
    * a new writing group, adds the instance to the list, and returns the instance
    * @param list    The list of the writing groups
    * @return  the writing group instance that is created
    */
   public static Writing_Group createWritingGroup(List<Writing_Group> list){
      System.out.println("Enter the Writing Group's Name: ");
      String Name = getString();
      System.out.println("Enter the Writing Group's email: ");
      String Email = getString();
      System.out.println("Enter the Writing Group's Head Writer: ");
      String HeadWriter = getString();
      System.out.println("Enter the year the group was formed: ");
      int year = getInt();
      Writing_Group wr = new Writing_Group(Email,Name,HeadWriter,year);
      list.add(wr);
      return wr;

   }
   /**
    * createIndividualAuthor method prompts the user to enter a name and email as a String
    * to create an Individual_Author. That author is added to the list of Individual_Author entities
    * and returns the new individual author.
    * @param list    The list of the individual authors
    * @return        The Individual_Author instance corresponding to that authors name
    * */
   public static Individual_Author createIndividualAuthor(List<Individual_Author> list){
      System.out.println("Enter the author's Name: ");
      String Name = getString();
      System.out.println("Enter the author's email: ");
      String Email = getString();
      Individual_Author author = new Individual_Author(Name,Email);
      list.add(author);
      return author;
   }

   /**
    * createAdHocTeam method prompts the user to enter a name and email and uses those input to create a new instance of Ad_Hoc_team
    * @param list  The list of Ad Hoc teams
    * @return      The Ad_Hoc_team instance corresponding to the user's input
    */

   public static Ad_Hoc_Team createAdHocTeam(List<Ad_Hoc_Team> list){
      System.out.println("Enter the author's Name: ");
      String Name = getString();
      System.out.println("Enter the author's email: ");
      String Email = getString();
      Ad_Hoc_Team adHoc= new Ad_Hoc_Team(Email,Name);
      list.add(adHoc);
      return adHoc;
   }
   /**
    * getGroup grabs the specific group with the specified name from the parameter
    * @param name    The name of the group that you are looking for
    * @return        The Writing_Group instance corresponding to that group name
    * */
   public Writing_Group getGroup (String name){
      //Run the native query that we defined in Writing_Group entity to find the right group
      List<Writing_Group> groups = this.entityManager.createNamedQuery("ReturnWritingGroup", Writing_Group.class).setParameter(1, name).getResultList();
      if(groups.size() == 0){
         //Invalid group name passed in
         return null;
      } else {
         //Return the group object that they asked for
         return groups.get(0);
      } //End of the getGroup method
   }

   /**
    * getAuthor grabs the author with the specified name from the parameter
    * @param name    The name of the author that you are looking for
    * @return        The Individual_Author instance corresponding to that author name
    * */
   public Individual_Author getAuthor (String name){
      //Run the native query that we defined in Individual_Author entity to find the right author
      List<Individual_Author> authors = this.entityManager.createNamedQuery("ReturnIndividualAuthor", Individual_Author.class).setParameter(1, name).getResultList();
      if(authors.size() == 0){
         //Invalid author name passed in
         return null;
      } else {
         //Return the author object that they asked for
         return authors.get(0);
      } //End of the getAuthor method
   }

   /**
    * getTeam method takes in a String name that corresponds to an Ad_Hoc_Team. It searches
    * for the team name in the native query.
    * @param name    The name of the team that you are looking for
    * @return        The Ad_Hoc_Team instance corresponding to that team name
    * */
   public Ad_Hoc_Team getTeam (String name){
      //Run the native query that we defined in Ad_Hoc_Team entity to find the right team
      List<Ad_Hoc_Team> teams = this.entityManager.createNamedQuery("ReturnAdHocTeam", Ad_Hoc_Team.class).setParameter(1, name).getResultList();
      if(teams.size() == 0){
         //Invalid team name passed in
         return null;
      } else {
         //Return the team object that they asked for
         return teams.get(0);
      } //End of the getTeam method
   }


} // End of JPABooks class
