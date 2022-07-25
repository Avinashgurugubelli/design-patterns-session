package code.mediator;

// Singleton class- This could be normal class as well
    // But there is no need to create multiple obj as dependency VitalEventMediator is single ton
    public class VitalMonitor implements IVitalMonitor {
        private static VitalMonitor singleInstance;
        private IVitalEvent mediator;

        // private constructor
        private VitalMonitor(){
            this.mediator = VitalEventMediator.getInstance();
        };

        public static VitalMonitor getInstance() {
            if (singleInstance == null)
            singleInstance = new VitalMonitor();
            return singleInstance;
        }

        public void notifyVital(Vital vital, String value) {
            this.mediator.notifyVital(vital, value);
        }
    }