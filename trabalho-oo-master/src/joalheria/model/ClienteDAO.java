package joalheria.model;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    Cliente insere(Cliente cliente) throws SQLException;
    Cliente buscaId(int id) throws SQLException;
    List<Cliente> lista() throws SQLException;
    List<Cliente> buscaNome(String nome) throws SQLException;
    Cliente atualiza(Cliente cliente) throws SQLException;
    boolean remove(Cliente cliente) throws SQLException;
}
