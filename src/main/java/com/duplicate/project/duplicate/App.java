/*
 * package com.duplicate.project.duplicate;
 * 
 * import java.io.IOException; import java.sql.Connection; import
 * java.sql.ResultSet; import java.sql.Statement; import java.util.ArrayList;
 * import java.util.Arrays; import java.util.List; import java.util.Map; import
 * java.util.concurrent.ConcurrentHashMap; import java.util.function.Function;
 * import java.util.function.Predicate; import java.util.stream.Collectors;
 * 
 * import org.eclipse.collections.impl.block.factory.HashingStrategies; import
 * org.eclipse.collections.impl.utility.ListIterate;
 * 
 * 
 * import com.duplicate.project.dao.Dao;
 * 
 * 
 * import com.duplicate.project.pojo.CandidateDetails; import
 * com.duplicate.project.pojo.DuplicateDetails; import
 * com.duplicate.project.pojo.ExcelView; import
 * com.duplicate.project.pojo.RddPosts; import
 * com.duplicate.project.util.DbConnection;
 * 
 * 
 * 
 *//**
	 * Hello world!
	 *
	 *//*
		 * public class App {
		 * 
		 * 
		 * public static void main( String[] args ) {
		 * 
		 * Dao dao=new Dao(); List<CandidateDetails> CandidateList=new
		 * ArrayList<CandidateDetails>();
		 * 
		 * 
		 * 
		 * 
		 * List<DuplicateDetails> list1= new ArrayList<DuplicateDetails>();
		 * List<CandidateDetails> list2= new ArrayList<CandidateDetails>();
		 * List<CandidateDetails> isDuplicateList= new ArrayList<CandidateDetails>();
		 * List<CandidateDetails> differnceList= new ArrayList<CandidateDetails>();
		 * 
		 * 
		 * String[] myArray= new String[2];
		 * myArray[0]="Civil Engineering Assistant_Ahmednagar-Non PESA";
		 * myArray[1]="Civil Engineering Assistant_Aurangabad-Non PESA";
		 * 
		 * for(String a :myArray) {
		 * 
		 * 
		 * 
		 * 
		 * } list1=Dao.getList1(); // to get list of candidates
		 * System.out.println("list1 size"+list1.size());
		 * 
		 * //*************to get list of candidates on the basis of isDuplicate
		 * isDuplicateList=Dao.getIsDuplicateList();
		 * System.out.println("getIsDuplicateList size"+isDuplicateList.size());
		 * 
		 * // filtering isDuplicate list using unique user_id List<CandidateDetails>
		 * filteredisDuplicateList = ListIterate.distinct( isDuplicateList,
		 * HashingStrategies.fromFunction(CandidateDetails::getUserId));
		 * 
		 * System.out.println("filteredisDuplicateList size"+filteredisDuplicateList.
		 * size());
		 * 
		 * 
		 * 
		 * // on the basis of post selection starts here **************
		 * 
		 * List<DuplicateDetails> postSelectedlist= new ArrayList<DuplicateDetails>();
		 * postSelectedlist=Dao.getPostSelectedDetails();
		 * 
		 * @SuppressWarnings("unchecked") List<DuplicateDetails> postList =
		 * postSelectedlist .stream()
		 * .filter(distinctByKeys(DuplicateDetails::getCandidateName,
		 * DuplicateDetails::getFatherName,DuplicateDetails::getMotherName,
		 * DuplicateDetails::getDob,DuplicateDetails::getPostApplied))
		 * .collect(Collectors.toList());
		 * 
		 * 
		 * postSelectedlist.removeAll(postList);
		 * 
		 * @SuppressWarnings("unchecked") List<DuplicateDetails> filteredPostList =
		 * postSelectedlist .stream()
		 * .filter(distinctByKeys(DuplicateDetails::getCandidateName,
		 * DuplicateDetails::getFatherName,DuplicateDetails::getMotherName,
		 * DuplicateDetails::getDob,DuplicateDetails::getPostApplied))
		 * .collect(Collectors.toList());
		 * System.out.println("filteredPostList size is "+filteredPostList.size()); //
		 * *****************************************ends
		 * 
		 * 
		 * 
		 * int count=0; int i=0;
		 * 
		 * List<DuplicateDetails> personListFiltered = ListIterate.distinct( list1,
		 * HashingStrategies.fromFunction(DuplicateDetails::getUser_id));
		 * 
		 * // System.out.println("personListFiltered size"+personListFiltered.size());
		 * 
		 * //**************** normal condition
		 * 
		 * @SuppressWarnings("unchecked") List<DuplicateDetails> list11 = list1
		 * .stream() .filter(distinctByKeys(DuplicateDetails::getCandidateName,
		 * DuplicateDetails::getFatherName,DuplicateDetails::getMotherName,
		 * DuplicateDetails::getDob)) .collect(Collectors.toList());
		 * 
		 * 
		 * list1.removeAll(list11);
		 * 
		 * 
		 * 
		 * //unique duplicate list generation
		 * 
		 * @SuppressWarnings("unchecked") List<DuplicateDetails> filteredList = list1
		 * .stream() .filter(distinctByKeys(DuplicateDetails::getCandidateName,
		 * DuplicateDetails::getFatherName,DuplicateDetails::getMotherName,
		 * DuplicateDetails::getDob)) .collect(Collectors.toList());
		 * System.out.println("count of distinct"+filteredList.size());
		 * //System.out.println(filteredList.toString());
		 * 
		 * CandidateList=Dao.getCandidatesList();
		 * System.out.println("CandidateList size "+CandidateList.size()); // Filtering
		 * Candidate list on the basis of user_id starts List<CandidateDetails>
		 * filteredCandidateDetails = ListIterate.distinct( CandidateList,
		 * HashingStrategies.fromFunction(CandidateDetails::getUserId));
		 * 
		 * System.out.println("filteredCandidateDetails size"+filteredCandidateDetails.
		 * size());
		 * 
		 * // Filtering Candidate list on the basis of user_id ends ************
		 * 
		 * 
		 * 
		 * //Complete duplicate list generation (Sheet 2 generation)
		 * for(CandidateDetails cd:CandidateList) { for(DuplicateDetails
		 * ds:filteredList) {
		 * if((cd.getCandidateName().equalsIgnoreCase(ds.getCandidateName())) &&
		 * (cd.getFatherName().equalsIgnoreCase(ds.getFatherName())) &&
		 * (cd.getMotherName().equalsIgnoreCase(ds.getMotherName())) &&
		 * (cd.getDob().equals(ds.getDob()))) {
		 * 
		 * list2.add(cd); } } }
		 * 
		 * System.out.println("list2 size "+list2.size());
		 * filteredisDuplicateList.removeAll(list2);
		 * System.out.println("filteredisDuplicateList size "+filteredisDuplicateList.
		 * size()); //taking out differnce between the filteredisDuplicateList and list
		 * 2 /*for(CandidateDetails cd:filteredisDuplicateList ) { for(CandidateDetails
		 * ds:list2) {
		 * if(!(cd.getCandidateName().equalsIgnoreCase(ds.getCandidateName())) &&
		 * (cd.getFatherName().equalsIgnoreCase(ds.getFatherName())) &&
		 * (cd.getMotherName().equalsIgnoreCase(ds.getMotherName())) &&
		 * (cd.getDob().equals(ds.getDob()) )) {
		 * 
		 * differnceList.add(cd); //filteredisDuplicateList.remove(cd); } } }
		 * 
		 * 
		 * //filteredisDuplicateList.removeAll(differnceList);
		 * System.out.println(" filteredCandidateDetails size is "
		 * +filteredCandidateDetails.size());
		 * System.out.println(" differnceList size is "+differnceList.size());
		 * 
		 * 
		 * //To find count of k(no of duplicate registrations done by a candidate for
		 * the sheet 1) for(DuplicateDetails ds:filteredList) { for(CandidateDetails
		 * cd:list2) {
		 * if((ds.getCandidateName().equalsIgnoreCase(cd.getCandidateName())) &&
		 * (ds.getFatherName().equalsIgnoreCase(cd.getFatherName())) &&
		 * (ds.getMotherName().equalsIgnoreCase(cd.getMotherName())) &&
		 * (ds.getDob().equals(cd.getDob()))) { i++;
		 * 
		 * switch(i) { case 2:ds.setCount(2); break; case 3:ds.setCount(3); break; case
		 * 4:ds.setCount(4); break; case 5:ds.setCount(5); break; case 6:ds.setCount(6);
		 * break; case 7:ds.setCount(7); break; case 8:ds.setCount(8); break; case
		 * 9:ds.setCount(9); break; case 10:ds.setCount(10); break; case
		 * 11:ds.setCount(11); break; case 12:ds.setCount(12); break; }
		 * 
		 * } ds.setCount(i); } i=0; }
		 * 
		 * for(DuplicateDetails ds:filteredList) { for(DuplicateDetails
		 * cd:isDuplicateList) {
		 * if(!(cd.getCandidateName().equalsIgnoreCase(ds.getCandidateName())) &&
		 * (cd.getFatherName().equalsIgnoreCase(ds.getFatherName())) &&
		 * (cd.getMotherName().equalsIgnoreCase(ds.getMotherName())) &&
		 * (cd.getDob().equals(ds.getDob()))) {
		 * 
		 * differnceList.add(cd); } } }
		 * 
		 * 
		 * // System.out.println("differnTList size is "+differnceList.size());
		 * 
		 * 
		 * // System.out.println("filteredList to string \t"+filteredList.toString());
		 * //System.out.println("::::::"+i);
		 * 
		 * 
		 * //Collections.sort(list2,new DateComparator()); for(CandidateDetails
		 * ds:list2) { //Collections.sort(list2); //
		 * System.out.println(":::list2::\n"+ds.toString()); i++; }
		 * //System.out.println("list2 count"+ list2.size());
		 * 
		 * 
		 * 
		 * //String excelFilePath =
		 * "D:\\Department\\PHD\\PostWiseDuplicateList\\GeneratedFiles\\Unique_Dietician.xls";
		 * String excelFilePath2 =
		 * "D:\\RDD_REPORT_520\\Health worker (Male)_ST PESA Nandurbar_DuplicateList.xls"
		 * ;
		 * 
		 * try { //dao.writeExcel(filteredList, excelFilePath2); dao.writeExcel2(list2,
		 * excelFilePath2); } catch(IOException e) { e.printStackTrace(); }
		 * 
		 * //*************************************RDD REPORT related stuff************
		 * RddPosts rdd=new RddPosts(); List<RddPosts> rddList=new
		 * ArrayList<RddPosts>(); List<DuplicateDetails> user_ids= new
		 * ArrayList<DuplicateDetails>();
		 * 
		 * user_ids=Dao.getCandidateDetails();
		 * //System.out.println(user_ids.toString());
		 * //System.out.println("No of candidates "+user_ids.size()); //List<ExcelView>
		 * newList=new ArrayList<ExcelView>();
		 * 
		 * List<DuplicateDetails> allposts=new ArrayList<DuplicateDetails>();
		 * List<DuplicateDetails> allposts1=new ArrayList<DuplicateDetails>();
		 * allposts=Dao.getAllposts(); //System.out.println(allposts.toString());
		 * allposts1=Dao.getAllposts();
		 * //System.out.println("No of posts "+allposts.toString());
		 * System.out.println("::::::"+allposts.get(0));
		 * 
		 * 
		 * 
		 * //System.out.println("firstList "+allposts.get(1));
		 * //System.out.println("second list "+allposts1.get(1)); for(DuplicateDetails
		 * posts:allposts) { for(DuplicateDetails posts2:allposts1) {
		 * if(!(posts.getAllPosts().equals(posts2.getAllPosts())))
		 * 
		 * { for(DuplicateDetails dd:user_ids) {
		 * if(posts2.getAllPosts().equals(dd.getPostApplied())){ count=count+1;
		 * System.out.println("in  the if"); } }
		 * 
		 * //}
		 * if(posts2.getAllPosts().equals("Laboratory Technician_Aurangabad-Non PESA"))
		 * rdd.setLaboratory_Technician_Aurangabad_Non_PESA(count); else
		 * if(posts2.getAllPosts().equals("Pharmacist_Latur-Non PESA"))
		 * rdd.setPharmacist_Latur_Non_PESA(count); else
		 * if(posts2.getAllPosts().equals("Senior Assistant (Account)_Raigad-Non PESA"))
		 * rdd.setSenior_Assistant_Account_Raigad_Non_PESA(count);
		 * 
		 * 
		 * }
		 * 
		 * else break; rddList.add(rdd); count =0; }
		 * 
		 * String excelFilePath = "D:\\Department\\RDDPosts_4.xls"; for(DuplicateDetails
		 * d:allposts) {
		 * 
		 * for(DuplicateDetails d1:allposts) {
		 * 
		 * int cnt = 0; if(d.getPostApplied().equals(d1.getPostApplied())) { break; }
		 * else{
		 * 
		 * 
		 * try { Connection con=null; Statement st=con.createStatement(); String
		 * query="select user_id from user_exam where freefield_2="+d.getPostApplied()
		 * +"and user_exam_status='REGISTERED_SUCCESS'\r\n" + "intersect\r\n" +
		 * "select user_id from user_exam where freefield_2="+d1.getPostApplied()
		 * +" and user_exam_status='REGISTERED_SUCCESS'"; ResultSet
		 * rs=st.executeQuery(query); while(rs.next()) {
		 * 
		 * cnt++;
		 * 
		 * } } catch(Exception ex) { ex.printStackTrace(); } }
		 * 
		 * if(cnt>0) { try {
		 * 
		 * 
		 * allposts.get(i);
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * 
		 * } } //System.out.println(newList.toString()); //String excelFilePath =
		 * "D:\\Department\\RDDPosts.xls"; try { dao.writeExcelRddPosts(rddList,
		 * excelFilePath);
		 * 
		 * } catch(IOException e) { e.printStackTrace(); } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * public static <T> Predicate<T> distinctByKey(Function<? super T, Object>
		 * keyExtractor) { Map<Object, Boolean> seen = new ConcurrentHashMap<>(); return
		 * t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; }
		 * 
		 * 
		 * 
		 * public static boolean compareList(List ls1, List ls2){ return
		 * ls1.containsAll(ls2) && ls1.size() == ls2.size() ? true :false; }
		 * 
		 * 
		 * // Function to remove duplicates from an ArrayList public static <T>
		 * List<DuplicateDetails> removeDuplicates(List<DuplicateDetails> list) {
		 * 
		 * List<DuplicateDetails> newList = new ArrayList<DuplicateDetails>();
		 * 
		 * // Traverse through the first list for (DuplicateDetails element : list) {
		 * 
		 * // If this element is not present in newList // then add it if
		 * (!newList.contains(element)) {
		 * 
		 * newList.add(element); } }
		 * 
		 * // return the new list return newList; }
		 * 
		 * 
		 * 
		 * private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>...
		 * keyExtractors) { final Map<List<?>, Boolean> seen = new
		 * ConcurrentHashMap<>();
		 * 
		 * return t -> { final List<?> keys = Arrays.stream(keyExtractors) .map(ke ->
		 * ke.apply(t)) .collect(Collectors.toList());
		 * 
		 * return seen.putIfAbsent(keys, Boolean.TRUE) == null; }; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */