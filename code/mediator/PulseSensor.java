package code.mediator;

public class PulseSensor implements IVitalSensor {
    private String units;
    private String value;
    private Vital vitalName;
    public IVitalMonitor vitalMonitor;

    public PulseSensor() {
        this.vitalName = Vital.pulse;
        this.units = "BPM";
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
