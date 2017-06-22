package com.intfocus.hdk.util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.vo.ColProperty;
/**
 * descripton: 
 * 1.the version only support 97-2004 excel file.
 * 2.read xls file and convert it to sql script
 * 3.import users,roles,groups.
 * 4.acount must be unique,so I shold use ' select options where exists ( select * from sys_users where user_num = 'acount')'
 */
public class ExcelReader {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private static String tableName ;
    private static final int USER_LIST     = 0 ;
    private static final int GROUP_LIST  = 2 ;
    private static final int ROLE_LIST      = 1 ;
    
    public static String getResourcePath(Class clazz) {
        String className = clazz.getName();
        
        String classNamePath = className.replace(".", "/") + ".class";
        URL is = clazz.getClassLoader().getResource(classNamePath);
        String path = is.getFile();
        path = StringUtils.replace(path, "%20", " ");
        path = StringUtils.replace(path, StringUtils.replace(className , clazz.getPackage().getName() + ".", "") + ".class", "");
        return path;
    }
    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle(int sheetIndex, InputStream is  , List<ColProperty> list) {
        try {
        	  is.mark(1000000);
            InputStream wrappedStream = POIFSFileSystem.createNonClosingInputStream(is);
 
            wb = new HSSFWorkbook(wrappedStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(sheetIndex);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
        	
            title[i] = getCellFormatValue(row.getCell((short) i));
            for( ColProperty col  :  list){
            	     if(null != title[i] && title[i].endsWith(col.getExcelCol())){
            	    	      col.setExcelIndex(i);
            	     }
            }    
            
        }
        try {
			is.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return title;
    }

    
    public List<String> sqlGennerater(InputStream is , String tableName){
    	List<String> sqlList = new ArrayList<String>();
        List<ColProperty> cols = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, tableName);
        String sqlTemplate = generator(cols,tableName);
        String sql = "" ;
        this.readExcelTitle(0, is, cols);
        String value = "" ;
        InputStream wrappedStream = POIFSFileSystem.createNonClosingInputStream(is);

        try {
			wb = new HSSFWorkbook(wrappedStream);
	        sheet = wb.getSheetAt(0);
			
	        int rowNum = sheet.getLastRowNum();
	        ColProperty colProperty =	null ;
			// 第一层循环，从第二行开始一行一行的遍历，
	        for (int i = 1; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            sql = sqlTemplate;
	 	       for(int j = 0 ; j  < cols.size() ; j ++ ){
		    	   colProperty = cols.get(j);
				   if("int".equalsIgnoreCase(colProperty.getCtype())) {
					   value =  getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() ;
		    	   }else if("varchar".equalsIgnoreCase(colProperty.getCtype())){
		    		   value = "'" + getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() +"'";
		    	   }
		    	     sql = sql.replaceAll("@"+colProperty.getCol()+"@",  value);
	 	       }
	 	       sqlList.add(sql);
	        }
	        
			// 底层遍历这一列所在字段名称
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return sqlList ;
    }
    
    /**
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is , List<ColProperty> userList ,List<ColProperty> roleList , List<ColProperty> groupList ) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        
        
        String str = "" ; 
        String sql = generator(userList,"sys_users");
        
        
        try {
            this.readExcelTitle(ROLE_LIST, is, roleList);
            this.readExcelTitle(GROUP_LIST, is, groupList);	
            this.readExcelTitle(USER_LIST, is, userList);
            InputStream wrappedStream = POIFSFileSystem.createNonClosingInputStream(is);
            sheet = wb.getSheetAt(0);
            wb = new HSSFWorkbook(wrappedStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
       int userColNumber = 0 ;
       
       for (ColProperty colProperty : userList) {
		   if( "0".equalsIgnoreCase(colProperty.getForeign()) ){
    	           userColNumber = userList.indexOf(colProperty) + 1;  			   
		   }
    	   
	    }
        
        sheet = wb.getSheetAt(0);
        
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
//        int colNum = row.getPhysicalNumberOfCells();
           int colNum = userList.size();
           ColProperty  colProperty = null ;
           String groupInsertSql = "";
           String roleInsertSql = "" ;
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
       	    str = sql;
       	    colProperty = new ColProperty();
	       
	       groupInsertSql += " INSERT INTO sys_user_groups \n " +
				      " (user_id, group_id)  \n" +
				      " select u.id , g.id \n" +
				      " from sys_users as u \n" + 
				      " left join sys_groups as g on 1=1 \n" +
				      " where u.user_name = '@user_name@' and g.group_name = '@group_name@' ; \n" 
           ;
	       roleInsertSql =  " INSERT INTO sys_user_roles  \n" +
						    		   " (user_id, role_id)\n " +
						    		   " select u.id , r.id  \n" +
						    		   " from sys_users as u \n" + 
						    		   " left join sys_roles as r on 1=1\n " +
						    		   " where u.user_name = '@user_name@' and r.role_name = '@role_name@' ;\n" ; 
 
	       
	       for( ; j  < userList.size() ; j ++ ){
	    	     colProperty = userList.get(j);
             groupInsertSql =	groupInsertSql.replaceAll("@"+colProperty.getCol()+"@", getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim());
             roleInsertSql     = roleInsertSql.replaceAll("@"+colProperty.getCol()+"@", getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim());
	      
   	       if("int".equalsIgnoreCase(colProperty.getCtype()) && "0".equalsIgnoreCase(colProperty.getForeign())){
               str                     = str.replaceAll("@"+colProperty.getCol()+"@", 
            		                                                            "".equalsIgnoreCase(getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim())?
            		                                                            		!"".equalsIgnoreCase(colProperty.getDefaults())?colProperty.getDefaults():"NULL" 
            		                                                                  :getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() ) ;
           } else if("varchar".equalsIgnoreCase(colProperty.getCtype()) && "0".equalsIgnoreCase(colProperty.getForeign())){
               str                     = str.replaceAll("@"+colProperty.getCol()+"@", 
            		         							"".equalsIgnoreCase(getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim())? 
            		         							!"".equalsIgnoreCase(colProperty.getDefaults())? "'" + colProperty.getDefaults() + "'":"NULL" 
            		         						    : "'" + getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() + "'") ;
          }
	       
	       }
	       
	       
            str += groupInsertSql + roleInsertSql ;
            content.put(i, str);
            str = "";
        }
        
        str = "" ;
        sql = generator(roleList,"sys_roles");
        sheet = wb.getSheetAt(1);
        rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        
       

        for(int i = 1 ; i < rowNum ; i ++){
        	     
        	row = sheet.getRow(i);
        	
        	str = sql ;
            for( int j = 0 ; j  < roleList.size() ; j ++ ){
	    	     colProperty = roleList.get(j);

  	       if("int".equalsIgnoreCase(colProperty.getCtype()) && "0".equalsIgnoreCase(colProperty.getForeign())){
              str                     = str.replaceAll("@"+colProperty.getCol()+"@", 
           		                                                            "".equalsIgnoreCase(getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim())?
           		                                                            		!"".equalsIgnoreCase(colProperty.getDefaults())?colProperty.getDefaults():"NULL" 
           		                                                                  :getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() ) ;
          } else if("varchar".equalsIgnoreCase(colProperty.getCtype()) && "0".equalsIgnoreCase(colProperty.getForeign())){
              str                     = str.replaceAll("@"+colProperty.getCol()+"@", 
           		         							"".equalsIgnoreCase(getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim())? 
           		         							!"".equalsIgnoreCase(colProperty.getDefaults())? "'" + colProperty.getDefaults() + "'":"NULL" 
           		         						    : "'" + getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() + "'") ;
         }
            }
            content.put(content.size() + 1, str);     
        }
    
        
        str = "" ;
        sql = generator(groupList,"sys_groups");
        sheet = wb.getSheetAt(this.GROUP_LIST);
        rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        

        for(int i = 1 ; i < rowNum ; i ++){
        	     
        	row = sheet.getRow(i);
        	
        	str = sql ;
            for( int j = 0 ; j  < groupList.size() ; j ++ ){
	    	     colProperty = groupList.get(j);

  	       if("int".equalsIgnoreCase(colProperty.getCtype()) && "0".equalsIgnoreCase(colProperty.getForeign())){
              str                     = str.replaceAll("@"+colProperty.getCol()+"@", 
           		                                                            "".equalsIgnoreCase(getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim())?
           		                                                            		!"".equalsIgnoreCase(colProperty.getDefaults())?colProperty.getDefaults():"NULL" 
           		                                                                  :getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() ) ;
          } else if("varchar".equalsIgnoreCase(colProperty.getCtype()) && "0".equalsIgnoreCase(colProperty.getForeign())){
              str                     = str.replaceAll("@"+colProperty.getCol()+"@", 
           		         							"".equalsIgnoreCase(getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim())? 
           		         							!"".equalsIgnoreCase(colProperty.getDefaults())? "'" + colProperty.getDefaults() + "'":"NULL" 
           		         						    : "'" + getCellFormatValue(row.getCell((short) colProperty.getExcelIndex())).trim() + "'") ;
         }
            }
            content.put(content.size() + 1, str);     
        }
        return content;
    }

    /*
     * @method: generate sql by list
     * 
     * */
    private static String generator(List<ColProperty> list , String tableName){
    	
    	    String sql = " insert into " + tableName + " (";
    	    
    	    String select = "select " ;
    	    
    	    String where = " from dual where not exists ( select keyCol from " + tableName + " where keyCol = @keyCol@ ); " ;
    	    String k = "";
    	    int i = 0 ;
    	    
    	    k= list.stream().filter(c -> "1".equalsIgnoreCase(c.getPrimary())  ).findFirst().get().getCol() ;  	    

    	    for(ColProperty colProperty : list){
    	    	   
    	    	      if("0".equalsIgnoreCase(colProperty.getForeign())){
    	    	      i = list.indexOf(colProperty) + 1;
    	    	      }
    	       }
    	    for (ColProperty colProperty : list) {
    	    	       if("1".equalsIgnoreCase(colProperty.getForeign())) continue ;
    	    	        if(i ==( list.lastIndexOf(colProperty)+ 1)){
    	    	        	      sql +=  "`" + colProperty.getCol() + "`" + ")\n" ;
    	    	        	      select += "@" + colProperty.getCol()  + "@" ;
    	    	        }else{
    	    	        	select += "@" + colProperty.getCol()  + "@," ;
    	    	        	  sql += "`" + colProperty.getCol() + "`" +  "," ;
    	    	        	
    	    	        }
			}
    	    sql += select + where.replaceAll("keyCol", k);
    	    return sql ;
    }
    
    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(new DecimalFormat("#").format(cell.getNumericCellValue()));
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static void main(String[] args) {
        try {
            ExcelReader excelReader = new ExcelReader();
//            List<ColProperty> user = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_users");
//            List<ColProperty> group = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_groups");
//            List<ColProperty> role = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_roles");


            // 对读取Excel表格内容测试
            InputStream file = new BufferedInputStream( new FileInputStream("/Users/wangyifei/Documents/状态信息.xls"));
		     System.out.println(JSONObject.toJSONString(new ExcelReader().sqlGennerater(file, "state")));
//            Map<Integer, String> map = excelReader.readExcelContent( file , user  ,role , group   );
//            System.out.println("获得Excel表格的内容:");
//            for (int i = 1; i <= map.size(); i++) {
//                System.out.println(map.get(i));
//            }

        } catch (Exception e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public static int getUserList() {
		return USER_LIST;
	}
	public static int getGroupList() {
		return GROUP_LIST;
	}
	public static int getRoleList() {
		return ROLE_LIST;
	}
}
