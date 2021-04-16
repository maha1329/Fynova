package tn.fynova.spring.service;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.repository.CreditRepository;
 
public class TransactionExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Transaction> listTrans;
     
    @Autowired
	CreditRepository creditRepository;
	
    public TransactionExcelExporter(List<Transaction> listTrans) {
        this.listTrans = listTrans;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Transactions");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "transaction ID", style);      
        createCell(row, 1, "Amount", style);       
        createCell(row, 2, "Date", style);    
        createCell(row, 3, "type", style);
      //  createCell(row, 4, "credit_id", style);
      //  createCell(row, 5, "account_id", style);
      //  createCell(row, 6, "user", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof java.lang.Float) {
            cell.setCellValue((float) value);
        }
        else if (value instanceof Operation) {
            cell.setCellValue((String) "credit");
        }
        else if (value instanceof java.util.Date) {
            cell.setCellValue((Date) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;

    	
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Transaction t : listTrans) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, t.getTransactionid(), style);
            createCell(row, columnCount++, t.getAmountC(), style);
            createCell(row, columnCount++, t.getTransactionDate(), style);
            createCell(row, columnCount++, t.getTransactionType(), style);
           // createCell(row, columnCount++, t.getTransactionCredit().getCredit_id(), style);
            /*Optional<Credit> CreditOptional=creditRepository.findById(t.getTransaction_credit().getCredit_id());
    	    Credit c=CreditOptional.get();	        
            createCell(row, columnCount++, c.getCredit_account(), style);
            createCell(row, columnCount++, c.getCredit_account().getAccount_user().getFirstname(), style);
             */
        }
    }
     
    public String export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
		return "succes";
         
    }
}