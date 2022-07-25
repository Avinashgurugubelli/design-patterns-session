package code.mediator;

public class Client {
    public static void main(String[] args) {
        IVitalObserver watchDisplayer = new VitalObserver("WATCH_DISPLAYER");
        IVitalObserver altersAggregator = new VitalObserver("ALERTS_AGGREGATOR");

        // altersAggregator -> observe only for pulse
        altersAggregator.subscribe(Vital.pulse);

        // Subscribe for both SPO2 and pulse vitals
        watchDisplayer.subscribe(Vital.SPO2);
        watchDisplayer.subscribe(Vital.pulse);

        // Simulate vital sensors
        IVitalSensor spo2Sensor = new SPO2Sensor();
        spo2Sensor.setVitalValue("98");
        spo2Sensor.setVitalValue("97");
        spo2Sensor.setVitalValue("96");

        IVitalSensor pulseSensor = new PulseSensor();
        pulseSensor.setVitalValue("120");
        pulseSensor.setVitalValue("118");
        pulseSensor.setVitalValue("116");

    }
}
