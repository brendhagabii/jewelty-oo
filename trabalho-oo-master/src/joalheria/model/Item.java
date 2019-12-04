package joalheria.model;

public class Item {
    private int id;
    private int compra_id;
    private int produto_id;
    private int quantidade;

    public Item(int compra_id, int produto_id, int quantidade) {
        this.compra_id = compra_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
    }

    public Item(int id, int compra_id, int produto_id, int quantidade) {
        this.id = id;
        this.compra_id = compra_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(int compra_id) {
        this.compra_id = compra_id;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", compra_id=" + compra_id +
                ", produto_id=" + produto_id +
                ", quantidade=" + quantidade +
                '}';
    }
}
