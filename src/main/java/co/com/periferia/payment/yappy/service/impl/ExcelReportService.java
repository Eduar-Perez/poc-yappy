package co.com.periferia.payment.yappy.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;
import co.com.periferia.payment.yappy.entity.TxYampyEntity;
import co.com.periferia.payment.yappy.service.CustomerYappyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelReportService {

	private final CustomerYappyService customerYappyService;

	public byte[] generateExcel() throws IOException {

		log.info("Se inicia proceso de creación de reporte");

		List<CustomerYampyEntity> customers = customerYappyService.getdataUserAdmin();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Reporte Clientes");

		Row header = sheet.createRow(0);

		header.createCell(0).setCellValue("CC");
		header.createCell(1).setCellValue("Tipo Documento");
		header.createCell(2).setCellValue("Nombre");
		header.createCell(3).setCellValue("Email");
		header.createCell(4).setCellValue("Telefono");
		header.createCell(5).setCellValue("Monto");
		header.createCell(6).setCellValue("N° Orden");
		header.createCell(7).setCellValue("Fecha");

		int rowNum = 1;

		CreationHelper createHelper = workbook.getCreationHelper();

		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));

		CellStyle currencyStyle = workbook.createCellStyle();
		currencyStyle.setDataFormat(createHelper.createDataFormat().getFormat("\"USD\" #,##0.00"));

		for (CustomerYampyEntity customer : customers) {

			if (customer.getTx() != null && !customer.getTx().isEmpty()) {

				for (TxYampyEntity tx : customer.getTx()) {

					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(customer.getCc() != null ? customer.getCc() : "");
					row.createCell(1).setCellValue(customer.getDocumentType() != null ? customer.getDocumentType() : "");
					row.createCell(2).setCellValue(customer.getName() != null ? customer.getName() : "");
					row.createCell(3).setCellValue(customer.getEmail() != null ? customer.getEmail() : "");
					row.createCell(4).setCellValue(customer.getPhone() != null ? customer.getPhone() : "");

					Cell amountCell = row.createCell(5);

					if (tx.getBilling() != null) {

						amountCell.setCellValue(tx.getBilling().getTotal());
						amountCell.setCellStyle(currencyStyle);

					} else {
						amountCell.setCellValue(0);
					}

					row.createCell(6).setCellValue(tx.getOrderId() != null ? tx.getOrderId() : "");

					Cell dateCell = row.createCell(7);

					if (tx.getPaymentDate() != null) {

						dateCell.setCellValue(tx.getPaymentDate());
						dateCell.setCellStyle(dateStyle);

					} else {
						dateCell.setCellValue("");
					}
				}

			} else {

				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(customer.getCc() != null ? customer.getCc() : "");
				row.createCell(1).setCellValue(customer.getDocumentType() != null ? customer.getDocumentType() : "");
				row.createCell(2).setCellValue(customer.getName() != null ? customer.getName() : "");
				row.createCell(3).setCellValue(customer.getEmail() != null ? customer.getEmail() : "");
				row.createCell(4).setCellValue(customer.getPhone() != null ? customer.getPhone() : "");
			}
		}

		for (int i = 0; i < 8; i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		workbook.write(outputStream);
		workbook.close();

		log.info("Termina proceso de creación de reporte");

		return outputStream.toByteArray();
	}
}