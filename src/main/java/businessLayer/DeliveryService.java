package businessLayer;

import dataLayer.FileWriter1;
import presentationLayer.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private HashMap<Order, HashSet<MenuItem>> hashmap = new HashMap<Order, HashSet<MenuItem>>();
    private HashSet<MenuItem> allProductsinMenu = new HashSet<>();
    private List<MenuItem> allItemsInOrder = new ArrayList<>();
    private FileWriter1 fData = new FileWriter1();

    private Function<String, MenuItem> mapToItem = (line) ->{
        String[] p = line.split(",");
        BaseProduct item = new BaseProduct();
        item.setTitle(p[0]);
        item.setRatings(Float.parseFloat(p[1]));
        item.setCalories(Float.parseFloat(p[2]));
        item.setProteins(Float.parseFloat(p[3]));
        item.setFats(Float.parseFloat(p[4]));
        item.setSodium(Float.parseFloat(p[5]));
        item.setPrice(Float.parseFloat(p[6]));

        return item;
    };
    //for client
    @Override
    public HashSet<MenuItem> viewProducts() throws IOException {
        String filename = "products.csv";
        Path pathToFile = Paths.get(filename);
        File file = new File(pathToFile.toString());
        InputStream inputFS = new FileInputStream(file);
        BufferedReader br=new BufferedReader(new InputStreamReader(inputFS));
        HashSet<MenuItem> menu = new HashSet<>();
        menu= br.lines()
                .skip(1)
                .map(mapToItem)
                .distinct()
                .collect(Collectors.toCollection(HashSet::new));
        allProductsinMenu = menu;

        return allProductsinMenu;
    }

    public HashSet<MenuItem> getAllProductsinMenu() {
        return allProductsinMenu;
    }

    @Override
    public HashSet<MenuItem> serchByWord(String s) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getTitle().contains(s))
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }

    @Override
    public HashSet<MenuItem> searchByRate(float f) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getRatings() == f)
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }

    @Override
    public HashSet<MenuItem> searchByCal(float i) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getCalories() == i)
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }


    @Override
    public HashSet<MenuItem> searchByProt(float i) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getProteins() == i)
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }

    @Override
    public HashSet<MenuItem> searchByFat(float i) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getFats() == i)
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }

    @Override
    public HashSet<MenuItem> searchBySodium(float i) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getSodium() == i)
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }

    @Override
    public HashSet<MenuItem> searchByPrice(float i) throws IOException {
        HashSet<MenuItem> goodProducts = new HashSet<>();
        goodProducts=allProductsinMenu.stream()
                .filter(c->c.getPrice() == i)
                .collect(Collectors.toCollection(HashSet::new));
        return goodProducts;
    }

    @Override
    public void createOrder(HashSet<MenuItem> items, int clientID) throws IOException {
        Order order = new Order();
        Random rand = new Random();
        order.setOrderID(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        Date date1 = new Date(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        order.setOrderDate(formatter.format(date));
        order.setTime(form.format(date1));
        for(Order o: hashmap.keySet()){
            while(order.getOrderID()<=o.getOrderID()){
                order.setOrderID(order.getOrderID()+1);
            }
        }
        allItemsInOrder.addAll(items);
        //System.out.println(allItemsInOrder.toString());
        order.setClientId(clientID);
        fData.writeInFile("S-a creat cu succes comanda numarul "+order.getOrderID()+" a clientului Client"+ order.getClientId()+ " la ora "+form.format(date1));

        hashmap.put(order, items);

        addObserver(new Employee());
        setChanged();
        notifyObservers();
        System.out.println("S-a creat cu succes comanda numarul "+order.getOrderID()+" a clientului Client"+ order.getClientId()+ " la ora "+form.format(date1));
    }

    //for administrator
    @Override
    public HashSet<MenuItem> addProduct(BaseProduct item) throws IOException {
        HashSet<MenuItem> produse = new HashSet<>();
        produse = allProductsinMenu.stream()
                .filter(o->o.getTitle().equals(item.getTitle()))
                .collect(Collectors.toCollection(HashSet::new));
        allProductsinMenu.removeAll(produse);
        allProductsinMenu.add(item);
        System.out.println("Produsul a fost adaugat cu succes!");
        return allProductsinMenu;
    }

    @Override
    public HashSet<MenuItem> deleteProduct(BaseProduct item) {
        HashSet<MenuItem> produse = new HashSet<>();
        produse = allProductsinMenu.stream()
                .filter(o->o.getTitle().equals(item.getTitle()))
                .collect(Collectors.toCollection(HashSet::new));
        allProductsinMenu.removeAll(produse);
        System.out.println("Produsul a fost sters cu succes!");
        return allProductsinMenu;
    }


    @Override
    public HashSet<MenuItem> updateProduct(BaseProduct item) {
        HashSet<MenuItem> produse = new HashSet<>();
        produse = allProductsinMenu.stream()
                .filter(o->o.getTitle().equals(item.getTitle()))
                .collect(Collectors.toCollection(HashSet::new));
        allProductsinMenu.removeAll(produse);
        allProductsinMenu.add(item);
        System.out.println("Produsul a fost editat cu succes!");
        return allProductsinMenu;
    }

    @Override
    public HashSet<MenuItem> mergeProducts(JTable tabel, String newTitle) {
        CompositeProduct products = new CompositeProduct();
        Float rating=0.0f, cal=0.0f, prot=0.0f, fat=0.0f, sodium=0.0f, price=0.0f;
        BaseProduct newProduct = new BaseProduct();
        List<String> nume = new ArrayList<>();
        int[] selectedRows = tabel.getSelectedRows();
        for(int i=0; i<selectedRows.length; i++){
            newProduct.setTitle( tabel.getModel().getValueAt(selectedRows[i], 0).toString());
            newProduct.setRatings(Float.parseFloat(tabel.getModel().getValueAt(selectedRows[i], 1).toString()));
            newProduct.setCalories(Float.parseFloat(tabel.getValueAt(selectedRows[i], 2).toString()));
            newProduct.setProteins(Float.parseFloat(tabel.getValueAt(selectedRows[i], 3).toString()));
            newProduct.setFats(Float.parseFloat(tabel.getValueAt(selectedRows[i], 4).toString()));
            newProduct.setSodium(Float.parseFloat(tabel.getValueAt(selectedRows[i], 5).toString()));
            newProduct.setPrice(Float.parseFloat(tabel.getValueAt(selectedRows[i], 6).toString()));
            products.addItemToList(newProduct);
            nume.add(tabel.getModel().getValueAt(selectedRows[i], 0).toString());

            rating+=Float.parseFloat(tabel.getModel().getValueAt(selectedRows[i], 1).toString());
            cal+=Float.parseFloat(tabel.getValueAt(selectedRows[i], 2).toString());
            prot+=Float.parseFloat(tabel.getValueAt(selectedRows[i], 3).toString());
            fat+=Float.parseFloat(tabel.getValueAt(selectedRows[i], 4).toString());
            sodium+=Float.parseFloat(tabel.getValueAt(selectedRows[i], 5).toString());

        }
        products.setTitle(newTitle);
        products.setCalories(cal);
        products.setProteins(prot);
        products.setRatings(rating);
        products.setFats(fat);
        products.setSodium(sodium);

        allProductsinMenu.add(products);

        System.out.println("Meniul "+newTitle+" are in componenta sa urmatoarele produse "+nume.toString());
        return allProductsinMenu;
    }

    @Override
    public List<Order> interval(int start, int end) {
        String x,y;
        if(start<10)
            x="0"+start+":00:00";
        else
            x=start+":00:00";
        if(end<10)
            y="0"+end+"00:00";
        else
            y=end+":00:00";
        List<Order> orders = new ArrayList<>();
        orders = hashmap.keySet().stream()
                .filter(e->x.compareTo(e.getTime())<=0)
                .filter(e->y.compareTo(e.getTime())>=0)
                .collect(Collectors.toList());
        return orders;
    }


    public void afisdate(){
        hashmap.entrySet().stream()
                .forEach(s->System.out.println(s.getKey().getOrderDate()));
    }

   public List<MenuItem> moreThan(int value){
        List<MenuItem> items = new ArrayList<>();
        List<MenuItem> distinctMenuItems = new ArrayList<>();
        distinctMenuItems = allItemsInOrder.stream().distinct().collect(Collectors.toList());
        int[] cnt = new int[100];
        int k=0;
        for(MenuItem m: distinctMenuItems){
            for(MenuItem m1: allItemsInOrder){
                if(m.equals(m1)){
                    cnt[k]++;
                }
            }
            k++;
        }
        int i =0;
        for(MenuItem m:distinctMenuItems){
            if(cnt[i]>value){
                items.add(m);
            }
            i++;
        }
        System.out.println(items.toString());
        return items;
    }

    public List<Integer> moreThanClient(int value){

        return null;
    }

    public List<MenuItem> productsInADay(String day){
        List<MenuItem> products = new ArrayList<>();
        Map<Order, HashSet<MenuItem>> items = new HashMap<>();
        items = hashmap.entrySet().stream()
                .filter(e->e.getKey().getOrderDate().equals(day))
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
        for(Order o: items.keySet()){
            for(MenuItem m: items.get(o)){
                products.add(m);
            }
        }
        List<MenuItem> menuItems = products.stream().distinct().collect(Collectors.toList());

        return menuItems;
    }


}
