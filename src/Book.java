    import java.util.Scanner;

    public class Book {
        Scanner sc = new Scanner(System.in);
        //fields
        private String name;
        private String author;
        private int year;
        private boolean available = true;
        //constructors
        public Book(String name, String author, int year){
            this.name = name;
            this.author = author;
            this.year = year;
        }
        public Book(String name, String author, int year, boolean available){
            this.name = name;
            this.author = author;
            this.year = year;
            this.available = available;
        }
        public Book(){
            addBook();
        }
        //methods
        public void Info(){
            System.out.println("Name: "+this.name);
            System.out.println("Author: "+this.author);
            System.out.println("Year: "+this.year);
            System.out.println("Availability: "+available);
        }
        public void Take(){
            this.available = false;
        }
        public void Return(){
            this.available = true;
        }
        public void addBook(){
            int year;
            String author, name;
            System.out.println("Enter name of the book: ");
            name = sc.nextLine();
            System.out.println("Enter author of the book: ");
            author = sc.nextLine();
            System.out.println("Enter year of the book: ");
            year = sc.nextInt();


            this.name = name;
            this.author = author;
            this.year = year;
        }
        //setters and getters
        public String getName(){
            return this.name;
        }
        public int getYear(){
            return this.year;
        }
        public String getAuthor(){
            return this.author;
        }
        public boolean getAvailable(){
            return this.available;
        }


    }
