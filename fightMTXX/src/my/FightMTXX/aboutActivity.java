package my.FightMTXX;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class aboutActivity extends Activity{
	
	private TextView contenttxt;
	private TextView authortxt;
	private Button backbtn;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		contenttxt = (TextView)findViewById(R.id.content);
		authortxt = (TextView)findViewById(R.id.author);
		backbtn = (Button)findViewById(R.id.back);
		
		contenttxt.setText("       这是一个图像处理的程序！\n" +
				"       这里有很多的图像效果，你可以根据自己的需要获得想要的图片效果，而且还可以自己画图哦！希望你图像处理的旅程快乐！\n" +
				"\n友情提示：如果你无法选择图片进行处理的话，请在sdcard里添加文件名为 images的文件夹哦！");
		authortxt.setText("作者：\n" +
				"10389140    邱彩珊\n" +
				"10389115      任双  \n" +
				"10389112      叶东  \n");
		
		backbtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {			
				Intent intent = new Intent();
				intent.setClass(aboutActivity.this, FightMTXXActivity.class);
				startActivity(intent);
				finish();
			}			
		});
	}
}