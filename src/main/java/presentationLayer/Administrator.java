package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Administrator extends JFrame {
    private JTextField m_title = new JTextField(20);
    private JTextField m_ratings = new JTextField(10);
    private JTextField m_calories = new JTextField(10);
    private JTextField m_proteins = new JTextField(10);
    private JTextField m_fats = new JTextField(10);
    private JTextField m_sodium = new JTextField(10);
    private JTextField m_price = new JTextField(10);
    private JTextField m_newTitle = new JTextField(20);
    DeliveryService delivery = new DeliveryService();

    private JButton buttView = new JButton("View All");
    private JButton buttAdd = new JButton("Insert");
    private JButton buttEdit = new JButton("Modify");
    private JButton buttDelete = new JButton("Delete");
    private JButton buttComposed = new JButton("Composed Product");
    private JButton buttReports = new JButton("Reports");
    private JButton buttBack = new JButton("Back");
    private JButton buttReset = new JButton("Reset");
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
    JPanel panel11 = new JPanel();
    JPanel panel12 = new JPanel();

    List<Register> lista=new ArrayList<>();

    Administrator(List<Register> l, DeliveryService d){
        delivery=d;
        lista=l;


        JLabel label1 = new JLabel("title: ");
        panel1.add(label1);
        panel1.add(m_title);
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

        JLabel label8 = new JLabel("new product name: ");
        panel8.add(label8);
        panel8.add(m_newTitle);
        panel8.setLayout(new FlowLayout());

        panel9.add(buttView);
        panel9.add(buttAdd);
        panel9.add(buttEdit);
        panel9.add(buttDelete);
        panel9.add(buttReports);
        panel9.add(buttComposed);
        panel9.setLayout(new FlowLayout());

        panel10.add(buttReset);
        panel10.add(buttBack);
        panel10.setLayout(new FlowLayout());

        panel12.add(buttComposed);
        panel12.setLayout(new FlowLayout());

        buttBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrator.super.setVisible(false);
            }

        });
        buttView.addActionListener(new viewButt());
        buttReset.addActionListener(new resetButt());
        buttAdd.addActionListener(new addButt());
        buttDelete.addActionListener(new deleteButt());
        buttEdit.addActionListener(new updateButt());
        buttComposed.addActionListener(new composeButt());
        buttReports.addActionListener(new reportsButt());

        JPanel p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        p.add(panel4);
        p.add(panel5);
        p.add(panel6);
        p.add(panel7);
        p.add(panel9);
        p.add(panel8);
        p.add(panel12);
        p.add(panel10);
        p.add(panel11);

        setPreferredSize(new Dimension(600, 600));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setContentPane(p);
        this.pack();
        this.setTitle("Administrator");
    }
    class resetButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            m_calories.setText(null);
            m_fats.setText(null);
            m_title.setText(null);
            m_price.setText(null);
            m_proteins.setText(null);
            m_ratings.setText(null);
            m_sodium.setText(null);
            tabel.setVisible(false);
        }
    }

    class addButt implements ActionListener{
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
                user1Input=m_title.getText();
                user2Input=m_ratings.getText();
                user3Input=m_calories.getText();
                user4Input=m_proteins.getText();
                user5Input=m_fats.getText();
                user6Input=m_sodium.getText();
                user7Input=m_price.getText();
                BaseProduct baseProduct = new BaseProduct();
                baseProduct.setTitle(user1Input);
                baseProduct.setRatings(Float.parseFloat(user2Input));
                baseProduct.setCalories(Float.parseFloat(user3Input));
                baseProduct.setProteins(Float.parseFloat(user4Input));
                baseProduct.setFats(Float.parseFloat(user5Input));
                baseProduct.setSodium(Float.parseFloat(user6Input));
                baseProduct.setPrice(Float.parseFloat(user7Input));

                HashSet<MenuItem> goodProducts = new HashSet<>();
                goodProducts = delivery.addProduct(baseProduct);
                Object[][] produse = new Object[15000][7];
                produse =  goodProducts.stream()
                        .map(o -> o.getAll())
                        .toArray(Object[][]::new);
                String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                tabel= new JTable(produse, coloane);
                tabel.setBounds(80, 80, 500, 500);
                JScrollPane sp=new JScrollPane(tabel);
                panel11.add(sp);
                tabel.setVisible(true);
                setVisible(true);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class deleteButt implements ActionListener{

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
                user1Input=m_title.getText();
                user2Input=m_ratings.getText();
                user3Input=m_calories.getText();
                user4Input=m_proteins.getText();
                user5Input=m_fats.getText();
                user6Input=m_sodium.getText();
                user7Input=m_price.getText();
                BaseProduct baseProduct = new BaseProduct();
                baseProduct.setTitle(user1Input);
                baseProduct.setRatings(Float.parseFloat(user2Input));
                baseProduct.setCalories(Float.parseFloat(user3Input));
                baseProduct.setProteins(Float.parseFloat(user4Input));
                baseProduct.setFats(Float.parseFloat(user5Input));
                baseProduct.setSodium(Float.parseFloat(user6Input));
                baseProduct.setPrice(Float.parseFloat(user7Input));

                HashSet<MenuItem> goodProducts = new HashSet<>();
                goodProducts = delivery.deleteProduct(baseProduct);
                Object[][] produse = new Object[15000][7];
                produse =  goodProducts.stream()
                        .map(o -> o.getAll())
                        .toArray(Object[][]::new);
                String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                tabel= new JTable(produse, coloane);
                tabel.setBounds(80, 80, 500, 500);
                JScrollPane sp=new JScrollPane(tabel);
                panel11.add(sp);
                tabel.setVisible(true);
                setVisible(true);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class updateButt implements ActionListener{

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
                user1Input=m_title.getText();
                user2Input=m_ratings.getText();
                user3Input=m_calories.getText();
                user4Input=m_proteins.getText();
                user5Input=m_fats.getText();
                user6Input=m_sodium.getText();
                user7Input=m_price.getText();
                BaseProduct baseProduct = new BaseProduct();
                baseProduct.setTitle(user1Input);
                baseProduct.setRatings(Float.parseFloat(user2Input));
                baseProduct.setCalories(Float.parseFloat(user3Input));
                baseProduct.setProteins(Float.parseFloat(user4Input));
                baseProduct.setFats(Float.parseFloat(user5Input));
                baseProduct.setSodium(Float.parseFloat(user6Input));
                baseProduct.setPrice(Float.parseFloat(user7Input));

                HashSet<MenuItem> goodProducts = new HashSet<>();
                goodProducts = delivery.updateProduct(baseProduct);
                Object[][] produse = new Object[15000][7];
                produse =  goodProducts.stream()
                        .map(o -> o.getAll())
                        .toArray(Object[][]::new);
                String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                tabel= new JTable(produse, coloane);
                tabel.setBounds(80, 80, 500, 500);
                JScrollPane sp=new JScrollPane(tabel);
                panel11.add(sp);
                tabel.setVisible(true);
                setVisible(true);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class composeButt implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input = "";

            try{
                user1Input = m_newTitle.getText();
                HashSet<MenuItem> newItems = new HashSet<>();
                newItems = delivery.mergeProducts(tabel, user1Input);
                Object[][] produse = new Object[15000][7];
                produse =  newItems.stream()
                        .map(o -> o.getAll())
                        .toArray(Object[][]::new);
                String[] coloane={"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
                tabel= new JTable(produse, coloane);
                tabel.setBounds(80, 80, 500, 500);
                JScrollPane sp=new JScrollPane(tabel);
                panel11.add(sp);
                tabel.setVisible(true);
                setVisible(true);


            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
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
                panel11.add(sp);
                tabel.setVisible(true);
                setVisible(true);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class reportsButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Reports reports = new Reports(delivery);
            reports.setVisible(true);

        }
    }



}
