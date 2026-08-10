import java.util.Scanner;
public class passValidator{
    public static void main(String[]args){
        String uname = "Bilal";
        int pass  = 1234;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter admin pass :");
        String enterpass = sc.nextLine();
        if(pass == enterpass){
            System.out.println("Admin access granted");
        }
        else{
            System.out.println("Enter corret password");
        }
        
    }
}