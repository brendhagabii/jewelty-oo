package joalheria.model;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoDAO {
    Produto insere(Produto produto) throws SQLException;
    Produto buscaId(int id) throws SQLException;
    List<Produto> lista() throws SQLException;
    List<Produto> buscaNome(String nome) throws SQLException;
    Produto atualiza(Produto produto) throws SQLException;
    boolean remove(Produto produto) throws SQLException;
}
