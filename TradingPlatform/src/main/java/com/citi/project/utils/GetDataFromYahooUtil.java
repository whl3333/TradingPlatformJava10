//package com.citi.project.algorithm;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import com.citi.project.entities.OrderBook;
//
//public class GetDataFromYahooUtil {  
//    
//    public static final String YAHOO_FINANCE_URL = "http://finance.yahoo.com/d/quotes.csv?";  
//
//    public static void main(String[] args) throws IOException {
//    	ArrayList<OrderBook>  list =  getStockCsvData();
//    	if(list!=null)
//    	{
//    		for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i));
//			}
//    	}
//	}
//    public static ArrayList<OrderBook> getStockCsvData() {  
//    	ArrayList<OrderBook> list = new ArrayList<OrderBook>();  
//        String url = "http://download.finance.yahoo.com/d/quotes.csv?s=XOM+BBDb.TO+JNJ+MSFT&f=saa5bb5";
//          
//        URL MyURL = null;  
//        URLConnection con = null;  
//        InputStreamReader ins = null;  
//        BufferedReader in = null;  
//        System.out.println(list.size());
//        try {  
//            MyURL = new URL(url);  
//            con = MyURL.openConnection();  
//            con.setConnectTimeout(50000);
//            con.setReadTimeout(50000);
//            ins = new InputStreamReader(con.getInputStream(), "UTF-8");  
//            in = new BufferedReader(ins);  
//            System.out.println("dd"+list.size());
//            String newLine = in.readLine();// 标题行  
//              
//            while ((newLine = in.readLine()) != null) {  
//            	//System.out.println(newLine);
//                String stockInfo[] = newLine.trim().split(",");  
//               // System.out.println(stockInfo[2]);
//                if(isNumeric((stockInfo[2])))
//                {
//                	 OrderBook sd = new OrderBook(); 
//                	 sd.setSymbol(stockInfo[0]);
//                	 sd.setType('a');
//                	 sd.setQuantity(Integer.parseInt(stockInfo[2]));
//                	 sd.setPrice(Float.parseFloat(stockInfo[1]));
//                	   list.add(sd);  
//                }
//                if(isNumeric((stockInfo[4])))
//                {
//                	 OrderBook sd = new OrderBook(); 
//                	 sd.setSymbol(stockInfo[0]);
//                	 sd.setType('b');
//                	 sd.setQuantity(Integer.parseInt(stockInfo[4]));
//                	 sd.setPrice(Float.parseFloat(stockInfo[3]));
//                	   list.add(sd);   
//                }
//            }  
//         //   System.out.println(list.size());
//        } catch (Exception ex) {  
//        	ex.printStackTrace();
//            return null; //无交易数据  
//        } finally {  
//            if (in != null)  
//                try {  
//                    in.close();  
//                } catch (IOException ex) {  
//                    ex.printStackTrace();  
//                }  
//        }  
//        return list;  
//    }  
//    
//    public static boolean isNumeric(String str){ 
//    	   Pattern pattern = Pattern.compile("[0-9]*"); 
//    	   Matcher isNum = pattern.matcher(str);
//    	   if( !isNum.matches() ){
//    	       return false; 
//    	   } 
//    	   return true; 
//    	}
//    /** 
//     * 根据 股票编码、日期 获取股票数据 
//     * 
//     * @param stockName   沪市：000000.ss 深市：000000.sz 
//     * @param date 日期 
//     * @return StockData 
//     */  
//  
//}  
//
