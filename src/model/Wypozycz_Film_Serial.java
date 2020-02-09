package model;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
TO DO:
zrobić wyjątki czy coś w razie jakby jakiejś informacji nie było :/

coś z gatunkami, jakoś przecinkami oddzielić czy cóś???
 */

public class Wypozycz_Film_Serial  implements Runnable, Obserwowany {

    private ArrayList<Filmy> filmy=new ArrayList<Filmy>() ;//lista obiektów filmy
    private ArrayList<Seriale> seriale=new ArrayList<Seriale>() ;//lista  obiektów seriale
    private ArrayList<String> urls_filmy=new ArrayList<String>() ;//lista  linków do danego filmy/serialu
    private ArrayList<String> urls_seriale=new ArrayList<String>() ;//lista  linków do danego filmy/serialu
    private ArrayList<Użytkownik> uzytkownicy=new ArrayList<Użytkownik>();//lista  użytkowników
    //private List<Użytkownik> uzytkownicy;
    private Obserwator obserwator;

    //najpierw tworzę linki
    public Wypozycz_Film_Serial() throws IOException {


        //filmy
        ArrayList<String> oceny_lista_filmy =new ArrayList<String>();
        ArrayList<String> oceny_lista_seriale =new ArrayList<String>();

        for (int i = 1; i <= 1; i++) {//ograniczenie do 1 bo lata świetlne miną zanim to się załaduje XD
            Connection connect = Jsoup.connect("https://www.filmweb.pl/films/search?orderBy=popularity&descending=true&page=" + i);//tworzenie url do strony konkretnego filmu/serialu
            Document document = connect.get();//łączenie
            Elements alla = document.select("a[class=filmPreview__link]");//określenie selektora css żeby złapało to co chcemy

            for (Element elem : alla) {
                String linkHref = elem.attr("href");
                urls_filmy.add("https://www.filmweb.pl" + linkHref);//wejście w stronę danego filmu po info
            }

            Elements oceny = document.select("span[class=rateBox__rate]");//dwa razy kreska na dole :))))
            for(Element ocenh: oceny)
            {
                String ocena=ocenh.text().replace(",",".");//żeby można było rzucić na int
                oceny_lista_filmy.add(ocena);

            }

        }


        for(int j=0;j<urls_filmy.size();j++) {
            Connection connect2 = Jsoup.connect(urls_filmy.get(j));
            Document document2 = connect2.get();//łączenie XD

            //nazwa
            Elements elem_tytuł = document2.select("h1 a[title]");
            String tytuł_filmu = elem_tytuł.attr("title");

            //data_premiery
            Elements elem_data_premiery = document2.select("h1 span");
            String data_premiery = elem_data_premiery.text().substring(1,5); //substring żeby był sam rok

            //gatunek zrobc tak zeby przed dużą literą dać przecinek, albo ile tych gatunków?
            Elements elem_gatunek = document2.select("div[class] table tbody tr td ul[class=inline sep-comma genresList] li a[href]");
            String gatunek = elem_gatunek.text();

            //reżyseria
            Elements elem_reżyseria = document2.select("div[class] table tbody tr td ul[class=inline sep-comma] li[itemprop=director] a[href]");
            String reżyseria = elem_reżyseria.text();

            //opis
            Elements elem_opis = document2.select("div[class] p[itemprop=description]");
            String opis = elem_opis.text();

            //długość filmu
            Elements elem_długosć = document2.select("div time[class=filmTime]");
            String długość = elem_długosć.text().substring(0,elem_długosć.text().length()-10);

            //produkcja
            Elements elem_produkcja = document2.select("a[href*=films/search?countries]");
            String produkcja=elem_produkcja.text();

            //dodanie utworzonego filmu/serialu do naszych list
            filmy.add(new Filmy(gatunek,oceny_lista_filmy.get(j),tytuł_filmu,data_premiery,reżyseria,opis,produkcja,długość));
        }

        //seriale
        for (int i = 1; i <= 1; i++) {
            Connection connect = Jsoup.connect("https://www.filmweb.pl/serials/search?orderBy=popularity&descending=true&page=" + i);
            Document document = connect.get();
            Elements alla = document.select("a[class=filmPreview__link]");

            for (Element elem : alla) {
                String linkHref = elem.attr("href");
                urls_seriale.add("https://www.filmweb.pl" + linkHref);
            }

            Elements oceny = document.select("span[class=rateBox__rate]");
            for(Element ocenh: oceny)
            {
                String ocena=ocenh.text().replace(",",".");
                oceny_lista_seriale.add(ocena);

            }
        }


        for(int j=0;j<urls_seriale.size();j++) {
            Connection connect2 = Jsoup.connect(urls_seriale.get(j));
            Document document2 = connect2.get();//łączenie XD

            //nazwa
            Elements elem_tytuł = document2.select("h1 a[title]");
            String tytuł_serialu = elem_tytuł.attr("title");

            //data_premiery
            Elements elem_data_premiery = document2.select("h1 span");
            String data_premiery = elem_data_premiery.text().substring(1,5);


            //gatunek
            Elements elem_gatunek = document2.select("a[href*=/serials/search?genres]");
            String gatunek = elem_gatunek.text();


            //reżyseria
            Elements elem_reżyseria = document2.select("div[class*=filmInfo] a[href*=/person/]");
            String reżyseria = elem_reżyseria.text();

            //opis
            Elements elem_opis = document2.select("div[class] p[itemprop=description]");
            String opis = elem_opis.text();

            //długość filmu
            Elements elem_długosć = document2.select("div time[class=filmTime]");
            String długość = elem_długosć.text().substring(0,elem_długosć.text().length()-10);

            //liczba sezonów
            Elements elem_liczba_sezonów = document2.select("ul[class*=list inline episodesSeason] a[class=link normal]");
            String liczba_sezonów=elem_liczba_sezonów.text().substring(0,1);


           seriale.add(new Seriale(gatunek,oceny_lista_seriale.get(j),tytuł_serialu,data_premiery,reżyseria,opis,długość,liczba_sezonów));
        }

        // wczytanie użytkowników
        //File file = new File("src\\model\\użytkownicy.txt");
        //Scanner in = new Scanner(file);
        ZapisOdczyt_Info zapisOdczyt_info=new ZapisOdczyt_Info();
        this.uzytkownicy=zapisOdczyt_info.odczytaj_użytkowników();
        this.uzytkownicy.add(new Użytkownik("root","root"));

    }

    //getery
    public ArrayList<Filmy> get_filmy() {

        return filmy;
    }

    public ArrayList<Seriale> get_seriale() {

        return seriale;
    }

    public ArrayList<Użytkownik> get_uzytkownicy() {

        return uzytkownicy;
    }
    public List<String> get_urls_filmy() {

        return urls_filmy;
    }
    public List<String> get_urls_seriale() {

        return urls_seriale;
    }

    //metody
   public Filmy wyszukaj_Film(String podane) {
        Filmy wyszukane=new Filmy();
       for (int i = 0; i < filmy.size(); i++) {
           if (podane.toLowerCase().equals(filmy.get(i).getNazwa().toLowerCase())) {
               wyszukane=filmy.get(i);
           }
       }
       if(wyszukane!=null){
       }
       else{
           wyszukane=null;
       }
       return wyszukane;
   }



    public Seriale wyszukaj_Serial(String podane) {

        Seriale wyszukane=new Seriale();
        for (int i = 0; i < seriale.size(); i++) {
            if (podane.toLowerCase().equals(seriale.get(i).getNazwa().toLowerCase())) {
                wyszukane=seriale.get(i);
            }
        }
        if(wyszukane!=null){
        }
        else{
            wyszukane=null;
        }
        return wyszukane;
    }

    public Użytkownik zaloguj(String login, String hasło) {
        Użytkownik my_user=new Użytkownik(login,hasło);
        boolean czy_user;
        czy_user = false;
        int nr_użytk;

        for (int i = 0; i < uzytkownicy.size(); i++) {
            if ((uzytkownicy.get(i).getLogin().contains(login)) && (uzytkownicy.get(i).getHaslo().contains(hasło))) {
                czy_user = true;
                uzytkownicy.get(i).setStan(true);
                my_user=uzytkownicy.get(i);
            }
        }
        if (czy_user) {
        } else {
            my_user = null;
        }
        return my_user;
    }

    public void wyloguj(Użytkownik uzytkownik) throws UżytkownikException {
        if (uzytkownik.getStan() == true) {
            uzytkownik.setStan(false);
            ZapisOdczyt_Info zapisz=new ZapisOdczyt_Info();
            zapisz.zapiszUżytkownika(uzytkownik);
        }
        else
            throw new UżytkownikException();

    }

    public int zarejestruj(String login, String hasło) {
        int a=0;

        ZapisOdczyt_Info zapisOdczyt_info=new ZapisOdczyt_Info();

        for (int i = 0; i < this.uzytkownicy.size(); i++) {
            if (this.uzytkownicy.get(i).getLogin().equals(login)) {
                a++;
            } else {

            }
        }
        if (a == 0) {
            uzytkownicy.add(new Użytkownik(login, hasło));
            System.out.println("Zarejestrowano poprawnie!");
            zapisOdczyt_info.zapiszUżytkownika(new Użytkownik(login, hasło));

        } else
            System.out.println("Taki użytkownik już istnieje!");

        return a;
    }

    public void usun_uzytkownika(Użytkownik stary_użytkownik) {

        ZapisOdczyt_Info zapisOdczyt_info=new ZapisOdczyt_Info();
        //this.uzytkownicy.remove(new Użytkownik(old_user.getLogin(), old_user.getHaslo()));
        for(int i=0;i<this.uzytkownicy.size();i++) {
            if (this.uzytkownicy.get(i).getLogin().equals(stary_użytkownik.getLogin()))
            {
                this.uzytkownicy.remove(this.uzytkownicy.get(i));
            }
        }
        zapisOdczyt_info.usuń_użytkownika(stary_użytkownik);

    }


    public static void main(String args[]) throws IOException, UżytkownikException {
       Wypozycz_Film_Serial WolfRose= new Wypozycz_Film_Serial();
      // System.out.println(WolfRose.get_urls());//działa XDD !


    }

    @Override
    public void run() {
        Ranking ranking = new Ranking(seriale,  filmy);
         String filmy = new String();
         String serial = new String();

        while (true) {

            for (int i = 0; i < 5; i++) {
                filmy = ranking.getTop_5_filmów().get(i).getNazwa();
                serial = ranking.getTop_5_seriali().get(i).getNazwa();

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                if (obserwator != null)
                    obserwator.inform(filmy, serial);

            }
        }


    }

    @Override
    public void subskrybuj(Obserwator o) {
        this.obserwator = o;
    }

    @Override
    public void odsubskrybuj(Obserwator o) {
        if (obserwator == o)
            this.obserwator = null;

    }
}
//jakieś śmieci:

 /*
            String text = doc.body().text(); // "An example link"
            String linkHref = link.attr("href"); // "http://example.com/"
            String linkText = link.text(); // "example""
            System.out.println(linkText);
            System.out.println(linkHref);
            */
//String linkOuterH = link.outerHtml();
// "<a href="http://example.com"><b>example</b></a>"
//String linkInnerH = link.html(); // "<b>example</b>"

//filmy.add(new Filmy(elem.text()));
                /*Pattern p = Pattern.compile("href=\"(.*?)\"");
                Matcher m = p.matcher(elem.html());
                String url = null;
                if (m.find()) {
                    url = m.group(1); // this variable should contain the link URL
                    System.out.println(url);
                }
                */


                           /*
        Filmy moj_film = new Filmy();
        int nr = 0;
        moj_film = null;
        for (int i = 0; i < 20; i++) {
            if (podane.toLowerCase().equals(filmy[i].getNazwa().toLowerCase()))
                nr = i + 1;
        }
        if (nr != 0)
            moj_film = filmy[nr - 1];
        return moj_film;
        */

                              /*
        Seriale moj_serial = new Seriale();
        int nr = 0;
        moj_serial = null;
        for (int i = 0; i < 20; i++) {
            if (podane.toLowerCase().equals(seriale[i].getNazwa().toLowerCase()))
                nr = i + 1;
        }
        if (nr != 0)
            moj_serial = seriale[nr - 1];
        return moj_serial;
        */

                              /*
    public void run() {
        Ranking ranking = new Ranking();
        String filmy = new String();
        String serial = new String();

        while (true) {

            for (int i = 0; i < 10; i++) {
                filmy = ranking.getTop_10_filmow()[i];
                serial = ranking.getTop_10_seriali()[i];

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                if (obserwator != null)
                    obserwator.inform(filmy, serial);

            }
        }

    }
    */

    /*public void subskrybuj(Obserwator ob) {
        this.obserwator = ob;
    }

    public void odsubskrybuj(Obserwator ob) {
        if (obserwator == ob)
            this.obserwator = null;
    }
    */