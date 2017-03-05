package com.cinema.Addons;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.cinema.dao.TicketDAO;
import com.cinema.pojo.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PDFAndEmailSender {

	public static void createPDF(Ticket ticket) throws DocumentException{

		Document pdfdoc = new Document();
		int id = ticket.getTicketid();

		try {
			PdfWriter.getInstance(pdfdoc, new FileOutputStream("C:\\Users\\Bhavesh Patel\\Documents\\workspace-sts-3.7.3.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\"+ticket.getTicketid()+".pdf"));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException" + e);
		}
		pdfdoc.open();

		TicketDAO ticketDAO = new TicketDAO();

		Image image = null;
		try {
			image = Image.getInstance("C:\\Users\\Bhavesh Patel\\Documents\\workspace-sts-3.7.3.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\"+ticket.getMovie().getPhotoName());
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException" + e);
		} catch (IOException e) {
			System.out.println("IOException" + e);
		}
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
		pdfdoc.close();

		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("C:\\Users\\Bhavesh Patel\\Documents\\workspace-sts-3.7.3.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\"+ticket.getTicketid()+".pdf");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Movie Ticket");
		attachment.setName(String.valueOf((ticket.getTicketid())));
		

		MultiPartEmail email = new MultiPartEmail();
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("ilovedc27@gmail.com", "8888801313"));
		email.setSSL(true);
		email.setHostName("smtp.googlemail.com");
		try {
			email.addTo(ticket.getCustomer().getEmail());
			email.setFrom("ilovedc27@gmail.com");
			email.setMsg("Here is the tickets you wanted");
			email.attach(attachment);
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not send email" + e);
		}
	}

}
