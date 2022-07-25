package code.mediator;

public interface IVitalEvent {
    public void subscribe(Vital vital, IVitalObserver subscriber);
    public void unSubscribe(Vital vital, IVitalObserver subscriber);
    public void notifyVital(Vital vital, String value);
}
