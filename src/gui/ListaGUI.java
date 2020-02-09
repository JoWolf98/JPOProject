package gui;

import model.Wypozycz_Film_Serial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ListaGUI {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextArea dostępneFilmyISerialeTextArea;
    private JList list1;
    private JPanel Panel2;
    private JScrollPane Jscrollpane;
    private Wypozycz_Film_Serial wolfrose;

    public JPanel getPanel1()
    {
        return panel1;
    }

    public ListaGUI(Wypozycz_Film_Serial wolfrose) {

        this.wolfrose=wolfrose;

        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem().equals("Filmy")){

                    DefaultListModel<String> l1 = new DefaultListModel<>();
                    for(int i=0;i<wolfrose.get_filmy().size();i++)
                    {
                        l1.addElement(wolfrose.get_filmy().get(i).getNazwa());
                    }
                    list1.setModel(l1);

                }
                else {
                    DefaultListModel<String> l1 = new DefaultListModel<>();
                    for(int i=0;i<wolfrose.get_seriale().size();i++)
                    {
                        l1.addElement(wolfrose.get_seriale().get(i).getNazwa());
                    }
                    list1.setModel(l1);

                }
                }
            });

    }

    public static void main(String args[]) throws IOException {
        Wypozycz_Film_Serial wolfrose=new Wypozycz_Film_Serial();
        JFrame frame = new JFrame("Lista");
        frame.setContentPane(new ListaGUI(wolfrose).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
