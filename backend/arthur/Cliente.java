package loja;



public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    


//construtor
    public Cliente(String nome, String cpf, String email) {
        this.nome =nome; 
        this.cpf = cpf;
        this.email = email;
        
    }

    
//get e set
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        
        this.email = email;
    }
    

    

}
