import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class TextUI
{
    public void displayMenu(){
        System.out.print("1. Load logic circuit file\n" +
                         "2. Simulation\n" +
                         "3. Display truth table\n" +
                         "4. Exit\n" +
                         "Command:");
    }
    public void processCommand(){
        LogicSimulator ls = new LogicSimulator();
        boolean isExit = false;
        boolean isLoad = false;
        while(!isExit){
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()){
                case 1:  //load
                    try {
                        while(!isLoad){
                            isLoad = commandLoad(ls);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:  //Simulation
                    if(!isLoad){
                        System.out.println("Please load an lcf file, before using this operation.\n");
                        break;  //keep choosing
                    }
                    try {
                        commandSimulation(ls);
                    } catch (Exception e) {
                        System.out.println("commandSimulation\n");
                        e.printStackTrace();
                    }
                    break;
                case 3:  //truth table
                    try {
                        if(!isLoad){
                            System.out.println("Please load an lcf file, before using this operation.\n");
                            break;  //keep choosing
                        }
                        System.out.println(ls.getTruthTable());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:  //truth table
                    System.out.println("Goodbye, thanks for using LS.");
                    isExit = true;
                    break;
            }

        }
    }

    private boolean commandLoad(LogicSimulator ls) throws IOException {
        ls.clear();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Please key in a file path:");
        String filepath = scanner.next();
        boolean isLoad = false;
        isLoad = ls.load(filepath);
        String circuit_status = "File not found or file format error!!";
        if(isLoad){
            circuit_status = "Circuit: " + ls.getNI() + " input pins, " + ls.getNO() + " output pins and " + ls.getNG() + " gates\n";
            System.out.println(circuit_status);
        }
        return isLoad;
    }

    private void commandSimulation(LogicSimulator ls) throws Exception{
        Vector<Boolean> inputValues = new Vector<Boolean>();
        Scanner scanner = new Scanner(System.in);
        int ni = ls.getNI();
        for(int i = 0; i<ni; i++){
            System.out.print("Please key in the value of input pin " + i +":");
            String inputPin = scanner.next();
            if(inputPin.equals("0")){
                inputValues.add(false);
            }
            else if(inputPin.equals("1")){
                inputValues.add(true);
            }
            else
            {
                System.out.println("The value of input pin must be 0/1");
                i--;
            }
        }
        System.out.println(ls.getSimulationResult(inputValues));
    }

    private void commandTruthTable(LogicSimulator ls) {
        String result = ls.getTruthTable();
        System.out.println(ls.getTruthTable());
    }

}