/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import entities.Custvend;
import entities.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import sessionsBeans.ProductFacade;

/**
 * @author user
 */
public class CreatePdf {

    @EJB
    ProductFacade productFacade;

    public static String File = "C:/Users/user/Documents/NetBeansProjects/PrimeFaces/new.pdf";
    public static final String DEST = "results/zugferd/pdf/basic%05d.pdf";
    public static final String ICC = "resources/color/sRGB_CS_profile.icm";
    public static final String REGULAR = "resources/fonts/Alkaios.ttf";
    public static final String BOLD = "resources/fonts/Alkaios-Bold.ttf";
    public static final String NEWLINE = "\n";

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    public String createPDF(List<Product> pd, Custvend custvend, int orderID, int shipping) throws DocumentException {


        FacesContext ctx = FacesContext.getCurrentInstance();
        Float vat = Float.parseFloat(ctx.getExternalContext().getInitParameter("vat"));

        float totalInvoice = 0;
        Document document = new Document();

        float linenetval = 0;
        float linevatval = 0;
        float linesumval = 0;

        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/user/Documents/NetBeansProjects/PrimeFaces/web/resources/invoices/" + orderID + ".pdf"));
            document.open();


            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(15);
            document.add(new Paragraph("eZikos.gr ", f));

            Paragraph top1 = new Paragraph();
            top1.add(NEWLINE);

            Paragraph top = new Paragraph();
            top.add(dateFormat.format(date));
            top.setAlignment(Element.ALIGN_RIGHT);


            Paragraph p = new Paragraph();
            p.add(DateTime.getDateTimeForDatabase());
            p.setAlignment(Element.ALIGN_LEFT);
            document.add(p);

            Paragraph order = new Paragraph();
            order.add("Order ID: " + orderID);
            order.setAlignment(Element.ALIGN_LEFT);
            document.add(order);

            Paragraph cd = new Paragraph();
            cd.add("                                                                  Customer Details");
            document.add(cd);
            Paragraph p2 = new Paragraph();
            p2.add("MIXALAKOPOYLOY 39" + "                            " + custvend.getAddress());
            p2.setAlignment(Element.ALIGN_LEFT);
            document.add(p2);
            Paragraph p3 = new Paragraph();
            p3.add("ATHENS, 18547" + "                                       " + custvend.getCity() + ", " + custvend.getZip());
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p3);
            Paragraph p4 = new Paragraph();
            p4.add("6942988390" + "                                              " + custvend.getPhone());
            p4.setAlignment(Element.ALIGN_LEFT);
            document.add(p4);

            Paragraph p5 = new Paragraph();
            p5.add(NEWLINE);
            p5.setAlignment(Element.ALIGN_LEFT);
            document.add(p5);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            // first row
            PdfPCell cell = new PdfPCell(new Phrase("INVOICE"));
            cell.setColspan(50);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(10.0f);
            cell.setBackgroundColor(new BaseColor(140, 221, 8));
            table.addCell(cell);

            table.addCell("Product ID");
            table.addCell("Quantity");
            table.addCell("Product Name");
            table.addCell("Unit Price");
            table.addCell("Total");

            for (Product products : pd) {
                table.addCell("" + products.getProductid());
                table.addCell("" + products.getQty());
                table.addCell(ConvertToGreeklish.greek2Roman(products.getName()));
                table.addCell("" + products.getSellprice() + "€");
                float totalProduct = Math.round(products.getQty() * products.getSellprice() * 100.00) / 100.00f;
                totalInvoice += totalProduct;
                table.addCell("" + totalProduct + "€");


                linenetval += Math.round(products.getQty() * products.getSellprice() * 100.00) / 100.00f;
            }
            linevatval = Math.round(vat * linenetval * 100.00) / 100.00f;
            linesumval = Math.round((linenetval + linevatval + shipping) * 100.00) / 100.00f;


            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("Net Val");
            table.addCell("" + linenetval + "€");

            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("Vat Val");
            table.addCell("" + linevatval + "€");

            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("Shipping");
            table.addCell("" + shipping + "€");

            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("Total Price");
            table.addCell("" + linesumval + "€");

            document.add(table);

            Paragraph footer = new Paragraph();
            footer.add(NEWLINE);
            footer.add("The goods travel at risk and responsibility of the buyer; all our responsibility ceases upon delivery of goods to the carrier.");
            footer.add(NEWLINE);
            footer.add(NEWLINE);
            footer.add("The goods remain property of eZikos SA until complete payment of the total amount due is made.");
            footer.add(NEWLINE);
            footer.add(NEWLINE);
            footer.add("For all our agreements only the Hellinic law is applicable. In case of disputes in respect of the execution or interpretation only the Courts of Athens are competent.");
            document.add(footer);


            document.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreatePdf.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderID + ".pdf";
    }
}
