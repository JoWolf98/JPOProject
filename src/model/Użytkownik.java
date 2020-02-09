package model;

import java.util.ArrayList;
import java.util.List;

public class Użytkownik {
    private String login;
    private String haslo;
    private int ilosc_wypozyczonych_filmów;
    private int ilosc_wypozyczonych_seriali;
    private int ilosc_polubionych_seriali;
    private int ilosc_polubionych_filmów;
    private boolean stan;
    private List<Filmy> baza_wypożyczonych_filmów;
    private List<Seriale> baza_wypożyczonych_seriali;
    private List<Filmy> baza_polubionych_filmów;
    private List<Seriale> baza_polubionych_seriali;
    //private File json_zapis_list;

    public Użytkownik(String login, String haslo)  {
        this.login = login;
        this.haslo = haslo;
        this.ilosc_polubionych_seriali = 0;
        this.ilosc_polubionych_filmów = 0;
        this.ilosc_wypozyczonych_filmów = 0;
        this.ilosc_wypozyczonych_seriali = 0;
        this.stan = false;
        baza_wypożyczonych_filmów = new ArrayList<Filmy>();// czy walnosc filmy i seriale w jedno jak kiedyś??
        baza_wypożyczonych_seriali = new ArrayList<Seriale>();
        baza_polubionych_filmów = new ArrayList<Filmy>();
        baza_polubionych_seriali = new ArrayList<Seriale>();

        //json_zapis_list= ;
    }

    public Użytkownik() {}

    //gettery

    public List<Filmy> get_baza_wypożyczonych_filmów() {
        return this.baza_wypożyczonych_filmów;
    }

    public List<Seriale> get_baza_wypożyczonych_seriali() {
        return this. baza_wypożyczonych_seriali;
    }

    public List<Filmy> get_baza_polubionych_filmów() {
        return this.baza_polubionych_filmów;
    }

    public List<Seriale> get_baza_polubionych_seriali() {
        return this.baza_polubionych_seriali;
    }

    public boolean getStan() { return this.stan; }

    public String getLogin() {
        return this.login;
    }

    public String getHaslo() { return this.haslo; }

    public int getIlosc_wypozyczonych_filmów() {

        return this.ilosc_wypozyczonych_filmów;
    }

    public int getGetIlosc_wypozyczonych_seriali() {

        return this.ilosc_wypozyczonych_seriali;
    }

    public int getIlosc_polubionych_filmów() {

        return this.ilosc_polubionych_filmów;
    }

    public int getIlosc_polubionych_seriali() {

        return this.ilosc_polubionych_seriali;
    }


    //setery
    public void setStan(boolean stan) { this.stan = stan; }

    public void setIlosc_wypozyczonych_filmów(){
        ilosc_wypozyczonych_filmów=baza_wypożyczonych_filmów.size();
    }

    public void setIlosc_wypozyczonych_seriali(){
        ilosc_wypozyczonych_seriali=baza_wypożyczonych_seriali.size();
    }

    public void setIlosc_polubionych_filmów(){
        ilosc_polubionych_filmów=baza_polubionych_filmów.size();
    }

    public void setIlosc_polubionych_seriali(){
        ilosc_polubionych_seriali=baza_polubionych_seriali.size();
    }



    public int wypozycz_film(Filmy film) throws UżytkownikException { //będzie wyrzucany komunkat o sukcesie lub nie
        int i = 0;
        String komunikat;
        if (stan == true) {
            if (baza_wypożyczonych_filmów.contains(film)) {
                System.out.println("Ten film już wypożyczyłeś!");
                i = 2;
            }
            else {
                if (this.ilosc_wypozyczonych_filmów < 10) {//są limity hehe
                    baza_wypożyczonych_filmów.add(film);
                    setIlosc_wypozyczonych_filmów();
                    i=1;
                    //komunikat="Wypożyczyłeś film: "+film.getNazwa();
                } else {
                    System.out.println("nie mozesz wypozyczyc wiecej filmow");
                }
            }
        }
        else {
            throw new UżytkownikException();
        }
        return i;
    }


    public int wypozycz_serial(Seriale serial) throws UżytkownikException {//będzie wyrzucany komunkat o sukcesie lub nie

        int i = 0;
        String komunikat;
        if (stan == true) {//jeśli zalogowany
            if (baza_wypożyczonych_seriali.contains(serial)) {
                System.out.println("Ten serial już wypożyczyłeś!");
                i=2;
            }
            else {
                if (this.ilosc_wypozyczonych_seriali < 10) {
                    baza_wypożyczonych_seriali.add(serial);
                    setIlosc_wypozyczonych_seriali();
                   // komunikat="Wypożyczyłeś serial: "+serial.getNazwa();
                    i=1;
                } else {
                    System.out.println("nie mozesz wypozyczyc wiecej seriali!");
                }
            }
        } else {
            throw new UżytkownikException();
        }
        return i;
    }



    public int oddaj_film(Filmy film) throws UżytkownikException {

        int k = 0;
        String komunikat="";
        if (stan == true) {
            for(int i=0;i<baza_wypożyczonych_filmów.size();i++){
            if (baza_wypożyczonych_filmów.get(i).getNazwa().equals(film.getNazwa())) {
                baza_wypożyczonych_filmów.remove(baza_wypożyczonych_filmów.get(i));
                setIlosc_wypozyczonych_filmów();
               // komunikat="Oddałeś film: "+film.getNazwa();
                k++;
            } else
                System.out.println("nie wypożyczyłes tego filmu");
        }} else {
            throw new UżytkownikException();
        }
        return k;
    }

    public int oddaj_serial(Seriale serial) throws UżytkownikException {

        int k=0;
        String komunikat="";
        if (stan == true) {
            for(int i=0;i<baza_wypożyczonych_seriali.size();i++){
                if (baza_wypożyczonych_seriali.get(i).getNazwa().equals(serial.getNazwa())) {
                    baza_wypożyczonych_seriali.remove(baza_wypożyczonych_seriali.get(i));
                    setIlosc_wypozyczonych_seriali();
                   // komunikat="Oddałeś serial: "+serial.getNazwa();
                    k++;
                } else
                    System.out.println("nie wypożyczyłes tego serialu");
            }} else {
            throw new UżytkownikException();
        }
        return k;
    }

    public void polub_odlub_film(Filmy wybrany) throws UżytkownikException {

        if (stan == true) {
            if(baza_polubionych_filmów.contains(wybrany))
            {
                baza_polubionych_filmów.remove(wybrany);
                setIlosc_polubionych_filmów();
                System.out.println("odlubiłeś film"+wybrany.getNazwa());
            }
            else{
                baza_polubionych_filmów.add(wybrany);
                setIlosc_polubionych_filmów();
                System.out.println("Polubiłeś film: "+wybrany.getNazwa());
            }

         }else {
            throw new UżytkownikException();
        }


    }
    public void polub_odlub_serial(Seriale wybrany) throws UżytkownikException {

        if (stan == true) {
            if(baza_polubionych_seriali.contains(wybrany))
            {
                baza_polubionych_seriali.remove(wybrany);
                setIlosc_polubionych_seriali();
                System.out.println("Odlubiłeś serial "+wybrany.getNazwa());
            }
            else{
                baza_polubionych_seriali.add(wybrany);
                setIlosc_polubionych_seriali();
                System.out.println("Polubiłeś serial: "+wybrany.getNazwa());
            }

        }else {
            throw new UżytkownikException();
        }


    }

  /*  public void PostIMG() throws ClientProtocolException, IOException {


       //Tworzymy klienta ktory trzyma naszego requesta
        CloseableHttpClient client = HttpClients.createDefault();
        //Laczymy sie do naszej strony
        HttpPost httpPost = new HttpPost("http://localhost:4000/page3");


        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("nazwa_uzytkownik", "dfdfdfdf"); //name to title
        builder.addTextBody("ilosc_wypozyczonych_filmow", Integer.toString(this.ilosc_wypozyczonych_filmów));
        builder.addTextBody("ilosc_wypozyczonych_seriali", Integer.toString(this.ilosc_wypozyczonych_seriali));
        builder.addTextBody("ilosc_polubionych_filmow", Integer.toString(this.ilosc_polubionych_filmów));
        builder.addTextBody("ilosc_polubionych_seriali ", Integer.toString(this.ilosc_polubionych_seriali));
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);
  assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
  if(response.getStatusLine().getStatusCode()==200)
      System.out.println("jest ok");
        client.close();
    }*/
}
