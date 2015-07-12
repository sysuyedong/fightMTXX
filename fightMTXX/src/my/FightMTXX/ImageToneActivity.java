package my.FightMTXX;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ImageToneActivity extends Activity implements OnSeekBarChangeListener {  
    private ToneLayer mToneLayer;  
    private ImageView mImageView;  
    private Bitmap mBitmap;
	private Button cancel;
	private Button save;  
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.tone); 
        
        Bundle bundle = this.getIntent().getExtras();
        String path = bundle.getString("path");
        init(path);  
        
        save = (Button)findViewById(R.id.save);
	    save.setOnClickListener(new OnClickListener()
	    {
	    	private String saveFileName;

			public void onClick(View v)
	    	{
	    		saveFileName = null;
	    		AlertDialog.Builder builder = new AlertDialog.Builder(ImageToneActivity.this);
	    		LayoutInflater factory = LayoutInflater.from(ImageToneActivity.this);
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
	    				Toast.makeText(ImageToneActivity.this, "Saved " + saveFileName + ".jpg!", Toast.LENGTH_SHORT).show();
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
				finish();
			}
		});
    }  
      
    private void init(String path)  
    {  
        mToneLayer = new ToneLayer(this);  
        
        BitmapFactory.Options options = new BitmapFactory.Options();
        mBitmap = BitmapFactory.decodeFile(path,options);
       
        mImageView = (ImageView) findViewById(R.id.imageview);  
        mImageView.setImageBitmap(mBitmap);  
        
        ((LinearLayout) findViewById(R.id.tone_view)).addView(mToneLayer.getParentView());  
        ArrayList<SeekBar> seekBars = mToneLayer.getSeekBars();  
        for (int i = 0, size = seekBars.size(); i < size; i++)  
        {  
            seekBars.get(i).setOnSeekBarChangeListener(this);  
        }  
    }  
  
    public void onProgressChanged(SeekBar seekBar, int progress,  
            boolean fromUser) {  
        int flag = (Integer) seekBar.getTag();  
        switch (flag)  
        {  
        case ToneLayer.FLAG_SATURATION:  
            mToneLayer.setSaturation(progress);  
            break;  
        case ToneLayer.FLAG_LUM:  
            mToneLayer.setLum(progress);  
            break;  
        case ToneLayer.FLAG_HUE:  
            mToneLayer.setHue(progress);  
            break;  
        }  
          
        mImageView.setImageBitmap(mToneLayer.handleImage(mBitmap, flag));  
    }  
  
    public void onStartTrackingTouch(SeekBar seekBar) {  
          
    }  
    
    public void onStopTrackingTouch(SeekBar seekBar) {  
          
    }  
    
    public Bitmap getPicFromImageView()
    {
    	ImageView iv = (ImageView)findViewById(R.id.imageview);
		iv.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(iv.getDrawingCache());
		iv.setDrawingCacheEnabled(false);
		
		return bmp;
    }
}  
