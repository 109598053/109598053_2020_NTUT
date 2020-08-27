public class IPin extends Device
{
    private boolean iPin;

    @Override
    public void setInput(boolean value)
    {
        this.iPin = value;
    }

    @Override
    public boolean getOutput()
    {
        return this.iPin;
    }
}