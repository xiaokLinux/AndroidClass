package com.example.spoc_spinner;

import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class WuweiMainActivity extends Activity {

	Spinner sp1,sp2;
	TextView tv;
	Resources res;
	TypedArray sizes,colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wuwei_main);
        
        sp1=(Spinner) findViewById(R.id.spinner1);
        sp2=(Spinner) findViewById(R.id.Spinner2);
        tv=(TextView) findViewById(R.id.textView1);
        res=this.getResources();
        sizes=res.obtainTypedArray(R.array.sizevalues);
        colors=res.obtainTypedArray(R.array.coloevalues);
        
        sp1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
        	//字体大小控制
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				tv.setTextSize(sizes.getDimension(arg2, 0));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
       
        sp2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

        	//颜色控制
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				tv.setTextColor(colors.getColor(arg2, 0));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wuwei_main, menu);
        return true;
    }
    
}
