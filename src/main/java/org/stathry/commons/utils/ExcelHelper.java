/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.stathry.commons.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.stathry.commons.pojo.config.StyleParams;

/**
 * @author dongdaiming@free.com
 *
 *         2016年8月22日
 */
public class ExcelHelper {
	
	private static StyleParams titleStyle;
	private static StyleParams headerStyle;
	private static StyleParams defaultStyle;

	public static CellStyle getCellStyle(Workbook workbook, StyleParams params) {

		if (workbook == null || params == null) {
			return null;
		}

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setAlignment(params.getAlign());
		style.setVerticalAlignment(params.getVerticalAlign());
		if(params.isHasBorder()) {
			style.setBorderBottom(CellStyle.BORDER_THIN); 
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);
		}
		
		Font font = workbook.createFont();
		font.setBold(params.isFontBold());
		font.setCharSet(XSSFFont.DEFAULT_CHARSET);
		font.setFontName(params.getFontFamily());
		
		if(params.getBackgroundColor() > 0) {
			style.setFillForegroundColor(params.getBackgroundColor());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		
		font.setColor(params.getFontColor());
		style.setFont(font);

		return style;
	}

	public static StyleParams getTitleStyle() {
		if(titleStyle == null) {
			titleStyle = new StyleParams();
			titleStyle.setFontBold(true);
			titleStyle.setHasBorder(false);
		}
		return titleStyle;
	}

	public static StyleParams getHeaderStyle(StyleParams titleStyle) {

		if(headerStyle == null) {
			headerStyle = new StyleParams();
			headerStyle.setBackgroundColor(IndexedColors.PALE_BLUE.getIndex());
			headerStyle.setFontBold(true);
//			headerStyle.setFontHeight((short) 14);
			headerStyle.setStartRow(titleStyle == null ? 0 : titleStyle.getStartRow() + 1);
			headerStyle.setStartColumn(titleStyle == null ? 0 : titleStyle.getStartColumn());
		}
		return headerStyle;
	}

	public static StyleParams getDefaultStyle(StyleParams headerStyle) {

		if(defaultStyle == null) {
			defaultStyle = new StyleParams();
			defaultStyle.setFontBold(false);
//			defaultStyle.setFontHeight((short) 14);
			defaultStyle.setStartRow(headerStyle == null ? 0 : headerStyle.getStartRow() + 1);
			defaultStyle.setStartColumn(headerStyle == null ? 0 : headerStyle.getStartColumn());
		}
		return defaultStyle;
	}

}
