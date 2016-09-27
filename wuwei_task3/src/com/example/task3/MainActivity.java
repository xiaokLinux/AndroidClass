package com.example.task3;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tv1;
	ImageView img1;
	ListView lsv1;
	int imageIds[];
	String points[];
	int contextPosition;
	LinearLayout diaglogLayout;
	AlertDialog.Builder builder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuwei_main);
		 builder=new AlertDialog.Builder(this);
		diaglogLayout=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog, null,false);
		builder.setView(diaglogLayout);
		builder.create().show();
		imageIds=new int[]{R.drawable.wenda,R.drawable.zheda,R.drawable.xihu
				,R.drawable.gugong};
		img1=(ImageView) findViewById(R.id.imageView1);
		img1.setImageResource(imageIds[0]);
		tv1=(TextView) findViewById(R.id.textView1);
		lsv1=(ListView) findViewById(R.id.listView1);
		registerForContextMenu(lsv1);
		points=new String[]{"温州大学","浙江大学","杭州西湖","北京故宫"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,points);
		lsv1.setAdapter(adapter);
		tv1.setText(points[0]);
		
		lsv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1,
        			int arg2, long arg3) {
        		img1.setImageResource(imageIds[arg2]);      		
        		tv1.setText(points[arg2]);
        	}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub	
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.context_menu, menu);
		contextPosition=((AdapterView.AdapterContextMenuInfo)menuInfo).position;
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		String str;//debug
		str="";//debug
		switch (item.getItemId()) {
		case R.id.context_add:
			str="context_add";//debug
			break;
			
		case R.id.context_replay:
			str="context_replay";//debug
			break;		
		case R.id.context_del:
			str="context_del";//debug
			break;
		default:
			break;
		}
		
		Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();//debug
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "begin", Toast.LENGTH_SHORT).show();
		int position=item.getItemId();
		switch (position) {
		case R.id.add:
			Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
			break;
		case R.id.reset:
			Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}

}
