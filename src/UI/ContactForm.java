package UI;

import business.ContactBusiness;
import entities.ContactEntity;
import repository.ContactRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactForm extends JFrame{
    private JButton cancelarButton;
    private JButton salvarButton;
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;

    private ContactBusiness mContactBusiness;

    public ContactForm(){
        setContentPane(rootPanel);
        setSize(300,200);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width/2-getWidth()/2,dimension.height/2-getHeight()/2);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setListners();
        mContactBusiness = new ContactBusiness();
    }
    private void  setListners(){
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   String name = textField1.getText();
                   String phone = textField2.getText();
                   mContactBusiness.save(name,phone);
                   new MainForm();
                   dispose();
               }
               catch (Exception exception){
                   JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
               }
            }
        });
    }
}
