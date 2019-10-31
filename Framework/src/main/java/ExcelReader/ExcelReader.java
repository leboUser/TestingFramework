package ExcelReader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;
    private Iterator<Row> row;

    ExcelReader(String file_name,int Sheetnum) throws IOException {
        FileInputStream excelInputStream = new FileInputStream(new File(file_name));
        this.workbook = new XSSFWorkbook(excelInputStream);
        this.sheet = workbook.getSheetAt(Sheetnum);
        this.row = (Iterator<Row>) this.sheet;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Iterator<Row> getRow() {
        return row;
    }

    public void setRow(Iterator<Row> row) {
        this.row = row;
    }


}
