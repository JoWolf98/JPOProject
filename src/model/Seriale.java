package model;

public class Seriale implements Comparable<Seriale>{
    private String gatunek;
    private String ocena;
    private String nazwa;
    private String data_premiery;
    private String reżyseria;//==reżyseria
    private String opis;

    private String dlugosc_odcinka;
    private String liczba_sezonow;


    public Seriale(String gatunek, String ocena, String nazwa,String data_premiery,String reżyseria,String opis, String dlugosc_odcinka,String liczba_sezonow) {
        //super(gatunek, ocena, nazwa,data_premiery,reżyseria,opis);
        this.dlugosc_odcinka=dlugosc_odcinka;
        this.liczba_sezonow=liczba_sezonow;
        this.gatunek = gatunek;
        this.ocena = ocena;
        this.nazwa = nazwa;
        this.data_premiery = data_premiery;
        this.reżyseria = reżyseria;
        this.opis = opis;
    }


    public Seriale(){  }

    public Seriale(String nazwa){ this.nazwa=nazwa;}

    public String getDlugosc_odcinka()
    {
        return dlugosc_odcinka;
    }

    public String getLiczba_sezonow(){
        return liczba_sezonow;
    }

    public String getData_premiery() { return this.data_premiery; }

    public String getGatunek(){ return this.gatunek; }

    public String getOcena(){ return this.ocena; }

    public String getNazwa() { return nazwa; }

    public String getOpis() { return opis; }

    public String getReżyseria() { return reżyseria; }

    @Override
    public int compareTo(Seriale seriale) {
        if(Double.valueOf(this.getOcena())> Double.valueOf(seriale.getOcena())) return -1;
        else if (Double.valueOf(this.getOcena())<Double.valueOf(seriale.getOcena())) return 1;
        else return 0;
    }
    /*public void PostIMG() throws ClientProtocolException, IOException {


        //Tworzymy klienta ktory trzyma naszego requesta
        CloseableHttpClient client = HttpClients.createDefault();
        //Laczymy sie do naszej strony
        HttpPost httpPost = new HttpPost("http://localhost:4000");


        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("gatunek", this.gatunek); //name to title
        builder.addTextBody("ocena", this.ocena);
        builder.addTextBody("nazwa", this.nazwa); //name to title
        builder.addTextBody("data_premiery", this.data_premiery);
        builder.addTextBody("rezyseria", this.reżyseria);
        builder.addTextBody("opis", this.opis);
        builder.addTextBody("dlugosc_odcinka", this.dlugosc_odcinka);
        builder.addTextBody("liczba_sezonow", this.liczba_sezonow);
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);

        client.close();
    }*/

}
