import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Driver{

    public static void main(String args[]){
        int numTestCases;
        long start = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();
        int x = 2;
        while(numTestCases-->0){
            int size;
            size = sc.nextInt();
            // String to_match = "Defragment";
            
            A2DynamicMem obj = new A2DynamicMem(size,x);
            // obj.printBlk();       
            // System.out.println("");
            int numCommands = sc.nextInt();
            while(numCommands-->0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        System.out.println(result);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        System.out.println(result);
                        break;
                    case "Defragment":
                        obj.Defragment();
                       //System.out.println(result);
                    default:
                        break;
                }
                
                //for testing
                // System.out.println("command= "+command+" argument= "+argument + "   ");
                // obj.printBlk();       
                // System.out.println("");       
            }
            
        }
        long end = System.currentTimeMillis();
        if(x==2)System.out.println("Time to execute program for BST: " + (double)(end - start)/1000 + "s"); 
        else System.out.println("Time to execute program for AVL: " + (double)(end - start)/1000 + "s"); 
    }
}
