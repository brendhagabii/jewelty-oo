package joalheria.model;

import java.sql.SQLException;
import java.util.List;

public interface CompraDAO {
    Compra insere(Compra compra) throws SQLException;
    Compra busca(int id) throws SQLException;
    List<Compra> lista() throws SQLException;
    List<Compra> lista(Cliente cliente) throws SQLException;
}
