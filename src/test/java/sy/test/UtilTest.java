package sy.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.intfocus.hdk.util.ComUtil;

public class UtilTest {

	@Test
	public void comUtilDateFormat() {
		System.out.println(ComUtil.dateFormat("2017-05-15 16:57:10"));	
	}
	@Test
	public void apacheUitljoinTest(){
		List<String> urls = new ArrayList<String>();
//		System.out.println(ComUtil.savePicture("", ""));
		urls.add("dsdfas");
		System.out.println(org.apache.commons.lang.StringUtils.join(urls.toArray(),","));
	}
}
