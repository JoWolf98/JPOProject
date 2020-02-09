package model;

public class Filmy implements Comparable<Filmy> {

    private String gatunek;

    private String ocena;

    private String nazwa;

    private String data_premiery;//narazie string bo sie lepiej wczytuje xd potem mozna by rzutowac na inta

    private String reżyseria;

    private String opis;

    private String produkcja; //w sensie kraj XD

    private String dlugosc_filmu;//ok


    public Filmy(String gatunek, String ocena, String nazwa, String data_premiery, String reżyseria, String opis, String produkcja, String dlugosc_filmu) {
        //super(gatunek, ocena, nazwa, data_premiery, reżyseria, opis); //dziedziczony konstruktor
        this.dlugosc_filmu = dlugosc_filmu;
        this.produkcja = produkcja;
        this.gatunek = gatunek;
        this.ocena = ocena;
        this.nazwa = nazwa;
        this.data_premiery = data_premiery;
        this.reżyseria = reżyseria;
        this.opis = opis;

    }

    public Filmy() {}


    public Filmy(String nazwa) {
        this.nazwa=nazwa;
    }

    public String getdlugosc_filmu() {

        return this.dlugosc_filmu;
    }

    public String getProdukcja() {

        return this.produkcja;
    }

    public String getData_premiery() { return this.data_premiery; }

    public String getGatunek(){ return this.gatunek; }

    public String getOcena(){ return this.ocena; }

    public String getNazwa() { return nazwa; }

    public String getOpis() { return opis; }

    public String getReżyseria() { return reżyseria; }

    @Override
    public int compareTo(Filmy filmy) {
        if (Double.valueOf(this.getOcena()) > Double.valueOf(filmy.getOcena())) return -1;
        else if (Double.valueOf(this.getOcena()) < Double.valueOf(filmy.getOcena())) return 1;
        else return 0;
    }

   /* public void PostIMG() throws ClientProtocolException, IOException {


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
        builder.addTextBody("dlugosc_filmu", this.dlugosc_filmu);

        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);

        client.close();
    }



*/
}

