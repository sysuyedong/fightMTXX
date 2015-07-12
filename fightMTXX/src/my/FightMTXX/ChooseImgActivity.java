package my.FightMTXX;

import java.io.File;

import my.FightMTXX.PictureAdapter;

import my.FightMTXX.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseImgActivity extends Activity
{
	private GridView image;	
	private Button cancel;
	
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.image_list);
	    
        final String[] titles = getImageNames("/sdcard/images/");
        final String[] imagePaths = new String[titles.length];
        for (int i = 0; i < titles.length; i++)
        {
        	imagePaths[i]="/sdcard/images/" + titles[i];
        }
        
        image = (GridView)findViewById(R.id.gridview);
        PictureAdapter adapter = new PictureAdapter(titles, imagePaths, this);  
        image.setAdapter(adapter);

        image.setOnItemClickListener(new OnItemClickListener()
        {       	
        	public void onItemClick( AdapterView<?> parent, View v, int position, long id)
        	{
        		Bundle bundle = new Bundle();
        		bundle.putString("path", imagePaths[position]);
        		bundle.putString("name", titles[position]);
	    		Intent intent = new Intent();
	    		intent.setClass(ChooseImgActivity.this, ImageProcessing.class);
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		finish();
        	}
        }
        );
        
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new OnClickListener()
        {			
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(ChooseImgActivity.this, FightMTXXActivity.class);
				startActivity(intent);
				finish();
			}
		});
        
	}
	
	public static String[] getImageNames(String folderPath)
	{
		File file = new File(folderPath);
		String[] images = file.list();
		return images;
	}
}