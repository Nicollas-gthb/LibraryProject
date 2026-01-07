package com.system.dao;

import com.system.database.ConnectionFactory;
import com.system.model.Cliente;
import com.system.model.Emprestimo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    /**
     * {@code CREATE} (INSERT INTO)
     * */
    public void create(Emprestimo emprestimo) {

        String sqlEmprestimo =
                "INSERT INTO emprestimo (id_livro, id_cliente, data_emprestimo) " +
                        "VALUES (?, ?, ?)";

        String sqlLivro =
                "UPDATE livro SET disponivel = false WHERE id_livro = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            conn.setAutoCommit(false); //

            try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmprestimo);
                 PreparedStatement stmtLivro = conn.prepareStatement(sqlLivro)) {

                // INSERT emprestimo
                stmtEmp.setInt(1, emprestimo.getIdLivro());
                stmtEmp.setInt(2, emprestimo.getIdCliente());
                stmtEmp.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
                stmtEmp.executeUpdate();

                // UPDATE livro
                stmtLivro.setInt(1, emprestimo.getIdLivro());
                stmtLivro.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar empréstimo", e);
        }
    }

    public void devolver(int idEmprestimo, int idLivro, LocalDate dataDevolucao) {

        String sqlEmprestimo =
                "UPDATE emprestimo SET data_devolucao = ? WHERE id_emprestimo = ?";

        String sqlLivro =
                "UPDATE livro SET disponivel = true WHERE id_livro = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmprestimo);
                 PreparedStatement stmtLivro = conn.prepareStatement(sqlLivro)) {

                // Atualiza devolução
                stmtEmp.setDate(1, Date.valueOf(dataDevolucao));
                stmtEmp.setInt(2, idEmprestimo);
                stmtEmp.executeUpdate();

                // Marca livro como disponível
                stmtLivro.setInt(1, idLivro);
                stmtLivro.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar devolução", e);
        }
    }

    /**
     * {@code READ} (SELECT FROM)
     * */
    public List<Emprestimo> read(){

        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Emprestimo emprestimo = new Emprestimo();

                emprestimo.setIdEmprestimo(rs.getInt("id_emprestimo"));
                emprestimo.setIdLivro(rs.getInt("id_livro"));
                emprestimo.setIdCliente(rs.getInt("id_cliente"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());

                Date dataDev = rs.getDate("data_devolucao");
                if(dataDev != null){
                    emprestimo.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
                }

                emprestimos.add(emprestimo);
            }
        }catch(SQLException e){
            throw new RuntimeException("!! Falha ao carregar emprestimo !!", e);
        }

        return emprestimos;
    }

    public Emprestimo findById(int idEmprestimo){

        Emprestimo emprestimo = null;
        String sql = "SELECT * FROM emprestimo WHERE id_emprestimo = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idEmprestimo);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                emprestimo = new Emprestimo();

                emprestimo.setIdEmprestimo(rs.getInt("id_emprestimo"));
                emprestimo.setIdLivro(rs.getInt("id_livro"));
                emprestimo.setIdCliente(rs.getInt("id_cliente"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());

                Date dataDev = rs.getDate("data_devolucao");
                if(dataDev != null){
                    emprestimo.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
                }

            }

        }catch(SQLException e){
            throw new RuntimeException("!! Erro ao carregar os emprestimos !!", e);
        }

        return emprestimo;
    }

    /**
     * {@code UPDATE} (UPDATE SET)
     * */
    public void update(int idEmprestimo, Emprestimo emprestimo){

        String sql = "UPDATE emprestimo SET " +
                "id_livro = ?, id_cliente = ?, " +
                "data_emprestimo = ?, data_devolucao = ? " +
                "WHERE id_emprestimo = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, emprestimo.getIdLivro());
            stmt.setInt(2, emprestimo.getIdCliente());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));

            if(emprestimo.getDataDevolucao() != null){
                stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            }else{
                stmt.setNull(4, Types.DATE);
            }

            stmt.setInt(5, idEmprestimo);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Falha ao atualizar emprestimo !!", e);
        }
    }

    public void updateDevolucao(int idEmprestimo, LocalDate dataDevolucao){

        String sql = "UPDATE emprestimo SET " +
                "data_devolucao = ? " +
                "WHERE id_emprestimo = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setDate(1, Date.valueOf(dataDevolucao));
            stmt.setInt(2, idEmprestimo);

            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("!! Falha ao atualizar emprestimo !!", e);
        }
    }

    /**
     * {@code DELETE} (DELETE FROM)
     * */
    public void delete(int idEmprestimo){

        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idEmprestimo);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("!! Falha ao deletar emprestimo !!", e);
        }
    }
}
