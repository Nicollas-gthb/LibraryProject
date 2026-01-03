package com.system.dao;

import com.system.model.Cliente;
import com.system.database.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    /**
     * {@code CREATE} (INSERT INTO)
     * */
    public void create(Cliente cliente){

        String sql = "INSERT INTO cliente (nome, cpf, email) " +
                "VALUES (?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao cadastrar cliente !!", e);
        }
    }

    /**
     * {@code READ} (SELECT FROM)
     * */
    public List<Cliente> read(){

        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));

                clientes.add(cliente);
            }

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao carregar os clientes !!", e);
        }

        return clientes;
    }

    /**
     * {@code UPDATE} (UPDATE SET)
     * */
    public void updateNome(int idCliente, String nomeCliente){

        String sql = "UPDATE cliente SET nome = ? WHERE id_cliente = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, nomeCliente);
            stmt.setInt(2, idCliente);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao atualizar nome do cliente !!", e);
        }
    }

    public void updateCpf(int idCliente, String cpfCliente){

        String sql = "UPDATE cliente SET cpf = ? WHERE id_cliente = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cpfCliente);
            stmt.setInt(2, idCliente);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao atualizar cpf do cliente !!", e);
        }
    }

    public void updateEmail(int idCliente, String emailCliente){

        String sql = "UPDATE cliente SET email = ? WHERE id_cliente = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, emailCliente);
            stmt.setInt(2, idCliente);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao atualizar email do cliente !!", e);
        }
    }

    /**
     * {@code DELETE} (DELETE FROM)
     * */
    public void delete(int idCliente){

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idCliente);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao deletar cliente !!", e);
        }
    }
}
