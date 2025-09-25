package com.weg.SistemaBiblioteca.repository;

import com.weg.SistemaBiblioteca.database.Conexao;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    public void salvar(Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO emprestimo(livro_id,usuario_id,data_emprestimo) VALUES(?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, emprestimo.getLivro_id());
            stmt.setInt(2, emprestimo.getUsuario_id());
            stmt.setDate(3, Date.valueOf(emprestimo.getData_emprestimo()));

            stmt.executeUpdate();
        }
    }

    public List<Emprestimo> buscarTodos() throws SQLException {
        String query = "SELECT id,livro_id,usuario_id,data_emprestimo, data_devolucao FROM usuario";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int livro_id = rs.getInt("livro_id");
                int usuario_id = rs.getInt("usuario_id");
                LocalDate data_emprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate data_devolucao = rs.getDate("data_devolucao").toLocalDate();


                Emprestimo emprestimo = new Emprestimo(id, livro_id, usuario_id, data_emprestimo, data_devolucao);
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public Emprestimo buscarPorId(int id) throws SQLException {
        String query = "SELECT id,livro_id,usuario_id,data_emprestimo, data_devolucao FROM usuario WHERE id = ?";
        Emprestimo emprestimo = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newId = rs.getInt("id");
                int livro_id = rs.getInt("livro_id");
                int usuario_id = rs.getInt("usuario_id");
                LocalDate data_emprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate data_devolucao = rs.getDate("data_devolucao").toLocalDate();


                emprestimo = new Emprestimo(newId, livro_id, usuario_id, data_emprestimo, data_devolucao);
            }
        }
        return emprestimo;
    }

    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET livro_id=?,usuario_id=?,data_emprestimo=?, data_devolucao=? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, emprestimo.getLivro_id());
            stmt.setInt(2, emprestimo.getUsuario_id());
            stmt.setDate(3, Date.valueOf(emprestimo.getData_emprestimo()));
            stmt.setDate(3, Date.valueOf(emprestimo.getData_devolucao()));
            stmt.setInt(4, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM emprestimo WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
