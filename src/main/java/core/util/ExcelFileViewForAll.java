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

public class ExcelFileViewForAll extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HSSFSheet worksheet = null;
		HSSFRow row = null;

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("0"));
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String todayString = sdf.format(today);
		
		List<OrderBundle> bundleList= (List<OrderBundle>) model.get("bundleList");
		String fileName = URLEncoder.encode("Meesig", "UTF-8");
		worksheet = workbook.createSheet(fileName);

		row = worksheet.createRow(0);
		row.createCell(0).setCellValue("0. NO.");
		row.createCell(1).setCellValue("1. 받는분");
		row.createCell(2).setCellValue("2. 전화번호");

		row.createCell(3).setCellValue("3. 주소");
		row.createCell(4).setCellValue("4. 우편번호");
		row.createCell(5).setCellValue("5. 요청사항");

		row.createCell(6).setCellValue("6. 상품");
		row.createCell(7).setCellValue("7. 수량");
		row.createCell(8).setCellValue("8. Order ID");

		row.createCell(9).setCellValue("9. 옵션");

		int rowNum = 1;
		for (int i = 0; i < bundleList.size(); i++) {
			OrderBundle bundle = bundleList.get(i);
			OrderDelivery delivery = bundle.getDelivery();
			List<OrderMenu> menus = bundle.getOrderMenus();
			
			for(int j =0; j < menus.size(); j++){
				OrderMenu menu = menus.get(j);
				row = worksheet.createRow(rowNum);
				row.createCell(0).setCellValue(rowNum++);
				if(delivery!=null){
					row.createCell(1).setCellValue(delivery.getDelivery_name());
					row.createCell(2).setCellValue(delivery.getDelivery_phone());
					row.createCell(3).setCellValue(delivery.getDelivery_addr1() +" "+ delivery.getDelivery_addr2());
					row.createCell(4).setCellValue(delivery.getDelivery_pcode());
					row.createCell(5).setCellValue(delivery.getDelivery_des());
				}
				row.createCell(6).setCellValue(menu.getItem_name());
				row.createCell(7).setCellValue(menu.getOm_item_count());
				row.createCell(8).setCellValue(bundle.getOrders_order_id());

				row.createCell(9).setCellValue(menu.getOm_option());
			}

			

		}
		OrderBundle bundle = bundleList.get(0);
		int shopId = bundle.getShops_shop_id();
		fileName = URLEncoder.encode("Meesig_"+ shopId + "_" + todayString, "UTF-8");
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="
				+ fileName + ".xls");

	}

}
