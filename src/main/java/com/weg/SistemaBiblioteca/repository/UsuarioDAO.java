package com.weg.SistemaBiblioteca.repository;

import com.weg.SistemaBiblioteca.database.Conexao;
import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {

    public Usuario salvar(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario(nome,email) VALUES(?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }
        }
        return usuario;
    }

    public List<Usuario> buscarTodos() throws SQLException {
        String query = "SELECT id,nome,email FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Usuario usuario = new Usuario(id, nome, email);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String query = "SELECT id,nome,email FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newId = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                usuario = new Usuario(newId, nome, email);
            }
        }
        return usuario;
    }

    public Usuario atualizar(Usuario usuario) throws SQLException {
        String query = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
        }
        return usuario;
    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
