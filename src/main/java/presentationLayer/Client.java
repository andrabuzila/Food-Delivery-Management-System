package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Client extends JFrame {
    private JTextField m_keyword = new JTextField(20);
    private JTextField m_ratings = new JTextField(10);
    private JTextField m_calories = new JTextField(10);
    private JTextField m_proteins = new JTextField(10);
    private JTextField m_fats = new JTextField(10);
    private JTextField m_sodium = new JTextField(10);
    private JTextField m_price = new JTextField(10);
    DeliveryService delivery = new DeliveryService();
    private int clientNb;

    JButton buttView = new JButton("View Menu");
    JButton search = new JButton("Search");
    JButton create = new JButton("Create");
    JButton buttBack = new JButton("Back");
    JButton buttReset = new JButton("Reset");
    List<Register> lista=new ArrayList<>();
    private JTable tabel = new JTable();

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();
    JPanel panel8 = new JPanel();
    JPanel panel9 = new JPanel();
    JPanel panel10 = new JPanel();

    Client(List<Register> l, DeliveryService d){
        delivery = d;
        lista=l;

        JLabel label1 = new JLabel("title: ");
        panel1.add(label1);
        panel1.add(m_keyword);
        panel1.setLayout(new FlowLayout());

        JLabel label2 = new JLabel("rating: ");
        panel2.add(label2);
        panel2.add(m_ratings);
        panel2.setLayout(new FlowLayout());

        JLabel label3 = new JLabel("calories: ");
        panel3.add(label3);
        panel3.add(m_calories);
        panel3.setLayout(new FlowLayout());

        JLabel label4 = new JLabel("proteins: ");
        panel4.add(label4);
        panel4.add(m_proteins);
        panel4.setLayout(new FlowLayout());

        JLabel label5 = new JLabel("fat: ");
        panel5.add(label5);
        panel5.add(m_fats);
        panel5.setLayout(new FlowLayout());

        JLabel label6 = new JLabel("sodium: ");
        panel6.add(label6);
        panel6.add(m_sodium);
        panel6.setLayout(new FlowLayout());

        JLabel label7 = new JLabel("price: ");
        panel7.add(label7);
        panel7.add(m_price);
        panel7.setLayout(new FlowLayout());

        panel8.add(buttView);
        panel8.add(search);
        panel8.add(create);
        panel8.setLayout(new FlowLayout());

        panel9.add(buttBack);
        panel9.add(buttReset);

        buttBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.super.setVisible(false);
                clientNb++;
            }

        });

        buttReset.addActionListener(new resetButt());
        buttView.addActionListener(new viewButt());
        search.addActionListener(new searchButt());
        create.addActionListener(new createButt());

        JPanel p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        p.add(panel4);
        p.add(panel5);
        p.add(panel6);
        p.add(panel7);
        p.add(panel8);
        p.add(panel9);
        p.add(panel10);
        this.setPreferredSize(new Dimension(500, 500));

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setContentPane(p);
        this.pack();
        this.setTitle("Client");

    }

    class viewButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                HashSet<MenuItem> allProducts = new HashSet<>();
                allProducts = delivery.getAllProductsinMenu();
                Object[][] produse = new Object[10000][7];
                produse =  allProducts.stream()
                        .map(o -> o.getAll())
                        .toArray(Object[][]::new);
                String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                tabel= new JTable(produse, coloane);
                tabel.setBounds(40, 40, 200, 200);
                JScrollPane sp=new JScrollPane(tabel);
                panel10.add(sp);
                tabel.setVisible(true);
                setVisible(true);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class resetButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            m_calories.setText(null);
            m_fats.setText(null);
            m_keyword.setText(null);
            m_price.setText(null);
            m_proteins.setText(null);
            m_ratings.setText(null);
            m_sodium.setText(null);
            tabel.setVisible(false);
        }
    }

    class searchButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input="";
            String user2Input="";
            String user3Input="";
            String user4Input="";
            String user5Input="";
            String user6Input="";
            String user7Input="";

            try{
                user1Input=m_keyword.getText();
                user2Input=m_ratings.getText();
                user3Input=m_calories.getText();
                user4Input=m_proteins.getText();
                user5Input=m_fats.getText();
                user6Input=m_sodium.getText();
                user7Input=m_price.getText();


                if(!user1Input.isEmpty()) {
                    HashSet<MenuItem> goodProducts = new HashSet<>();
                    goodProducts = delivery.serchByWord(user1Input);
                    Object[][] produse = new Object[15000][7];
                    produse =  goodProducts.stream()
                            .map(o -> o.getAll())
                            .toArray(Object[][]::new);
                    String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                    tabel= new JTable(produse, coloane);
                    tabel.setBounds(80, 80, 500, 500);
                    JScrollPane sp=new JScrollPane(tabel);
                    panel10.add(sp);
                    tabel.setVisible(true);
                    setVisible(true);
                }
                else {
                    if(!user2Input.isEmpty()){
                        HashSet<MenuItem> goodProducts = new HashSet<>();
                        goodProducts=delivery.searchByRate(Float.valueOf(user2Input));
                        Object[][] produse = new Object[15000][7];
                        produse =  goodProducts.stream()
                                .map(o -> o.getAll())
                                .toArray(Object[][]::new);
                        String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                        tabel= new JTable(produse, coloane);
                        tabel.setBounds(80, 80, 500, 500);
                        JScrollPane sp=new JScrollPane(tabel);
                        panel10.add(sp);
                        tabel.setVisible(true);
                        setVisible(true);
                    }
                    else
                    {
                        if(!user3Input.isEmpty()) {
                            HashSet<MenuItem> goodProducts = new HashSet<>();
                            goodProducts = delivery.searchByCal(Float.valueOf(user3Input));
                            Object[][] produse = new Object[15000][7];
                            produse =  goodProducts.stream()
                                    .map(o -> o.getAll())
                                    .toArray(Object[][]::new);
                            String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                            tabel= new JTable(produse, coloane);
                            tabel.setBounds(80, 80, 500, 500);
                            JScrollPane sp=new JScrollPane(tabel);
                            panel10.add(sp);
                            tabel.setVisible(true);
                            setVisible(true);
                        }
                        else{
                            if(!user4Input.isEmpty()){
                                HashSet<MenuItem> goodProducts = new HashSet<>();
                                goodProducts=delivery.searchByProt(Float.valueOf(user4Input));
                                Object[][] produse = new Object[15000][7];
                                produse =  goodProducts.stream()
                                        .map(o -> o.getAll())
                                        .toArray(Object[][]::new);
                                String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                                tabel= new JTable(produse, coloane);
                                tabel.setBounds(80, 80, 500, 500);
                                JScrollPane sp=new JScrollPane(tabel);
                                panel10.add(sp);
                                tabel.setVisible(true);
                                setVisible(true);
                            }
                            else{
                                if(!user5Input.isEmpty()){
                                    HashSet<MenuItem> goodProducts = new HashSet<>();
                                    goodProducts=delivery.searchByFat(Float.valueOf(user5Input));
                                    Object[][] produse = new Object[15000][7];
                                    produse =  goodProducts.stream()
                                            .map(o -> o.getAll())
                                            .toArray(Object[][]::new);
                                    String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                                    tabel= new JTable(produse, coloane);
                                    tabel.setBounds(80, 80, 500, 500);
                                    JScrollPane sp=new JScrollPane(tabel);
                                    panel10.add(sp);
                                    tabel.setVisible(true);
                                    setVisible(true);
                                }
                                else{
                                    if(!user6Input.isEmpty()){
                                        HashSet<MenuItem> goodProducts = new HashSet<>();
                                        goodProducts=delivery.searchBySodium(Float.valueOf(user6Input));
                                        Object[][] produse = new Object[15000][7];
                                        produse =  goodProducts.stream()
                                                .map(o -> o.getAll())
                                                .toArray(Object[][]::new);
                                        String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                                        tabel= new JTable(produse, coloane);
                                        tabel.setBounds(80, 80, 500, 500);
                                        JScrollPane sp=new JScrollPane(tabel);
                                        panel10.add(sp);
                                        tabel.setVisible(true);
                                        setVisible(true);
                                    }
                                    else{
                                        if(!user7Input.isEmpty()){
                                        HashSet<MenuItem> goodProducts = new HashSet<>();
                                        goodProducts=delivery.searchByPrice(Float.valueOf(user7Input));
                                        Object[][] produse = new Object[15000][7];
                                        produse =  goodProducts.stream()
                                                .map(o -> o.getAll())
                                                .toArray(Object[][]::new);
                                        String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                                        tabel= new JTable(produse, coloane);
                                        tabel.setBounds(80, 80, 500, 500);
                                        JScrollPane sp=new JScrollPane(tabel);
                                        panel10.add(sp);
                                        tabel.setVisible(true);
                                        setVisible(true);}
                                    }
                                }
                            }
                        }
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    private Function<String, BaseProduct> mapToItem = (line) ->{
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

    class createButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            HashSet<MenuItem> products = new HashSet<>();
            int[] selectedRows = tabel.getSelectedRows();
            for(int i=0; i<selectedRows.length; i++){
                BaseProduct newProduct = new BaseProduct();
                newProduct.setTitle( tabel.getModel().getValueAt(selectedRows[i], 0).toString());
                newProduct.setRatings(Float.parseFloat(tabel.getModel().getValueAt(selectedRows[i], 1).toString()));
                newProduct.setCalories(Float.parseFloat(tabel.getValueAt(selectedRows[i], 2).toString()));
                newProduct.setProteins(Float.parseFloat(tabel.getValueAt(selectedRows[i], 3).toString()));
                newProduct.setFats(Float.parseFloat(tabel.getValueAt(selectedRows[i], 4).toString()));
                newProduct.setSodium(Float.parseFloat(tabel.getValueAt(selectedRows[i], 5).toString()));
                newProduct.setPrice(Float.parseFloat(tabel.getValueAt(selectedRows[i], 6).toString()));
                products.add(newProduct);
            }
                //System.out.println(products.toString());
                delivery.createOrder(products, clientNb);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }



}
