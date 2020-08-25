

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class LogicSimulator
{
    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;
    private Integer ni;
    private Integer ng;

    public String getSimulationResult(Vector<Boolean> value){        //輸入
        String result = "Simulation Result:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" ;
//        +                "0 1 1 | 0\n";

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
        Vector circuits_data = new Vector();
//        ArrayList arrayLists = new ArrayList();
        List list = null;
        System.out.println("list0:"+list);
        while ((str = br.readLine()) != null)
        {
//            circuits_data.add(Arrays.asList(str.split(" ")));
                    list.add(Arrays.asList(str.split(" ")));
            System.out.println("list:"+list);
            System.out.println("listi:"+list.get(2));
//            System.out.println("arr:"+Arrays.asList(str.split(" ")));
//            System.out.println("arri:"+Arrays.asList(str.split(" ")).get(2));
            System.out.println("\n");
//            this.circuits.add(new Vector<>(Arrays.asList(str.split(" "))));
        }
        br.close();
        fr.close();

        System.out.println("circuits_data:"+ circuits_data);

        int data_size = circuits_data.size();
        for(int i=0;i<data_size;i++){
            List data_lists = Arrays.asList(circuits_data.get(i));
            System.out.println("circuits_data.gets:"+data_lists);
            int dl_size = data_lists.size();
            for(int j=0;j<dl_size;j++){
                String data = data_lists.get(j).toString();
                System.out.println("data:"+data_lists.get(j));
                if(data.contains(".")){
//                    int dot_index = arrayLists.get(i).toString().indexOf(".");
//                    System.out.println("al"+i+":"+dot_index);
//                    System.out.println("data:"+data);
                }
            }
        }
//        if()
        return true;
    }
}
