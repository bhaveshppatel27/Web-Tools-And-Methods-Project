package com.cinema.Addons;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cinema.dao.TicketDAO;
import com.cinema.pojo.Ticket;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class PdfTicketView extends AbstractPdfView
{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int ticketid = Integer.parseInt(request.getParameter("ticket"));
		TicketDAO ticketDAO = new TicketDAO();
		Ticket ticket = ticketDAO.get(ticketid);
		Image image = Image.getInstance("C:\\Users\\Bhavesh Patel\\Documents\\workspace-sts-3.7.3.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\"+ticket.getMovie().getPhotoName());
		image.setAbsolutePosition(420f, 580f);
		image.scalePercent(60);
		
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
		String dateString = format.format(ticket.getShowTimings().getDate());
		
		
		Font font_helvetica_16_normal_black = new Font(Font.HELVETICA, 18, Font.NORMAL, Color.BLACK); 
		Font font_courier_20_italic_red = new Font(Font.COURIER, 25, Font.ITALIC, Color.RED);
		Font font_times_16_bold_green = new Font(Font.TIMES_ROMAN, 18, Font.BOLD, Color.GREEN);
		
		
		
		Paragraph prg0 = new Paragraph("Cinema Docket",font_courier_20_italic_red);
		prg0.setAlignment(Element.ALIGN_CENTER);
		Paragraph prg1 = new Paragraph("Ticket Id: "+ticket.getTicketid(), font_helvetica_16_normal_black);
		Paragraph prg2 = new Paragraph("Movie: "+ticket.getMovie().getTitle(), font_helvetica_16_normal_black);
		Paragraph prg3 = new Paragraph("Theatre: "+ticket.getShowTimings().getTheatre().getName()+"-"+
		ticket.getShowTimings().getTheatre().getLocation().getLocate(), font_helvetica_16_normal_black);
		Paragraph prg4 = new Paragraph("Auditorium: "+ticket.getShowTimings().getAudi().getName(), font_helvetica_16_normal_black);
		Paragraph prg5 = new Paragraph("Time: "+dateString, font_helvetica_16_normal_black);
		Paragraph prg6 = new Paragraph("No. of Passes: "+ticket.getNoOfSeats(), font_helvetica_16_normal_black);
		Paragraph prg7 = new Paragraph("Paid Amount: $"+(ticket.getCost() * ticket.getNoOfSeats()), font_helvetica_16_normal_black);
		Paragraph prg8 = new Paragraph("Bookingdate: "+ticket.getBookingdate(), font_helvetica_16_normal_black);
		
		pdfdoc.add(prg0);
		pdfdoc.add(prg1);
		pdfdoc.add(prg2);
		pdfdoc.add(prg3);
		pdfdoc.add(prg4);
		pdfdoc.add(prg5);
		pdfdoc.add(prg6);
		pdfdoc.add(prg7);
		pdfdoc.add(prg8);
		pdfdoc.add(image);
		
	}

}
