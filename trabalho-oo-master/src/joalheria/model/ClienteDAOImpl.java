package joalheria.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public Cliente insere(Cliente cliente) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("INSERT INTO tca_clientes (nome, cpf, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getTelefone());
        stmt.setString(5, cliente.getEndereco());

        stmt.executeUpdate();
        stmt.close();
        con.close();

        return cliente;
    }

    @Override
    public Cliente buscaId(int id) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_clientes WHERE id=?");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        Cliente cliente = null;
        while (res.next()) {
            String nome = res.getString("nome");
            String cpf = res.getString("cpf");
            String email = res.getString("email");
            String telefone = res.getString("telefone");
            String endereco = res.getString("endereco");

            cliente = new Cliente(id, nome, cpf, email, telefone, endereco);
        }

        res.close();
        stmt.close();
        con.close();

        return cliente;
    }

    @Override
    public List<Cliente> lista() throws SQLException {
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_clientes");
        ResultSet res = stmt.executeQuery();

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        while (res.next()) {
            int id = res.getInt("id");
            String nome = res.getString("nome");
            String cpf = res.getString("cpf");
            String email = res.getString("email");
            String telefone = res.getString("telefone");
            String endereco = res.getString("endereco");

            clientes.add(new Cliente(id, nome, cpf, email, telefone, endereco));
        }

        res.close();
        stmt.close();
        con.close();

        return clientes;
    }

    @Override
    public List<Cliente> buscaNome(String nome) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_clientes WHERE nome=?");
        stmt.setString(1, nome);

        ResultSet res = stmt.executeQuery();

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        while (res.next()) {
            int id = res.getInt("id");
            String nomec = res.getString("nome");
            String cpf = res.getString("cpf");
            String email = res.getString("email");
            String telefone = res.getString("telefone");
            String endereco = res.getString("endereco");

            clientes.add(new Cliente(id, nomec, cpf, email, telefone, endereco));
        }

        res.close();
        stmt.close();
        con.close();

        return clientes;
    }

    @Override
    public Cliente atualiza(Cliente cliente) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("UPDATE tca_clientes SET nome=?, cpf=?, email=?, telefone=?, endereco=? WHERE id=?");
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getTelefone());
        stmt.setString(5, cliente.getEndereco());
        stmt.setInt(6, cliente.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();

        return cliente;
    }

    @Override
    public boolean remove(Cliente cliente) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("DELETE FROM tca_clientes WHERE id=?");
        stmt.setInt(1, cliente.getId());

        stmt.executeUpdate();

        stmt.close();
        con.close();

        return true;
    }
}
