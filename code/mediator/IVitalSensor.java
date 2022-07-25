package code.mediator;

public interface IVitalSensor {
    public String getVitalValue();
    public void setVitalValue(String value);
    public void notifyVital();
}
