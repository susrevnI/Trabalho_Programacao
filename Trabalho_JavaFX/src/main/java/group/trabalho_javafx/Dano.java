package group.trabalho_javafx;

public class Dano {
    
    private String descricaoDano;

    public Dano(String descricaoDano) {
        this.descricaoDano = descricaoDano;
    }

    public String getDescricaoDano(){
        return descricaoDano;
    }

    @Override
    public String toString() {
        return "Dano [descricaoDano=" + descricaoDano + "]";
    }

}
