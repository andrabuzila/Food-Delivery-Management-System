import businessLayer.BaseProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import presentationLayer.LogIn;
import presentationLayer.Register;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Register> reg = new ArrayList<>();
        LogIn login = new LogIn(reg);
        login.setVisible(true);
        reg=login.getRegisters();
        for(Register r: reg){
            System.out.println(r.getUsername());
        }
        HashSet<MenuItem> pr = new HashSet<>();
        DeliveryService del  = new DeliveryService();
        pr = del.viewProducts();
        System.out.println(pr.size());

    }
}
