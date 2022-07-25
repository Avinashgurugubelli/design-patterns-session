package code.mediator;

public class VitalObserver extends IVitalObserver {
    public  String observerName;
    private IVitalEvent mediator;
    
    public VitalObserver(String observerName) {
        this.observerName = observerName;
        this.mediator = VitalEventMediator.getInstance();
    }

    public void subscribe(Vital vital) {
        this.mediator.subscribe(vital, this);
    }

    public void unSubscribe(Vital vital) {
        this.mediator.subscribe(vital, this);
    }

    public void update(Vital vital, String value) {
        String message = String.format("%s Observer received an update for vital: %s, Vital value: %s", this.observerName, vital, value);
        System.out.println(message);
    }
}
