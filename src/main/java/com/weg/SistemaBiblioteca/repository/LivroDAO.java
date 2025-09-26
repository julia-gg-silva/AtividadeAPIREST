package com.weg.SistemaBiblioteca.repository;

import com.weg.SistemaBiblioteca.database.Conexao;
import com.weg.SistemaBiblioteca.model.Livro;
import org.springframework.http.codec.multipart.MultipartWriterSupport;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroDAO {

    public Livro salvar(Livro livro) throws SQLException{
        String query = "INSERT INTO livro(titulo,autor,ano_publicacao) VALUES(?,?,?)";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setInt(3, livro.getAno_publicacao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                livro.setId(rs.getInt(1));
            }
        }
        return livro;
    }

    public List<Livro> buscarTodos() throws SQLException{
        String query = "SELECT id,titulo,autor,ano_publicacao FROM livro";
        List<Livro> livros = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano_publicacao = rs.getInt("ano_publicacao");

                Livro livro = new Livro(id, titulo, autor, ano_publicacao);
                livros.add(livro);
            }
        }
        return livros;
    }

    public Livro buscarPorId(int id) throws SQLException{
        String query = "SELECT id,titulo,autor,ano_publicacao FROM livro WHERE id = ?";
        Livro livro = new Livro();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int newId = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano_publicacao = rs.getInt("ano_publicacao");

                livro = new Livro(newId, titulo, autor, ano_publicacao);
            }
        }
        return livro;
    }

    public Livro atualizar(Livro livro) throws SQLException{
        String query = "UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.setInt(4, livro.getId());
            stmt.executeUpdate();
        }
        return livro;
    }

    public void deletar(int id) throws SQLException{
        String query = "DELETE FROM livro WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
