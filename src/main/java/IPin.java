public class IPin extends Device
{
    boolean iPin = false;

    @Override
    public void setInput(boolean value)
    {
        iPin = value;
    }

    @Override
    public boolean getOutput()
    {
        return iPin;
    }
}