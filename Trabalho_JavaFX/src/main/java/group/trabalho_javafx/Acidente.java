package group.trabalho_javafx;

import java.util.List;

public class Acidente {
    
    private String titulo;
    private String data;
    private String horario;
    private String dataHorario;
    private String relatorio;
    private List<String> listaDanos;

    public Acidente(String titulo, String data, String horario, String relatorio, List<String> listaDanos) {
        this.titulo = titulo;
        this.data = data;
        this.horario = horario;
        this.relatorio = relatorio;
        this.listaDanos = listaDanos;

        dataHorario =  data+" "+horario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getDataHorario() {
        return dataHorario;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public List<String> getListaDanos() {
        return listaDanos;
    }

    @Override
    public String toString() {
        return "Acidente [titulo=" + titulo + ", data=" + data + ", horario=" + horario + ", dataHorario=" + dataHorario
                + ", relatorio=" + relatorio + ", listaDanos=" + listaDanos + "]";
    }

    

}
