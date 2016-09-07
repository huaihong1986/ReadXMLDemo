package cn.edu.chd.xmlutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author Rowand jj
 *
 *解析天气的xml处理器
 */
public class SaxHandler4Weather extends DefaultHandler
{
	private Map<String,String> map = null;//存储单个解析的完整对象
	private List<Map<String,String>> list = null;//存储所有的解析对象

	private String currentTag = null;//正在解析的元素的标签
	private String currentValue = null;//解析当前元素的值
	private String nodeName = "city";//待解析的xml文件中代表一个实体的xml根节点名

	@Override
	public void startDocument() throws SAXException
	{
		list = new ArrayList<Map<String,String>>();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
							 Attributes attributes) throws SAXException
	{
		if(qName.equals(nodeName))//发现city节点
		{
			map = new HashMap<String, String>();
		}
		if(attributes!=null && map!= null)
		{
			for(int i = 0; i < attributes.getLength();i++)
			{
				if(attributes.getQName(i).equals("name"))
				{
					map.put("city", attributes.getValue(i));
				}
			}
		}
		currentTag = qName;
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException
	{
		if(qName.equals(nodeName))//一个city节点解析完毕
		{
			list.add(map);
			map = null;
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException
	{
		if(currentTag!=null && map!=null)
		{
			currentValue = new String(ch,start,length).trim();
			if(nodeName.equals(currentTag))//city节点
			{
			}else if("weather".equals(currentTag))//是否是weather节点
			{
				map.put("weather", currentValue);
			}else if("temp".equals(currentTag))//是否是temp节点
			{
				map.put("temp",currentValue);
			}else if("wind".equals(currentTag))//是否是wind节点
			{
				map.put("wind",currentValue);
			}
		}
		currentTag = null;
		currentValue = null;
	}
	public List<Map<String, String>> getList()
	{
		return list;
	}
}












