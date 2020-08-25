

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LogicSimulator
{
    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;
    private Integer ni;
    private Integer ng;

    public LogicSimulator(){
        this.circuits = new Vector<>();
        this.iPins = new Vector<>();
        this.oPins = new Vector<>();
    }
    public String getSimulationResult(Vector<Boolean> inputValues){        //輸入
        String result = "Simulation Result:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" ;
//        +                "0 1 1 | 0\n";

        for(int i=0;i<inputValues.size();i++){
//            this.iPins.addInputPin(inputValues.get(i));
        }
        return result;
    }

    public String getTruthTable(){        //顯示完整表格
        return "getTruthTable";
    }

    public boolean load(String filepath) throws IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        ni = Integer.valueOf(br.readLine());//number_input
        ng = Integer.valueOf(br.readLine());//number_gate
        String str = null;
        List circuits_data = new ArrayList<List>();
        Vector circuits_vector = new Vector();
        while ((str = br.readLine()) != null)
        {
            circuits_data = Arrays.asList(str.split(" "));  //[1, -1, 2.1, 3.1, 0]
            circuits_vector.add(circuits_data);  //[[1, -1, 2.1, 3.1, 0], [3, -2, 0], [2, 2.1, -3, 0]]
            //buildGates
            String circuit_type = circuits_data.get(0).toString();
            switch (circuit_type)
            {
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


        int cv_size = circuits_vector.size();
        for(int i=0;i<cv_size;i++){
            circuits_data = (List) circuits_vector.get(i);
            System.out.println("circuits_data.gets:"+circuits_data);
            int j=1;
            while(!circuits_data.equals("0")){
                String data = circuits_data.get(j).toString();
                System.out.println("circuits_data:"+circuits_data);
                System.out.println("datai:"+data);
                if(data.contains(".")){
                    int op = Integer.valueOf(data);
                    circuits.get(circuits.size()-1).addInputPin(oPins.get(op-1));
//                    oPinUsedTimes.set(op-1, oPinUsedTimes.get(op-1)+1);
                }
                else if(data.contains("-")){
                    int ip = (-1) * Integer.parseInt(data);
                    circuits.get(circuits.size()-1).addInputPin(iPins.get(ip-1));
            }
        }
                j++;
            }
        return true;
////        if()
    }

}
