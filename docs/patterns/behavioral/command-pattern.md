# Command Pattern


``` Java

    public enum ListenCommand {
        start,
        stop,
        status
    }
    public interface IListener {
        public void start();
        public void stop();
        public void status();
    }

    public abstract class ICommand {
        private IListener listener;
        public void Execute();
    }

    public class StartCommand implements ICommand {
         public StartCommand(IListener listener) {
            this.listener = listener;
         }
         public void Execute() {
            System.out.println("Start command executed");
            this.listener.start();
         }
    }

    public class StopCommand implements ICommand {
        public StopCommand(IListener listener) {
            this.listener = listener;
        }
         public void Execute() {
            System.out.println("Stop command executed");
            this.listener.stop();
         }
    }

    public class StatusCommand implements ICommand {
        public StatusCommand(IListener listener) {
            this.listener = listener;
        }
         public void Execute() {
            System.out.println("Status command executed");
            this.listener.status();
         }
    }


    public class ListenerA implements IListener {
        private static ListenerA instance;
        private Boolean state;

        //single ton
        private ListenerA() {}

         public static ListenerA getInstance() {
            if(instance == null){
                instance =  new ListenerA();
            }
            return instance;
        }

        public void start() {
            this.state = true;
            System.out.println(">>>>>>>>>> ListenerA started >>>>>>>>>");
        }
        public void stop() {
            this.state = false;
            System.out.println(">>>>>>>>>> ListenerA stopped >>>>>>>>>");

        }
        public void status() {
            if(this.state) {
                System.out.println(">>>>>>>>>> ListenerA is running >>>>>>>>>");
            }
            System.out.println(">>>>>>>>>> ListenerA has stopped >>>>>>>>>");
        }
    }

    public class ListenerB implements IListener {
        private static ListenerA instance;
        private Boolean state;

        //single ton
        private ListenerB() {}

        public static ListenerB getInstance() {
            if(instance == null){
                instance =  new ListenerB();
            }
            return instance;
        }

        public void start() {
            this.state = true;
            System.out.println(">>>>>>>>>> ListenerB started >>>>>>>>>");
        }
        public void stop() {
            this.state = false;
            System.out.println(">>>>>>>>>> ListenerB stopped >>>>>>>>>");

        }
        public void status() {
            if(this.state) {
                System.out.println(">>>>>>>>>> ListenerB is running >>>>>>>>>");
            }
            System.out.println(">>>>>>>>>> ListenerB has stopped >>>>>>>>>");
        }
    }

    public class Program {

        public static void main (String[] args) {
            ListenerA listenerA = ListenerA.getInstance();
            
            ICommand startCmd = new StartCommand(listenerA);
            ICommand stopCmd = new StopCommand(listenerA);
            ICommand statusCmd = new StatusCommand(listenerA);
        }

    }

```