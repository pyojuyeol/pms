package kr.or.pms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import kr.or.pms.dto.UserVO;

public class ExcelUtils {
	
   public static SXSSFWorkbook makeSimpleUserExcelWorkbook(List<UserVO> list) {
   	SXSSFWorkbook workbook = new SXSSFWorkbook();
   	
   	// 시트 생성
   	SXSSFSheet sheet = workbook.createSheet("사원 명부");
   	
   	//시트 열 너비 설정
   	sheet.setColumnWidth(0, 1000);
   	sheet.setColumnWidth(1, 3000);
   	sheet.setColumnWidth(2, 2000);
//   	sheet.setColumnWidth(3, 5000);
   	sheet.setColumnWidth(3, 5000);
//   	sheet.setColumnWidth(5, 4000);
//   	sheet.setColumnWidth(6, 2000);
   	sheet.setColumnWidth(4, 1500);
   	sheet.setColumnWidth(5, 1500);
   	sheet.setColumnWidth(6, 3000);
   	sheet.setColumnWidth(7, 4000);
   	sheet.setColumnWidth(8, 1500);
   	
   	// 헤더 행 생
   	Row headerRow = sheet.createRow(0);
   	// 해당 행의 1번째 열 셀 생성
   	Cell headerCell = headerRow.createCell(0);
   	headerCell.setCellValue("NO");
   	// 해당 행의 2번째 열 셀 생성
   	headerCell = headerRow.createCell(1);
   	headerCell.setCellValue("사원 번호");
   	// 해당 행의 3번째 열 셀 생성
   	headerCell = headerRow.createCell(2);
   	headerCell.setCellValue("*이 름");
   	// 해당 행의 4번째 열 셀 생성
//   	headerCell = headerRow.createCell(3);
//   	headerCell.setCellValue("주소");
   	// 해당 행의 5번째 열 셀 생성
   	headerCell = headerRow.createCell(3);
   	headerCell.setCellValue("*이메일");
   	// 해당 행의 6번째 열 셀 생성
//   	headerCell = headerRow.createCell(5);
//   	headerCell.setCellValue("전화번호");
   	// 해당 행의 7번째 열 셀 생성
//   	headerCell = headerRow.createCell(6);
//   	headerCell.setCellValue("생년월일");
   	// 해당 행의 8번째 열 셀 생성
   	headerCell = headerRow.createCell(4);
   	headerCell.setCellValue("성 별");
   	// 해당 행의 9번째 열 셀 생성
   	headerCell = headerRow.createCell(5);
   	headerCell.setCellValue("직 급");
   	// 해당 행의 10번째 열 셀 생성
   	headerCell = headerRow.createCell(6);
   	headerCell.setCellValue("권 한");
   	// 해당 행의 11번째 열 셀 생성
   	headerCell = headerRow.createCell(7);
   	headerCell.setCellValue("부 서");
   	// 해당 행의 12번째 열 셀 생성
   	headerCell = headerRow.createCell(8);
   	headerCell.setCellValue("상 태");
   	
   	// 사원명부 내용 행 및 셀 생성
   	if(list == null) {
   		return workbook;
   	}
   	
   	Row bodyRow = null;
   	Cell bodyCell = null;
   	for(int i=0; i<list.size(); i++) {
   		UserVO userVO = list.get(i);
   		
   		// 행 생성
   		bodyRow = sheet.createRow(i+1);
   		// 데이터 번호 표시
   		bodyCell = bodyRow.createCell(0);
   		bodyCell.setCellValue(i + 1);
   		// 사원번호 표시
   		bodyCell = bodyRow.createCell(1);
   		bodyCell.setCellValue(userVO.getUserId());
   		// 이 름 표시
   		bodyCell = bodyRow.createCell(2);
   		bodyCell.setCellValue(userVO.getUserNm());
   		// 주 소 표시
//   		bodyCell = bodyRow.createCell(3);
//   		bodyCell.setCellValue(userVO.getAddr());
   		// 이메일 표시
   		bodyCell = bodyRow.createCell(3);
   		bodyCell.setCellValue(userVO.getEmail());
   		// 전화번호 표시
//   		bodyCell = bodyRow.createCell(5);
//   		bodyCell.setCellValue(userVO.getHp());
   		// 생년월일 표시
//   		bodyCell = bodyRow.createCell(6);
//   		bodyCell.setCellValue(userVO.getBirth());
   		// 성별 표시
   		bodyCell = bodyRow.createCell(4);
   		bodyCell.setCellValue(userVO.getGender());
   		// 직급 표시
   		bodyCell = bodyRow.createCell(5);
   		bodyCell.setCellValue(userVO.getPosition());
   		// 권한 표시
   		bodyCell = bodyRow.createCell(6);
   		bodyCell.setCellValue(userVO.getAuthGrp());
   		// 부서 표시
   		bodyCell = bodyRow.createCell(7);
   		bodyCell.setCellValue(userVO.getDept());
   		// 상태 표시
   		bodyCell = bodyRow.createCell(8);
   		bodyCell.setCellValue(userVO.getUserStt());
   	}
   	
   	return workbook;
   }
   
   /**
    * 생성한 엑셀 워크북을 컨트롤레에서 받게해줄 메소
    * @param list
    * @return
    */
   public static SXSSFWorkbook excelFileDownloadProcess(List<UserVO> list) {
   	return makeSimpleUserExcelWorkbook(list);
   }
   public static SXSSFWorkbook excelFileFormDownloadProcess() {
	   return makeSimpleUserExcelWorkbook(null);
   }
   
   /**
    *업로드한 엑셀파일을 사원 리스트로 만들기
    * @param excelFile
    * @return 생성한 사원 리스트
    */
   
   public static List<UserVO> uploadExcelFile(MultipartFile excelFile) {
	   List<UserVO> list = new ArrayList<UserVO>();
       try {
           OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
           XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
           
           // 첫번째 시트 불러오기
           XSSFSheet sheet = workbook.getSheetAt(0);
           
           for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
        	   UserVO userVO = new UserVO();
               XSSFRow row = sheet.getRow(i);
               
               // 행이 존재하기 않으면 패스
               if(null == row) {
                   continue;
               }
               
               // 행의 2번째 열 
               XSSFCell cell = row.getCell(1);
               if(null != cell) userVO.setUserId(cell.getStringCellValue()+"");
               // 행의 3번째 열 
               cell = row.getCell(2);
               if(null != cell) userVO.setUserNm(cell.getStringCellValue());
               // 행의 4번째 열 
//               cell = row.getCell(3);
//               if(null != cell) userVO.setAddr(cell.getStringCellValue());
               // 행의 5번째 열 
               cell = row.getCell(3);
               if(null != cell) userVO.setEmail(cell.getStringCellValue());
               // 행의 6번째 열 
//               cell = row.getCell(5);
//               if(null != cell) userVO.setHp(cell.getStringCellValue());
               // 행의 7번째 열 
//               cell = row.getCell(6);
//               if(null != cell) userVO.setBirth(cell.getStringCellValue()+"");
               // 행의 8번째 열 
               cell = row.getCell(4);
               if(null != cell) userVO.setGender(cell.getStringCellValue());
               // 행의 9번째 열 
               cell = row.getCell(5);
               if(null != cell) userVO.setPosition(cell.getStringCellValue());
               // 행의 10번째 열 
               cell = row.getCell(6);
               if(null != cell) userVO.setAuthGrpCode(cell.getStringCellValue());
               // 행의 11번째 열 
               cell = row.getCell(7);
               if(null != cell) userVO.setDept(cell.getStringCellValue());
               // 행의 12번째 열 
               cell = row.getCell(8);
               if(null != cell) userVO.setUserStt(cell.getStringCellValue());
               
               list.add(userVO);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }
}
