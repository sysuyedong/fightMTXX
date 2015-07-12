package my.FightMTXX;


import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Drawimage extends Activity{

	private LinearLayout layout;
	private MyDrawView mView = null;
	private Button ret;
	private Button save;
	private Button drawColor;
	private Button redBtn;
	private Button greenBtn;
	private Button blueBtn;
	private Button clearBtn;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawimage);
        
        layout = (LinearLayout) findViewById(R.id.drawImageLayout);
        ret = (Button) findViewById(R.id.retIndex);
        save = (Button) findViewById(R.id.drawSave);
        drawColor = (Button) findViewById(R.id.drawColor);
        clearBtn = (Button) findViewById(R.id.clearDraw);
        
        mView = new MyDrawView(this);
        
        LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mView.setLayoutParams(params);
		mView.setFocusable(true);
		layout.addView(mView);
		
		ret.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Drawimage.this, FightMTXXActivity.class);
				startActivity(i);
				finish();
			}
		});
		
		save.setOnClickListener(new OnClickListener() {
			private String saveFileName;
			
			public void onClick(View v) {
				saveFileName = null;
	    		AlertDialog.Builder builder = new AlertDialog.Builder(Drawimage.this);
	    		LayoutInflater factory = LayoutInflater.from(Drawimage.this);
	    		final View textEntryView = factory.inflate(R.layout.input_dialog, null);
	    		builder.setTitle("保存的文件名");
	    		builder.setView(textEntryView);
	    		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    				EditText filename = (EditText) textEntryView.findViewById(R.id.filename);
	    				saveFileName = filename.getText().toString();
	    				Bitmap bmp = getPicFromImageView();
	    				try {
							ImageUtil.saveMyBitmap(bmp, saveFileName);
						} catch (IOException e) {
							e.printStackTrace();
						}
	    				Toast.makeText(Drawimage.this, "Saved " + saveFileName + ".jpg!", Toast.LENGTH_SHORT).show();
	    			}
	    		});
	    		
	    		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    			}
	    		});
	    		builder.show();
			}
		});
		
		drawColor.setOnClickListener(new OnClickListener() {
			private AlertDialog dialog;
			
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Drawimage.this);
				LayoutInflater inflater = (LayoutInflater)Drawimage.this.getSystemService(LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.colorlist, null);
				builder.setView(view);
				dialog = builder.create();
				dialog = builder.show();
				
				redBtn = (Button) view.findViewById(R.id.redBtn);
				greenBtn = (Button) view.findViewById(R.id.greenBtn);
				blueBtn = (Button) view.findViewById(R.id.blueBtn);
				
				redBtn.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						mView.setColor(Color.RED);
						dialog.dismiss();
					}
				});
				
				greenBtn.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						mView.setColor(Color.GREEN);
						dialog.dismiss();
					}
				});
				
				blueBtn.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						mView.setColor(Color.BLUE);
						dialog.dismiss();
					}
				});
			}
		});
		
		clearBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				mView.clear();
			}
		});
    }
    
    private Bitmap getPicFromImageView(){
    	return mView.getBitmap();
    }
}
