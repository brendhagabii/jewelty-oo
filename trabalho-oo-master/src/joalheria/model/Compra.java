package joalheria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Compra {
    private int id;
    private int cliente_id;
    private LocalDate data_compra;

    public Compra(int cliente_id, LocalDate data_compra) {
        this.cliente_id = cliente_id;
        this.data_compra = data_compra;
    }

    public Compra(int id, int cliente_id, LocalDate data_compra) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.data_compra = data_compra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public LocalDate getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDate data_compra) {
        this.data_compra = data_compra;
    }

    @Override
    public String toString() {
        return "Compra Nº " + id + " - Data da Compra: " + data_compra.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
