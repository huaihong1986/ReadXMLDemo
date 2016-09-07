package cn.edu.chd.xmlutils;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherParserByPull {
    private static final String TAG = "WeatherParserByPull";

    private WeatherParserByPull() {
    }

    public static List<Map<String, String>> getWeatherData(InputStream is) {
        if (is == null)
            return null;


        XmlPullParser parser = Xml.newPullParser();
        List<Map<String, String>> list = null;
        Map<String, String> map = null;
        try {
            parser.setInput(is, "utf-8");

            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("info".equals(parser.getName())) {
                            list = new ArrayList<Map<String, String>>();
                        }
                    /*
					to weather.xml
					else if("city".equals(parser.getName()))
					{
						map = new HashMap<String, String>();

					}else if("name".equals(parser.getName())){
						map.put("city",parser.nextText());
					}
					 */

                        else if ("city".equals(parser.getName())) {
                            map = new HashMap<String, String>();
                            map.put("city", parser.getAttributeValue(null, "name"));
                        } else if ("weather".equals(parser.getName())) {
                            map.put("weather", parser.nextText());
                        } else if ("temp".equals(parser.getName())) {
                            map.put("temp", parser.nextText());
                        } else if ("wind".equals(parser.getName())) {
                            map.put("wind", parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("city".equals(parser.getName())) {
                            list.add(map);
                            map = null;
                        }
                        break;

                    default:
                        break;
                }
                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, e.getMessage());
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}



