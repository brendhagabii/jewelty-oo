package joalheria.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public Produto insere(Produto produto) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("INSERT INTO tca_produtos (nome, descricao, preco) VALUES (?, ?, ?)");
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setDouble(3, produto.getPreco());

        stmt.executeUpdate();
        stmt.close();
        con.close();

        return produto;
    }

    @Override
    public Produto buscaId(int id) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_produtos WHERE id=?");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        Produto produto = null;
        while (res.next()) {
            String nome = res.getString("nome");
            String descricao = res.getString("descricao");
            double preco = res.getDouble("preco");

            produto = new Produto(id, nome, descricao, preco);
        }

        res.close();
        stmt.close();
        con.close();

        return produto;
    }

    @Override
    public List<Produto> lista() throws SQLException {
        Connection con = FabricaConexao.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_produtos");
        ResultSet res = stmt.executeQuery();

        ArrayList<Produto> produtos = new ArrayList<Produto>();
        while (res.next()) {
            int id = res.getInt("id");
            String nome = res.getString("nome");
            String descricao = res.getString("descricao");
            double preco = res.getDouble("preco");

            produtos.add(new Produto(id, nome, descricao, preco));
        }

        res.close();
        stmt.close();
        con.close();

        return produtos;
    }

    @Override
    public List<Produto> buscaNome(String nome) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tca_produtos WHERE nome=?");
        stmt.setString(1, nome);

        ResultSet res = stmt.executeQuery();

        ArrayList<Produto> produtos = new ArrayList<Produto>();
        while (res.next()) {
            int id = res.getInt("id");
            String nomep = res.getString("nome");
            String descricao = res.getString("descricao");
            double preco = res.getDouble("preco");

            produtos.add(new Produto(id, nomep, descricao, preco));
        }

        res.close();
        stmt.close();
        con.close();

        return produtos;
    }

    @Override
    public Produto atualiza(Produto produto) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("UPDATE tca_produtos SET nome=?, descricao=?, preco=? WHERE id=?");
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setDouble(3, produto.getPreco());
        stmt.setInt(4, produto.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();

        return produto;
    }

    @Override
    public boolean remove(Produto produto) throws SQLException {
        Connection con = FabricaConexao.getConnection();

        PreparedStatement stmt = con.prepareStatement("DELETE FROM tca_produtos WHERE id=?");
        stmt.setInt(1, produto.getId());

        stmt.executeUpdate();

        stmt.close();
        con.close();

        return true;
    }
}
