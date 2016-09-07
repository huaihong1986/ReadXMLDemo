package cn.edu.chd.readxmldemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.edu.chd.xmlutils.WeatherParserByPull;

/**
 * @author Rowand jj
 *
 */
public class PullParserActivity extends Activity
{
	private ListView lv_pull = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_layout);
		lv_pull = (ListView) findViewById(R.id.lv_pull);
		AssetManager assetManager = getAssets();//文件保存在assets目录下，得到assetManager管理器
		InputStream is = null;
		try {
			is = assetManager.open("weatherNew.xml");//打开文件，得到输入流
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, String>> data = WeatherParserByPull.getWeatherData(is);
		lv_pull.setAdapter(new SimpleAdapter(this, data,R.layout.lv_item_pull, new String[]{"city","weather","temp","wind"}, new int[]{R.id.tv_pull_city,R.id.tv_pull_weather,R.id.tv_pull_temp,R.id.tv_pull_wind}));
	}
}


















