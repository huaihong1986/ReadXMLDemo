package cn.edu.chd.xmlutils;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherParserByDom {
    private static final String TAG = "WeatherParserByDom";

    private WeatherParserByDom() {
    }

    public static List<Map<String, String>> getWeatherData(InputStream is) {
        List<Map<String, String>> list = null;
        list = new ArrayList<Map<String,String>>();
        // 创建DOM工厂对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder(); //从factory获取DocumentBuilder实例
            Document doc = builder.parse(is);   //解析输入流 得到Document实例
            Element rootElement = doc.getDocumentElement();
            NodeList items = rootElement.getElementsByTagName("city");
            for (int i = 0; i < items.getLength(); i++) {
                Map<String, String> map = null;
                Element personElement = (Element) items.item(i);
                map = new HashMap<String, String>();
                map.put("city", personElement.getAttribute("name"));
                NodeList personChildrenNodes = personElement.getChildNodes();//获取person节点的所有子节点
                //遍历所有子节点
                for (int j = 0; j < personChildrenNodes.getLength(); j++) {
                    //判断子节点是否是元素节点（如果是文本节点，可能是空白文本，不处理）
                    if (personChildrenNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        //子节点--元素节点
                        Element childNode = (Element) personChildrenNodes.item(j);
                        if ("weather".equals(childNode.getNodeName())) {
                            map.put("weather", childNode.getFirstChild().getNodeValue());
                        } else if ("temp".equals(childNode.getNodeName())) {
                            map.put("temp", childNode.getFirstChild().getNodeValue());
                        } else if ("wind".equals(childNode.getNodeName())) {
                            map.put("wind", childNode.getFirstChild().getNodeValue());
                        }
                    }

                }
                list.add(map);
            }

        } catch (Exception e) {
            Log.i(TAG,  e.getMessage());
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}











