package sy.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.util.ComUtil;
import com.intfocus.hdk.vo.Project;

public class UtilTest {

	private static final String DATE1 = "1999-12-12 15:21";
	private static final String DATE2 = "1999-12-12 15:20";
	@Test
	public void dataCompare(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			long d = dt1.getTime() - dt2.getTime();
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void Null(){
		Project p = new Project();
		p.setCount(null);
		p.setCreatedAt(null);
		p.setId(null);
		p.setIsLast(1);
		p.setLeftNum(null);
		System.out.println(ComUtil.reflect(p));
	}
	@Test
	public void StringIndex(){
		System.out.println("门店信息导入模板.xls".indexOf("项目"));
	}
	@Test
	public void comUtilDateFormat() {
		System.out.println(ComUtil.dateFormat(new Date(), "yyMMdd"));	
		
	}
	@Test
	public void testComUtilRemove(){
		String[] ss = {"ddddd","d"};
		
		System.out.println(JSONObject.toJSON(ComUtil.remove("dd",ss	)));
	}
	@Test
	public void testArrayLength(){
		String[] ss = {"d","d"};
		System.out.println(ss.length);
	}
	@Test
	public void apacheUitljoinTest(){
		List<String> urls = new ArrayList<String>();
//		System.out.println(ComUtil.savePicture("", ""));
		urls.add("dsdfas");
		System.out.println(org.apache.commons.lang.StringUtils.join(urls.toArray(),","));
	}
}
