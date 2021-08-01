package com.epam.training.services.pdf;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.Ticket;
import com.epam.training.services.event.EventService;
import com.epam.training.services.ticket.TicketService;
import com.epam.training.services.user.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Slf4j
@Service
public class BookedTicketsPdfService {

  @Autowired
  private TicketService ticketService;

  @Autowired
  private EventService eventService;

  @Autowired
  private UserService userService;


  public ByteArrayInputStream getPdfReport() {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    List<Ticket> tickets = ticketService.getAllTickets();

    try {
      PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(60);
      table.setWidths(new int[]{1, 3, 3});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

      PdfPCell hcell;
      hcell = new PdfPCell(new Phrase("Id", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Event", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Person", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      for (Ticket ticket : tickets) {
        PdfPCell cell;

        cell = new PdfPCell(new Phrase(ticket.getId().toString()));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        String title = eventService.getEventById(ticket.getEventId()).getTitle();
        cell = new PdfPCell(new Phrase(title));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        String name = userService.getUserById(ticket.getUserId()).getName();
        cell = new PdfPCell(new Phrase(String.valueOf(name)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingRight(5);
        table.addCell(cell);
      }

      PdfWriter.getInstance(document, out);
      document.open();
      document.add(table);

      document.close();
    } catch (DocumentException | InvalidUserException | InvalidEventException ex) {
      log.error("PDF creation error");
    }

    return new ByteArrayInputStream(out.toByteArray());
  }
}
