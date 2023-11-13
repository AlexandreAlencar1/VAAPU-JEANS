package DAO;

import java.util.List;

import entity.Usuario;

public interface UsuarioDAO {
    List<Usuario> listarUsuarios();
    Usuario obterUsuarioPorEmail(String email);
    void adicionarUsuario(Usuario usuario);
    void atualizarUsuario(Usuario usuario);
    void excluirUsuario(String email);
}
