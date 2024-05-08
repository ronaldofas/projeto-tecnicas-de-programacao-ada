package aulas;

public class Filho extends Pai{

    public Filho(final String nome) {
        super(nome);
    }

    public Filho() {
        this("nome padrao");
    }


    public static void main(String[] args) {
        var x = new Filho();
    }
}
