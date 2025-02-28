// Task 2 with CodeAlpha company
import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class WordNo {
    public static void main(String args[]){
        JFrame f=new JFrame("text");

        Scanner s=new Scanner(System.in);
        String n=s.nextLine();
        n=n.trim();
        System.out.println("N:"+n);
       // int a=0;
       // String A="";
        if(!n.isEmpty())
        {
         String [] A= n.split("//s+");
        System.out.println("The text After delete the spaces is:"+ A);
        System.out.println("The number of the chars in the text is:"+A.length);
        }
        else System.out.println("There is not found any text");
//        int i=0,k=1;
//        while(i<n.length())
//        {
//            if(n.charAt(i)==' ') k++;
//           // System.out.println("K:"+k);
//            i++;
//        }

    }
}
