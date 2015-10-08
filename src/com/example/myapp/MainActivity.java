package com.example.myapp;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
	private TextToSpeech t1;
	private EditText mess;
	private Button extract;
	private Button stop;
	int result;
	
	String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mess=(EditText)findViewById(R.id.editText1);
        t1=new TextToSpeech(MainActivity.this,new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				if(status==TextToSpeech.SUCCESS)
				{
					result=t1.setLanguage(Locale.UK);
				}
				else
				{
					Toast.makeText(getApplicationContext(),"feature not supported",Toast.LENGTH_SHORT).show();
					
				}
			}
		});
        extract = (Button) findViewById(R.id.button1);
        extract.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA)
					{
						Toast.makeText(getApplicationContext(),"feature not supported",Toast.LENGTH_SHORT).show();
						
						
					}
					else
					{
						text=mess.getText().toString();
						t1.speak(text, TextToSpeech.QUEUE_FLUSH,null);
					}
				}
		});
        stop = (Button)findViewById(R.id.button2);
        stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(t1!=null)
				{
					t1.stop();
				}
			}
		});
}
  
    protected Toast makeText(Context applicationContext, String string,
			int lengthShort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
