package UI;

import business.ContactBusiness;
import entities.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JButton deletarButton;
    private JButton adicionarButton;
    private JTable table1;
    private JPanel rootPanel;
    private JLabel labelContactCount;

    private ContactBusiness mContactBusiness;
    private String mName;
    private String mPhone;

    public MainForm() {
        setContentPane(rootPanel);
        setSize(500, 300);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getWidth() / 2, dimension.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mContactBusiness = new ContactBusiness();

        setListners();
        loadContacts();


    }

    private void setListners() {
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ContactForm();


            }
        });

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (table1.getSelectedRow() != -1){
                        mName = table1.getValueAt(table1.getSelectedRow(), 0).toString();
                        mPhone = table1.getValueAt(table1.getSelectedRow(), 1).toString();
                    }
                }

            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    mContactBusiness.delete(mName,mPhone);
                    loadContacts();
                    mName = "";
                    mPhone = "";

                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
                }

            }
        });
    }

    private void loadContacts() {
        List<ContactEntity> contactList = mContactBusiness.getList();

        String[] columnNames = {"Nome", "Telefone"};

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity ce : contactList) {
            Object[] o = new Object[2];
            o[0] = ce.getName();
            o[1] = ce.getPhone();
            model.addRow(o);
        }
        table1.clearSelection();
        table1.setModel(model);

        labelContactCount.setText(mContactBusiness.getContactCountDescription());
    }


}



