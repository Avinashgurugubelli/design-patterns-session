package code.mediator;

public class SPO2Sensor implements IVitalSensor {
    private String units;
    private String value;
    private Vital vitalName;
    public IVitalMonitor vitalMonitor;

    public SPO2Sensor() {
        this.vitalName = Vital.SPO2;
        this.units = "%";
        this.vitalMonitor = VitalMonitor.getInstance();
    }
    public String getVitalValue() {
        return value;
    }
    public void setVitalValue(String value) {
        this.value = value;
        this.notifyVital();
    }

    public void notifyVital() {
        // notify value
        this.vitalMonitor.notifyVital(this.vitalName, this.value + " " + this.units);
    }
}
