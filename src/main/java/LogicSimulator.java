import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class LogicSimulator
{
    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;
    private Integer ni;
    private Integer no;
    private Integer ng;

    public LogicSimulator(){
        this.circuits = new Vector<>();
        this.iPins = new Vector<>();
        this.oPins = new Vector<>();
    }

    public String getSimulationResult(Vector<Boolean> inputValues){
        String result = "Simulation Result:\n";
        result += tableTitle();
        for(int i=0;i<ni;i++){
            iPins.get(i).setInput(inputValues.get(i));
        }
        for(int i=0;i<ni;i++){
            result += (iPins.get(i).getOutput() ? "1":"0") + " ";
        }
        result += "|";
        result += getOutputsResult();
        return result;
    }

    public String getTruthTable(){  //ipin
        String result = "Truth table:\n";
        result += tableTitle();
        int intResult = (int) Math.pow(2, ni);

        for(int i=0;i<intResult;i++){
            String inputString = Integer.toBinaryString(i);
            Vector<Boolean> inputValues = new Vector<>();
            int inputLength = inputString.length();
            while(inputLength<ni){
                inputValues.add(false);  //padding 0
                result += "0 ";
                inputLength++;
            }
            inputLength = inputString.length();
            for(int j=0;j<inputLength;j++){
                String inputSubString = inputString.substring(j, j+1);
                inputValues.add(inputSubString.equals("1"));
                result = result + inputSubString + " ";
            }

            result += "|";

            for(int k=0;k<ni;k++){
                iPins.get(k).setInput(inputValues.get(k));
            }
            //opin
            result += getOutputsResult();
        }
        return result;
    }

    public boolean load(String filepath){
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            setNI(Integer.valueOf(br.readLine()));
            setNG(Integer.valueOf(br.readLine()));
            String str;
            List circuits_data = new ArrayList<List>();
            Vector circuits_vector = new Vector();
            while ((str = br.readLine()) != null) {
                circuits_data = Arrays.asList(str.split(" "));  //[1, -1, 2.1, 3.1, 0]
                circuits_vector.add(circuits_data);  //[[1, -1, 2.1, 3.1, 0], [3, -2, 0], [2, 2.1, -3, 0]]
                //buildGates
                String circuit_type = circuits_data.get(0).toString();
                switch (circuit_type) {
                    case "1":
                        circuits.add(new GateAND());
                        break;
                    case "2":
                        circuits.add(new GateOR());
                        break;
                    case "3":
                        circuits.add(new GateNOT());
                        break;
                }
            }
            br.close();
            fr.close();

            buildCircuits(circuits_vector, circuits_data);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private void buildCircuits(Vector circuits_vector, List circuits_data) {
        buildIPins();

        boolean[] isOPin = new boolean[ng];
        for(int i=0;i<ng;i++){
            isOPin[i] = true;
        }

        int cv_size = circuits_vector.size();
        for (int i = 0; i < cv_size; i++) {
            circuits_data = (List) circuits_vector.get(i);
            int j = 1;
            while (!circuits_data.get(j).equals("0")) {
                String data = circuits_data.get(j).toString();
                if (data.contains(".")) {
                    int op = Integer.parseInt(data.substring(0, data.indexOf(".")));
                    circuits.get(i).addInputPin(circuits.get(op-1));
                    isOPin[op-1] = false;
                } else if (data.contains("-")) {
                    int ip =  - Integer.parseInt(data);
                    circuits.get(i).addInputPin(iPins.get(ip - 1));
                }
                j++;
            }
        }

        buildOPins(isOPin);
    }

    private String tableTitle() {
        String result = "";
        for(int i=0;i<ni;i++){
            result += "i ";
        }
        result += "|";
        no = getNO();
        for(int i=0;i<no;i++){
            result += " o";
        }
        result += "\n";

        for(int i=1;i<=ni;i++){
            result = result + i + " ";
        }
        result += "|";
        for(int i=1;i<=no;i++){
            result = result + " " + i;
        }
        result += "\n";

        for(int i=1;i<=ni;i++){
            result += "--";
        }
        result += "+";
        for(int i=1;i<=no;i++){
            result += "--";
        }
        result += "\n";
        return result;
    }

    private String getOutputsResult() {
        String result = "";
        for(int i=0;i<no;i++){
            result = result + " " + (oPins.get(i).getOutput() ? "1" : "0");
        }
        result += "\n";
        return result;
    }

    private void buildIPins() {
        for (int i = 0; i < ni; i++) {
            iPins.add(new IPin());
        }
    }
    private void buildOPins(boolean[] isOPin) {
        for (int i = 0; i < ng; i++) {
            if(isOPin[i]){
                oPins.add(new OPin());
                oPins.get(oPins.size()-1).addInputPin(circuits.get(i));
            }
        }
    }

    public Integer getNO(){
        no = oPins.size();
        return no;
    }
    public Integer getNI(){
        return ni;
    }
    public Integer getNG(){
        return ng;
    }

    private void setNI(Integer value){
        this.ni = value;
    }
    private void setNG(Integer value){
        this.ng = value;
    }

    public void clear() {
        this.circuits.clear();
        this.iPins.clear();
        this.oPins.clear();
    }
}
