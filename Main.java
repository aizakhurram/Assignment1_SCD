import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

interface Configuration{
    void displayInfo();
    double calculateCost();
}
class Item implements Configuration{
    public String title;
    public boolean isBorrowed;
    public int popCount;
    public static int nextID=1;
    public int cost;
    int id;
    Item(String t, boolean b, int p, int c){
        id=nextID++;
        title=t;
        isBorrowed=b;
        popCount=p;
        cost=c;


    }
    public int gettype(){
       return this.gettype();
    }
 public int getcost(){
    return this.cost;
 }
 public void setTitle(String t){
    this.title=t;
 }
 public String getTitle(){
    return this.title;
 }
 public void setPopCount(){
      popCount=popCount+1;
 }
    @Override
    public void displayInfo(){
          
          System.out.println("Title: "+title+"  Popularity Count: "+popCount+" Cost: "+cost);
          

    }
    @Override
    public double calculateCost(){
          return 0;
    }
}
class Borrower{
    public String name;
     public String borrowedTitle;
    Borrower(String n, String b){
       name=n;
       borrowedTitle=b;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setBorrowed(String b){
       borrowedTitle=b;
    }
    public void display(){
        System.out.println("Name: "+name+"  borrowed: "+borrowedTitle);
    }

}
class Library{
    public ArrayList<Item> items;
    public ArrayList<Borrower> borrowers;
     public Library() {
        items = new ArrayList<>();
        borrowers= new ArrayList<>();
       
    }
    public boolean borrowItem(String n){
        System.out.println("Which Item do you want to Borrow from the available Items: ");
        for(int i=0; i<items.size(); i++){
                if(items.get(i).isBorrowed==false){
                     items.get(i).displayInfo();
                     System.out.println();
                }
        }
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter the title of item you want to borrow");
        String tit= obj.nextLine();
        double cost=0.0;
        for(int i=0; i<items.size(); i++){

                if(items.get(i).getTitle().equalsIgnoreCase(tit)){
                    if(borrowers.isEmpty()){
                        items.get(i).isBorrowed=true;
                              items.get(i).setPopCount();
                              cost= items.get(i).calculateCost();
                            System.out.println("Item Borrowing Cost: "+cost);
                            items.get(i).displayInfo();
                              break;
                    }
                    else{
                        for(int j=0; j<borrowers.size(); j++){
                        if(borrowers.get(j).name.equalsIgnoreCase(n) && borrowers.get(j).borrowedTitle.equalsIgnoreCase(tit)){
                            System.out.println("This user has already borrowed that book once.");
                            return false;
                        }
                        
                        else{
                            
                              items.get(i).isBorrowed=true;
                              items.get(i).setPopCount();
                              cost= items.get(i).calculateCost();
                            System.out.println("Item Borrowing Cost: "+cost);
                            items.get(i).displayInfo();
                              break;
                        }
                    }
                }
                     
                }
               
        }
        Borrower b= new Borrower(n, tit);
        borrowers.add(b);
       


        return true;
    }
    public void HotPicks(){
        Comparator<Item> popularityComparator = new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item2.popCount - item1.popCount;
            }
        };

        
        Collections.sort(items, popularityComparator);
    }
    public void addBook(){
              Scanner obj= new Scanner(System.in);
              System.out.println("Enter the title of the book: ");
              String title=obj.nextLine();
              System.out.println("Enter the Author of the book: ");
              String author=obj.nextLine();
              System.out.println("Enter the year of publishing of the book: ");
              String year=obj.nextLine();
              System.out.println("Enter the Popularity count of the book: ");
              int popc=obj.nextInt();
              System.out.println("Enter the price of the book: ");
              int price=obj.nextInt();

              Book book= new Book(title, author, year, false, popc, price);
              items.add(book);
              
    }
    public void addMagazine(){
        Scanner obj= new Scanner(System.in);
        System.out.println("Enter the title of the Magazine: ");
        String title=obj.nextLine();
        System.out.println("Enter the  publisher company of the Magazine: ");
        String pub=obj.nextLine();
        System.out.println("Enter the Popularity count of the Magazine: ");
        int popc=obj.nextInt();
        System.out.println("Enter the price of the Magazine: ");
        int price=obj.nextInt();
        Magazine magazine= new Magazine(title, pub, false, popc, price);

        System.out.println("Enter the no of Authors of the Magazine: ");
        int size=obj.nextInt();

        for(int i=0; i<size; i++){
            obj.nextLine();
            System.out.println("Enter name of Author "+(i+1)+" :");
            String name=obj.nextLine();
           
             magazine.addAuthor(name);
        
        }
    
        items.add(magazine);
        
}
public void addNewspaper(){
    Scanner obj= new Scanner(System.in);
    System.out.println("Enter the title of the Newspaper: ");
    String title=obj.nextLine();
    System.out.println("Enter the  publisher company of the Magazine: ");
    String pub=obj.nextLine();
    System.out.println("Enter the date of the Newspaper: ");
    String date=obj.nextLine();
    
    System.out.println("Enter the Popularity count of the book: ");
    int popc=obj.nextInt(); 
    NewsPaper news= new NewsPaper(title, pub, date, false, popc);
    items.add(news);
    
}
    public void viewItemByID(int ID){
          for(int i=0; i<items.size(); i++ ){
            if(items.get(i).id==ID){
                items.get(i).displayInfo();
            }
         }
    }
    public void viewAllItems(){
        System.out.println("Items: ");
         for(int i=0; i<items.size(); i++ ){
            items.get(i).displayInfo();
            System.out.println();
         }
    }
    public void viewItem(Item i){
        i.displayInfo();
        System.out.println();
    }
    
    public void deleteItemByID(int ID){
         for(int i=0; i<items.size(); i++ ){
            if(items.get(i).id==ID){
              items.remove(i);
              System.out.println("Book deleted");
            }
         }
    }

public void editItem(int ID){
     Scanner input = new Scanner(System.in);
         for(int i=0; i<items.size(); i++ ){
            if(items.get(i).id==ID){
              System.out.println("What property you want to edit? ");
              System.out.println("Press 1 for title, 2 for author.");
              int choice= input.nextInt();
              input.nextLine();
              switch (choice){
                case 1:
                System.out.println("Enter new title: ");
                String title= input.nextLine();
                items.get(i).setTitle(title);
                System.out.println("Title Updated");
                break;
                // case 2:
                // if(items.get(i).gettype()==2){
                //     System.out.println("Which Author you want to edit?");
                //     String name=input.nextLine();
                //      System.out.println("Edited Author name: ");
                //     String Editedname=input.nextLine();
                //     items.get(i).updateAuthor(name, Editedname);


                // }
                // else{
                // System.out.println("Enter new author: ");
                // String author= input.nextLine();
                // items.get(i).setAuthor(author);
                // }
                // System.out.println("Author Updated");
                
               //break;
                default:
                 System.out.println("Invalid choice. Please try again.");
                    break;


              }
             
            }
            else{
                continue;
            }
             
         }
          input.close();
        
        }
public void ScanItems(){     

        try {
        
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);

            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", "); 

               
                    
                    int itemType = Integer.parseInt(parts[0]);
                    if(itemType==1){
                    String itemTitle = parts[1];
                    String itemAuthor= parts[2];
                    String itemYear = parts[3];
                    int itemPopularityCount = Integer.parseInt(parts[4]);
                    int itemPrice = Integer.parseInt(parts[5]);
                    Book book= new Book(itemTitle, itemAuthor, itemYear, false,itemPopularityCount, itemPrice);
                    items.add(book);
                    }
                    else if(itemType==2){
                    String itemTitle = parts[1];
                    String publisherCompany="";
                    int itemPopularityCount=0;
                    int itemPrice=0;
                    ArrayList<String> author= new ArrayList<>();
                    int index=0;
                    for(int i=2; i<parts.length; i++){
                         if(parts[i].contains(".")){
                              index=i;
                         }
                    }
                    for(int i=2; i<=index; i++){
                           author.add(parts[i]);
                    }
                    
                    for(int j=index+1; j<parts.length; j++){
                     publisherCompany = parts[j];
                    j++;
                     itemPopularityCount = Integer.parseInt(parts[j]);
                    j++;
                     itemPrice = Integer.parseInt(parts[j]);
                    }
                    Magazine magazine= new Magazine(itemTitle, publisherCompany, false, itemPopularityCount, itemPrice);
                    for(int i=0; i<author.size(); i++){
                           magazine.addAuthor(author.get(i));
                    }
                    items.add(magazine);
                    }
                     else if(itemType==3){
                    String itemTitle = parts[1];
                    String Company = parts[2];
                    int itemPopularityCount = Integer.parseInt(parts[3]);
                    String date=parts[4];
                    
                     NewsPaper news= new NewsPaper(itemTitle, Company, date, false, itemPopularityCount);
                     items.add(news);
                     }
            }
                scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }



    }
public void borrowersList(){
        for(int i=0; i<borrowers.size(); i++){
          borrowers.get(i).display();
        }
    }
};
class Book extends Item{
    public int type;
    public String author;
    public String year;
    Book(String t, String a, String y, boolean b, int Count, int cost){
        super(t, b, Count, cost);
     type=1;
     
      this.author=a;
      this.year=y;
    }

     public void setAuthor(String a){
        this.author=a;
    }
     public void setYear(String y){
        this.year=y;
    }
    @Override
     public int gettype(){
        return type;
    }
    @Override
    public void displayInfo() {
       super.displayInfo();
       System.out.println("ID: "+id+" Title: "+title+" by "+author+" ("+year+") ");
    }
    @Override
    public double calculateCost(){
    double percentage= 0.2*super.cost;
    return super.cost+percentage+200;
     
    }

};
class Magazine extends Item{
    public static int nextID=1;
    public int type;
    public ArrayList<String> authors;
    public String publisherCompany;
    Magazine(String t, String y, boolean b, int Count, int cost){
     super(t, b, Count, cost);
        type=2;
       authors = new ArrayList<>();
       publisherCompany=y;
     
    }
    public void addAuthor(String name){
        authors.add(name);
    }
     public void setCompany(String y){
        this.publisherCompany=y;
    }
    public void updateAuthor(String oldAuth, String newAuth){
            for(int i=0; i<authors.size(); i++){
                if(authors.get(i)==oldAuth){
                    authors.set(i, newAuth);
                }
            }


    }
    @Override
     public int gettype(){
        return type;
    }
     @Override
    public double calculateCost(){
    
       return super.cost*super.popCount;
     
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Magazine ID: "+this.id+" Company: "+this.publisherCompany);
        System.out.println("Authors: "+ authors);
      
    }
   
};
class NewsPaper extends Item{
    public static int nextID=1;
    public int type;
    public String Date;
    public String publisherCompany;
    public final static int cost=15;
    NewsPaper(String t, String y,String d, boolean b, int Count){
     super(t, b, Count, cost);
    type=3;
    publisherCompany=y;
     Date=d;
    }
    @Override
    public int gettype(){
        return type;
    }
    public void setDate(String d){
        Date=d;
    }
   
     public void setCompany(String y){
        this.publisherCompany=y;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Newspaper ID: "+this.id+" Date: "+this.Date+" Publisher company: "+publisherCompany);

    }
      @Override
    public double calculateCost(){
    
       return cost;
     
    }
};
public class Main{
   public static void main(String args[]){
        
        Library library = new Library();
        int choice=0;
         Scanner myobj = new Scanner(System.in);
        do {
            System.out.println("_____________________________________________");
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Edit Item");
            System.out.println("3. Delete Item");
            System.out.println("4. View All Items");
            System.out.println("5. View Item by ID");
            System.out.println("6. Load Items from File");
            System.out.println("7. Hot Picks");
            System.out.println("8. Borrow item");
            System.out.println("9. View Borrowers List");
            System.out.println("0. Exit");
             System.out.println("_____________________________________________");
             System.out.println();
             System.out.println();
            System.out.print("Enter your choice: ");
            if(myobj.hasNextInt()) 
            {
               choice = myobj.nextInt();
            }
    
            myobj.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("What type of item you want to add? (Book, Magazine, Newspaper): ");
                    String type= myobj.nextLine();
                    System.out.println(type);
                    if(type.equalsIgnoreCase("Book")){
                        library.addBook();
                        System.out.println("Book Added Successfully");
                        System.out.println();
                    }
                    else if(type.equalsIgnoreCase("Magazine")){
                        library.addMagazine();
                        System.out.println("Magazine Added Successfully");
                        System.out.println();
                    }
                    else if(type.equalsIgnoreCase("Newspaper")){
                        library.addNewspaper();
                        System.out.println("Newspaper added Successfully");
                        System.out.println();
                    } 
                    else{
                        System.out.println("Invalid Type");
                    }
                    break;
                case 2:
                   System.out.println("Enter the ID of Item to be edited: ");
                   int viewID=myobj.nextInt();
                   library.editItem(viewID);
                    break;
                case 3:
                   System.out.println("Enter the ID of Item to be deleted: ");
                   int ID=myobj.nextInt();
                   library.deleteItemByID(ID);

                    break;
                case 4:
                    library.viewAllItems();
                    break;
                case 5:
                     System.out.println("Enter the ID of Item to be viewed: ");
                   int id=myobj.nextInt();
                   library.viewItemByID(id);
                    break;
                case 6:
                    library.ScanItems();
                    System.out.println("Items loaded");
                    break;
                case 7:
                    
                    library.HotPicks();
                    library.viewAllItems();;
                    break;
                case 8:
                    System.out.println("Enter your name: ");
                   String name=myobj.nextLine();
                    if(library.borrowItem(name)==true){
                          System.out.println("Item Borrowed Successfully");
                    }
                    else{
                        System.out.println("Try again.");
                    }
                    break;
                case 9:
                    System.out.println("Borrowers List: ");
                    library.borrowersList();
                    break;
                case 0:
                    
                     System.out.println("exit");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
          
            
        } while (choice != 0);
        
       

        
    

     
}
}
