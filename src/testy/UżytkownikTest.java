package testy;

import model.Użytkownik;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

//import model.Wypozycz_Film_Serial;

class UżytkownikTest {

    @org.junit.jupiter.api.Test
    void getStan() throws IOException {
        Użytkownik joanna=new Użytkownik("JoannaXD","javaisthebest");
        //Wypozycz_Film_Serial wypozyczalnia=new Wypozycz_Film_Serial();
        //wypozyczalnia.zarejestruj(joanna.getLogin(),joanna.getHaslo());
        //joanna=wypozyczalnia.zaloguj(joanna.getLogin(),joanna.getHaslo());
        assertEquals(true,joanna.getStan());

        Użytkownik rozanna=new Użytkownik("rozanna","teleinformatykaforever");
        assertEquals(false,rozanna.getStan());

    }


}