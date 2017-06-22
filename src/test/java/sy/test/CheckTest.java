package sy.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.ProblemMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.SurveyMapper;
import com.intfocus.hdk.dao.UserMapper;
import com.intfocus.hdk.util.StaticVariableUtil;
import com.intfocus.hdk.vo.Project;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CheckTest {

//	@Test
//	public void test() {
//		JuheDemo.check("","");	
//	}
    private   static  String param1;    
    private   static  String param2;    
    @Resource
    private ProjectMapper pm ;
    @Resource
    private ProblemMapper pblm ;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SurveyMapper surveyMapper;
    
    @Test
    public void testSurveyMapperSelectByWhere(){
    	Map<String, String> where = new HashMap<String,String>();
//    	where.put("shopName","项目A");
    	where.put("surId", "sur_001");
//    	where.put("order", "desc");
//    	where.put("shopStation", "dd");
		System.out.println(JSONObject.toJSONString(surveyMapper.selectByWhere(where)));
    }
    
    @Test
    public void testProjectCount(){
    	
    	Map<String,String> where = new HashMap<String,String>();
    	where.put("proName", "项目A");
    	
    	JSONObject.toJSONString(pm.getCheck( where));

    	JSONObject.toJSONString(pm.getCashCount( where));

    	JSONObject.toJSONString(pm.getEquipment(where));

    	JSONObject.toJSONString(pm.getProjectProblem(where));
    }
    
    @Test
    public void testUserMapperSelectByWhere(){
    	System.out.println(userMapper.selectByWhere(null));
    }
    @Test
    public void testUserMapperGetDepartMent(){
    	System.out.println(userMapper.getDepartment());
    }

    
    @Test
    public void testProblemMapperGetCount(){
    	
    	Map<String,String> where = new HashMap<String,String>();
    	where.put("proName", "pro_001");
    	String str  = JSONObject.toJSONString(pblm.getCount(where));
    	System.out.println("json:" +str);
    }
    @Test
    public void testProblemMapperSelectByWhere(){
    	
    	Map<String,String> where = new HashMap<String,String>();
    	where.put("proName", "pro_001");
    	where.put("problemObject", "海鼎");
    	where.put("state", "未完成");
    	String str  = JSONObject.toJSONString(pblm.selectByWhere(where));
    	System.out.println("json:" +str);
    }
    
    
    
    @Test
    public void testStatic(){
    	System.out.println(StaticVariableUtil.APPID);
    	System.out.println(StaticVariableUtil.BASE_URL);
    }
     @Test
     public void testProject(){
    	System.out.println( "dsfasdf" +JSONObject.toJSONString(pm.selectProjectCount()));
     }
	@Test
	public void test() throws ParseException{

		Properties prop =  new  Properties();
	        
	        try  {    
	            InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/weixinInfo.properties" ));    
	            prop.load(in);    
	            param1 = prop.getProperty( "appid" ).trim();    
	            param2 = prop.getProperty( "secret" ).trim();    
	            System.out.println(param1);
	            System.out.println(param2);
	        }  catch  (IOException e) {    
	            e.printStackTrace();    
	        }    
//		SalesData sd = new SalesData();
//		
//		sd.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-04-23 14:09:00"));
//		sd.setSalesAmount(123.4f);
//		sd.setSalesDate("2017-04-23");
//		sd.setStoreUuid("523");
//		Map<String,String> mp = JuheDemo.uplaodSalesData(sd );
//		System.out.println(mp.get("message") + mp.get("result"));
	}

}