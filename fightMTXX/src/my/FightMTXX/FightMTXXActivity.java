package my.FightMTXX;

import my.FightMTXX.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FightMTXXActivity extends Activity
{
    private Button choose_img;
	private Button about;
	private Button drawimage;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        choose_img = (Button)findViewById(R.id.choose_img);
        choose_img.setOnClickListener(new OnClickListener()
        {			
			public void onClick(View arg0)
			{
				choose_img.setBackgroundResource(drawable.choose_img);
				
				Intent intent = new Intent();
				intent.setClass(FightMTXXActivity.this, ChooseImgActivity.class);
				startActivity(intent);
				
				choose_img.setBackgroundResource(drawable.choose_img2);				
				finish();
				
			}
		});
        
        drawimage = (Button)findViewById(R.id.draw_image);
        drawimage.setOnClickListener(new OnClickListener()
        {			
			public void onClick(View arg0)
			{
				drawimage.setBackgroundResource(drawable.draw_img);
				
				Intent intent = new Intent();
				intent.setClass(FightMTXXActivity.this, Drawimage.class);
				startActivity(intent);
				
				drawimage.setBackgroundResource(drawable.draw_img2);
			}
		});
        
        about = (Button)findViewById(R.id.about);
        about.setOnClickListener(new OnClickListener()
        {			
			public void onClick(View arg0)
			{
				about.setBackgroundResource(drawable.about_img);
				
				Intent intent = new Intent();
				intent.setClass(FightMTXXActivity.this, aboutActivity.class);
				startActivity(intent);
				
				about.setBackgroundResource(drawable.about_img2);
				
				finish();
				
			}
		});
    }
}