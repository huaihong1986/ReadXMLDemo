package cn.edu.chd.readxmldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	private Button but_sax = null;
	private Button but_pull = null;
	private Button but_dom = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		but_pull = (Button) findViewById(R.id.but_pull);
		but_sax = (Button) findViewById(R.id.but_sax);
		but_dom = (Button)  findViewById(R.id.but_dom);
		but_pull.setOnClickListener(this);
		but_sax.setOnClickListener(this);
		but_dom.setOnClickListener(this);
	}
	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		if(id == R.id.but_pull)
		{
			Intent intent = new Intent(this,PullParserActivity.class);
			startActivity(intent);
		}
		else if(id == R.id.but_sax)
		{
			Intent intent = new Intent(this,SaxParserActivity.class);
			startActivity(intent);
		}
		else if(id == R.id.but_dom)
		{
			Intent intent = new Intent(this,DomParserActivity.class);
			startActivity(intent);
		}
	}
	
}





















