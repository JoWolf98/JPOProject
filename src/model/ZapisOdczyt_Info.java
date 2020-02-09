package model;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;

public class ZapisOdczyt_Info {


    public void zapiszUżytkownika(Użytkownik użytkownik) {
        //1. klasa gson to zapisu danych
        Gson gson = new Gson();
        //2.pykamy sb nowy pliczek i zapisujemy w formacie json
        try (FileWriter writer = new FileWriter("src\\użytkownicy\\"+użytkownik.getLogin()+".json")) {

            gson.toJson(użytkownik, writer);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Użytkownik> odczytaj_użytkowników()
    {
        ArrayList<Użytkownik> użytkownicy=new ArrayList<Użytkownik>();

        File dir = new File("src\\użytkownicy");
        //System.out.println(dir.list().length);

        for (File file : dir.listFiles()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                Gson gson =new Gson();
                String użytkownik=new String();
                użytkownik=reader.readLine();
                System.out.println(użytkownik);
                użytkownicy.add(gson.fromJson(użytkownik,Użytkownik.class));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return użytkownicy;
    }

    public String usuń_użytkownika(Użytkownik stary_użytkownik){
        String komunikat="";
        File dir = new File("src\\użytkownicy");
        for (File file : dir.listFiles()) {
           if(file.getName().equals(stary_użytkownik.getLogin()+".json"))
           {
               file.delete();
               komunikat="Usunięto użytkownika: "+stary_użytkownik.getLogin();
           }

           else
           {
               komunikat="Nie ma takiego użytkownika!";
           }
        }
        return komunikat;
    }




    public static void main(String args[]) throws IOException, UżytkownikException {

        ZapisOdczyt_Info eh=new ZapisOdczyt_Info();

        Wypozycz_Film_Serial wolfrose = new Wypozycz_Film_Serial();
        Użytkownik ja = new Użytkownik("rozia", "haha");
        wolfrose.zarejestruj(ja.getLogin(),ja.getHaslo());

        Filmy hehzja = wolfrose.wyszukaj_Film("Zielona mila");
        Filmy hehzja2 = wolfrose.wyszukaj_Film("Leon Zawodowiec");
        ja=wolfrose.zaloguj(ja.getLogin(),ja.getHaslo());
        //wolfrose.wyloguj(ja);
        //eh.zapisz_mnie();

        //Gson gson=new Gson();
        System.out.println(ja.wypozycz_film(hehzja));
        //ja.wypozycz_film(hehzja2);
        wolfrose.wyloguj(ja);
        //System.out.println(wolfrose.usun_uzytkownika(ja));
        ja=wolfrose.zaloguj(ja.getLogin(),ja.getHaslo());

        Filmy filmy=wolfrose.wyszukaj_Film("Zielona mila");
        System.out.println(ja.oddaj_film(filmy));
        ja.oddaj_film(filmy);
        wolfrose.wyloguj(ja);


    }
}


//String json1 = gson.toJson(użytkownik.get_baza_polubionych_filmów());
// String json2 = gson.toJson(użytkownik.get_baza_polubionych_seriali());
// String json3 = użytkownik.get_baza_wypożyczonych_filmów().get(0).hehz();
//String json4 = gson.toJson(użytkownik.get_baza_wypożyczonych_seriali());

// System.out.println(json3);
        /*
        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("src\\model\\staff.json")) {

            gson.toJson(rozia,writer);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        */
//Gson hehz=new Gson();
//JsonArray lista_filmów = new JsonArray();
        /*

        try (FileWriter file = new FileWriter("employees.json")) {

            file.write(lista_filmów.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        */






    /*
    public String hehz(Filmy filmy)
    {
        JsonObject ob=new JsonObject();
        ob.addProperty("gatunek",filmy.getGatunek());
        ob.addProperty("ocena",filmy.getOcena());
        ob.addProperty("nazwa",filmy.getNazwa());

        return ob.toString();
/*
        private String gatunek;
        private String ocena;
        private String nazwa;
        private String data_premiery;//narazie string bo sie lepiej wczytuje xd potem mozna by rzutowac na inta
        private String reżyseria;
        private String opis;

        private String produkcja; //w sensie kraj XD
        private String dlugosc_filmu;//



        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(rozia);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("src\\model\\staff.json")) {

            gson.toJson(rozia, writer);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        */