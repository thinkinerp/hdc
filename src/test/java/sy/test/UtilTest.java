package sy.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.util.ComUtil;

public class UtilTest {

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
