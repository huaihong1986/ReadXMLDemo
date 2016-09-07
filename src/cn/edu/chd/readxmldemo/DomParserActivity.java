package cn.edu.chd.readxmldemo;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.edu.chd.xmlutils.WeatherParserByDom;

/**
 * @author Rowand jj
 *
 */
public class DomParserActivity extends Activity
{
	private ListView lv_dom = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dom_layout);
		lv_dom = (ListView) findViewById(R.id.lv_dom);
		InputStream is=null;

		try {
			is=getAssets().open("weatherNew.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, String>> data = WeatherParserByDom.getWeatherData(is);
		lv_dom.setAdapter(new SimpleAdapter(this, data,R.layout.lv_item_dom, new String[]{"city","weather","temp","wind"}, new int[]{R.id.tv_dom_city,R.id.tv_dom_weather,R.id.tv_dom_temp,R.id.tv_dom_wind}));
	}
}


















