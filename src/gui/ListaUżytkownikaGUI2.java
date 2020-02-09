package gui;

import model.Użytkownik;

import javax.swing.*;
import java.io.IOException;

public class ListaUżytkownikaGUI2 {
    private JPanel panel1;
    private JTextArea twojeWypożyczoneSerialeTextArea;
    private JTextArea twojeWypożyczoneFilmyTextArea;
    private JPanel Panel2;
    private JScrollPane Jscrollpane;
    private JList list1;
    private Użytkownik użytkownik;

    public JPanel getPanel1()
    {
        return panel1;
    }

    public ListaUżytkownikaGUI2(Użytkownik użytkownik) {
        this.użytkownik=użytkownik;
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for(int i=0;i<użytkownik.get_baza_wypożyczonych_seriali().size();i++)
        {
            l1.addElement(użytkownik.get_baza_wypożyczonych_seriali().get(i).getNazwa());
        }
        list1.setModel(l1);

    }

    public static void main(String args[]) throws IOException {

    }
}
