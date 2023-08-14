package businessLayer;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IDeliveryServiceProcessing {
    //operations for client
    public HashSet<MenuItem> viewProducts() throws IOException;
    public HashSet<MenuItem> serchByWord(String s) throws IOException;
    public HashSet<MenuItem> searchByRate(float f) throws IOException;
    public HashSet<MenuItem> searchByCal(float i) throws IOException;
    public HashSet<MenuItem> searchByProt(float i) throws IOException;
    public HashSet<MenuItem> searchByFat(float i) throws IOException;
    public HashSet<MenuItem> searchBySodium(float i) throws IOException;
    public HashSet<MenuItem> searchByPrice(float i) throws IOException;
    public void createOrder(HashSet<MenuItem> items, int clientID) throws IOException;


    //operations for administrator
    public HashSet<MenuItem> addProduct(BaseProduct item) throws IOException;
    public HashSet<MenuItem> deleteProduct(BaseProduct item);
    public HashSet<MenuItem> updateProduct(BaseProduct item);
    public HashSet<MenuItem> mergeProducts(JTable tabel, String newTitle);
    public List<Order> interval( int start, int end);
    public List<MenuItem> moreThan(int value);
    public List<MenuItem> productsInADay(String day);

}
