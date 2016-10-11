package com.example.spoc_arrayadapter;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class WuweiMainActivity extends Activity {

	ListView lv;
	int[] picIds={R.drawable.ic_launcher,R.drawable.call3,R.drawable.nocall};
	String[] names={"姓名","吴维","学号"};
	String[] numbers={"","693550","14211160137"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wuwei_main);
        lv=(ListView) findViewById(R.id.listView1);
        
        lv.setAdapter(new myAdapter(this,android.R.layout.simple_list_item_1,names));
        
        
    }

  class myAdapter extends ArrayAdapter{

	public myAdapter(Context context, int resource,Object[] objects) {
		super(context, resource,objects);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(WuweiMainActivity.this).inflate(R.layout.wuwei_adapterstyle,null, false);
			//通过LayoutInflater.inflate实现布局xml的动态view生成
			// 第一个参数为样式的xml文件索引，第二个参数为父视图，可以是null，也可以是parent
			//  第三个参数 false 改成true，会出错
		}
		ImageView iv1=(ImageView)convertView.findViewById(R.id.imageView1);
		ImageView iv2=(ImageView)convertView.findViewById(R.id.imageView2);
		TextView tv1=(TextView) convertView.findViewById(R.id.textView1);
		TextView tv2=(TextView) convertView.findViewById(R.id.textView2);
		// 必须是convertView.findViewById,若是直接用findViewById会出错
		iv1.setImageResource(picIds[0]);//挂头像
		
		tv1.setText(names[position]);//挂名称
		if(numbers[position]=="" || numbers[position].length()<=2){
			iv2.setImageResource(picIds[2]);//没有号码
		}
		else{
			iv2.setImageResource(picIds[1]);
		}
		tv2.setText(numbers[position]);
		return convertView;
		
	
	}
  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wuwei_main, menu);
        return true;
    }
    
}

