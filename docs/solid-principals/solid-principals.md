# Solid Principals

- In software development, Object-Oriented Design plays a crucial role when it comes to writing flexible, scalable, maintainable, and reusable code. There are so many benefits of using OOD but every developer should also have the knowledge of the SOLID principle for good object-oriented design in programming. The SOLID principle was introduced by Robert C. Martin, also known as Uncle Bob and it is a coding standard in programming.
- what is SOLID and how does it help us write better code? Simply put, Martin and Feathers' design principles encourage us to create more maintainable, understandable, and flexible software. Consequently, as our applications grow in size, we can reduce their complexity and save ourselves a lot of headaches further down the road!

The following five concepts make up our SOLID principles:

1. **S**ingle Responsibility principal
2. **O**pen/Closed principal
3. **L**iskov Substitution
4. **I**nterface Segregation principal
5. **D**ependency Inversion principal

## 1. Single Responsibility principal

- ### The **S**  in SOLID stands for Single Responsibility principal, This principle states that a class should only have one responsibility. Furthermore, it should only have one reason to change.
  
- Let's look into the below code and identify the issues.

    ``` java
    public class BatteryMonitor {
        static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
            if(chargeRate > 0.8) {
                System.out.println("Charge Rate is out of range!");
                return false;
            }
            if(soc < 20 || soc > 80) {
                System.out.println("State of Charge is out of range!");
                return false;
            }
            if(temperature < 0 || temperature > 45) {
                System.out.println("Temperature is out of range!");
                return false;
            
            }
            return true;
        }
    }
    ```

- Problems in the above code:
  - batteryIsOk Function is complex (more cyclometer's complexity)
  - More cliometric complexity
  - Violation of SRP (As it is logging and checking the battery status)
  - Can not be extended say we want to implement multiple loggers like ConsoleLogger, FileLogger.
  - Assume that ranges for each parameter are not going to change so no need to worry about conditions in the if statements.
  - IO operations involved so can not perform log testing. (few technologies supports it by using the buffer writing, but few not).

- Refactored code:

    ``` java

    public class ConsoleLogger {
        public void log(String message) {
            System.out.println(message)
        };
    }

    public class BatteryMonitor {

        private ConsoleLogger logger;

        // dependency -> Composition
        public BatteryMonitor() {
            this.logger = new ConsoleLogger();
        }

        private static boolean checkTemp(float temperature) {
        if(temperature < 0 || temperature > 45) {
                this.logger.log("Temperature is out of range!");
                return false;
            
            }
            return true;
        }
        
        private static boolean checkSoc(float soc) {
        if(soc < 20 || soc > 80) {
                this.logger.log("State of Charge is out of range!");
                return false;
        }
            return true;
        }
        
        private static boolean checkChargeRate(float chargeRate) {
            if(chargeRate > 0.8) {
                this.logger.log("Charge Rate is out of range!");
                return false;
            }
            return true;
        }
        
        static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
            return checkTemp(temperature) && checkSoc(soc) && checkChargeRate(chargeRate);
        }

    }
    ```

- To one extend the above now addresses the below issues.
  - Now the batteryIsOk not complex (complexity problem SOLVED).
  - SRP achieved -> BatteryMonitor class now only doing the core logic to check the battery state, logging moved to separate functionality.
  - Testability *is still not solved*, let say we want to test the above code, even though we have separated the logging functionality but still we left with tightly coupled, we can not switch between the loggers. it always creates the console logger for all the class instances. Now let's solve this problem using **Dependency inversion principal**

- So SRP principle help us to build better software? Let's see a few of its benefits:
  - **Testing** – A class with one responsibility will have far fewer test cases.
  - **Lower coupling** – Less functionality in a single class will have fewer dependencies.
  - **Organization** – Smaller, well-organized classes are easier to search than monolithic ones.

## 2. Dependency inversion principal

- The **D** in SOLID stands for Dependency inversion principal, The principle of dependency inversion refers to the decoupling of software modules. This way, instead of high-level modules depending on low-level modules, both will depend on abstractions.**

- The above code works, and we will be able to use the console logger freely within the BatteryMonitor class but by clearing the logger with the new key word in the constructor we have tightly coupled these two classes BatteryMonitor and ConsoleLogger. This made the BatteryMonitor class hard to test and also we lost the ability to switch out our logger.

- So let's decouple our monitor system from logging system by adding a more general ILogger interface and use this in the BatteryMonitor class.
- Refactored code:

  ``` Java
   public interface ILogger {
        public void log(String message);
        public void ArchiveFileLog();
        public void ClearConsole();
    }

    public class ConsoleLogger implements ILogger {
        public void log(String message) {
            System.out.println(message)
        };
        public void ClearConsole() {
           // clearing the console on some condition
        };

        // Unnecessary method for ConsoleLogger, but since it is there in the interface we need to implement
        public void ArchiveFileLog() {
            throw new UnsupportedOperationException("ArchiveFileLog is not a valid method of ConsoleLogger")
        };
    }

    public class FileLoggerLogger implements ILogger {
        public void log(String message) {
            // File logger logic
        };

        public void ArchiveFileLog() {
            // Archive the log files after desired time
        };

        // Unnecessary method for FileLogger, but since it is there in the interface we need to implement
        public void ClearConsole() {
            throw new UnsupportedOperationException("ClearConsole is not a valid method of FileLoggerLogger")
        };
    }

    public class BatteryMonitor {

        private ILogger logger;

        // dependency -> Aggregation
        public BatteryMonitor(ILogger logger) {
            // This can also be achieved by dependency Injection like autoWired etc in spring boot.
            // But in this case we achieve by constructor injection
            this.logger = logger;
        }

        private static boolean checkTemp(float temperature) {
            if(temperature < 0 || temperature > 45) {
                this.logger.log("Temperature is out of range!");
                return false;
            
            }
            return true;
        }
        
        private static boolean checkSoc(float soc) {
            if(soc < 20 || soc > 80) {
                    this.logger.log("State of Charge is out of range!");
                    return false;
            }
            return true;
        }
        
        private static boolean checkChargeRate(float chargeRate) {
            if(chargeRate > 0.8) {
                this.logger.log("Charge Rate is out of range!");
                return false;
            }
            return true;
        }
        
        static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
            return checkTemp(temperature) && checkSoc(soc) && checkChargeRate(chargeRate);
        }

    }

    public class App {
        public static void main(String[] args) {
            ILogger logger = new ConsoleLogger();
            // we can pass the desired logger
            BatteryMonitor bm = new BatteryMonitor(logger);
        }
    }
  ```

- Observations:
  - Now if you see the logger class it can extend to any type of logger, this segregation we achieved using Dependency inversion principal and this code segregation is also called aa strategy Patten, which we will can dive deep into it in design patterns section.
  - Now we decoupled the logger, we can set the mock logger for testing, we can pass the desired logger while creating the BatteryMonitor class.
  - But if you observe, the ILogger interface now is FAT, it has the methods which is not necessary for few loggers for example ArchiveFileLog is invalid operation for console logger. one way to solve is by removing it from the ILoggerInterface and add the relevant methods in the desired logger classes, But this we way we lost the logger template function to expose to 3rd party lets say. So this can be solved using **Interface Segregation principal**.

## 3. Interface Segregation principal

- The **I**  in SOLID stands for interface segregation, and it simply means that larger interfaces should be split into smaller ones. By doing so, we can ensure that implementing classes only need to be concerned about the methods that are of interest to them.

- As discussed above, Now the ILogger interface is holding the all methods which may not be concerned for few loggers, so let's refactor the code like below.

    ``` Java
    public interface ILogger {
        public void log(String message);
    }

    public interface IConsoleLogger extends ILogger {
        public void ClearConsole();
    }

    public interface IFileLogger extends ILogger {
        public void ArchiveFileLog();

    }

    public class ConsoleLogger implements IConsoleLogger {
        public void log(String message) {
            System.out.println(message)
        };
        public void ClearConsole() {
           // clearing the console on some condition
        };
    }

    public class FileLoggerLogger implements IFileLogger {
        public void log(String message) {
            // File logger logic
        };

        public void ArchiveFileLog() {
            // Archive the log files after desired time
        };
    }

    public class BatteryMonitor {

        private ILogger logger;

        // dependency -> Aggregation
        public BatteryMonitor(ILogger logger) {
            // This can also be achieved by dependency Injection like autoWired etc in spring boot.
            // But in this case we achieve by constructor injection
            this.logger = logger;
        }

        private static boolean checkTemp(float temperature) {
            if(temperature < 0 || temperature > 45) {
                this.logger.log("Temperature is out of range!");
                return false;
            
            }
            return true;
        }
        
        private static boolean checkSoc(float soc) {
            if(soc < 20 || soc > 80) {
                    this.logger.log("State of Charge is out of range!");
                    return false;
            }
            return true;
        }
        
        private static boolean checkChargeRate(float chargeRate) {
            if(chargeRate > 0.8) {
                this.logger.log("Charge Rate is out of range!");
                return false;
            }
            return true;
        }
        
        static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
            return checkTemp(temperature) && checkSoc(soc) && checkChargeRate(chargeRate);
        }

    }

    public class App {
        public static void main(String[] args) {
            ILogger logger = new ConsoleLogger();
            // we can pass the desired logger
            BatteryMonitor bm = new BatteryMonitor(logger);
        }
    }
    ```

- Code changes:
  - Now we segregated out the large interface ILogger to two interfaces, i.e the core functionality of logger i.e log method kept in the ILogger interface and the sub functionalities moved to the respective interfaces i.e. ClearConsole() method moved to IConsoleLogger(observe it extends the ILogger so log() functionality inherently exists) and ArchiveFileLog() method moved to IFileLogger interface.
  - You may think that we are only using the log method, then what is the need of having separate interfaces file and  console logger. Assume that we are building library so that any one can extend, Interface will act like templates.