package joalheria.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public void insere(Item item) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("INSERT INTO tca_itens (compra_id, produto_id, quantidade) VALUES (?, ?, ?)");
        stmt.setInt(1, item.getCompra_id());
        stmt.setInt(2, item.getProduto_id());
        stmt.setInt(3, item.getQuantidade());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public Item busca(int id) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_itens WHERE id=?");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        Item item = null;
        while (res.next()) {
            int compra_id = res.getInt("compra_id");
            int produto_id = res.getInt("produto_id");
            int quantidade = res.getInt("quantidade");

            item = new Item(id, compra_id, produto_id, quantidade);
        }

        return item;
    }

    @Override
    public List<Item> lista(Compra compra) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_itens WHERE compra_id=?");
        stmt.setInt(1, compra.getId());

        ResultSet res = stmt.executeQuery();

        ArrayList<Item> itens = new ArrayList<Item>();
        while (res.next()) {
            int id = res.getInt("id");
            int compra_id = res.getInt("compra_id");
            int produto_id = res.getInt("produto_id");
            int quantidade = res.getInt("quantidade");

            itens.add(new Item(id, compra_id, produto_id, quantidade));
        }

        return itens;
    }
}
