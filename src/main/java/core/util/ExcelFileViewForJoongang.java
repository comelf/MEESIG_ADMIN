package core.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.meesig.model.OrderBundle;
import com.meesig.model.OrderDelivery;
import com.meesig.model.OrderMenu;

/**
 * Created by byungwoo on 15. 1. 29.. Meesig
 */
public class ExcelFileViewForJoongang extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HSSFSheet worksheet = null;
		HSSFRow row = null;

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("0"));

		List<OrderBundle> bundleList= (List<OrderBundle>) model.get("bundleList");
		String fileName = URLEncoder.encode("Meesig", "UTF-8");
		worksheet = workbook.createSheet(fileName);

		row = worksheet.createRow(0);
		row.createCell(0).setCellValue("날짜");
		row.createCell(1).setCellValue("수취인");
		row.createCell(2).setCellValue("연락처");

		row.createCell(3).setCellValue("우편번호");
		row.createCell(4).setCellValue("주소");
		
		row.createCell(5).setCellValue("옵션");
		row.createCell(6).setCellValue("개수");
		row.createCell(7).setCellValue("요청사항");
		
		row.createCell(10).setCellValue("");
		row.createCell(11).setCellValue("10. Bundle ID");
		row.createCell(12).setCellValue("11. 배송업체명");
		row.createCell(13).setCellValue("12. 운송장");

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
		String todayString = sdf.format(today);
		
		int rowNum = 1;
		for (int i = 0; i < bundleList.size(); i++) {
			OrderBundle bundle = bundleList.get(i);
			OrderDelivery delivery = bundle.getDelivery();
			List<OrderMenu> menus = bundle.getOrderMenus();
			
			for(int j =0; j < menus.size(); j++){
				OrderMenu menu = menus.get(j);
				row = worksheet.createRow(rowNum++);
				row.createCell(0).setCellValue(todayString);
				if(delivery!=null){
					row.createCell(1).setCellValue(delivery.getDelivery_name());
					row.createCell(2).setCellValue(delivery.getDelivery_phone());
					row.createCell(3).setCellValue(delivery.getDelivery_pcode());
					row.createCell(4).setCellValue(delivery.getDelivery_addr1() +" "+ delivery.getDelivery_addr2());
					row.createCell(7).setCellValue(delivery.getDelivery_des());
				}
				row.createCell(5).setCellValue(menu.getOm_option());
				row.createCell(6).setCellValue(menu.getOm_item_count());
				row.createCell(11).setCellValue(bundle.getBundle_id());
			}

			

		}
		OrderBundle bundle = bundleList.get(0);
		int shopId = bundle.getShops_shop_id();
		
		fileName = URLEncoder.encode("Meesig_"+ shopId + "_" + todayString, "UTF-8");
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="
				+ fileName + ".xls");
		
		
//		row = worksheet.createRow(rowNum++);
//		row.createCell(6).setCellValue("순살 보통맛");
//		row.createCell(7).setCellFormula("SUMPRODUCT((G2:G"+i+"=\"순살닭강정(1300g)\")*(H2:H"+i+"=\"보통 맛\"),I2:I"+i+")");
//		
//		row = worksheet.createRow(rowNum++);
//		row.createCell(6).setCellValue("순살 매운맛");
//		row.createCell(7).setCellFormula("SUMPRODUCT((G2:G"+i+"=\"순살닭강정(1300g)\")*(H2:H"+i+"=\"매운 맛\"),I2:I"+i+")");
//		
//		row = worksheet.createRow(rowNum++);
//		row.createCell(6).setCellValue("뼈 보통맛");
//		row.createCell(7).setCellFormula("SUMPRODUCT((G2:G"+i+"=\"뼈있는 닭강정(1700g)\")*(H2:H"+i+"=\"보통 맛\"),I2:I"+i+")");
//		
//		
//		row = worksheet.createRow(rowNum++);
//		row.createCell(6).setCellValue("뼈 매운맛");
//		row.createCell(7).setCellFormula("SUMPRODUCT((G2:G"+i+"=\"뼈있는 닭강정(1700g)\")*(H2:H"+i+"=\"매운 맛\"),I2:I"+i+")");
		
		
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="
				+ fileName + ".xls");

	}

//	private String transName(String item_Name) {
//		if(item_Name.equals(ItemName.JUNGANG_SUNSAL)){
//			return "순살닭강정(1300g)";
//		}
//		if(item_Name.equals(ItemName.JUNGANG_BONE)){
//			return "뼈있는 닭강정(1700g)";
//		}
//		if(item_Name.equals(ItemName.JUNGANG_SUNSAL_STORYBALL)){
//			return "순살닭강정(1300g)";
//		}
//		
//		
//		return item_Name;
//	}

}
