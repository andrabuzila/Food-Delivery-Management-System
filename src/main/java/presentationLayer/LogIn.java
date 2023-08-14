package presentationLayer;

import businessLayer.DeliveryService;
import dataLayer.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends JFrame {
    private JTextField user = new JTextField(10);
    private JTextField pass = new JTextField(10);

    DeliveryService deliveryService = new DeliveryService();
    private JButton register = new JButton("Register");
    private JButton admin = new JButton("Administrator");
    private JButton client = new JButton("Client");
    private JButton emp = new JButton("Employee");
    private List<Register> registers;
    private List<Register> clients;
    private List<Register> admins;

    public LogIn(List<Register> r) throws IOException, ClassNotFoundException {
        registers=r;

        deliveryService.viewProducts();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel label1 = new JLabel("username: ");
        panel1.add(label1);
        panel1.add(user);
        panel1.setLayout(new FlowLayout());

        JLabel label2 = new JLabel("password: ");
        panel2.add(label2);
        panel2.add(pass);
        panel2.setLayout(new FlowLayout());

        register.setBackground(Color.ORANGE);
        panel3.add(register);
        panel3.add(admin);
        panel3.add(client);
        panel3.add(emp);
        panel3.setLayout(new FlowLayout());

        this.register.addActionListener(new registerBtn());
        this.admin.addActionListener(new adminBtn());
        this.client.addActionListener(new clientBtn());
        Serializator serializator = new Serializator();
        serializator.whenCustomSerializingAndDeserializing_ThenObjectIsTheSame(deliveryService);


        JPanel p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        setPreferredSize(new Dimension(500, 500));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setContentPane(p);
        this.pack();
        this.setTitle("Log in");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public List<Register> getRegisters(){
        return this.registers;
    }

    class registerBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input = "";
            String user2Input = "";
            try{
                user1Input=user.getText();
                user2Input=pass.getText();
                Register reg1 = new Register(user1Input, user2Input);
                registers.add(reg1);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class adminBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input = "";
            String user2Input = "";
            try{
                user1Input=user.getText();
                user2Input=pass.getText();
                if((user1Input != null) && (user2Input != null)){
                    int cnt=0;
                    for(Register r: registers){
                        if (r.getUsername().equals(user1Input) && r.getPass().equals(user2Input)) {
                            Administrator ad = new Administrator(getRegisters(), deliveryService);
                            ad.setVisible(true);
                        }


                    }

                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class clientBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String user1Input = "";
            String user2Input = "";
            try{
                user1Input=user.getText();
                user2Input=pass.getText();
                if((user1Input != null) && (user2Input != null)){
                    for(Register r: registers){
                        if(r.getUsername().equals(user1Input) && r.getPass().equals(user2Input)){
                            Client cl = new Client(getRegisters(), deliveryService);
                            cl.setVisible(true);
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }



}
