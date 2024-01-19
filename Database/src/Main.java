package Database.src;
import java.sql.SQLException;
import java.util.*;
public class Main {
    public Main() throws SQLException {
        Scanner in=new Scanner(System.in);
        System.out.println("    WELCOME");
        System.out.println("SELECT AN OPTION");
        System.out.println("1.SIGN UP \n2.SIGN IN \n3.ADMIN");
        System.out.println("ENTER YOUR OPTION:");
        int op=in.nextInt();
        if(op==1){
            System.out.println("ENTER YOUR EMAIL:");
            String email=in.next();
            System.out.println("ENTER YOUR PASSWORD:");
            String password=in.next();
            new SignUp(email,password);
        }else if(op==2){
            System.out.println("ENTER YOUR EMAIL:");
            String email=in.next();
            System.out.println("ENTER YOUR PASSWORD:");
            String password=in.next();
            new SignIn(email,password);
        }else if(op==3){
            System.out.println("ENTER YOUR EMAIL:");
            String email=in.next();
            System.out.println("ENTER YOUR PASSWORD:");
            String password=in.next();
            new Admin(email,password);
        }else{
            System.out.println("ENTER VALID INPUT");
            new Main();
        }
    }
    public static void main(String[] args) throws SQLException {
        new Main();
    }
}