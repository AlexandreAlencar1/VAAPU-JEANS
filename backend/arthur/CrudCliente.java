package loja;

import java.util.ArrayList;
import java.util.Scanner;

public class CrudCliente {
    private ArrayList<Cliente> list ;
    private Scanner scan = new Scanner(System.in); 
    
    public CrudCliente(){
        list = new ArrayList<>();
    }
    
    public void menu(){

            System.out.println("Digite a opção desejada");
            System.out.println("1) Criar Conta");
            System.out.println("2) Deleta Conta");
            System.out.println("3) Atualiza Conta");
            System.out.println("4) Ler Conta");
            while (true) {
                int opcao = scan.nextInt();
            switch (opcao) {
                case 1: criar();
                break;
                case 2: deleta();
                break;
                case 3: atualiza();
                    break;
                case 4: ler();
                break;
                
                default: System.out.println("Opção invalida");
                break;
            }
        }
        
        
    }
    
    public void criar(){
        String nomeC = "";
        scan.nextLine();
        while (true) {
            System.out.println("Digite seu nome");
            nomeC=scan.nextLine();
            if (isValidNome(nomeC)) {
                break;//nome valido
            }else{
                System.out.println("Nome inválido. Certifique-se de que contenha apenas letras e espaços.");
            }
            
        }
        String emailC ="";
        while (true) {
            System.out.println("Digite seu Email ");
            emailC = scan.nextLine();
            if (isValidEmail(emailC)) {
                break;
            }else{
                System.out.println("Email inválido. Certifique-se de que esteja no formato correto.");
            }
        }
        String cpfC ="";
        while (true) {
            System.out.println("Digite seu CPF");
            cpfC = scan.nextLine();
            if (isValidCPF(cpfC)) {
                break;
            }else{
                System.out.println("CPF invalido. Certifique-se de que que esteja no formato correto.");
            }
                
            
        }
        Cliente cliente = new Cliente(nomeC, cpfC, emailC);
        list.add(cliente);
        System.out.println("Usuario criado");
    }

    public void deleta(){
        System.out.println("Deletando um cliente");
        String delete = scan.nextLine();
        delete = scan.nextLine();
        boolean clienteRemovido = false; // Variável para acompanhar se o cliente foi removido
        for (int i = 0; i < list.size(); i++) {
            Cliente cliente = list.get(i);
            if (cliente.getNome().equals(delete)) {
                list.remove(i);
                clienteRemovido = true;
                System.out.println("Cliente excluído com sucesso!");
                break; // Saia do loop após remover o cliente
            }
        }
        if (!clienteRemovido) {
            System.out.println("Cliente não encontrado.");
        }
    }
    

    public void atualiza(){
        System.out.println("Digite o Usuario que vc quer atualizar");
        String usuario = scan.nextLine();
        usuario=scan.nextLine();
        System.out.println("Atualizando... ");
        System.out.println("Digite os campos que vc quer atualizar ");
        String updateN;
        while (true) {
            System.out.print("Atualiza nome: ");
            updateN = scan.nextLine();
            if (isValidNome(updateN)) {
                break;
            }else{
                System.out.println("Nome inválido. Certifique-se de que contenha apenas letras e espaços.");
            }
        }
        String updateE;
        while (true) {
            System.out.print("Atualiza Email: ");
            updateE = scan.nextLine();
            if (isValidEmail(updateE)) {
                break;
            }else{
                System.out.println("Email inválido. Certifique-se de que esteja no formato correto.");
            }
        }
        String updateC;
        while (true) {
            System.out.print("Atualiza CPF: ");
            updateC = scan.nextLine();
            if (isValidCPF(updateC)) {
                break;
            }else{
                System.out.println("CPF invalido. Certifique-se de que que esteja no formato correto.");
            }
            
        }
        for (Cliente clientes : list) {
            if (clientes.getNome().equals(usuario)) {
                clientes.setNome(updateN);
                clientes.setEmail(updateE);
                clientes.setCpf(updateC);
                
                
            }
            System.out.println("Atualizado");
        }
    }

    public void ler(){
        for (Cliente clientes : list) {
            System.out.println("Nome: "+ clientes.getNome()+ "/"+
                                "Email: "+ clientes.getEmail()+"/"+
                                "CPF: "+ clientes.getCpf());
        }
    }

    public boolean isValidNome(String nome) {
        // Verifica se o nome não é nulo ou vazio
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        // Verifica se o nome contém apenas letras e espaços
        if (!nome.matches("^[a-zA-Z ]+$")) {
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        // Verifica se o email não é nulo ou vazio
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        // Utiliza uma expressão regular para validar o formato do email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(regex)) {
            return false;
        }
        return true;
    }
    public boolean isValidCPF(String cpf) {
        // Remove espaços em branco e caracteres especiais
        cpf = cpf.replaceAll("[^0-9]", "");
    
        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
    
        // Verifica se todos os dígitos são iguais (CPF inválido)
        boolean todosDigitosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }
        // Calcula e verifica os dígitos verificadores
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) {
            primeiroDigito = 0;
        }
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }
        return cpf.charAt(9) - '0' == primeiroDigito && cpf.charAt(10) - '0' == segundoDigito;
    }
}
