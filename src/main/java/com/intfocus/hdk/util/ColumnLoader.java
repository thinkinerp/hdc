package com.intfocus.hdk.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.intfocus.hdk.vo.ColProperty;
public class ColumnLoader {
    public static String getResourcePath(Class clazz) {
        String className = clazz.getName();
        
        String classNamePath = className.replace(".", "/") + ".class";
        URL is = clazz.getClassLoader().getResource(classNamePath);
        String path = is.getFile();
        path = StringUtils.replace(path, "%20", " ");
        path = StringUtils.replace(path, StringUtils.replace(className , clazz.getPackage().getName() + ".", "") + ".class", "");
        return path;
    }
    
public static List<ColProperty> sqlGenerator(String xmlFileName , Class clazz , String insertTable){
	
	List<ColProperty> list = new ArrayList<ColProperty>();
	ColProperty colProperty = null;
	String path = getResourcePath(clazz) +  xmlFileName;
    File file = new  File(path);
    SAXBuilder builder = new SAXBuilder();
    Document parse;
	try {
		parse = builder.build(file);
		Element root = parse.getRootElement();
		List<Element> tables = XPath.selectNodes(parse, "/content/table[@name='"+insertTable+"']");;
		
		for (Element e : tables) {
			List<Element> es = e.getChildren("c");
			for (Element element : es) {
				
				colProperty = new ColProperty() ;
				colProperty.setCol(element.getAttribute("col").getValue()+ "");
				colProperty.setCtype(element.getAttribute("ctype").getValue() + "");
				colProperty.setDefaults(element.getAttribute("default").getValue()+"");
				colProperty.setForeign(element.getAttribute("foreign").getValue()+"");
				colProperty.setExcelCol(element.getValue() + "");
				colProperty.setPrimary(element.getAttribute("primary").getValue() + "");
                list.add(colProperty);
			}
		}
		
	} catch (JDOMException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list  ;
}    
}
