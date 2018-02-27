package com.example.notesapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ListView list;
    private List<String> List_file;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List_file =new ArrayList<String>();
        list = (ListView)findViewById(R.id.listview);
 
        CreateListView();
        
     // Locate the button in activity_main.xml
     		Button button = (Button) findViewById(R.id.button1);
      
     		// Capture button clicks
     		button.setOnClickListener(new OnClickListener() {
     			public void onClick(View arg0) {
      
     				// Start NewActivity.class
     				Intent myIntent = new Intent(MainActivity.this,
     						NewFileActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     				startActivity(myIntent);
     			}
     		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void CreateListView()
    {
         //List_file.add("Coderzheaven");
         //List_file.add("Google");
         //List_file.add("Android");
         //List_file.add("iPhone");
         //List_file.add("Apple");

		File dirFiles = getApplicationContext().getFilesDir();
		for (String strFile : dirFiles.list())
		{
			List_file.add(strFile.replace(".txt", ""));
		}
         //Create an adapter for the listView and add the ArrayList to the adapter.
         list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List_file));
         
         list.setOnItemClickListener(new OnItemClickListener(){
        	 
         
         public void onItemClick(AdapterView<?>adapter,View v, int position, long id){

        	 String sText = ((TextView) v).getText().toString();
        	 System.out.println(sText);

         Intent intent = new Intent(getApplicationContext(),FileViewActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         //based on item add info to intent
         intent.putExtra("FileName", sText+".txt");
         startActivity(intent);

         }

		

		
         });
         
         
    }
}
