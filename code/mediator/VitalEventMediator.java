package code.mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Singleton class
// Event Aggregator
public class VitalEventMediator implements IVitalEvent {
    private Map<Vital, List<IVitalObserver>> vitalObservers = new HashMap<>();

    private static VitalEventMediator singleInstance;

    // private constructor
    private VitalEventMediator() {};

    public static VitalEventMediator getInstance() {
        if (singleInstance == null)
            singleInstance = new VitalEventMediator();
        return singleInstance;
    }

    public void subscribe(Vital vital, IVitalObserver subscriber) {
        if (!vitalObservers.containsKey(vital)) {
            List<IVitalObserver> observers = new ArrayList<IVitalObserver>();
            observers.add(subscriber);
            vitalObservers.put(vital, new ArrayList<IVitalObserver>(){
            });
        }
        vitalObservers.get(vital).add(subscriber);
    }
    public void unSubscribe(Vital vital, IVitalObserver subscriber) {
        // remove observer -> from given vital and then remove subscriber from the list
    }
    public void notifyVital(Vital vital, String value) {
        if (vitalObservers.containsKey(vital)) {
            List<IVitalObserver> observers = vitalObservers.get(vital);
            for(IVitalObserver observer: observers) {
                observer.update(vital, value);
            }
        }
    }
}