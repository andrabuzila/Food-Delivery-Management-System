package presentationLayer;

import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Reports extends JFrame{
    private JTextField m_startHour = new JTextField(10);
    private JTextField m_endHour = new JTextField(10);
    private JTextField m_nbOfTimes = new JTextField(10);
    private JTextField m_nbOfTimesClient = new JTextField(10);
    private JTextField m_valueHigherThan = new JTextField(10);
    private JTextField m_day = new JTextField(10);

    private JButton buttInterval = new JButton("Time Interval");
    private JButton buttFrequencyProd = new JButton("Product Frequency");
    private JButton buttFrequencyClient = new JButton("Client Frequency");
    private JButton buttDate = new JButton("Products Ordered");
    private JButton buttBack = new JButton("Back");
    private JTextPane interval = new JTextPane();
    DeliveryService deliveryService = new DeliveryService();

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

    Reports(DeliveryService d){
        deliveryService = d;

        JLabel label1 = new JLabel("start hour: ");
        JLabel label2 = new JLabel("end hour: ");
        panel1.add(label1);
        panel1.add(m_startHour);
        panel1.add(label2);
        panel1.add(m_endHour);
        panel1.setLayout(new FlowLayout());

        panel2.add(buttInterval);
        panel2.setLayout(new FlowLayout());

        JLabel label3 = new JLabel("number of products: ");
        panel3.add(label3);
        panel3.add(m_nbOfTimes);
        panel3.setLayout(new FlowLayout());

        panel4.add(buttFrequencyProd);
        panel4.setLayout(new FlowLayout());

        JLabel label4 = new JLabel("number of clients: ");
        JLabel label5 = new JLabel("value of the order: ");
        panel5.add(label4);
        panel5.add(m_nbOfTimesClient);
        panel5.add(label5);
        panel5.add(m_valueHigherThan);
        panel5.setLayout(new FlowLayout());

        panel6.add(buttFrequencyClient);
        panel6.setLayout(new FlowLayout());

        JLabel label6 = new JLabel("day: ");
        panel7.add(label6);
        panel7.add(m_day);
        panel7.setLayout(new FlowLayout());

        panel8.add(buttDate);
        panel8.setLayout(new FlowLayout());

        panel9.add(buttBack);
        panel9.setLayout(new FlowLayout());
        panel10.add(interval);
        panel10.setLayout(new FlowLayout());

        buttBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reports.super.setVisible(false);
            }

        });
        buttInterval.addActionListener(new timeIntervalButt());
        buttFrequencyProd.addActionListener(new frequencyProdButt());
        buttDate.addActionListener(new dayButt());

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

        setPreferredSize(new Dimension(600, 600));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setContentPane(p);
        this.pack();
        this.setTitle("Reports");


    }


    class timeIntervalButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input = "";
            String user2Input = "";
            try{
                user1Input = m_startHour.getText();
                user2Input = m_endHour.getText();
                List<Order> orders = new ArrayList<>();
                orders = deliveryService.interval(Integer.valueOf(user1Input), Integer.valueOf(user2Input));

                String s="";
                for(Order o: orders){
                    s+=" Order number "+o.getOrderID()+"\n";
                }
                interval.setText(s);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class frequencyProdButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input="";
            try{
                user1Input = m_nbOfTimes.getText();
                List<MenuItem> products = new ArrayList<>();
                products=deliveryService.moreThan(Integer.valueOf(user1Input));

                String p = "";
                for(MenuItem i: products){
                    p+=i.getTitle()+"\n";
                }
                interval.setText(p);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class dayButt implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input="";
            try{
                user1Input=m_day.getText();
                List<MenuItem> products = new ArrayList<>();
                deliveryService.afisdate();
                products = deliveryService.productsInADay(user1Input);
                String p = "";
                for(MenuItem i: products){
                    p+=i.getTitle()+"\n";
                }
                interval.setText(p);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }



}
