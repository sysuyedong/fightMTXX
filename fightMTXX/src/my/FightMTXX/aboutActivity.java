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
		
		contenttxt.setText("       ����һ��ͼ����ĳ���\n" +
				"       �����кܶ��ͼ��Ч��������Ը����Լ�����Ҫ�����Ҫ��ͼƬЧ�������һ������Լ���ͼŶ��ϣ����ͼ������ó̿��֣�\n" +
				"\n������ʾ��������޷�ѡ��ͼƬ���д���Ļ�������sdcard������ļ���Ϊ images���ļ���Ŷ��");
		authortxt.setText("���ߣ�\n" +
				"10389140    ���ɺ\n" +
				"10389115      ��˫  \n" +
				"10389112      Ҷ��  \n");
		
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