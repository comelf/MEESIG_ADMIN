package core.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by byungwoo on 15. 1. 29..
 * Meesig
 */
public class ExcelFileView extends AbstractExcelView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = "test";   //model.get("target").toString();

        HSSFSheet worksheet = null;
        HSSFRow row = null;

        //if(fileName == "test") {

            fileName = URLEncoder.encode("테스트","UTF-8");
            worksheet = workbook.createSheet(fileName);

            row = worksheet.createRow(0);
            row.createCell(0).setCellValue("글번호");
            row.createCell(1).setCellValue("제목");
            row.createCell(2).setCellValue("날짜");
            row.createCell(3).setCellValue("글쓴이");

            for(int i=1; i<2; i++){
                row = worksheet.createRow(i);
                row.createCell(0).setCellValue(i);
                row.createCell(1).setCellValue(i);
                row.createCell(2).setCellValue(i);
                row.createCell(3).setCellValue(i);
            }

            response.setContentType("Application/Msexcel");
            response.setHeader("Content-Disposition","ATTachment; Filename=" + fileName +"-excel");

        //}

    }
}
