import java.util.Scanner;
public class Driver{
    
    public static void main(String args[]){
        int numTestCases;
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();
        while(numTestCases-->0){
            int size;
            size = sc.nextInt();
            // String to_match = "Defragment";
            A2DynamicMem obj = new A2DynamicMem(size,2);
            int numCommands = sc.nextInt();
            while(numCommands-->0) {
                String command;
                command = sc.next();
                // System.out.print(command + " ");
                // System.out.print(command==to_match);
                // System.out.print(" ");
                // if (command.equals("Defragment")){
                //     obj.Defragment();
                //     // System.out.println(-100);
                //     // System.out.println("command= "+command);
                //     // obj.printBlk();       
                //     // System.out.println("");
                //     continue;
                // }
                // System.out.println(command);
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        // obj.Defragment();
                        break;
                    case "Defragment":
                        obj.Defragment();
                    default:
                        break;
                }
                System.out.println(result);
                //for testing
                // System.out.println("command= "+command+" argument= "+argument);
                // obj.printBlk();       
                // System.out.println("");       
            }
            
        }
    }
}