package com.example.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	int gradchoose;
	TextView tv1;
	ImageView img1;
	ListView lsv1;
	List<Integer> imageIds;
	List<Integer> initimageIds;
	List<String> points;
	List<String> initpoints;
	EditText ed;
	TextView tv;
    String [] from ={"image"};
    int [] to = {R.id.imageView1};
	int contextPosition;
	LinearLayout diaglogLayout;
	AlertDialog.Builder builder;
	GridView gv;
	ArrayAdapter<String> adapter;
	SimpleAdapter sim_adapter;//dialog gridview
	private List<Map<String, Object>> data_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuwei_main);
		imageIds=new ArrayList<Integer>(Arrays.asList(R.drawable.wenda,R.drawable.zheda,R.drawable.xihu,R.drawable.gugong));
		initimageIds=new ArrayList<Integer>(Arrays.asList(R.drawable.wenda,R.drawable.zheda,R.drawable.xihu,R.drawable.gugong));//用于初始化、修改
		points=new ArrayList<String>(Arrays.asList("温州大学","浙江大学","杭州西湖","北京故宫"));	
//		initpoints=new ArrayList<String>(Arrays.asList("温州大学","浙江大学","杭州西湖","北京故宫"));//备份，用于reset				
		
		img1=(ImageView) findViewById(R.id.imageView1);
		img1.setImageResource(imageIds.get(0));
		tv1=(TextView) findViewById(R.id.textView1);
		lsv1=(ListView) findViewById(R.id.listView1);		
		
		registerForContextMenu(lsv1);
		adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,points);
		lsv1.setAdapter(adapter);
		tv1.setText(points.get(0));
		
		
		
		
		lsv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1,
        			int arg2, long arg3) {
        		img1.setImageResource(imageIds.get(arg2));      		
        		tv1.setText(points.get(arg2));
        	}
		});
		
	}
	
	public List<Map<String, Object>> getData(){        
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<initimageIds.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", initimageIds.get(i));
            data_list.add(map);
        }
            
        return data_list;
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
		ContextMenuInfo menuInfo = (ContextMenuInfo) item.getMenuInfo();       
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();   
	    final int id = (int)info.id;
		switch (item.getItemId()) {
		case R.id.context_add:
			str="context_add";//debug			
			process_add();			
			break;
			
		case R.id.context_replay:
			str="context_replay";//debug
			
			builder=new AlertDialog.Builder(this);
			diaglogLayout=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog, null,false);
			gv=(GridView) diaglogLayout.findViewById(R.id.gridView1);
			gv.setOnItemClickListener(new GridView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					gradchoose=arg2;
					Toast.makeText(MainActivity.this, "选中图片: "+(arg2+1), Toast.LENGTH_SHORT).show();
				}
			
			});
			tv=(TextView) diaglogLayout.findViewById(R.id.textView1);
			ed=(EditText) diaglogLayout.findViewById(R.id.editText1);
			tv.setText("风景名称");
			ed.setText(points.get(id).toString());
			//新建List
	        data_list = new ArrayList<Map<String, Object>>();
	        //获取数据
	        getData();
	        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
	        //配置适配器
	        gv.setAdapter(sim_adapter);	        	        
	        builder.setView(diaglogLayout);
	        builder.setMessage("修改");	 
	        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					points.set(id, ed.getText().toString());
					imageIds.set(id,initimageIds.get(gradchoose));
					gradchoose=0;
					adapter.notifyDataSetChanged();
				}
			});
	        
			builder.create().show();
			Toast.makeText(MainActivity.this, "原有图片: "+(id+1), Toast.LENGTH_SHORT).show();
			break;		
		case R.id.context_del:			
			str="context_del"+id;//debug
			points.remove(id);
			imageIds.remove(id);
			adapter.notifyDataSetChanged();
			img1.setImageResource(imageIds.get(0));
			tv1.setText(points.get(0));
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
			
			process_add();
			
			
			break;
		case R.id.reset:
			Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
			onCreate(null);
			break;
		default:
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}

	private void process_add() {
		// TODO Auto-generated method stub
		
		builder=new AlertDialog.Builder(this);
		diaglogLayout=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog, null,false);
		gv=(GridView) diaglogLayout.findViewById(R.id.gridView1);
		gv.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				gradchoose=arg2;
				Toast.makeText(MainActivity.this, "选中图片: "+(arg2+1), Toast.LENGTH_SHORT).show();
			}
		
		});
		TextView tv=(TextView) diaglogLayout.findViewById(R.id.textView1);
		ed=(EditText) diaglogLayout.findViewById(R.id.editText1);
		tv.setText("风景名称");
		ed.setText("请填写风景名称");
		//新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        //配置适配器
        gv.setAdapter(sim_adapter);	        	        
        builder.setView(diaglogLayout);
        builder.setMessage("添加");	 
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				points.add( ed.getText().toString());
				imageIds.add(initimageIds.get(gradchoose));
				gradchoose=0;
				adapter.notifyDataSetChanged();
			}
		});
        
		builder.create().show();
		Toast.makeText(MainActivity.this, "添加了 "+ed.getText().toString(), Toast.LENGTH_SHORT).show();
		
	}

}
