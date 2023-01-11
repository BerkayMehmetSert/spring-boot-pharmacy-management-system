package com.bms.pharmacymanagementsystem.core.utilities;

import com.bms.pharmacymanagementsystem.model.Report;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    public ByteArrayInputStream generatePdf(final Report report) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        final String title = "Report " + report.getCustomer().getId();
        final String content = "Customer: " + report.getCustomer().getFirstName()
                + " " + report.getCustomer().getLastName() + "\n"
                + "Medicine: " + report.getPurchaser().getMedicine().getName() + "\n"
                + "Total Price: " + report.getSale().getTotalAmount();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(18);
        titleFont.setColor(0, 0, 0);

        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA);
        contentFont.setSize(14);
        titleFont.setColor(0, 0, 0);

        Paragraph titleParagraph = new Paragraph(title, titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);

        Paragraph contentParagraph = new Paragraph(content, contentFont);

        document.open();
        document.addAuthor("BMS");
        document.addCreator("BMS");
        document.add(titleParagraph);
        document.add(contentParagraph);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
