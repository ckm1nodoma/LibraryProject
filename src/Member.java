import java.util.Scanner;

public class Member {
    Scanner sc = new Scanner(System.in);
    //fields
    private String name;
    private int age;
    private int identnumber;
    private int[] record = {-1,-1,-1};

    //constructors
    public Member(String name, int age, int number){
        this.name = name;
        this.age = age;
        this.identnumber = number;
    }
    public Member(){
        addMember();
    }
    public Member(String name, int age, int number, int a, int b, int c){
        this.name = name;
        this.age = age;
        this.identnumber = number;
        this.record[0] = a;
        this.record[1] = b;
        this.record[2] = c;
    }

    //setters and getters
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    public void setNumber(int number){
        this.identnumber = number;
    }
    public int getNumber(){
        return this.identnumber;
    }
    public int[] getRecord(){
        return this.record;
    }
    public boolean RecordAvailable(){
        for(int i = 0; i < 3; i++){
            if(record[i] == -1){
                return true;
            }
        }
        return false;
    }
    //methods
    public void Info(){
        System.out.println("Member: "+this.name);
        System.out.println("Age: "+ this.age);
        System.out.println("Identification nubmer: "+ this.identnumber);
        System.out.println("Record: ");
    }
    public void takeBook(int a){
        for(int i = 0; i < 3; i++){
            if(record[i] == -1){
                record[i] = a;
                return;
            }
        }
    }
    public int returnBook(int a){
        int temp = 0;
        for(int i = 0; i < 3; i++){
            if(record[i] == a){
                temp = a;
                record[i] = -1;
                return temp;
            }
        }
        return -1;
    }
    public void readRecord(int a, int b , int c){
        record[0] = a;
        record[1] = b;
        record[2] = c;
    }
    public void addMember(){
        String name;
        int age, identnumber;
        System.out.println("Enter name of the member: ");
        name = sc.nextLine();
        System.out.println("Enter age of member: ");
        age = sc.nextInt();
        System.out.println("Enter identification number of member: ");
        identnumber = sc.nextInt();


        this.name = name;
        this.age = age;
        this.identnumber = identnumber;
    }
}
