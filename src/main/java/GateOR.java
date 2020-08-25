public class GateOR extends Device{
    @Override
    public boolean getOutput() {
        int size = this.iPins.size();
        boolean result = false;
        for(int i=0; i<size; i++){
            result = result | this.iPins.get(i).getOutput();
        }
        return result;
    }
}
