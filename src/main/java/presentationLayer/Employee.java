package presentationLayer;

import java.util.Observable;
import java.util.Observer;

public class Employee extends  Observable implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("New order!");
    }
}
