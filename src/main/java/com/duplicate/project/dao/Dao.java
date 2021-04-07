package com.duplicate.project.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.duplicate.project.pojo.CandidateDetails;
import com.duplicate.project.pojo.DuplicateDetails;
import com.duplicate.project.pojo.ExcelDetail;
import com.duplicate.project.pojo.ExcelView;
import com.duplicate.project.pojo.RddPosts;
import com.duplicate.project.util.DbConnection;

public class Dao {
	
	
	public static List<DuplicateDetails> getPost(){
		//	System.out.println("post in getList1:::::::::::::"+ post );
			List<DuplicateDetails> list1=new ArrayList<DuplicateDetails>();
			try {
				Connection con=DbConnection.getConnection();
				Statement stmt=con.createStatement();
				
				String query ="select exam_name,exam_code from admin_exam_master";

				ResultSet rs=stmt.executeQuery(query);
				while(rs.next()) {
					DuplicateDetails d=new DuplicateDetails();
					//d.setCount(rs.getInt(1));
					d.setAllPosts(rs.getString(1));
					d.setExamCode(rs.getString(2));
					list1.add(d);
					
				}
				con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return list1;
			
		}
	
	public static List<CandidateDetails> getCandidatesList(String post){
		
		//System.out.println("post in getCandidatesList::::::::::::::::::"+ post );
		int count=0;
		List<CandidateDetails> list= new ArrayList<CandidateDetails>(); 
		try {
			Connection con=DbConnection.getConnection();
			Statement stmt=con.createStatement();
			//String query="select  ue.user_id as candidate_id,ue.id as exam_id, ue.preferred_location_1, ue.preferred_location_2, ue.preferred_location_3, up.dob,concat(up.first_name,' ',up.middle_name,' ',up.last_name) as candidate_name,up.mother_name, up.father_name, jh.email, up.mobile_num,upe.physical_disability from user_exam ue , user_profile_extended upe, user_profile up, jhi_user jh where ue.user_exam_status in ('REGISTERED_SUCCESS') and ue.user_id=upe.user_id and ue.user_id=up.user_id and ue.user_id=jh.id";
			
			//String query = "select * from(select count(*) k,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name,UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where ue.user_exam_status='ACTIVE' and up.user_id=ue.user_id group by candidate_name, up.mother_name, up.father_name, up.dob) q where k > 1)";
			//for the post attendent
			//String query="select  ue.user_id as candidate_id,ue.id as exam_id, ue.preferred_location_1, ue.preferred_location_2, ue.preferred_location_3, up.dob,concat(up.first_name,' ',up.middle_name,' ',up.last_name) as candidate_name,up.mother_name, up.father_name, jh.email, up.mobile_num,upe.physical_disability from user_exam ue , user_profile_extended upe, user_profile up, jhi_user jh where ue.user_exam_status in ('REGISTERED_SUCCESS') and ue.freefield_2='Attendant' and ue.user_id=upe.user_id and ue.user_id=up.user_id and ue.user_id=jh.id";
			//for the post 	Livestock Supervisor
			String query="select  ue.user_id as candidate_id,ue.id as exam_id, ue.preferred_location_1, ue.preferred_location_2, ue.preferred_location_3, up.dob,concat(up.first_name,' ',up.middle_name,' ',up.last_name) as candidate_name,up.mother_name, up.father_name, jh.email, up.mobile_num,upe.physical_disability from user_exam ue , user_profile_extended upe, user_profile up, jhi_user jh where ue.user_exam_status in ('REGISTERED_SUCCESS') and ue.freefield_2='"+post+"' and ue.user_id=upe.user_id and ue.user_id=up.user_id and ue.user_id=jh.id";

			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				CandidateDetails candidateDetails=new CandidateDetails();
				
				candidateDetails.setExamId(rs.getLong(2));
				//count++;
				//System.out.println(count);
				candidateDetails.setUserId(rs.getLong(1));
				candidateDetails.setPreferredLocation1(rs.getString(3));
				candidateDetails.setPreferredLocation2(rs.getString(4));
				candidateDetails.setPreferredLocation3(rs.getString(5));
				candidateDetails.setPhysicalDisability(rs.getBoolean(12));
				candidateDetails.setDob(rs.getDate(6));
				candidateDetails.setCandidateName(rs.getString(7));
				candidateDetails.setFatherName(rs.getString(9));
				candidateDetails.setMotherName(rs.getString(8));
				candidateDetails.setEmail(rs.getString(10));
				candidateDetails.setMobileNum(rs.getLong(11));
				//System.out.println(candidateDetails.toString());
				list.add(candidateDetails);
				
				//System.out.println(list);
				
			}
			con.close();	
		}
		catch(Exception ex){
			ex.printStackTrace();
			
		}
		return list;
	}
	
			 
	public static List<DuplicateDetails> getDuplicateList(){
		List<DuplicateDetails> list=new ArrayList<DuplicateDetails>();
		try {
			Connection con=DbConnection.getConnection();
			Statement stmt=con.createStatement();
			String query = "select * from (select count(*) k, UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where ue.user_exam_status='REGISTERED_SUCCESS' and up.user_id=ue.user_id group by candidate_name, up.mother_name, up.father_name, up.dob) q where k > 1";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				DuplicateDetails d=new DuplicateDetails();
				d.setCount(rs.getInt(1));
				d.setCandidateName(rs.getString(2));
				d.setMotherName(rs.getString(3));
				d.setFatherName(rs.getString(4));
				d.setDob(rs.getDate(5));
				list.add(d);
				
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public static List<DuplicateDetails> getList1(String post){
//	System.out.println("post in getList1:::::::::::::"+ post );
		List<DuplicateDetails> list1=new ArrayList<DuplicateDetails>();
		try {
			Connection con=DbConnection.getConnection();
			Statement stmt=con.createStatement();
			
			//String query = "select * from(select count(*) k,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name,UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where ue.user_exam_status='REGISTERED_SUCCESS' and up.user_id=ue.user_id group by candidate_name, up.mother_name, up.father_name, up.dob ) q where k > 1 ";
            //for the post Attendent
			//String query ="select ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS' and ue.freefield_2='Attendant' and up.user_id=ue.user_id ";
			// //for the post Livestock Supervisor
			//String query ="select ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS'and ue.freefield_2='Plumber' and up.user_id=ue.user_id ";

			String query ="select  ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS' and ue.freefield_2='"+ post +"' and up.user_id=ue.user_id  ";

			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				DuplicateDetails d=new DuplicateDetails();
				//d.setCount(rs.getInt(1));
				d.setUser_id(rs.getLong(1));
				d.setCandidateName(rs.getString(2));
				d.setMotherName(rs.getString(3));
				d.setFatherName(rs.getString(4));
				d.setDob(rs.getDate(5));
				
				list1.add(d);
				
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list1;
		
	}
	
	//Using  isDuplicate 
	
	public static List<CandidateDetails> getIsDuplicateList(){
		List<CandidateDetails> list1=new ArrayList<CandidateDetails>();
		try {
			Connection con=DbConnection.getConnection();
			Statement stmt=con.createStatement();
			
 
			//String query ="select distinct ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS' and up.is_duplicate= true and up.user_id=ue.user_id  ";
			String query="select  ue.user_id as candidate_id,ue.id as exam_id, ue.preferred_location_1, ue.preferred_location_2, ue.preferred_location_3, up.dob,concat(up.first_name,' ',up.middle_name,' ',up.last_name) as candidate_name,up.mother_name, up.father_name, jh.email, up.mobile_num,upe.physical_disability from user_exam ue , user_profile_extended upe, user_profile up, jhi_user jh where ue.user_exam_status in ('REGISTERED_SUCCESS') and up.is_duplicate= true and ue.user_id=upe.user_id and ue.user_id=up.user_id and ue.user_id=jh.id";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				DuplicateDetails d=new DuplicateDetails();
				//d.setCount(rs.getInt(1));
CandidateDetails candidateDetails=new CandidateDetails();
				
				candidateDetails.setExamId(rs.getLong(2));
				//count++;
				//System.out.println(count);
				candidateDetails.setUserId(rs.getLong(1));
				candidateDetails.setPreferredLocation1(rs.getString(3));
				candidateDetails.setPreferredLocation2(rs.getString(4));
				candidateDetails.setPreferredLocation3(rs.getString(5));
				candidateDetails.setPhysicalDisability(rs.getBoolean(12));
				candidateDetails.setDob(rs.getDate(6));
				candidateDetails.setCandidateName(rs.getString(7));
				candidateDetails.setFatherName(rs.getString(9));
				candidateDetails.setMotherName(rs.getString(8));
				candidateDetails.setEmail(rs.getString(10));
				candidateDetails.setMobileNum(rs.getLong(11));
				//System.out.println(candidateDetails.toString());
				list1.add(candidateDetails);
				
				//System.out.println(list);
				
				
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list1;
		
	}
  
	
	
	
	
	
	 Workbook workbook = new HSSFWorkbook();
	public  void  writeExcel(List<DuplicateDetails> listDuplicate, String excelFilePath) throws IOException {
	   
	    Sheet sheet = workbook.createSheet();
	 
	    Integer rowCount = 0;
	 
	    for (DuplicateDetails dups : listDuplicate) {
	        Row row = sheet.createRow(rowCount++);
	        writeBook(dups, row);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	private  void writeBook(DuplicateDetails d, Row row) {
		
		
		
	    Cell cell = row.createCell(2);
	    cell.setCellValue(d.getCandidateName());
	    
	 
	    cell = row.createCell(3);
	    cell.setCellValue(d.getFatherName());
	 
	    cell = row.createCell(4);
	    cell.setCellValue(d.getMotherName());
	    
	    
	    
	   /* CellStyle cellStyle = workbook.createCellStyle();
	    CreationHelper createHelper = workbook.getCreationHelper();
	    cellStyle.setDataFormat(
	        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
	    cell = row.createCell(4);
	    cell.setCellValue(new Date());
	    cell.setCellStyle(cellStyle);*/
	    cell = row.createCell(5);
	   /* CellStyle cellStyle = workbook.createCellStyle();
	    CreationHelper createHelper = workbook.getCreationHelper();
	    cellStyle.setDataFormat(
		        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
	    //cellStyle.setDataFormat((short)14);
	    cell.setCellStyle(cellStyle);*/
	    cell.setCellValue(d.getDob());
	    cell = row.createCell(1);
	    cell.setCellValue(d.getCount());
	    cell = row.createCell(6);
	    cell.setCellValue(d.getUser_id());
	    
	    
	    
	   
	    
	   
	 
	    

	}
	
	public  void  writeExcel2(List<CandidateDetails> listDuplicate, String excelFilePath,String PostName) throws IOException {
		 
		
		 Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet(PostName);
		
		
		//Sheet sheet = null;
		/// int sheetIndex = workbook.getSheetIndex(PostName);
		///    if(sheetIndex == -1){
		//	    sheet = workbook.createSheet(PostName);
		///	}
		   // else {
		   // 	sheet = workbook.createSheet(PostName);
		   // }
	   // sheet = workbook.createSheet(PostName);
	 //   if(sheet == null)
	       // sheet = workbook.createSheet(PostName);
	   
	    
	    int rowCount = 0;
	 
	    for (CandidateDetails dups : listDuplicate) {
	        Row row = sheet.createRow(++rowCount);
	        writeBook2(dups, row);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public  void  writeExcel3(List<ExcelDetail> listDuplicate, String excelFilePath) throws IOException {
		 
		
		 Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
	
	    
	    int rowCount = 0;
	 
	    for (ExcelDetail dups : listDuplicate) {
	        Row row = sheet.createRow(++rowCount);
	        writeBook3(dups, row);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	
private  void writeBook3(ExcelDetail d, Row row) {
		
	    Cell cell = row.createCell(2);
	    cell.setCellValue(d.getTotalCount());
	 
	    cell = row.createCell(1);
	    cell.setCellValue(d.getPostApplied());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(d.getUniqueCount());
	    cell = row.createCell(4);
	    cell.setCellValue(d.getDuplicateCount());
	   
	   
			
			
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

private  void writeBook2(CandidateDetails d, Row row) {
		
	    Cell cell = row.createCell(2);
	    cell.setCellValue(d.getExamId());
	 
	    cell = row.createCell(1);
	    cell.setCellValue(d.getUserId());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(d.getPreferredLocation1());
	    cell = row.createCell(4);
	    cell.setCellValue(d.getPreferredLocation2());
	    cell = row.createCell(5);
	    cell.setCellValue(d.getPreferredLocation3());
	    cell = row.createCell(6);
	   // CellStyle cellStyle = workbook.createCellStyle();
	   // cellStyle.setDataFormat((short)14);
	    cell.setCellValue(d.getDob());
	    cell = row.createCell(7);
	    cell.setCellValue(d.getCandidateName());
	    cell = row.createCell(8);
	    cell.setCellValue(d.getMotherName());
	    cell = row.createCell(9);
	    cell.setCellValue(d.getFatherName());
	   /* cell = row.createCell(10);
	    cell.setCellValue(d.getEmail());
	    cell = row.createCell(11);
	    cell.setCellValue(d.getMobileNum());*/
	    cell = row.createCell(10);
	    cell.setCellValue(d.isPhysicalDisability());
			
			
}


//Addition of postselected by candidate
public static List<DuplicateDetails> getPostSelectedDetails(){
	List<DuplicateDetails> list1=new ArrayList<DuplicateDetails>();
	try {
		Connection con=DbConnection.getConnection();
		Statement stmt=con.createStatement();
		
		//String query = "select * from(select count(*) k,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name,UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where ue.user_exam_status='REGISTERED_SUCCESS' and up.user_id=ue.user_id group by candidate_name, up.mother_name, up.father_name, up.dob ) q where k > 1 ";

		String query ="select  ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob,ue.freefield_2 from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS' and up.user_id=ue.user_id  ";
		//String query ="select distinct ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS' and up.is_duplicate= true and up.user_id=ue.user_id  ";

		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			DuplicateDetails d=new DuplicateDetails();
			//d.setCount(rs.getInt(1));
			d.setUser_id(rs.getLong(1));
			d.setCandidateName(rs.getString(2));
			d.setMotherName(rs.getString(3));
			d.setFatherName(rs.getString(4));
			d.setDob(rs.getDate(5));
			d.setPostApplied(rs.getString(6));
			
			list1.add(d);
			
		}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return list1;
	
}		
 			
public static List<DuplicateDetails> getCandidateDetails(){	
	List<DuplicateDetails> list1=new ArrayList<DuplicateDetails>();
	try {
		Connection con=DbConnection.getConnection();
		Statement stmt=con.createStatement();
		
		//String query = "select * from(select count(*) k,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name,UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where ue.user_exam_status='REGISTERED_SUCCESS' and up.user_id=ue.user_id group by candidate_name, up.mother_name, up.father_name, up.dob ) q where k > 1 ";

		String query ="select user_id,freefield_2 from user_exam where user_exam_status='REGISTERED_SUCCESS' order by user_id limit 100";
		//String query ="select distinct ue.user_id,UPPER(concat(up.first_name,' ',up.middle_name,' ',up.last_name)) as candidate_name, UPPER(up.mother_name) as mother_name , UPPER(up.father_name) as father_name, up.dob from user_profile up, user_exam ue where  ue.user_exam_status='REGISTERED_SUCCESS' and up.is_duplicate= true and up.user_id=ue.user_id  ";

		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) {
			DuplicateDetails d=new DuplicateDetails();
			//d.setCount(rs.getInt(1));
			d.setUser_id(rs.getLong(1));
			d.setPostApplied(rs.getString(2));
			list1.add(d);
			
		}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return list1;
	
}		
	public static List<DuplicateDetails> getAllposts(){
		List<DuplicateDetails> allposts=new ArrayList<DuplicateDetails>();
		try {
			Connection con=DbConnection.getConnection();
			Statement stmt=con.createStatement();
			
			String query="select exam_name from admin_exam_master where freefield_1 not in('') order by exam_name limit 5";
			//String query="select distinct freefield_2 from user_exam where freefield_2='Senior Assistant (Account)_Raigad-Non PESA' or freefield_2='Laboratory Technician_Aurangabad-Non PESA' or freefield_2='Pharmacist_Latur-Non PESA'";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				DuplicateDetails d=new DuplicateDetails();
				d.setAllPosts(rs.getString(1));
				allposts.add(d);
				
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return allposts;
	}
	
	
	public static List<DuplicateDetails> getAllposts2(){
		List<DuplicateDetails> allposts=new ArrayList<DuplicateDetails>();
		try {
			Connection con=DbConnection.getConnection();
			Statement stmt=con.createStatement();
			
			String query="select distinct freefield_2 from user_exam order by freefield_2";
			//String query="select distinct freefield_2 from user_exam where freefield_2='Senior Assistant (Account)_Raigad-Non PESA' or freefield_2='Laboratory Technician_Aurangabad-Non PESA' or freefield_2='Pharmacist_Latur-Non PESA' order by freefield_2 desc";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				DuplicateDetails d=new DuplicateDetails();
				d.setAllPosts(rs.getString(1));
				allposts.add(d);
				
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return allposts;
	}
	
	public  void  writeExcelRddPosts(List<RddPosts> excelView, String excelFilePath) throws IOException {
		   
	    Sheet sheet = workbook.createSheet();
	 
	    Integer rowCount = 0;
	 
	    for (RddPosts dups : excelView) {
	        Row row = sheet.createRow(rowCount++);
	        writeBookRDD(dups, row);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	private  void writeBookRDD(RddPosts d, Row row) {
		
		
		
	    Cell cell = row.createCell(1);
	    cell.setCellValue(d.getLaboratory_Technician_Aurangabad_Non_PESA());
	    
	 
	    cell = row.createCell(2);
	    cell.setCellValue(d.getPharmacist_Latur_Non_PESA());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(d.getSenior_Assistant_Account_Raigad_Non_PESA());
	    
	   
	 
	}
}
	
	


/*public  void  writeExcel1( String excelFilePath,int p,int p1,int count) throws IOException {
	   
    Sheet sheet = workbook.getSheetAt(0);
 
    Integer rowCount = 0;
 
    for (RddPosts dups : excelView) {
        Row row = sheet.createRow(rowCount++);
        
    }
    Row r = sheet.getRow(p); // 10-1
    if (r == null) {
       // First cell in the row, create
       r = sheet.createRow(p);
    }

    Cell c = r.getCell(p1); // 4-1
    if (c == null) {
        // New cell
        c = r.createCell(3, Cell.CELL_TYPE_);
    }
    c.setCellValue(count);
    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
        workbook.write(outputStream);
    }
}



}*/

