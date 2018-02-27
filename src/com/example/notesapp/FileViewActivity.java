package com.example.notesapp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FileViewActivity extends Activity {
	private TextView txtProduct ;
	private String filename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_view);
		txtProduct = (TextView) findViewById(R.id.textView1);
		txtProduct.setMovementMethod(ScrollingMovementMethod.getInstance());
		Intent i = getIntent();
        // getting attached intent data
        filename = i.getStringExtra("FileName");
        System.out.println(filename);
        ReadFile(filename);
        
        
     // Locate the button in activity_main.xml
 		Button button = (Button) findViewById(R.id.button2);
  
 		// Capture button clicks
 		button.setOnClickListener(new OnClickListener() {
 			public void onClick(View arg0) {
 				File file = new File(getApplicationContext().getFilesDir(), filename);
 				boolean deleted = file.delete();
 				
 				if (deleted){
 					AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(FileViewActivity.this);
		 
		            // Setting Dialog Title
		            alertDialog1.setTitle("Deletion");
		 
		            // Setting Dialog Message
		            alertDialog1.setMessage("File deletion successful");
		 
		            // Setting Icon to Dialog
		        //    alertDialog1.setIcon(R.drawable.tick);
		 
		            // Setting OK Button
		            alertDialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		 
		                public void onClick(DialogInterface dialog, int which) {
		                    // Write your code here to execute after dialog
		                    // closed
		                	Intent myIntent = new Intent(getApplicationContext(),
		     						MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		     				startActivity(myIntent);
		     				finish();
		                }
		            });
		            
		            AlertDialog alertDialog = alertDialog1.create();
		            alertDialog.show();
					}
 				
 				// Start NewActivity.class
 				
 			}
 		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_view, menu);
		return true;
	}
	
	public void ReadFile(String fileName){
		
	    File file = new File(getApplicationContext().getFilesDir(), fileName);
		String myData="";
		try {
		    FileInputStream fis = new FileInputStream(file);
		    DataInputStream in = new DataInputStream(fis);
		    BufferedReader br = 
		     new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    while ((strLine = br.readLine()) != null) {
		     myData = myData + strLine;
		     myData = myData + "\n";
		    }
		    in.close();
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		   
		
		
		
		txtProduct.setText(myData.toString());
	}

}
