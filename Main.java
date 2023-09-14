import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
class Library{
    public ArrayList<Book> books;
     public Library() {
        books = new ArrayList<>();
       
    }
    public void addBook(Book b){
         books.add(b);
    }
    public void viewBookByID(int ID){
          for(int i=0; i<books.size(); i++ ){
            if(books.get(i).id==ID){
                books.get(i).Display();
            }
         }
    }
    public void viewAllBooks(){
        System.out.println("Books: ");
         for(int i=0; i<books.size(); i++ ){
            books.get(i).Display();
         }
    }
    public void deleteBookByID(int ID){
         for(int i=0; i<books.size(); i++ ){
            if(books.get(i).id==ID){
              books.remove(i);
              System.out.println("Book deleted");
            }
         }
    }

public void editBook(int ID){
     Scanner input = new Scanner(System.in);
         for(int i=0; i<books.size(); i++ ){
            if(books.get(i).id==ID){
              System.out.println("What property you want to edit? ");
              System.out.println("Press 1 for title, 2 for author and 3 for year of publication");
              int choice= input.nextInt();
              input.nextLine();
              switch (choice){
                case 1:
                System.out.println("Enter new title: ");
                String title= input.nextLine();
                books.get(i).setTitle(title);
                System.out.println("Title Updated");
                break;
                case 2:
                System.out.println("Enter new author: ");
                String author= input.nextLine();
                books.get(i).setAuthor(author);
                System.out.println("Author Updated");
                break;
                case 3:
                 System.out.println("Enter new year of publication: ");
                String year= input.nextLine();
                books.get(i).setYear(year);
                System.out.println("Year Updated");
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
            public void ScanBooks(){
            String path="books.txt";
            System.out.println("book opened");
           List<String[]> data=new ArrayList<>();
            
             try {
            Scanner scanner = new Scanner(new File(path));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] values = line.split(",");
                data.add(values);
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       for (String[] row : data) {
        String title = row[0];
        String author = row[1];
        String year = row[2];
        Book b = new Book(title, author, year); // Create a new Book object
        books.add(b);
       }
    }
};
class Book{
    public static int nextID=1;
    public int type;
    public int id;
    public String title;
    public String author;
    public String year;
    Book(String t, String a, String y){
     type=1;
      this.id=nextID++;
      this.title=t;
      this.author=a;
      this.year=y;
    }
    public void setTitle(String t){
        this.title=t;
    }
     public void setAuthor(String a){
        this.author=a;
    }
     public void setYear(String y){
        this.year=y;
    }
    public void Display() {
       System.out.println("ID: "+id+" Title: "+title+" by "+author+" ("+year+") ");
    }
};

public class Main{
   public static void main(String args[]){
        
        Library library = new Library();
        int choice=0;
         Scanner myobj = new Scanner(System.in);
        do {
           
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Edit Book");
            System.out.println("3. Delete Book");
            System.out.println("4. View All Books");
            System.out.println("5. View Book by ID");
            System.out.println("6. Load Books from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            if(myobj.hasNextInt()) 
            {
               choice = myobj.nextInt();
            }
            
            if(myobj.hasNextLine()){
            myobj.nextLine();
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter the title of the book: ");
                    String title= myobj.nextLine();
    
                    System.out.println("Enter the author of the book: ");
                    String author= myobj.nextLine();
    
                    System.out.println("Enter the year of publication of the book: ");
                    String year=myobj.nextLine();

                    Book book= new Book(title,author,year);
                    library.addBook(book);
                    break;
                case 2:
                    System.out.println("Enter the ID of book to be edited: ");
                   int viewID=myobj.nextInt();
                   library.editBook(viewID);
                    break;
                case 3:
                   System.out.println("Enter the ID of book to be deleted: ");
                   int ID=myobj.nextInt();
                   library.deleteBookByID(ID);

                    break;
                case 4:
                    library.viewAllBooks();
                    break;
                case 5:
                     System.out.println("Enter the ID of book to be viewed: ");
                   int id=myobj.nextInt();
                   library.viewBookByID(id);
                    break;
                case 6:
                    library.ScanBooks();
                    System.out.println("Books loaded");
                    break;
                case 7:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
          
            
        } while (choice != 7);
         myobj.close();
       

        
    

     
}
}
