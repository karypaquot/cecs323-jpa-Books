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

      //prompt user to create/choose authoring entity, create a publisher, or create a book
      int mainChoice = mainMenu();
      while (mainChoice < 3) {

         //if user decides to display information
         if(mainChoice == 1) {
            //prompt user to select the information they would like to have displayed
            int dispChoice = displayMenu();

            //if user decides to display all publisher information
            if(dispChoice == 1) {
               //if publishers exist, then display all existing publishers and their information
               if(publishers.size() > 0){
                  for(int i = 0; i < publishers.size(); i++){
                     System.out.println("\n" + publishers.get(i));
                  }
                  //if no publishers exist, notify the user and bring them back to the main menu
               } else {
                  System.out.println("\n\nThere are no current existing publishers.");
               }

               //if the user decides to display all book information
            } else if(dispChoice == 2){
               //if books exist, then display all existing books and their information
               if(books.size() > 0){
                  for(int j = 0; j < books.size(); j++){
                     System.out.println("\n" + books.get(j));
                  }
                  //if no books exist, notify the user and bring them back to the main menu
               } else {
                  System.out.println("\n\nThere are no current existing books.");
               }

               //if the user decides to display all writing groups and their information
            } else if(dispChoice == 3){
               //if writing groups exist, then display all existing writing groups and their information
               if(writingGroups.size() > 0){
                  for(int k = 0; k < writingGroups.size(); k++){
                     System.out.println("\n" + writingGroups.get(k));
                  }
                  //if no writing groups exist, notify the user and bring them back to the main menu
               } else {
                  System.out.println("\n\nThere are no current existing books.");
               }

               //if the user decides to display all individual authors and their information
            } else if(dispChoice == 4) {
               //if individual authors exist, then display all existing individual authors and their information
               if(individualAuthors.size() > 0){
                  for(int l = 0; l < individualAuthors.size(); l++){
                     System.out.println("\n" + individualAuthors.get(l));
                  }
                  //if no individual authors exist, notify the user and bring them back to the main menu
               } else {
                  System.out.println("\n\nThere are no current existing individual authors.");
               }

               //if the user decides to display all Ad Hoc Teams and their information
            } else if(dispChoice == 5) {
               //if the ad hoc teams exist, then display all existing ad hoc teams and their information
               if(ad_hoc_teams.size() > 0){
                  for(int m = 0; m < ad_hoc_teams.size(); m++){
                     System.out.println("\n" + ad_hoc_teams.get(m));
                  }
                  //if no ad hoc teams exist, notify the user and bring them back to the main menu
               } else {
                  System.out.println("\n\nThere are no current existing ad hoc teams.");
               }
            }
         }

         //if the user decides to create an object
         else if(mainChoice == 2)
         {
            //prompt user to choose what they wish to create
            int createChoice = createMenu();

            //if user decides to create an author
            if(createChoice == 1)
            {
               int authorChoice = createAuthorMenu();
               if(authorChoice == 1)
               {
                  Writing_Group wr = createWritingGroup(writingGroups);
               }
               else if(authorChoice == 2)
               {
                  Individual_Author ia = createIndividualAuthor(individualAuthors);
                  if(ad_hoc_teams.size() > 0)
                  {
                     System.out.println("Would you like to add this author to an Ad Hoc Team? Enter yes/no");
                     String answer = getString();
                     if(answer.equalsIgnoreCase("yes"))
                     {
                        System.out.println("Please choose which ad hoc team you would like to add the author to");
                        //display all ad hoc teams
                        for (int i = 0; i < ad_hoc_teams.size(); i++)
                        {
                           System.out.println("\n\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
                        }
                        int team = getInt();
                        ad_hoc_teams.get(team - 1).addIndividual_Authors(ia);
                        System.out.println("The individual author has been added to the ad hoc team");
                     }

                  }
               }
               else if(authorChoice == 3)
               {
                  Ad_Hoc_Team aht = createAdHocTeam(ad_hoc_teams);
               }
            }
            else if(createChoice == 2)
            {
               Publishers pub = createPublishers(publishers);
            }
            else if(createChoice == 3)
            {
               if(publishers.size() == 0)
               {
                  System.out.println("No publishers have been created, please create a " +
                          "publisher first");
               }
               else
               {
                  System.out.print("Please choose the publisher of the book");
                  for (int j = 0; j < publishers.size(); j++)
                  {
                     System.out.println("\n" + (j + 1) + ". " + publishers.get(j));
                  }
                  int whichPublisher = getInt();
                  Publishers tempPub = publishers.get(whichPublisher - 1);
                  System.out.println("\nPlease choose which authoring entity you want to use: \n" +
                          "1. Writing Group \n2. Individual Author \n3. Ad Hoc Team");
                  int author = getInt();
                  if(author == 1)
                  {
                     if(writingGroups.size() == 0)
                     {
                        System.out.println("There are no Writing Groups created. Please create one first.");
                     }
                     else
                     {
                        System.out.println("Please choose which writing group is the author of the book");
                        for (int i = 0; i < writingGroups.size(); i++)
                        {
                           System.out.println("\n" + (i + 1) + ". " + writingGroups.get(i));
                        }
                        int group = getInt();
                        Writing_Group tempGroup = writingGroups.get(group - 1);
                        createBook(books, tempGroup, tempPub);
                     }

                  }
                  else if(author == 2)
                  {
                     if(individualAuthors.size() == 0)
                     {
                        System.out.println("There are no Individual Authors created. Please create one first.");
                     }
                     else
                     {
                        System.out.println("Please choose which individual author is the author of the book");
                        for (int i = 0; i < individualAuthors.size(); i++)
                        {
                           System.out.println("\n" + (i+1) + ". " + individualAuthors.get(i));
                        }
                        int group = getInt();
                        Individual_Author tempIndividual = individualAuthors.get(group - 1);
                        createBook(books, tempIndividual, tempPub);
                     }
                  }
                  else if(author == 3)
                  {
                     if(ad_hoc_teams.size() == 0)
                     {
                        System.out.println("There are no Ad Hoc Teams created. Please create one first.");
                     }
                     else
                     {
                        System.out.println("Please choose which ad hoc team is the author of the book");
                        for (int i = 0; i < ad_hoc_teams.size(); i++)
                        {
                           System.out.println("\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
                        }
                        int group = getInt();
                        Ad_Hoc_Team tempAdHoc = ad_hoc_teams.get(group - 1);
                        createBook(books, tempAdHoc, tempPub);
                     }
                  }
               }
            }
         }

         //user is brought back to the main menu to start from the beginning again
         mainChoice = mainMenu();
      }
      JPABooks.createEntity(ad_hoc_teams);
      JPABooks.createEntity(publishers);
      JPABooks.createEntity(books);
      JPABooks.createEntity(writingGroups);
      JPABooks.createEntity(individualAuthors);
      tx.commit();

      //prompt user to delete a book
//      boolean delete = false;
//      int index = 0;
//      while(!delete){
//         System.out.print("\n\nLet's try deleting a book now!!\n\nEnter the title for the book you'd like to delete: ");
//         String title = getString();
//         System.out.print("\n\nNow enter the name of of the publisher that published this book: ");
//         String pub = getString();
//         for(int i = 0; i < books.size(); i++){
//            String tempT = books.get(i).getTitle();
//            tempT = tempT.toLowerCase();
//            title = title.toLowerCase();
//            String tempP = books.get(i).getPublishers().getName();
//            tempP = tempP.toLowerCase();
//            pub = pub.toLowerCase();
//            if(tempT == title && tempP == pub){
//               index = i;
//               delete = true;
//            } else if(i == books.size() - 1 && tempT != title && tempP != pub){
//               System.out.println("\n\nSorry, the book you are trying to delete does not exist. Please try again....");
//            }
//         }
//         Books b = manager.find(Books.class, b.getISBN());
//         manager.getTransaction().begin();
//         manager.remove(b);
//         manager.getTransaction().commit();
//      }


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

   public static int displayMenu(){
      //display menu for user to select from
      System.out.println("\nSelect an option by entering the menu number:\n1. Display all Publishers and their information" +
              "\n2. Display all Books and their information\n3. Display all Writing Groups and their information" +
              "\n4. Display all Individual Authors and their information\n5. Display all Ad Hoc Teams and their information\n6. Back to Main Menu");
      //record user's choice
      int choice = getInt();
      //if the user selects a number not on the menu, they will be prompted to try again
      while(choice > 6 || choice < 1){
         System.out.println("\nPlease be sure to select a menu option from 1 - 6\n\nSelect an option by entering the menu number:\n1. Display all Publishers and their information" +
                 "\n2. Display all Books and their information\n3. Display all Writing Groups and their information" +
                 "\n4. Display all Individual Authors and their information\n5. Display all Ad Hoc Teams and their information\n6. Back to Main Menu");
         choice = getInt();
      }
      return choice;
   }

   /**
    * Menu method utilizes scanner to take in user input, stores the line
    * Then returns a String when method is called
    * @return input String value from scanner
    */
   public static int createMenu(){
      System.out.println("\nSelect an option: \n1. Create an authoring entity\n2. Create" +
              " a publisher\n3. Create a book\n4. Back to Main Menu");
      int choice = getInt();
      while(choice > 4 || choice < 1)
      {
         System.out.println("\nPlease choose between 1-4: \n1. Create an authoring entity\n2. Create " +
                 "a publisher\n3. Create a book\n4. Back to Main Menu");
         choice = getInt();
      }
      return choice;
   }



   /**
    * mainMenu() prompts the user to choose an option of either creating/choosing an authoring entity, creating a publisher
    * a book
    */
   public static int mainMenu()
   {
      System.out.println("\nMAIN MENU:\nPlease select an option by entering the menu number:\n1. Display an object\n2. Create an object\n3. Exit");
      int choice = getInt();
      while(choice > 3 || choice < 1)
      {
         System.out.println("Please select an option by entering the menu number:\n1. Display an object\n2. Create an object\n3. Exit" );
         choice = getInt();
      }
      return choice;
   }

   /**
    * createAuthorMenu method displays a menu that prompts the user to value
    * @return choice of user
    */
   public static int createAuthorMenu(){
      System.out.println("\nSelect an option: \n1. Create a Writing Group\n2: Create Individual Author\n3: Create Ad Hoc Team\n4: Back to Main Menu");
      int choice = getInt();
      while(choice>4 || choice<1){
         System.out.println("\nPlease enter a number from 1-4\nSelect an option: \n1. Create a Writing Group\n2: Create Individual Author\n3: Create Ad Hoc Team\n4: Back to Main Menu");
         choice = getInt();
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
      Individual_Author author = new Individual_Author(Email, Name);
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
