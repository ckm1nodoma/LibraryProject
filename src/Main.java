import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int ltemp = 0, temp, temp1;
        String stemp;
        String[] satemp;
        boolean btemp = false;
        Vector<Integer> vtemp = new Vector<Integer>();

        HashMap<Integer, Book> books = new HashMap<Integer, Book>();
        HashMap<Integer, Member> members = new HashMap<Integer, Member>();

        System.out.println("Do you want to start with prepared library?\n 1 - yes\n 2 - no");
        temp = sc.nextInt();
        if(temp == 1){
            Member Oleg = new Member("Oleg", 17, 17743);
            Member Maciej = new Member("Maciej", 20, 17744);
            members.put(0, Maciej);
            members.put(1, Oleg);
            Book CodingJava = new Book("Coding in Java", "Coder", 2008);
            books.put(0,CodingJava);
            Book CodingC = new Book("Coding in C", "Marcin Luter King", 2011);
            books.put(1,CodingC);
            Book CodingPython = new Book("Coding in Python", "Todorika", 1999);
            books.put(2,CodingPython);
            Book book1 = new Book("1984", "Orwell", 1984);
            books.put(3,book1);
            Book book2 = new Book("SpiderWik", "Alexa", 2013);
            books.put(4,book2);
        }

        while (true){
            System.out.println("\n1. See all books \n" +
                    "2. Add book\n" +
                    "3. Remove book\n" +
                    "4. See all members\n" +
                    "5. Add member\n" +
                    "6. Remove member\n" +
                    "7. Take book\n" +
                    "8. Return book\n" +
                    "9. Save file\n" +
                    "10. Read file\n");

            temp = sc.nextInt();
            switch (temp){
                case 10:
                    FileReader fr = new FileReader("database1.txt");
                    Scanner frscan = new Scanner(fr);
                    while (frscan.hasNextLine()){ // books
                        stemp = frscan.nextLine();
                        if(stemp.equals("Members: ")) break;
                        if(stemp.equals("Books: ")) stemp = frscan.nextLine();
                        satemp = stemp.split("_");
                        books.put(Integer.valueOf(satemp[0]),
                                new Book(satemp[1], satemp[2], Integer.parseInt(satemp[4]), Boolean.parseBoolean(satemp[3])));
                    }
                    System.out.println();
                    while (frscan.hasNextLine()){ // members
                        stemp = frscan.nextLine();
                        satemp = stemp.split("_");
                        members.put(Integer.valueOf(satemp[0]),
                                new Member(satemp[1], Integer.parseInt(satemp[2]), Integer.parseInt(satemp[3]),
                                        Integer.parseInt(satemp[4]), Integer.parseInt(satemp[5]), Integer.parseInt(satemp[6])));

                    }
                    fr.close();
                    break;
                case 9:
                    FileWriter fw = new FileWriter("database1.txt");
                    fw.write("Books: \n");
                    for(Map.Entry<Integer,Book> set : books.entrySet()){
                        fw.write(set.getKey() + "_" + set.getValue().getName() + "_");
                        stemp = set.getValue().getAuthor();
                        fw.write(stemp+"_");
                        btemp = set.getValue().getAvailable();
                        fw.write(String.valueOf(btemp)+"_");
                        temp = set.getValue().getYear();
                        fw.write(String.valueOf(temp));
                        fw.write("\n");
                    }
                    fw.write("Members: \n");
                    for (Map.Entry<Integer, Member> set : members.entrySet()){
                        fw.write(set.getKey() + "_" + set.getValue().getName() + "_");
                        fw.write(set.getValue().getAge() + "_" + set.getValue().getNumber()+"_");
                        for (int i : set.getValue().getRecord() ) {
                            fw.write(i+"_");
                        }
                        fw.write("\n");
                    }

                    fw.close();
                    return;
                case 1:
                    System.out.println("1. See all books\n" +
                            "2. See only available books\n" +
                            "3. See only unavailable books");
                    temp = sc.nextInt();
                    if(temp == 1){
                        books.forEach((key, value) -> {
                            System.out.println();
                            System.out.println((1+key)+". ");
                            value.Info();
                        });
                    } else if(temp ==2){
                        books.forEach((key, value) -> {
                            if(value.getAvailable()){
                                System.out.println();
                                System.out.println((1+key)+". ");
                                value.Info();
                            }
                        });
                    } else if (temp == 3) {
                        books.forEach((key, value) -> {
                            if(!value.getAvailable()){
                                System.out.println();
                                System.out.println((1+key)+". ");
                                value.Info();
                            }
                        });
                    }
                    break;
                case 2:
                    vtemp.clear();
                    temp = 0;
                    books.forEach((key, value) -> {
                        vtemp.add(key);
                    });
                    for(int i = 0; i < (vtemp.size()-1); i++){
                        if((vtemp.get(i)+1) != vtemp.get(i+1)){
                            temp = i+1;
                            break;
                        }
                    }
                    if(temp == 0) books.put(books.size(), new Book());
                    else books.put(temp, new Book());
                    break;
                case 3:
                    System.out.println("1. Remove book by name\n" +
                            "2. Remove book by number\n");
                    temp = sc.nextInt();
                    if(temp == 2){
                        System.out.println("Enter book number: ");
                        temp = sc.nextInt();
                        System.out.println("Removed book is: ");
                        books.get(temp-1).Info();
                        books.remove(temp-1);
                    }else if(temp == 1){
                        sc.nextLine();//Buffer clear
                        System.out.println("Enter book name: ");
                        stemp = sc.nextLine();
                        for(Map.Entry<Integer, Book> set: books.entrySet()){
                            if(set.getValue().getName().equals(stemp)){
                                System.out.println("Removed book is: ");
                                books.get(set.getKey()).Info();
                                temp = set.getKey();
                            }
                        }
                        books.remove(temp);
                    }
                    break;
                case 4:
                    vtemp.clear();
                    temp = 1; //counter
                    for(Map.Entry<Integer, Member> set: members.entrySet()){
                        System.out.println("\n" + temp + ": ");
                        set.getValue().Info();
                        for (int i : set.getValue().getRecord() ) {
                            if(i == -1) System.out.println("Empty ");
                            else{
                                for(Map.Entry<Integer, Book> b: books.entrySet()){
                                    if(b.getKey() == i){
                                        stemp = b.getValue().getName();
                                        System.out.println(stemp);
                                        break;
                                    }
                                }
                            }
                        }
                        temp++; //counter
                    }
                    break;
                case 5:
                    vtemp.clear();
                    temp = 0;
                    members.forEach((key, value) -> {
                        vtemp.add(key);
                    });
                    for(int i = 0; i < (vtemp.size()-1); i++){
                        if((vtemp.get(i)+1) != vtemp.get(i+1)){
                            temp = i+1;
                            break;
                        }
                    }
                    if(temp == 0) members.put(members.size(), new Member());
                    else members.put(temp, new Member());
                    break;
                case 6:
                    System.out.println("1. Remove member by name\n" +
                            "2. Remove member by number\n");
                    temp = sc.nextInt();
                    if(temp == 2){
                        System.out.println("Enter member number: ");
                        temp = sc.nextInt();
                        System.out.println("Removed member is: ");
                        members.get(temp-1).Info();
                        members.remove(temp-1);
                    }else if(temp == 1){
                        sc.nextLine();//Buffer clear
                        System.out.println("Enter member name: ");
                        stemp = sc.nextLine();
                        for(Map.Entry<Integer, Member> set: members.entrySet()){
                            if(set.getValue().getName().equals(stemp)){
                                System.out.println("Removed member is: ");
                                members.get(set.getKey()).Info();
                                temp = set.getKey();
                            }
                        }
                        members.remove(temp);
                    }
                    break;
                case 7:
                    System.out.println("Enter id.number of member: ");
                    temp = sc.nextInt();
                    for(Map.Entry<Integer, Member> set : members.entrySet()){
                        if(temp-1 == set.getKey()){
                            temp = set.getKey(); //taking id of member
                            break;
                        }
                    }
                    System.out.println("Enter number of book you want to take: ");
                    temp1 = sc.nextInt();
                    if(!books.get(temp1-1).getAvailable()) System.out.println("Sorry, this book is already taken. ");
                    else if(!members.get(temp).RecordAvailable()) System.out.println("Sorry, record of this member is already full. ");
                    else{
                        System.out.println(members.get(temp).getName() + " has taken " + books.get(temp1-1).getName());
                        members.get(temp).takeBook(temp1-1);
                        books.get(temp1-1).Take();
                    }
                    break;
                case 8:
                    vtemp.clear();
                    temp1 = 0;
                    System.out.println("Enter id.number of member");
                    temp = sc.nextInt();
                    for(Map.Entry<Integer, Member> set : members.entrySet()){
                        if(temp-1 == set.getKey()){
                            temp = set.getKey(); //taking id of member
                            ltemp = temp;
                            break;
                        }
                    }
                    System.out.println("Member "+ members.get(temp).getName() + " Id: "+members.get(temp).getNumber());
                    System.out.println("Record: ");
                    for (int i : members.get(temp).getRecord() ) {
                        if(i != -1){
                            for(Map.Entry<Integer, Book> b: books.entrySet()){
                                if(b.getKey() == i){
                                    temp1 +=1; //check if there are any books
                                    vtemp.add(b.getKey());
                                    stemp = b.getValue().getName();
                                    System.out.println(temp1+". "+stemp);
                                    break;
                                }
                            }
                        }
                    }
                    if(temp1 < 1){
                        System.out.println("This member has no books");
                        break;
                    }
                    System.out.println("Which book you would like to return? ");
                    temp = sc.nextInt();
                    if(temp > temp1 || temp < temp1){
                        System.out.println("Incorrect command, try again");
                        break;
                    }
                    books.get(members.get(ltemp).returnBook(vtemp.get(temp-1))).Return();
                    break;
                default:
                    System.out.println("Unknown command, try again.");
                    break;
            }

        }
    }
}

