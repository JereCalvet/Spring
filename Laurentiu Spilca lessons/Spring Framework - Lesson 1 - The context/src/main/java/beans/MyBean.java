package beans;

/*
Una bean es una clase normal la cual sus instancias van a ser puestas en el contexto.
*/

public class MyBean {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
