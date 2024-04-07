package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.exception.BankClientNotFoundException;
import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.Operation;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import com.defensive.defensiveprogramming.repository.CreditCardRepository;
import com.defensive.defensiveprogramming.repository.OperationRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final BankClientRepository bankClientRepository;
    private final CreditCardRepository cardRepository;
    private final OperationRepository operationRepository;

    private static final Font PARAGRAPH_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
    private static final Font PARAGRAPH_NAME_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 21, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
    @Override
    public ByteArrayInputStream generatePdfReport(Authentication authentication, Long id) {

        BankClient principal = (BankClient) authentication.getPrincipal();

        BankClient bankClient = bankClientRepository.findById(principal.getId())
                .orElseThrow(() -> new BankClientNotFoundException("BankClient not found"));


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.setMargins(20, 20, 20, 20);


            PdfPTable headerTable = new PdfPTable(new float[]{8, 2});
            headerTable.setWidthPercentage(100);


            PdfPCell bankNameCell = new PdfPCell(new Phrase("Easy Bank", HEADER_FONT));
            bankNameCell.setBorder(Rectangle.NO_BORDER);
            bankNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            bankNameCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            bankNameCell.setPaddingBottom(10);
            headerTable.addCell(bankNameCell);

            Image img = Image.getInstance("/Users/jakubsokol/Desktop/bank-app/bank-frontend/src/assets/pngfind.com-money-symbol-png-162850.png"); // Replace with your image path
            img.scaleToFit(20, 20);
            PdfPCell imageCell = new PdfPCell(img, true);
            imageCell.setBorder(Rectangle.NO_BORDER);
            imageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            imageCell.setVerticalAlignment(Element.ALIGN_TOP);
            imageCell.setFixedHeight(60); // Set a fixed height for the image cell
            headerTable.addCell(imageCell);

            document.add(headerTable);

            Paragraph title = new Paragraph("Transaction Report", TITLE_FONT);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            LineSeparator lineSeparator = new LineSeparator();
            lineSeparator.setLineWidth(1);

            document.add(new Chunk(lineSeparator));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = LocalDateTime.now().format(formatter);

            Paragraph ownerParagraph = new Paragraph("Owner: "+ bankClient.getName() + " " + bankClient.getLastName(), PARAGRAPH_FONT);
            Paragraph cardNumberParagraph = new Paragraph("print date: " + formattedDate, PARAGRAPH_FONT);
            document.add(ownerParagraph);
            document.add(cardNumberParagraph);

            LineSeparator lineSeparator1 = new LineSeparator();
            lineSeparator.setLineWidth(1);

            document.add(new Chunk(lineSeparator1));

            Paragraph spacerParagraph = new Paragraph();
            spacerParagraph.setSpacingBefore(60); // Adjust this value as needed
            document.add(spacerParagraph);
           Card card =  cardRepository.findCardByBankClientAndId(bankClient, id);

            Operation operation = operationRepository.findByBankClientAndId(bankClient, id);

            String cardNumber = operation.getCard().getCardNumber();
            String operationDate = operation.getOperationDate().format(formatter);
            String description = operation.getOperationDescription();
            String transactionType = operation.getOperationType().toString();
            String amount = String.valueOf(operation.getAmount());
            float leftIndentation = 50f;

            Paragraph cardNumberParagraph1 = new Paragraph("Card Number: " + cardNumber, PARAGRAPH_FONT);
            cardNumberParagraph1.setIndentationLeft(leftIndentation);
            document.add(cardNumberParagraph1);

            Paragraph operationDateParagraph = new Paragraph("Operation Date: " + operationDate, PARAGRAPH_FONT);
            operationDateParagraph.setIndentationLeft(leftIndentation);
            document.add(operationDateParagraph);

            Paragraph descriptionParagraph = new Paragraph("Description: " + description, PARAGRAPH_FONT);
            descriptionParagraph.setIndentationLeft(leftIndentation);
            document.add(descriptionParagraph);

            Paragraph transactionTypeParagraph = new Paragraph("Transaction Type: " + transactionType, PARAGRAPH_FONT);
            transactionTypeParagraph.setIndentationLeft(leftIndentation);
            document.add(transactionTypeParagraph);

            Paragraph amountParagraph = new Paragraph("Amount: " + amount, PARAGRAPH_FONT); // Make sure this uses PARAGRAPH_FONT if you want it to match the others
            amountParagraph.setIndentationLeft(leftIndentation);
            document.add(amountParagraph);



            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

    }
}
