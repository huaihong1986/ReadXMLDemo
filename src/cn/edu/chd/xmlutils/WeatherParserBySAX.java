package cn.edu.chd.xmlutils;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

public class WeatherParserBySAX
{
	private static final String TAG = "WeatherParserBySAX";
	private WeatherParserBySAX(){}
	public static List<Map<String,String>> getWeatherData(InputStream is)
	{
		List<Map<String,String>> list = null;
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			SaxHandler4Weather handler = new SaxHandler4Weather();
			parser.parse(is, handler);
			is.close();
			list = handler.getList();
			Log.i(TAG,"-->size = "+list.size());
		} catch (Exception e)
		{
			Log.i(TAG,e.getMessage());
		}
		return list;
	}
}











