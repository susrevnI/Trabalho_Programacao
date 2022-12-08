package group.trabalho_javafx;

public class Funcionario {
    
    private String nome;
    private String dataNasc;
    private String cpf;
    private String cnpj;
    private String email;
    private String telefone;
    
    public Funcionario(String nome, String dataNasc, String cpf, String cnpj, String email, String telefone) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

}
