package group.trabalho_javafx.Util;

import java.util.ArrayList;
import java.util.List;

import group.trabalho_javafx.Acidente;
import group.trabalho_javafx.Funcionario;

public class Listas {
    
    public static List<Funcionario> listaFuncionarios = new ArrayList<>();

    public static List<Acidente> listaAcidentes = new ArrayList<>();

    public static List<String> getTituloLista(){
        List<String> titulos = new ArrayList<>();

        for (Acidente elemento : listaAcidentes) {
            titulos.add(elemento.getTitulo());
        }

        return titulos;
    }
}
