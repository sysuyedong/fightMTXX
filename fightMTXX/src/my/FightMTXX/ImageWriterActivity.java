package my.FightMTXX;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageWriterActivity extends Activity{

	private Button cancel;
	private Button save;
	private Bitmap mBitmap;
	private ImageView writerimage;
	private Button ok;
	private EditText txt;
	private EditText y;
	private EditText x;

	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writer);
        
        Bundle bundle = this.getIntent().getExtras();
        String path = bundle.getString("path");
        
        BitmapFactory.Options options = new BitmapFactory.Options();
        mBitmap = BitmapFactory.decodeFile(path,options);
        
        writerimage = (ImageView) findViewById(R.id.writerimage);  
        writerimage.setImageBitmap(mBitmap);  
        
        save = (Button)findViewById(R.id.save);
	    save.setOnClickListener(new OnClickListener()
	    {
	    	private String saveFileName;

			public void onClick(View v)
	    	{
	    		saveFileName = null;
	    		AlertDialog.Builder builder = new AlertDialog.Builder(ImageWriterActivity.this);
	    		LayoutInflater factory = LayoutInflater.from(ImageWriterActivity.this);
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
	    				Toast.makeText(ImageWriterActivity.this, "Saved " + saveFileName + ".jpg!", Toast.LENGTH_SHORT).show();	   
	    			}
	    		});
	    		
	    		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    			}
	    		});
	    		builder.show();
	    	}
	    });
	    
	    cancel = (Button)findViewById(R.id.cancel);
	    cancel.setOnClickListener(new OnClickListener()
	    {			
			public void onClick(View v)
			{
				writerimage.setImageBitmap(mBitmap);
			}
		});
    
	    txt = (EditText)findViewById(R.id.content);
	    x = (EditText)findViewById(R.id.x);
	    y = (EditText)findViewById(R.id.y);
	    txt.setTextColor(Color.BLACK);
	    x.setTextColor(Color.BLACK);
	    y.setTextColor(Color.BLACK);
	    
        ok = (Button)findViewById(R.id.submit);  
        ok.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v) {
				String content = txt.getText().toString();
				String xdir = x.getText().toString();
				String ydir = y.getText().toString();
				int xvalue = strtoint(xdir);
				int yvalue = strtoint(ydir);
				Bitmap bitmap = getPicFromImageView();
				Bitmap bm = ImageUtil.drawText(bitmap, content, xvalue, yvalue); 
				writerimage.setImageBitmap(bm);
			}
        });
    }  

	private Bitmap getPicFromImageView()
    {
    	ImageView iv = (ImageView)findViewById(R.id.writerimage);
		iv.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(iv.getDrawingCache());
		iv.setDrawingCacheEnabled(false);
		
		return bmp;
    }
	
	public int strtoint( String s)
	{
		double value = 0;
		int j = s.length()-1;
		for ( int i = 0; i < s.length(); i++ )
		{
			char c = s.charAt(i);
			value = value + Math.pow(10,j)*(c-48);
			while ( j > 0)
			{
				j--;
			}
		}
		return (int)value;
	}
}