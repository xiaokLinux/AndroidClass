package com.example.task1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv2,tvcolor,tvseq;
	EditText ed1;
	Button button;
	RadioGroup rg;
	CheckBox cb1,cb2,cb3;
	boolean mark1,mark2,mark3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wuwei_main);
        
        ed1=(EditText) findViewById(R.id.editText1);
        tv2=(TextView) findViewById(R.id.textView2);//显示信息用
        tvcolor=(TextView) findViewById(R.id.textView3);
        tvseq=(TextView) findViewById(R.id.textView4);
        button=(Button) findViewById(R.id.button1);
        rg=(RadioGroup) findViewById(R.id.radioGroup1);
        cb1=(CheckBox) findViewById(R.id.checkBox1);
        cb2=(CheckBox) findViewById(R.id.checkBox2);
        cb3=(CheckBox) findViewById(R.id.checkBox3);
        button.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str;
				str="";
				str=ed1.getText().toString();
				tv2.setText(str);
			}
		});
        
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				String str;
				str="color:";
				switch (arg1) {
				case R.id.radio0:
					str+="red";
					break;
				case R.id.radio1:
					str+="green";
					break;
				case R.id.radio2:
					str+="blue";
					break;
				default:
					break;
				}
				tvcolor.setText(str);
			}
		});
        
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg0.isChecked())
				{
					mark1=true;
				}
				else
				{
					mark1=false;
				}
				String str;
				str="sequence:";
				str+=mark1?cb1.getText().toString():"";
				str+=" , ";
				str+=mark2?cb2.getText().toString():"";
				str+=" , ";
				str+=mark3?cb3.getText().toString():"";
				str+=" , ";
				tvseq.setText(str);
			}
		});
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg0.isChecked())
				{
					mark2=true;
				}
				else
				{
					mark2=false;
				}
				String str;
				str="sequence:";
				str+=mark1?cb1.getText().toString():"";
				str+=" , ";
				str+=mark2?cb2.getText().toString():"";
				str+=" , ";
				str+=mark3?cb3.getText().toString():"";
				str+=" , ";
				tvseq.setText(str);
			}
		});
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg0.isChecked())
				{
					mark3=true;
				}
				else
				{
					mark3=false;
				}
				String str;
				str="sequence:";
				str+=mark1?cb1.getText().toString():"";
				str+=" , ";
				str+=mark2?cb2.getText().toString():"";
				str+=" , ";
				str+=mark3?cb3.getText().toString():"";
				str+=" , ";
				tvseq.setText(str);
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
