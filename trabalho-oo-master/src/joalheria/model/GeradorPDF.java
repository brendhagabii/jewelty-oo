package joalheria.model;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GeradorPDF {
    private Document abreDocumento(String arq) throws IOException {
        PdfWriter writer = new PdfWriter(arq);
        PdfDocument pdf  = new PdfDocument(writer);
        return new Document(pdf);
    }

    public void gerarPDF(String arq, Cliente cliente) {
        try {
            Document document = abreDocumento(arq);

            Paragraph paragrafo = new Paragraph("Histórico de Compras do Cliente");
            paragrafo.setTextAlignment(TextAlignment.CENTER);
            paragrafo.setBold();
            document.add(paragrafo);

            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 30, 20})).useAllAvailableWidth();
            String[] cabecalho = {"Data da Compra", "Itens", "Valor Total"};

            for (String s : cabecalho) {
                Cell cell = new Cell();
                cell.add(new Paragraph(s));
                cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                cell.setBorder(new SolidBorder(ColorConstants.BLACK,2));
                table.addHeaderCell(cell);
            }

            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            table.setFont(font);
            table.setFontSize(12);

            java.util.List<Compra> compras = Joalheria.getInstance().listaCompras(cliente);
            for (Compra compra : compras) {
                java.util.List<Item> itens = Joalheria.getInstance().listaItens(compra);
                table.addCell(compra.getData_compra().format(DateTimeFormatter.ISO_LOCAL_DATE));
                table.addCell("" + itens.size());
                double valorTotal = 0.0;
                for (Item item : itens) {
                    Produto produto = Joalheria.getInstance().buscaProduto(item.getProduto_id());
                    valorTotal += (item.getQuantidade() * produto.getPreco());
                }
                table.addCell("R$ " + valorTotal);
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}