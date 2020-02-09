package model;

public class UżytkownikException extends Exception {

    private String komunikat= "Musisz być zalogowanym!";

    public String UżytkownikException()
    {
        return komunikat;
    }

}




