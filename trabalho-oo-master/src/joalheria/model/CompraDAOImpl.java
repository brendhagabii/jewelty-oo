package joalheria.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {
    @Override
    public Compra insere(Compra compra) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("INSERT INTO tca_compras (cliente_id, data_compra) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, compra.getCliente_id());
        stmt.setDate(2, Date.valueOf(compra.getData_compra()));

        stmt.executeUpdate();

        ResultSet res = stmt.getGeneratedKeys();
        if (res.next()) {
            compra.setId(res.getInt(1));
        }

        res.close();
        stmt.close();
        con.close();

        return compra;
    }

    @Override
    public Compra busca(int id) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_compras WHERE id=?");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        Compra compra = null;
        while (res.next()) {
            int cliente_id = res.getInt("cliente_id");
            LocalDate data_compra = res.getDate("data_compra").toLocalDate();

            compra = new Compra(id, cliente_id, data_compra);
        }

        res.close();
        stmt.close();
        con.close();

        return compra;
    }

    @Override
    public List<Compra> lista() throws SQLException {
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_compras");
        ResultSet res = stmt.executeQuery();

        ArrayList<Compra> compras = new ArrayList<Compra>();
        while (res.next()) {
            int id = res.getInt("id");
            int cliente_id = res.getInt("cliente_id");
            LocalDate data_compra = res.getDate("data_compra").toLocalDate();

            compras.add(new Compra(id, cliente_id, data_compra));
        }

        res.close();
        stmt.close();
        con.close();

        return compras;
    }

    @Override
    public List<Compra> lista(Cliente cliente) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_compras WHERE cliente_id=?");
        stmt.setInt(1, cliente.getId());

        ResultSet res = stmt.executeQuery();

        ArrayList<Compra> compras = new ArrayList<Compra>();
        while (res.next()) {
            int id = res.getInt("id");
            int cliente_id = res.getInt("cliente_id");
            LocalDate data_compra = res.getDate("data_compra").toLocalDate();

            compras.add(new Compra(id, cliente_id, data_compra));
        }

        res.close();
        stmt.close();
        con.close();

        return compras;
    }
}
