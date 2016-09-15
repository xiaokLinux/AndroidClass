package com.example.task2;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	ImageView imv1;
	ListView lsv1;
	TextView tv1;
	int[] imageIds;
	int[] cities;
	int[] provinces;
	Spinner sp1;
	int province_choosed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuwei_main);
		imageIds=new int[]{R.drawable.hangzhou,R.drawable.ningbo,R.drawable.wenzhou
				,R.drawable.hefei,R.drawable.huangshan,R.drawable.bengbu,R.drawable.nanjing,
				R.drawable.suzhou,R.drawable.xuzhou};
		cities=new int[]{R.string.city1,R.string.city2,R.string.city3,R.string.city4
				,R.string.city5,R.string.city6,R.string.city7,R.string.city8,R.string.city9};
		provinces=new int[]{R.string.province1,R.string.province2,R.string.province3};
		imv1=(ImageView) findViewById(R.id.imageView1);
		imv1.setImageResource(imageIds[0]);
		
		lsv1=(ListView) findViewById(R.id.listView1);
		tv1=(TextView) findViewById(R.id.textView1);
		
		sp1=(Spinner) findViewById(R.id.spinner1);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String[] citiesEntries=new String[3];
				for(int i=0;i<3;i++)
				{
					citiesEntries[i]=getString(cities[i+arg2*3]);
				}
				province_choosed=arg2;
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,citiesEntries);
				lsv1.setAdapter(adapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lsv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1,
        			int arg2, long arg3) {
        		// arg0是对应的Adapter视图
        		// arg1为列表中被点击的那行视图,可被强制转换为视图对应的组件类，从而获取该组件内的内容
        		// arg2 为position，即在listview中第几条被点击的位置，从0开始索引
        		// arg3与数据库挂钩，目前没有用到
        		// TODO Auto-generated method stub
//        		Toast.makeText(MainActivity.this, "第"+arg2+"项被点击", Toast.LENGTH_SHORT).show();
        		imv1.setImageResource(imageIds[province_choosed*3+arg2]);
        		String str;
        		str="";
        		str+=getString(provinces[province_choosed]);
        		str+=" ";
        		str+=getString(cities[province_choosed*3+arg2]);        		
        		tv1.setText(str);
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
