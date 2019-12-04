package joalheria.model;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Joalheria {
    private ClienteDAO clienteDAO = new ClienteDAOImpl();
    private ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    private CompraDAO compraDAO = new CompraDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();

    private ObservableList<Cliente> clientes;
    private ObservableList<Produto> produtos;
    private ObservableList<Compra> compras;
    private ObservableList<Item> itens;

    private static Joalheria instance = new Joalheria();

    public Joalheria() {
        clientes = FXCollections.observableArrayList();
        produtos = FXCollections.observableArrayList();
        compras = FXCollections.observableArrayList();
        itens = FXCollections.observableArrayList();
    }

    public static Joalheria getInstance() {
        return instance;
    }

    public void cadastraCliente(Cliente cliente) throws SQLException {
        clienteDAO.insere(cliente);
    }

    public void atualizaCliente(Cliente cliente) throws SQLException {
        clienteDAO.atualiza(cliente);
    }

    public void removeCliente(Cliente cliente) throws SQLException {
        clienteDAO.remove(cliente);
    }

    public ObservableList<Cliente> listaClientes() throws SQLException {
        clientes.clear();
        clientes.addAll(clienteDAO.lista());
        return clientes;
    }

    public ObservableList<Cliente> listaClientes(String nome) throws SQLException {
        clientes.clear();
        clientes.addAll(clienteDAO.buscaNome(nome));
        return clientes;
    }

    public void cadastraProduto(Produto produto) throws SQLException {
        produtoDAO.insere(produto);
    }

    public Produto buscaProduto(int id) throws SQLException {
        return produtoDAO.buscaId(id);
    }

    public void atualizaProduto(Produto produto) throws SQLException {
        produtoDAO.atualiza(produto);
    }

    public void removeProduto(Produto produto) throws SQLException {
        produtoDAO.remove(produto);
    }

    public ObservableList<Produto> listaProdutos() throws SQLException {
        produtos.clear();
        produtos.addAll(produtoDAO.lista());
        return produtos;
    }

    public ObservableList<Produto> listaProdutos(String nome) throws SQLException {
        produtos.clear();
        produtos.addAll(produtoDAO.buscaNome(nome));
        return produtos;
    }

    public Compra abrirCompra(Compra compra) throws SQLException {
        return compraDAO.insere(compra);
    }

    public void realizarCompra(List<Item> itens) throws SQLException {
        for (Item item : itens) {
            itemDAO.insere(item);
        }
    }

    public List<Compra> listaCompras(Cliente cliente) throws SQLException {
        return compraDAO.lista(cliente);
    }

    public List<Item> listaItens(Compra compra) throws SQLException {
        return itemDAO.lista(compra);
    }

    public void gerarHistoricoDeCompras(Cliente cliente) throws Exception {
        new GeradorPDF().gerarPDF(new File("relatorios", "historico_" + cliente.getNome() + ".pdf").getAbsolutePath(), cliente);
    }

    public void pdf() throws IOException {
        File file = new File("relatorios", "relatorio.pdf");

        if (file.canRead()) System.out.println("Permissão de Leitura concedida."); else System.out.println("Permissão de Leitura negada.");
        if (file.canWrite()) System.out.println("Permissão de Escrita concedida."); else System.out.println("Permissão de Escrita negada.");

        FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
        PdfWriter writer = new PdfWriter(fos);

        writer.writeString("PDF de Testes.");

        writer.close();
        fos.close();
    }
}
