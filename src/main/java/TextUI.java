import java.io.IOException;
import java.util.Scanner;

public class TextUI
{
    public void displayMenu(){
        System.out.println("1. Load logic circuit file\n" +
                "        2. Simulation\n" +
                "        3. Display truth table\n" +
                "        4. Exit");
    }
    public void processCommand() throws IOException {
        displayMenu();
        LogicSimulator ls = new LogicSimulator();
        boolean isLoad = false;
        System.out.print("Command:");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command){
            case 1:
                System.out.print("Please key in a file path:");
                String filepath = scanner.next();
                System.out.println(filepath);
//                ls.clear();
                String circuit_status = "File not found or file format error!!";
                isLoad = ls.load(filepath);
                if(isLoad){
                    circuit_status = ls.circuitStatus();
                }
                System.out.println(circuit_status);
                break;
            case 2:
                if(!isLoad){
                    System.out.print("Please load an lcf file, before using this operation.");
                    break;  //keep choosing
                }
                for(int i=0; i<ls.getNi(); i++){
                    System.out.print("Please key in the value of input pin " + i +":");
                    Integer inputPin;
                    try
                    {
                        inputPin = scanner.nextInt();
                        if(inputPin.equals("0")){

                        }
                        else if(inputPin.equals("1")) {

                        }
                        else
                        {
                            System.out.println("The value of input pin must be 0/1");
                            i--;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("The value of input pin must be number");
                    }

                }
                break;
        }
    }
}