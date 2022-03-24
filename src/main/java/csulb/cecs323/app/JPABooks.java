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
import javax.persistence.*;
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



      //prompt user to create/choose authoring entity, create a publisher, or create a book
      int mainChoice = mainMenu();
      while (mainChoice < 4) {

         //initiate information to be updated to the database
         tx.begin();

         //create array lists of the tables by gathering the data that is already in the database
         List<Ad_Hoc_Team> ad_hoc_teams =  manager.createQuery("Select a From Ad_Hoc_Team a ", Ad_Hoc_Team.class).getResultList();
         List<Publishers> publishers = manager.createQuery("Select a From Publishers a ", Publishers.class).getResultList();
         List<Books> books = manager.createQuery("Select a From Books a ", Books.class).getResultList();
         List<Writing_Group> writingGroups = manager.createQuery("Select a From Writing_Group a ", Writing_Group.class).getResultList();
         List<Individual_Author> individualAuthors = manager.createQuery("Select a From Individual_Author a ", Individual_Author.class).getResultList();

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
               //if user decides to create a writing group
               if(authorChoice == 1)
               {
                  //the user is then prompted to enter the information needed to create a writing group
                  createWritingGroup(writingGroups);
               }
               //if user decides to create an individual author
               else if(authorChoice == 2)
               {
                  //the user is then prompted to enter the information needed to create an individual author
                  Individual_Author ia = createIndividualAuthor(individualAuthors);
                  //once the individual author has been created and at least 1 ad hoc team exists, then we prompt the user
                  //to decide if they'd like to add that author to an ad hoc team
                  if(ad_hoc_teams.size() > 0)
                  {
                     System.out.println("Would you like to add this author to an Ad Hoc Team? Enter yes/no");
                     String answer = getString();
                     //make sure we get the proper input from the user
                     while(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
                     {
                        System.out.println("Would you like to add this author to an Ad Hoc Team? Enter yes/no");
                        answer = getString();
                     }
                     //if the user does want to add to an ad hoc team then they are prompted to select the team
                     //to add the individual author to
                     if(answer.equalsIgnoreCase("yes")){
                        System.out.println("Please choose which ad hoc team you would like to add the author to");
                        //display all ad hoc teams
                        for (int i = 0; i < ad_hoc_teams.size(); i++)
                        {
                           System.out.println("\n\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
                        }
                        int team = getInt();
                        //make sure we get the proper input from the user
                        while(team > ad_hoc_teams.size() || team < 1){
                           System.out.println("Please choose which ad hoc team you would like to add the author to");
                           //display all ad hoc teams
                           for (int i = 0; i < ad_hoc_teams.size(); i++)
                           {
                              System.out.println("\n\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
                           }
                           team = getInt();
                        }
                        //add the individual author to the ad hoc team that the user selected and notify them
                        ad_hoc_teams.get(team - 1).addIndividual_Authors(ia);
                        System.out.println("The individual author has been added to the ad hoc team");
                     }

                  }
               }
               //if the user decides to create an ad hoc team
               else if(authorChoice == 3)
               {
                  //the user is then prompted to enter the information needed to create a new ad hoc team
                  createAdHocTeam(ad_hoc_teams);
               }
            }
            //if the user wants to create a publisher
            else if(createChoice == 2)
            {
               //the user is then prompted to enter the information to create a new publisher
               createPublishers(publishers);
            }
            //if the user decides to create a book
            else if(createChoice == 3)
            {
               //since publishers are needed to create a book, we make sure there are publishers in the database
               //and if there are no publishers, then we will notify the user to create a publisher
               if(publishers.size() == 0)
               {
                  System.out.println("No publishers have been created, please create a " +
                          "publisher first");
               }
               else
               {
                  //prompt the user to select an existing publisher
                  System.out.print("Please choose the publisher of the book");
                  for (int j = 0; j < publishers.size(); j++)
                  {
                     System.out.println("\n" + (j + 1) + ". " + publishers.get(j));
                  }
                  int whichPublisher = getInt();
                  while(whichPublisher > publishers.size() || whichPublisher < 1){
                     System.out.print("Please choose the publisher of the book");
                     for (int j = 0; j < publishers.size(); j++)
                     {
                        System.out.println("\n" + (j + 1) + ". " + publishers.get(j));
                     }
                     whichPublisher = getInt();
                  }
                  Publishers tempPub = publishers.get(whichPublisher - 1);
                  //prompt the user to select the authoring entity they'd like to be attached to the book
                  System.out.println("\nPlease choose which authoring entity you want to use: \n" +
                          "1. Writing Group \n2. Individual Author \n3. Ad Hoc Team");
                  int author = getInt();
                  while(author > 3 || author < 1){
                     System.out.println("\nPlease choose which authoring entity you want to use: \n" +
                             "1. Writing Group \n2. Individual Author \n3. Ad Hoc Team");
                     author = getInt();
                  }
                  //if the user selects a writing group to be attached to the book
                  if(author == 1)
                  {
                     //check to see if there are existing writing groups, if not then the user will be
                     //notified that one will need to be created first
                     if(writingGroups.size() == 0)
                     {
                        System.out.println("There are no Writing Groups created. Please create one first.");
                     }
                     //other wise the user will be prompted to select an existing writing group
                     else
                     {
                        System.out.println("Please choose which writing group is the author of the book");
                        for (int i = 0; i < writingGroups.size(); i++)
                        {
                           System.out.println("\n" + (i + 1) + ". " + writingGroups.get(i));
                        }
                        int group = getInt();
                        while (group > writingGroups.size() || group < 1){
                           System.out.println("Please choose which writing group is the author of the book");
                           for (int i = 0; i < writingGroups.size(); i++)
                           {
                              System.out.println("\n" + (i + 1) + ". " + writingGroups.get(i));
                           }
                           group = getInt();
                        }
                        //the new writing group is formed and added to the database
                        Writing_Group tempGroup = writingGroups.get(group - 1);
                        createBook(books, tempGroup, tempPub);
                     }

                  }
                  //if the user decides to attach an individual author to the book
                  else if(author == 2)
                  {
                     //check if there are any existing individual authors, if there are none the user will
                     //be notified to create one first
                     if(individualAuthors.size() == 0)
                     {
                        System.out.println("There are no Individual Authors created. Please create one first.");
                     }
                     //otherwise the user will be prompted to select from the existing individual authors
                     //to attach to the book
                     else
                     {
                        System.out.println("Please choose which individual author is the author of the book");
                        for (int i = 0; i < individualAuthors.size(); i++)
                        {
                           System.out.println("\n" + (i+1) + ". " + individualAuthors.get(i));
                        }
                        int auth = getInt();
                        while(auth > individualAuthors.size() || auth < 1){
                           System.out.println("Please choose which individual author is the author of the book");
                           for (int i = 0; i < individualAuthors.size(); i++)
                           {
                              System.out.println("\n" + (i+1) + ". " + individualAuthors.get(i));
                           }
                           auth = getInt();
                        }
                        //the selected individual author is added to the book and the book is added to the database
                        Individual_Author tempIndividual = individualAuthors.get(auth - 1);
                        createBook(books, tempIndividual, tempPub);
                     }
                  }
                  //if the user decides to attach an ad hoc team to the book
                  else if(author == 3)
                  {
                     //make sure there are existing ad hoc teams, if not then the user is notified that
                     //they must create one first
                     if(ad_hoc_teams.size() == 0)
                     {
                        System.out.println("There are no Ad Hoc Teams created. Please create one first.");
                     }
                     //other wise the user is prompted to select an ad hoc team to attach to the book
                     else
                     {
                        System.out.println("Please choose which ad hoc team is the author of the book");
                        for (int i = 0; i < ad_hoc_teams.size(); i++)
                        {
                           System.out.println("\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
                        }
                        int adTeam = getInt();
                        while(adTeam > ad_hoc_teams.size() || adTeam < 1){
                           System.out.println("Please choose which ad hoc team is the author of the book");
                           for (int i = 0; i < ad_hoc_teams.size(); i++)
                           {
                              System.out.println("\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
                           }
                           adTeam = getInt();
                        }
                        //the selected ad hoc team is attached to the book and added to the database
                        Ad_Hoc_Team tempAdHoc = ad_hoc_teams.get(adTeam - 1);
                        createBook(books, tempAdHoc, tempPub);
                     }
                  }
               }
            }
         }
         else if(mainChoice == 3)
         {
            //prompt the user to enter which book they want to change the author of
            System.out.println("\nYou have the option to update the author of a book! Which book would you like to" +
                    " change the author of? Please choose a number: ");
            //display the books in the database
            if(books.size() > 0) {
               for (int j = 0; j < books.size(); j++) {
                  System.out.println("\n" + (j + 1) + ". " + books.get(j));
               }
            }
            //store what book the user chooses
            int whichBook = getInt();
            //prompt the user to enter which type of author they want to make as the new author
            System.out.println("What kind of author is the new author? Please choose one:\n" +
                    "1. Writing Group\n2. Individual Author\n3. Ad Hoc Team");
            int choice = getInt();
            while(choice > 3 || choice < 1)
            {
               System.out.println("Please choose a number in range: \n1. Writing Group\n2. Individual Author\n3. Ad Hoc Team");
               choice = getInt();
            }
            int author;
            //if the user wants a writing group
            if(choice == 1)
            {
               //prompt the user to choose which writing group they want
               System.out.print("Choose which writing group is the new author of your book");
               //display the writing groups in the database
               for (int i = 0; i < writingGroups.size(); i++)
               {
                  System.out.println("\n" + (i + 1) + ". " + writingGroups.get(i));
               }
               //store which number writing group
               author = getInt();
               //change the author of the book
               books.get(whichBook - 1).setAuthoring_Entity(writingGroups.get(author - 1));
            }
            //if the user wants an individual author
            else if(choice == 2)
            {
               //prompt the user to choose which individual author they want
               System.out.print("Choose which individual author is the new author of your book");
               //display the individual authors in the database
               for (int i = 0; i < individualAuthors.size(); i++)
               {
                  System.out.println("\n" + (i+1) + ". " + individualAuthors.get(i));
               }
               //store which number individual author
               author = getInt();
               //change the author of the book
               books.get(whichBook - 1).setAuthoring_Entity(individualAuthors.get(author - 1));
            }
            //if the user wants an ad hoc team
            else if(choice == 3)
            {
               //prompt the user to choose which ad hoc team they want
               System.out.print("Choose which ad hoc team is the new author of your book");
               //display the ad hoc teams in the database
               for (int i = 0; i < ad_hoc_teams.size(); i++)
               {
                  System.out.println("\n" + (i + 1) + ". " + ad_hoc_teams.get(i));
               }
               //store which number ad hoc team
               author = getInt();
               //change the author of the book
               books.get(whichBook - 1).setAuthoring_Entity(ad_hoc_teams.get(author - 1));
            }
         }

         //all new information is added to the database tables
         JPABooks.createEntity(ad_hoc_teams);
         JPABooks.createEntity(publishers);
         JPABooks.createEntity(books);
         JPABooks.createEntity(writingGroups);
         JPABooks.createEntity(individualAuthors);
         tx.commit();
         //the user is now prompted to delete a book or not then they are brought back
         //to the main menu once they are done deleting books
         deleteBook(manager);
         //user is brought back to the main menu to start from the beginning again
         mainChoice = mainMenu();
      }





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
    * return an integer that the user decided to
    */
   public static int mainMenu()
   {
      System.out.println("\nMAIN MENU:\nPlease select an option by entering the menu number:\n1. Display an object\n2. Create an object\n3. Update a book\n4. Exit");
      int choice = getInt();
      while(choice > 4 || choice < 1)
      {
         System.out.println("Please select an option by entering the menu number:\n1. Display an object\n2. Create an object\n3. Update a book\n4. Exit");
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
   public static void createPublishers(List<Publishers> list){
      System.out.println("Enter the publisher's name: ");
      String publishers_name = getString();
      System.out.println("Enter the publisher's number: ");
      String publishers_number = getString();
      System.out.println("Enter the publisher's email: ");
      String publishers_email = getString();
      list.add(new Publishers(publishers_name,publishers_number,publishers_email));

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
   public static void createWritingGroup(List<Writing_Group> list){
      System.out.println("Enter the Writing Group's Name: ");
      String Name = getString();
      System.out.println("Enter the Writing Group's email: ");
      String Email = getString();
      System.out.println("Enter the Writing Group's Head Writer: ");
      String HeadWriter = getString();
      System.out.println("Enter the year the group was formed: ");
      int year = getInt();
      list.add(new Writing_Group(Email,Name,HeadWriter,year));

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

   public static void createAdHocTeam(List<Ad_Hoc_Team> list){
      System.out.println("Enter the author's Name: ");
      String Name = getString();
      System.out.println("Enter the author's email: ");
      String Email = getString();
      list.add(new Ad_Hoc_Team(Email,Name));
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

   /**
    * deleteBook method take in an EntityManager m that corresponds to an existing EntityManager that is manipulating
    * the current database.
    * @param m       The EntityManager that is being used to manipulate the database.
    * @return        NULL
    * */
   public static void deleteBook(EntityManager m){
      //prompt the user to decide if they'd like to delete a book or not
      int choice = 0;
      while(choice != 2){
         System.out.println("\n\n Would you like to delete a book? Please enter a number from the following options: \n1. Yes\n2. No");
         choice = getInt();
         while(choice > 2 || choice < 1){
            System.out.println("\n\nPlease enter a number from the following options: \n1. Yes\n2. No");
            choice = getInt();
         }
         //if they decide to delete a book, then the current books in the database are displayed for the user
         //to delete
         if(choice == 1){
            int choice2 = 0;
            List<Books> b = m.createQuery("Select a From Books a", Books.class).getResultList();
            if(b.size() > 0){
               System.out.println("\n\nPlease select the following book you'd like to delete by entering the number from the following options: ");
               for(int i = 0; i < b.size(); i++){
                  System.out.println((i + 1) + ". Title: " + b.get(i).getTitle() + "\t\tPublisher: " + b.get(i).getPublishers().getName() + "\t\tISBN: " + b.get(i).getISBN());
               }
               choice2 = getInt();
               while(choice2 > b.size() || choice2 < 1){
                  System.out.println("\n\nPlease select the following book you'd like to delete by entering the number from the following options: ");
                  for(int i = 0; i < b.size(); i++){
                     System.out.println((i + 1) + ". Title: " + b.get(i).getTitle() + "\t\tPublisher: " + b.get(i).getPublishers().getName() + "\t\tISBN: " + b.get(i).getISBN());
                     choice2 = getInt();
                  }
               }
               //once the user decides which book to delete, then the database is updated with the correct data
               choice2 = choice2 - 1;
               Books tempB = m.find(Books.class, b.get(choice2).getISBN());
               m.getTransaction().begin();
               m.remove(tempB);
               m.getTransaction().commit();
              //if there are no existing books, the user is notified and exit the menu to delete the book
            } else {
               System.out.println("There are no books to delete!!!");
               choice = 2;
            }

         }

      }
   }

} // End of JPABooks class
