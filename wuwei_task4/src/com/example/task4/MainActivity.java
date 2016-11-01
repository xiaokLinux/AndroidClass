package com.example.task4;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.R.layout;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Element;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnScrollListener {

	Button bt1;
	ListView lv1;
	ArrayList<String> urls;
	ArrayList<String> datas;
	ArrayList<String> dates;
	Handler handler;
	int loaded;//视图中已经加载到多少了
	ProgressBar pg1;
	View moreView;
	TextView tv1;
	
	//数据处理相关
	Handler handler2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);
		handler2=new Handler();
		moreView=LayoutInflater.from(this).inflate(R.layout.more_data, null);
		tv1=(TextView) moreView.findViewById(R.id.textView1);
		pg1 = (ProgressBar) moreView.findViewById(R.id.progressBar1);
		
		bt1=(Button) findViewById(R.id.button1);
		lv1=(ListView) findViewById(R.id.listView1);
		lv1.setOnScrollListener(this);
		lv1.setOnItemClickListener(new OnItemClickListener() {
			
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Uri uri;
				Intent i;
				uri=Uri.parse(urls.get(arg2));
				i=new Intent(Intent.ACTION_VIEW,uri);
				
				startActivity(i);
			}
		});
		handler=new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message arg0) {
				// TODO Auto-generated method stub
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, datas);
				lv1.setAdapter(adapter);
				return false;
			}
		});
		bt1.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Thread thread=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						doUrlLoad();
					}
				});
				
				thread.start();
				
			}
			});
	}
		private void doUrlLoad() {
			// TODO Auto-generated method stub
			String url="http://jwc.wzu.edu.cn/Col/Col55/Index.aspx";
			
			
			try {
				Document doc=Jsoup.connect(url).timeout(7000).get();
				
				Log.e("test","--------------doc.html------");
				Log.e("test", doc.html());
				
				Log.e("test","--------------doc.html------");
				Log.e("test", doc.text());
				
				Elements elements=doc.getElementsByAttributeValue("class","newslist");
//				elements.first();
				org.jsoup.nodes.Element element=elements.get(0);
				Elements elements2=element.select("li");
				urls=new ArrayList<String>();
				dates=new ArrayList<String>();
				datas=new ArrayList<String>();
				String urlHead="http://jwc.wzu.edu.cn";
				for(int i=0;i<elements2.size();i++)
				{
					urls.add(urlHead+elements2.get(i).select("A").first().attr("href").toString());
					dates.add(elements2.get(i).select("span").get(0).text());
					datas.add(elements2.get(i).select("span").get(1).text());
//					dates.add(elements2.get(i).text());
//					datas.add(elements2.get(i+1).text());
//					Log.e("li:"+i, elements2.get(i).html());					
				}
				
				handler.sendEmptyMessage(0);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		//agg1第一个看见的 元素索引
		//arg2满屏能容纳多少行
		//arg3总共有多少
		loaded=arg1+arg2;
	}
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		if(arg1==OnScrollListener.SCROLL_STATE_IDLE && loaded<datas.size())
		{
			Toast.makeText(MainActivity.this, "Load./.", Toast.LENGTH_SHORT).show();
			loadData(arg0);
		}
	}
	void loadData(View arg0) {
		// TODO Auto-generated method stub
		pg1.setVisibility(View.VISIBLE);
		tv1.setVisibility(View.GONE);
		handler2.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loadMoreData();//加载更多数据
				pg1.setVisibility(View.GONE);
				tv1.setVisibility(View.VISIBLE);
				
			}

			private void loadMoreData() {
				// TODO Auto-generated method stub
				
			}
		}, 1000);
	}

}
