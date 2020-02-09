package model;

import java.io.IOException;
import java.util.*;


public class Ranking {

    private List<Seriale> top_5_seriali ;
    private List<Filmy> top_5_filmów ;

    public Ranking(List<Seriale> seriale, List<Filmy> filmy) {
        Collections.sort(seriale,Seriale::compareTo); //zaimplementowane metody compare to żeby móc porównywać tylko oceny
        top_5_seriali= seriale.subList(0,5);//bo top 5
        Collections.sort(filmy,Filmy::compareTo);
        top_5_filmów= filmy.subList(0,5);

    }


    //wyświetlać trzeba będzie w pętli
    public List<Seriale> getTop_5_seriali() {
        return top_5_seriali;
    }

    public List<Filmy> getTop_5_filmów() {
        return top_5_filmów;
    }



    public static void main(String args[]) throws IOException, UżytkownikException {
        //Wypozycz_Film_Serial WolfRose = new Wypozycz_Film_Serial();
        //Ranking ranking=new Ranking(WolfRose.get_seriale(),WolfRose.get_filmy());

    }

}