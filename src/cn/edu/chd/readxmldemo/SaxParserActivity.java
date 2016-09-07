package cn.edu.chd.readxmldemo;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.edu.chd.xmlutils.WeatherParserBySAX;

public class SaxParserActivity extends Activity
{ 
	private ListView lv_sax = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sax_layout);
		lv_sax = (ListView) findViewById(R.id.lv_sax);

		AssetManager assetManager = getAssets();//文件保存在assets目录下，得到assetManager管理器
		InputStream is = null;
		try {
			is = assetManager.open("weatherNew.xml");//打开文件，得到输入流
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, String>> data = WeatherParserBySAX.getWeatherData(is);
		lv_sax.setAdapter(new SimpleAdapter(this, data,R.layout.lv_item_sax, new String[]{"city","weather","temp","wind"}, new int[]{R.id.tv_sax_city,R.id.tv_sax_weather,R.id.tv_sax_temp,R.id.tv_sax_wind}));
	}
}











