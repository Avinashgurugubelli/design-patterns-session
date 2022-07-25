package code.mediator;

// Observer
public abstract class IVitalObserver {
    public String observerName;
    public abstract void subscribe(Vital vital);
    public abstract void unSubscribe(Vital vital);
    public abstract void update(Vital vital, String value);
}