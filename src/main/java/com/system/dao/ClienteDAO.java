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

    public Cliente findById(int idCliente){

        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            stmt.setInt(1, idCliente);

            if(rs.next()){
                cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
            }

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao carregar os clientes !!", e);
        }

        return cliente;
    }

    /**
     * {@code UPDATE} (UPDATE SET)
     * */
    public void update(Cliente cliente){

        String sql = "UPDATE cliente SET nome = ?, cpf = ?, email = ? WHERE id_cliente = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getIdCliente());

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao atualizar nome do cliente !!", e);
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
