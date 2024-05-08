package com.template.utils;

import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

public class DBSchemaExportAsExcel {

    public static final String CONNECTION_URL = "jdbc:mysql://47.92.213.61:3306/%s";
    
    public static final String DB_USER = "aoraki";
    
    public static final String DB_PASSWORD = "aoraki2021";
    
    public static final String DB_NAME = "star-pick";
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DriverManager.getConnection(String.format(CONNECTION_URL, DB_NAME), DB_USER, DB_PASSWORD);
        
        PreparedStatement psmtForTable =   connection.prepareStatement("SELECT * from information_schema.`TABLES` where TABLE_SCHEMA = ?");
        PreparedStatement psmtForColumn = connection.prepareStatement("SELECT * from information_schema.COLUMNS where TABLE_NAME = ?");
        
        ResultSet rsForTable = null;
        ResultSet rsForColumn = null;
        
        psmtForTable.setString(1, DB_NAME);
        rsForTable = psmtForTable.executeQuery();
        LinkedList<TableDefinition> tables = new LinkedList<>();
        
        while (rsForTable.next()) {
            String tableName = rsForTable.getString("TABLE_NAME");
            String tableComment = rsForTable.getString("TABLE_COMMENT");
            System.out.println("TABLE : " + tableName +" 备注："+tableComment);
            
            TableDefinition table = TableDefinition.builder().name(tableName).comment(tableComment).build();
            LinkedList<ColumnDefinition>  columns = new LinkedList<>();
            
            psmtForColumn.setString(1, tableName);
            rsForColumn = psmtForColumn.executeQuery();
            while (rsForColumn.next()) {
                String columnName = rsForColumn.getString("COLUMN_NAME");
                System.out.println("---- COLUMN : " + columnName);
                
                String columnType = rsForColumn.getString("DATA_TYPE");
                Integer columnLength =null;
                if(!columnType.equals("longtext")) {
                    columnLength = rsForColumn.getInt("CHARACTER_MAXIMUM_LENGTH");
                }else{
                    columnLength = Integer.MAX_VALUE;
                }
                String columnNullable = rsForColumn.getString("IS_NULLABLE");
                String columnComment = rsForColumn.getString("COLUMN_COMMENT");
                
                ColumnDefinition columnDefinition = ColumnDefinition.builder()
                        .name(columnName)
                        .type(columnType)
                        .length(columnLength)
                        .nullable(columnNullable)
                        .comment(columnComment)
                        .build();
                
                columns.add(columnDefinition);
            }
            table.setColumns(columns);
            
            tables.add(table);
        }
        
        rsForColumn.close();
        psmtForColumn.close();
        rsForTable.close();
        psmtForTable.close();
        connection.close();
        
        exportAsExcel(tables);
    }
    
    
    public static void exportAsExcel (LinkedList<TableDefinition> tables) throws IOException {
        String[] columnHeads = new String[]{"字段名","数据类型","长度","允许为空","注释"};
        
        Workbook workbook = new XSSFWorkbook();

        
        CellStyle tableCellStyle = workbook.createCellStyle();
        tableCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        tableCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        tableCellStyle.setAlignment(HorizontalAlignment.CENTER);  
        tableCellStyle.setBorderBottom(BorderStyle.THIN);  
        tableCellStyle.setBorderLeft(BorderStyle.THIN);  
        tableCellStyle.setBorderRight(BorderStyle.THIN);  
        tableCellStyle.setBorderTop(BorderStyle.THIN);

        CellStyle columnCellStyle = workbook.createCellStyle();
        columnCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        columnCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        columnCellStyle.setAlignment(HorizontalAlignment.CENTER);  
        columnCellStyle.setBorderBottom(BorderStyle.THIN);  
        columnCellStyle.setBorderLeft(BorderStyle.THIN);  
        columnCellStyle.setBorderRight(BorderStyle.THIN);  
        columnCellStyle.setBorderTop(BorderStyle.THIN);

        CellStyle cellStyle = workbook.createCellStyle();
        columnCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);




        for (TableDefinition table : tables) {
            int rowIndex = 0;
            Sheet sheet = workbook.createSheet(table.getComment());
            Row row = sheet.createRow(rowIndex++);
            // 绘制表名，并合并行
            Cell cell0 = row.createCell(0);
            row.createCell(1).setCellStyle(tableCellStyle);
            row.createCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellStyle(cellStyle);

            cell0.setCellValue("表名（英文）");
            cell0.setCellStyle(tableCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 0,1));
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(table.getName());
            cell2.setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 2,4));
            Row row1 = sheet.createRow(rowIndex++);
            // 绘制表名，并合并行
            Cell cell10 = row1.createCell(0);
            row1.createCell(1).setCellStyle(tableCellStyle);
            row1.createCell(2).setCellStyle(cellStyle);
            row1.createCell(3).setCellStyle(cellStyle);
            row1.createCell(4).setCellStyle(cellStyle);
            cell10.setCellValue("表名（中文）");
            cell10.setCellStyle(tableCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 0,1));
            Cell cell12 = row1.createCell(2);
            cell12.setCellValue(table.getComment());
            cell12.setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex-1, 2,4));

            // 绘制列定义行
            row = sheet.createRow(rowIndex++);
            for (int i = 0; i < columnHeads.length; i++) {
                Cell columnCell = row.createCell(i);
                columnCell.setCellStyle(columnCellStyle);
                columnCell.setCellValue(columnHeads[i]);
            }
            
            for (ColumnDefinition column : table.getColumns()) {
                row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(column.getName());
                row.createCell(1).setCellValue(column.getType());
                row.createCell(2).setCellValue(column.getLength());
                row.createCell(3).setCellValue(column.getNullable());
                row.createCell(4).setCellValue(column.getComment());
            }
        }
        
        FileOutputStream fos = new FileOutputStream(new File("F:\\摘星-2021-02-28.xlsx"));
        
        workbook.write(fos);
        workbook.close();
    }
}

@Data
@Builder
class TableDefinition {
    private String name;
    
    private String comment;
    
    private LinkedList<ColumnDefinition>  columns;
}

@Data
@Builder
class ColumnDefinition {
    private String name;
    
    private String type;
    
    private Integer length;
    
    private String nullable;
    
    private String comment;
}
