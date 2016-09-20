package com.example.spoc_xml;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button bt1,bt2,bt3;
	TextView tv;
	ImageView iv;
	TypedArray colors,sizes,imgs;
	
	int colorindex=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuwei_main);
		
		tv=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1);
		bt2=(Button) findViewById(R.id.button2);
		bt3=(Button) findViewById(R.id.button3);
		iv=(ImageView) findViewById(R.id.imageView1);
		
		//res
		Resources res=this.getResources();
		colors=res.obtainTypedArray(R.array.colors);
		
		bt1.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//设置tv的字体大小
			}
		});
		
		bt2.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//设置tv的颜色
				
				tv.setTextColor(colors.getColor((colorindex++)%4, 0));
			}
		});
		
		bt3.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//设置iv的资源id
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
