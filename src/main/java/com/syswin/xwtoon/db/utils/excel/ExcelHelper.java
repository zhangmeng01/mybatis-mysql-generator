package com.syswin.xwtoon.db.utils.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelHelper {
	Logger logger = LoggerFactory.getLogger(ExcelHelper.class);
	HSSFWorkbook workbook;
	FileOutputStream out;

	public ExcelHelper(String fileName) throws FileNotFoundException, IOException {
		workbook = new HSSFWorkbook(new FileInputStream(fileName));
		out = new FileOutputStream(fileName);

	}

	public HSSFSheet getSheet(int index) {
		return workbook.getSheetAt(index);
	}

	private void traversalBySheet(IExcelFormater excelTraversal, IExcelOperator operator, int sheeti)
			throws IOException {
		int sheetsNum = workbook.getNumberOfSheets();
		HSSFSheet sheet;
		// 倒着来，从未成交客户开始搜索
		sheet = workbook.getSheetAt(sheeti);
		int lastRowNum = sheet.getLastRowNum();
		HSSFRow row;
		// 不要抬头行，只遍历内容行
		for (int rowi = 1; rowi < lastRowNum; rowi++) {
			row = sheet.getRow(rowi);
			// 在遍历的时候实现不同的动作
			try {
				Object value = excelTraversal.getFormattedValue(row);
				operator.optValue(value);
			} catch (Exception e) {// 可能读到的cell是空的,或者读到的row是空的
				// TODO Auto-generated catch block
				logger.error(e.getMessage() + ":rowNum:" + (rowi + 1), e);
			}
		}
	}

	public void traversal(IExcelFormater excelTraversal, IExcelOperator operator, int sheeti) throws IOException {
		traversalBySheet(excelTraversal, operator, sheeti);
		writeBack();
	}

	public void traversal(IExcelFormater excelFormater, IExcelOperator operator) throws IOException {
		int sheetsNum = workbook.getNumberOfSheets();
		HSSFSheet sheet;
		// 倒着来，从未成交客户开始搜索
		for (int sheeti = sheetsNum - 1; sheeti >= 0; sheeti--) {
			traversalBySheet(excelFormater, operator, sheeti);
		}
		writeBack();
	}

	static public void printRow(HSSFRow row) {
		try {
			StringBuffer rowInfo = new StringBuffer();
			;
			rowInfo.append("");
			rowInfo.append("SHEET::" + row.getSheet().getSheetName());
			rowInfo.append("ROW::" + row.getRowNum());
			for (int i = 0; i < row.getLastCellNum(); i++) {
				rowInfo.append(i + "::" + row.getCell(i) + ";;  ");
			}
			rowInfo.append(" ");
			System.out.println(rowInfo.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("记录失败");
		}
	}

	public void removeRow(HSSFRow row, int rowIndex, HSSFSheet sheet) {// 不用poi自带的remove，因为只会清空行，但是会留下空白行，自己写的空白行也不想要
		// 传入rowIndex是因为row可能是null
		printRow(row);
		try {
			sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1);
		} catch (IllegalArgumentException e) {

		}
	}

	public void writeBack() throws IOException {
		workbook.write(out);
		out.close();
	}
}
