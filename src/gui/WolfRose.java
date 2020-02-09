package gui;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WolfRose implements Obserwator {
    private JTabbedPane tabbedPane1;
    private JTextArea textArea1;
    private JButton szukajFilmButton;
    private JButton szukajSerialButton;
    private JButton obejrzyjPrzykładowyFilmButton;
    private JTextField loginTextField;
    private JButton zalogujButton;
    private JButton nieMaszKontaZarejestrujButton;
    private JPasswordField hasłoPasswordField;
    private JTextField ilośćWypożyczonychFilmówTextField;
    private JTextField textField2;
    private JTextField ilośćWypożyczonychSerialiTextField;
    private JTextField textField3;
    private JTextField ilośćPolubionychFilmówTextField;
    private JTextField textField4;
    private JButton wylogujButton;
    private JTextField nazwaUżytkownikaTextField;
    private JTextField textField5;
    private JButton wypożyczFilmButton;
    private JButton oddajFilmButton;
    private JButton polubFilmButton;
    private JTextField nazwaTextField;
    private JTextField textField6;
    private JTextField gatunekTextField;
    private JTextField textField8;
    private JTextField ocenaTextField;
    private JTextField textField10;
    private JTextField dataPremieryTextField;
    private JTextField textField12;
    private JTextField długośćFilmuTextField;
    private JTextField textField14;
    private JButton wypożyczSerialButton;
    private JButton oddajSerialButton;
    private JButton polubSerialButton;
    private JTextField nazwaTextField2;
    private JTextField textField7;
    private JTextField gatunekTextField1;
    private JTextField textField11;
    private JTextField ocenaTextField1;
    private JTextField textField15;
    private JTextField dataPremieryTextField1;
    private JTextField textField17;
    private JTextField długośćOdcinkaTextField;
    private JTextField textField19;
    private JTextField liczbaSezonówTextField;
    private JTextField textField21;
    private JPanel Panel1;
    private JTextField rankingSerialiTextField;
    private JTextField textField13;
    private JTextField rankingFilmówTextField;
    private JTextField textField16;
    private JButton ButtonLista;
    private JButton pokażListęWypożyczonychFilmówButton;
    private JTextField opisTextField;
    private JTextField textField9;
    private JTextField reżyseriaTextField;
    private JTextField textField20;
    private JTextField reżyseriaTextField1;
    private JTextField textField18;
    private JTextField opisTextField1;
    private JTextField textField23;
    private JTextField produkcjaTextField;
    private JTextField textField25;
    private JTextField ilośćPolubionychSerialiTextField;
    private JTextField textField22;
    private JButton pokażListęWypożyczonchSerialiButton;
    private JButton pokażListęPolubionychFilmówButton;
    private JButton pokażListęPolubionchSerialiButton;
    private Wypozycz_Film_Serial wolfrose;
    private Użytkownik uzytkownik;
    private ZapisOdczyt_Info zapisOdczyt_info;
    private Filmy film;
    private Seriale serial;


    public WolfRose(final JFrame frame, final Wypozycz_Film_Serial wolfRose) {
        wolfRose.subskrybuj(this);
        this.uzytkownik = new Użytkownik();
        this.zapisOdczyt_info = new ZapisOdczyt_Info();
        this.wolfrose = wolfRose;
        this.film = new Filmy();
        this.serial = new Seriale();

        final JFrame frame2 = new JFrame("pomocnicze 2");
        final JFrame frame3 = new JFrame("pomocnicze3");

        zalogujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uzytkownik.getStan() == true)
                    JOptionPane.showMessageDialog(frame2, "Brak mi słów! Po co się logujesz jak już jesteś zalogowany?");
                else {
                    if (wolfRose.zaloguj(loginTextField.getText(), hasłoPasswordField.getText()) != null) {
                        JOptionPane.showMessageDialog(frame2, "Logowanie pomyślne");
                        uzytkownik = wolfRose.zaloguj(loginTextField.getText(), hasłoPasswordField.getText());
                        textField5.setText(uzytkownik.getLogin());
                        textField2.setText(Integer.toString(uzytkownik.getIlosc_wypozyczonych_filmów()));// rozdzieic ilosc wypozycznych na filmy i seriale
                        textField3.setText(Integer.toString(uzytkownik.getGetIlosc_wypozyczonych_seriali()));
                        textField4.setText(Integer.toString(uzytkownik.getIlosc_polubionych_filmów()));
                        textField22.setText(Integer.toString(uzytkownik.getIlosc_polubionych_seriali()));

                    } else {
                        JOptionPane.showMessageDialog(frame2, "Błędny login lub hasło");
                    }
                }
            }
        });
        nieMaszKontaZarejestrujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uzytkownik.getStan() == true) {
                    JOptionPane.showMessageDialog(frame2, "Jesteś zalogowany, masz już konto -,-");
                } else {
                    Zarejestruj zarejestruj = new Zarejestruj(wolfRose);
                    frame3.setContentPane(new Zarejestruj(wolfRose).getPanel1());
                    frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame3.pack();
                    frame3.setVisible(true);
                }


            }
        });
        wylogujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    wolfRose.wyloguj(uzytkownik);
                    JOptionPane.showMessageDialog(frame2, "Wylogowano pomyślnie");
                } catch (UżytkownikException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Nawet nie jesteś zalogowany XD!");
                }
                uzytkownik=new Użytkownik();
                refresh();

            }
        });

        szukajSerialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Seriale moj_serial = new Seriale();
                moj_serial = wolfRose.wyszukaj_Serial(textArea1.getText());
                serial = moj_serial;
                if (moj_serial != null) {
                    textArea1.setText("Mamy twój serial!");
                    textField7.setText(moj_serial.getNazwa());
                    textField11.setText(moj_serial.getGatunek());
                    textField15.setText(moj_serial.getOcena());
                    textField17.setText(moj_serial.getData_premiery());
                    textField19.setText(moj_serial.getDlugosc_odcinka());
                    textField21.setText(moj_serial.getLiczba_sezonow());
                    textField9.setText(moj_serial.getOpis());
                    textField20.setText(moj_serial.getReżyseria());

                } else
                    textArea1.setText("Nie znaleziono takiego serialu :c");
            }
        });

        szukajFilmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Filmy moj_film = new Filmy();
                moj_film = wolfRose.wyszukaj_Film(textArea1.getText());
                film = moj_film;
                if (moj_film != null) {
                    textArea1.setText("Mamy twój film! Zobacz w zakładce Film");
                    textField6.setText(moj_film.getNazwa());
                    textField8.setText(moj_film.getGatunek());
                    textField10.setText(moj_film.getOcena());
                    textField12.setText(moj_film.getData_premiery());
                    textField14.setText(moj_film.getdlugosc_filmu());
                    textField18.setText(moj_film.getReżyseria());
                    textField23.setText(moj_film.getOpis());
                    textField25.setText(moj_film.getProdukcja());
                } else
                    textArea1.setText("Nie znaleziono takiego filmu :c");
            }
        });
        wypożyczFilmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (uzytkownik.wypozycz_film(film) == 1) {
                        if (textField6.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame2, "Musisz wybrać film!");
                            uzytkownik.oddaj_film(film);
                        } else {
                            JOptionPane.showMessageDialog(frame2, "Wypożyczyłeś film");
                        }
                    } else if (uzytkownik.wypozycz_film(film) == 0) {
                        JOptionPane.showMessageDialog(frame2, "Nie możesz wypożyczyć więcej filmów");
                    } else JOptionPane.showMessageDialog(frame2, "Już wypożyczyłeś ten film!");
                } catch (UżytkownikException wyjatek) {
                    wyjatek.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Aby coś wypożyczyć, musisz być zalogowanym!");
                }
                refresh();
            }
        });


        oddajFilmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (uzytkownik.oddaj_film(film) == 1) {
                        if (textField6.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame2, "Musisz wybrać film!");
                        } else JOptionPane.showMessageDialog(frame2, "Oddałeś film");
                    } else {
                        JOptionPane.showMessageDialog(frame2, "Nie masz nic do oddania!");
                    }

                } catch (UżytkownikException wyjatek) {
                    wyjatek.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Aby coś oddać, musisz być zalogowanym!No proszę...");


                }
                refresh();
            }
        });
        wypożyczSerialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (uzytkownik.wypozycz_serial(serial) == 1) {
                        if (textField7.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame2, "Musisz wybrać serial!");
                            uzytkownik.oddaj_serial(serial);
                        } else {
                            JOptionPane.showMessageDialog(frame2, "Wypożyczyłeś serial");
                        }
                    } else if (uzytkownik.wypozycz_serial(serial) == 0)
                        JOptionPane.showMessageDialog(frame2, "Nie możesz wypożyczyć więcej seriali");
                    else {
                        JOptionPane.showMessageDialog(frame2, "Już wypożyczyłeś ten serial!");
                    }

                } catch (UżytkownikException wyjatek) {
                    wyjatek.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Aby coś wypożyczyć, musisz być zalogowanym!");


                }
                refresh();

            }
        });

        oddajSerialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    if (uzytkownik.oddaj_serial(serial) == 1) {
                        if (textField7.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame2, "Musisz wybrać serial!");
                        } else JOptionPane.showMessageDialog(frame2, "Oddałeś serial");
                    } else {
                        JOptionPane.showMessageDialog(frame2, "Nie masz nic do oddania!");
                    }

                } catch (UżytkownikException wyjatek) {
                    wyjatek.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Aby coś oddać, musisz być zalogowanym!No proszę...");


                }
                refresh();

            }
        });
        polubSerialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    if(textField7.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(frame2, "Wybierz serial!");
                    }
                    else {
                        uzytkownik.polub_odlub_serial(serial);
                        JOptionPane.showMessageDialog(frame2, "Polubiłes serial");
                    }
                }
                catch(UżytkownikException wyjatek){
                    wyjatek.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Aby coś polubić, musisz być zalogowanym");


                }
                refresh();


            }
        });
        polubFilmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    if(textField6.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(frame2, "Wybierz film!");
                    }
                    else {
                        uzytkownik.polub_odlub_film(film);
                        JOptionPane.showMessageDialog(frame2, "Polubiłes film");
                    }
                } catch (UżytkownikException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(frame2, "Aby coś polubić, musisz być zalogowanym");
                }
                refresh();
            }
        });
        pokażListęWypożyczonychFilmówButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(uzytkownik.getStan()==false)
                {
                    JOptionPane.showMessageDialog(frame2, "Musisz być zalogowanym");
                }
                else {
                    ListUżytkownikaGUI listaUżytkownikaGUI = new ListUżytkownikaGUI(uzytkownik);
                    frame3.setContentPane(new ListUżytkownikaGUI(uzytkownik).getPanel1());
                    frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame3.pack();
                    frame3.setVisible(true);
                }
            }
        });
        pokażListęWypożyczonchSerialiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(uzytkownik.getStan()==false)
                {
                    JOptionPane.showMessageDialog(frame2, "Musisz być zalogowanym");
                }
                else {
                    ListaUżytkownikaGUI2 listaUżytkownikaGUI2 = new ListaUżytkownikaGUI2(uzytkownik);
                    frame3.setContentPane(new ListaUżytkownikaGUI2(uzytkownik).getPanel1());
                    frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame3.pack();
                    frame3.setVisible(true);
                }
            }
        });
        pokażListęPolubionychFilmówButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(uzytkownik.getStan()==false)
                {
                    JOptionPane.showMessageDialog(frame2, "Musisz być zalogowanym");
                }
                else {
                    ListaUżytkownikaGUI3 listaUżytkownikaGUI3 = new ListaUżytkownikaGUI3(uzytkownik);
                    frame3.setContentPane(new ListaUżytkownikaGUI3(uzytkownik).getPanel1());
                    frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame3.pack();
                    frame3.setVisible(true);
                }
            }
        });
        pokażListęPolubionchSerialiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(uzytkownik.getStan()==false)
                {
                    JOptionPane.showMessageDialog(frame2, "Musisz być zalogowanym");
                }
                else {
                    ListaUżytkownikaGUI4 listaUżytkownikaGUI4 = new ListaUżytkownikaGUI4(uzytkownik);
                    frame3.setContentPane(new ListaUżytkownikaGUI4(uzytkownik).getPanel1());
                    frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame3.pack();
                    frame3.setVisible(true);
                }
            }
        });
        ButtonLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaGUI listaGUI = new ListaGUI(wolfRose);
                frame3.setContentPane(new ListaGUI(wolfRose).getPanel1());
                frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame3.pack();
                frame3.setVisible(true);
            }
        });

    }
        public void refresh()
        {
            if (uzytkownik.getStan() == false) {
                textField5.setText("");
                textField2.setText("");// rozdzieic ilosc wypozycznych na filmy i seriale
                textField4.setText("");
                textField3.setText("");
                textField22.setText("");
            } else {
                textField2.setText(Integer.toString(uzytkownik.getIlosc_wypozyczonych_filmów()));
                textField4.setText(Integer.toString(uzytkownik.getIlosc_polubionych_filmów()));
                textField3.setText(Integer.toString(uzytkownik.getGetIlosc_wypozyczonych_seriali()));
                textField22.setText(Integer.toString(uzytkownik.getIlosc_polubionych_seriali()));
            }
        }

        public static void main(String args[]) throws IOException {

            Wypozycz_Film_Serial wolfRose=new Wypozycz_Film_Serial();
            JFrame frame = new JFrame("WOLF&ROSES");
            frame.setContentPane(new WolfRose(frame,wolfRose).Panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            Thread t =new Thread(wolfRose);
            t.start();

        }
        public void inform(String film,String serial){
            textField13.setText(serial);
            textField16.setText(film);

        }
        /*




        ButtonLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaGUI listaGUI = new ListaGUI(wolfRose);
                frame3.setContentPane(new ListaGUI(wolfRose).getPanel1());
                frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame3.pack();
                frame3.setVisible(true);
            }
        });

    }



*/
}

