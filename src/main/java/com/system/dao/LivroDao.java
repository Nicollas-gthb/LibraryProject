package com.system.dao;

import com.system.database.ConnectionFactory;
import com.system.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {

    /**
     * {@code CREATE} (INSERT INTO)
     * */
    public void create(Livro livro){

        String sql = "INSERT INTO livro (titulo, autor, ano, disponivel) " +
                "VALUES (?, ?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBoolean(4, livro.isDisponivel());

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao cadastrar livro !!", e);
        }
    }

    /**
     * {@code READ} (SELECT FROM)
     * */
    public List<Livro> read(){

        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Livro livro = new Livro();

                livro.setIdLivro(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setDisponivel(rs.getBoolean("disponivel"));

                livros.add(livro);
            }

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao carregar livros !!", e);
        }

        return livros;
    }

    /**
     * {@code UPDATE} (UPDATE SET)
     * */
    public void updateDisponibilidade(int idLivro, boolean disponivel){

        String sql = "UPDATE livro SET disponivel = ? WHERE id_livro = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, idLivro);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao atualizar disponibilidade !!", e);
        }
    }

    /**
     * {@code DELETE} (DELETE FROM)
     * */
    public void delete(int idLivro){

        String sql = "DELETE FROM livro WHERE id_livro = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idLivro);
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao deletar livro !!", e);
        }
    }
}
