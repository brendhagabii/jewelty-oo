package joalheria.model;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    void insere(Item item) throws SQLException;
    Item busca(int id) throws SQLException;
    List<Item> lista(Compra compra) throws SQLException;
}
