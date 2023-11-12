package main.java.com.vaapu.dao;

import java.util.List;
import main.java.com.vaapu.model.Usuario;

public interface UsuarioDAO {
    List<Usuario> listarUsuarios();
    Usuario obterUsuarioPorEmail(String email);
    void adicionarUsuario(Usuario usuario);
    void atualizarUsuario(Usuario usuario);
    void excluirUsuario(String email);
}
