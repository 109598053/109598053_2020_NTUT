public class GateAND extends Device
{
    @Override
    public boolean getOutput() {
        boolean result = true;
        int size = this.iPins.size();
        for(int i=0; i<size; i++){
            result = result & this.iPins.get(i).getOutput();
        }
        return result;
    }
}