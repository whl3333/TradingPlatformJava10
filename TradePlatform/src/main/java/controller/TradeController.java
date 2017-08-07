package controller;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Trade;
import service.TradeService;

import org.springframework.beans.factory.annotation.Autowired;  

@RestController
public class TradeController {
   @Autowired
   private TradeService tradeService;
   @GetMapping(value = "/trades") 
   public List<Trade> findAll() {  
       List<Trade> list = tradeService.find ();  
       return list;  
   }  
  
//    @RequestMapping("/alltrade")
//    public List<Map<String, Object>> greeting(@RequestParam(value="name", defaultValue="World") String name) throws SQLException {
//    	Connection conn = null;
//        String sql;
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
//          String url = "jdbc:mysql://localhost:3306/test?"
//                  + "user=root&password=root&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
//          try {
//              Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
//              System.out.println("成功加载MySQL驱动程序");
//              conn = DriverManager.getConnection(url);
//              Statement stmt = conn.createStatement();
//      //                  sql = "insert into student(NO,name) values('20120012','陶伟基')";
////                  result = stmt.executeUpdate(sql);
////                  sql = "insert into student(NO,name) values('20120013','周小俊')";
////                  result = stmt.executeUpdate(sql);
//                  sql = "select * from trade";
//                  ResultSet rs = stmt.executeQuery(sql);
//                  ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
//                  int columnCount = md.getColumnCount();   //获得列数   
//                  while (rs.next()) {  
//                      
//                      Map<String,Object> rowData = new HashMap<String,Object>(); 
//                      for (int i = 1; i <= columnCount; i++) {  
//                          rowData.put(md.getColumnName(i), rs.getObject(i));  
//                      }  
//                      list.add(rowData);  
//            
//                  }  
//              } catch (ClassNotFoundException e) {  
//                  e.printStackTrace();  
//              } catch (SQLException e) {  
//                  e.printStackTrace();  
//              }  
//              return list;  
//    }
}