package gui;
import model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zarejestruj {
    private JPanel panel1;
    private JTextArea podajLoginIHasłoTextArea;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JButton zarejestrujSięButton;
    private Wypozycz_Film_Serial wolfrose;

    public JPanel getPanel1()
    {
        return panel1;
    }

    public Zarejestruj(final Wypozycz_Film_Serial wolfrose){
        this.wolfrose=wolfrose;
        final JFrame frame2=new JFrame("ZarejestrujWiadomosc");
        // EH
        zarejestrujSięButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((textField1.getText().equals("")) && (passwordField1.getText().equals(""))){
                    JOptionPane.showMessageDialog(frame2, "Niepoprawny login lub hasło,Spróbuj jeszcze raz!");
            }
                else {
                    if(wolfrose.zarejestruj(textField1.getText(), passwordField1.getText())==0){
                        JOptionPane.showMessageDialog(frame2, "Zarejestrowano poprawnie!");
                    }
                    else
                        JOptionPane.showMessageDialog(frame2, "Taki użytkownik juz istnieje! Zmień login!");

                }
            }
            });

    }

    public static void main(String args[]){
        /*
        JFrame frame = new JFrame("Zarejestruj");
        frame.setContentPane(new Zarejestruj().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        */
    }


}
