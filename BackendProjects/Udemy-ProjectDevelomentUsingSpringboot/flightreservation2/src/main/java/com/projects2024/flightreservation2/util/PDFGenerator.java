package com.projects2024.flightreservation2.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projects2024.flightreservation2.model.Reservation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PDFGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

    public PDFGenerator() {
    }

    public void generateItinerary(Reservation reservation, String filePath) {
        LOGGER.info("generateItinerary()");
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(this.generateTable(reservation));
            document.close();
        } catch (DocumentException | FileNotFoundException var5) {
            LOGGER.error("Exception in generateItinerary " + var5);
        }

    }

    private PdfPTable generateTable(Reservation reservation) {
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell = new PdfPCell(new Phrase("Flight Itinerary"));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Flight Details"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("Airlines ");
        table.addCell(reservation.getFlight().getOperatingAirlines());
        table.addCell("Departure City");
        table.addCell(reservation.getFlight().getDepartureCity());
        table.addCell("Arrival City");
        table.addCell(reservation.getFlight().getArrivalCity());
        table.addCell("Flight Number");
        table.addCell(reservation.getFlight().getFlightNumber());
        table.addCell("Departure Date");
        table.addCell(reservation.getFlight().getDepartureCity());
        table.addCell("Departure Time");
        table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
        cell = new PdfPCell(new Phrase("Passenger Details"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("First Name");
        table.addCell(reservation.getPassenger().getFirstName());
        table.addCell("Last Name");
        table.addCell(reservation.getPassenger().getLastName());
        table.addCell("Email");
        table.addCell(reservation.getPassenger().getEmail());
        table.addCell("Phone");
        table.addCell(reservation.getPassenger().getPhone());
        return table;
    }
}
