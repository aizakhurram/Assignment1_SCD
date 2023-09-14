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
 public int getcost(){
    return this.cost;
 }
 public void setTitle(String t){
    this.title=t;
 }
    @Override
    public void displayInfo(){
          System.out.println("Item Info: ");
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
                }
        }
        Scanner myobj=new Scanner(System.in);
        System.out.println("Enter the title of item you want to borrow");
        String title=myobj.nextLine();
        for(int i=0; i<items.size(); i++){
                if(items.get(i).title==title){
                    for(int j=0; j<borrowers.size(); j++){
                        if(borrowers.get(j).name==n && borrowers.get(j).borrowedTitle==title){
                            System.out.println("This user has already borrowed that book once.");
                            return false;
                        }
                        else{
                              items.get(i).isBorrowed=true;
                              items.get(i).popCount++;
                              break;
                        }
                    }
                     
                }
                else{
                    System.out.println("Invalid title");
                    return false;
                }
        }
        Borrower b= new Borrower(n, title);
        borrowers.add(b);
        myobj.close();
        return true;
    }
    public void HotPicks(){
        Comparator<Item> popularityComparator = new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                // Compare items based on their popularity counts
                return item2.popCount - item1.popCount;
            }
        };

        
        Collections.sort(items, popularityComparator);
    }
    public void addItem(Item i){

         items.add(i);
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
         }
    }
    public void viewItem(Item i){
        i.displayInfo();
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
                case 2:
                System.out.println("Enter new author: ");
                String author= input.nextLine();
                items.get(i).setAuthor(author);
                System.out.println("Author Updated");
                break;
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

            // Read and process each line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", "); // Split the line by comma and space

               
                    
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

                   

                    // Process the read data (you can use these values as needed)
              
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
    public void displayInfo() {
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
     @Override
    public double calculateCost(){
    
       return super.cost*super.popCount;
     
    }
    @Override
    public void displayInfo(){
        System.out.println("Magazine ID: "+this.id+" Company: "+this.publisherCompany);
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
    public void setDate(String d){
        Date=d;
    }
   
     public void setCompany(String y){
        this.publisherCompany=y;
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
            System.out.print("Enter your choice: ");
            if(myobj.hasNextInt()) 
            {
               choice = myobj.nextInt();
            }
            
            if(myobj.hasNextLine()){
            myobj.nextLine();
            }
            switch (choice) {
                // case 1:
                //     System.out.println("Enter the title of the book: ");
                //     String title= myobj.nextLine();
    
                //     System.out.println("Enter the author of the book: ");
                //     String author= myobj.nextLine();
    
                //     System.out.println("Enter the year of publication of the book: ");
                //     String year=myobj.nextLine();

                //     Book book= new Book(title,author,year);
                //     library.addBook(book);
                //     break;
                case 2:
                   System.out.println("Enter the ID of book to be edited: ");
                   int viewID=myobj.nextInt();
                   library.editItem(viewID);
                    break;
                case 3:
                   System.out.println("Enter the ID of book to be deleted: ");
                   int ID=myobj.nextInt();
                   library.deleteItemByID(ID);

                    break;
                case 4:
                    library.viewAllItems();
                    break;
                case 5:
                     System.out.println("Enter the ID of book to be viewed: ");
                   int id=myobj.nextInt();
                   library.viewItemByID(id);
                    break;
                case 6:
                    library.ScanItems();
                    System.out.println("Books loaded");
                    break;
                case 7:
                    
                    library.HotPicks();
                    library.viewAllItems();;
                    break;
                case 8:
                    System.out.println("Enter your name: ");
                   String name=myobj.nextLine();
                    if(library.borrowItem(name)){
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
         myobj.close();
       

        
    

     
}
}
