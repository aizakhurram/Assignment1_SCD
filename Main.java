import java.util.Scanner;
class Book{
    public static int nextID=1;
    public int type;
    public int id;
    public String title;
    public String author;
    public int year;
    Book(String t, String a, int y){
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
     public void setYear(int y){
        this.year=y;
    }
    public void Display() {
       System.out.println("ID: "+id+" Title: "+title+" by "+author+" ("+year+") ");
    }
   
};

public class Main{
   public static void main(String args[]){
        
       Scanner myobj=new Scanner(System.in);
       System.out.println("Enter the title of the book: ");
       String title= myobj.nextLine();
    
       System.out.println("Enter the author of the book: ");
       String author= myobj.nextLine();
    
       System.out.println("Enter the year of publication of the book: ");
       int year=myobj.nextInt();

       Book book= new Book(title,author,year);
       book.Display();
    myobj.close();

     
}
}

