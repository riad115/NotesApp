package com.example.notesapp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewFileActivity extends Activity {
	
	private EditText txtName;
	private EditText txtContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_file);
		
	    txtName = (EditText) findViewById(R.id.textNew);
	    txtContent = (EditText) findViewById(R.id.textNewContent);
		txtContent.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		Button button = (Button) findViewById(R.id.buttonSave);
	      
 		// Capture button clicks
 		button.setOnClickListener(new OnClickListener() {
 			public void onClick(View arg0) {
 				
 				String name = txtName.getText().toString();
 				String content = txtContent.getText().toString();
 				
 				File file = new File(getApplicationContext().getFilesDir(), name+".txt");
 				try {
 					FileOutputStream fos = new FileOutputStream(file);
 				    fos.write(content.getBytes());
 				    fos.close();
 				   } catch (IOException e) {
 				    e.printStackTrace();
 				   }
 				
 				AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(NewFileActivity.this);
 				 
	            // Setting Dialog Title
	            alertDialog1.setTitle("Save");
	 
	            // Setting Dialog Message
	            alertDialog1.setMessage("File saved successful");
	 
	            // Setting Icon to Dialog
	        //    alertDialog1.setIcon(R.drawable.tick);
	 
	            // Setting OK Button
	            alertDialog1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	 
	                public void onClick(DialogInterface dialog, int which) {
	                    // Write your code here to execute after dialog
	                    // closed
	                	Intent intent = new Intent(getApplicationContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		         //based on item add info to intent
	    		         startActivity(intent);
	    		         finish();
	                }
	            });
	            
	            AlertDialog alertDialog = alertDialog1.create();
	            alertDialog.show();
 				
 				// Start NewActivity.class
 				
 				
 			}
 		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_file, menu);
		return true;
	}

}
