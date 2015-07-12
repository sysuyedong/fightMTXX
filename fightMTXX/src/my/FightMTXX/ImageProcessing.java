package my.FightMTXX;


import java.io.File;
import java.io.IOException;

import my.FightMTXX.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ImageProcessing extends Activity
{
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			Bitmap effectImage = getImageToView(data);
			ImageView imageview = (ImageView)findViewById(R.id.show);
			imageview.setImageBitmap(effectImage);
			effect.setText("�ü�");
		}
	}

	private Button save;
	private Button cancel;
	private TextView effect;
    private ListView list;
    private Button method;
    private String path;
    
	private Bitmap mBitmap;
	private String[] methodlist = 
		{"ԭͼ","��С","�Ŵ�","Բ��","ˮƽ��ת","��ֱ��ת","��ת","��Ӱ","����","��Ƭ",
			"����","ģ��","�ữ","����","��","����","�ü�","ɫ�����Ͷȡ�ɫ�ࡢ����","ͼƬд����"};
	
	

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dip);
        
        /* ��ʾѡ��ͼƬ */
        Bundle bundle = this.getIntent().getExtras();
        final ImageView show = (ImageView)findViewById(R.id.show);
        //�޸�
        path = bundle.getString("path");
        BitmapFactory.Options options = new BitmapFactory.Options();
        final Bitmap bm = BitmapFactory.decodeFile(path,options);
        show.setImageBitmap(bm);
        
        /* ����ͼƬ */
        
        save = (Button)findViewById(R.id.save);
	    save.setOnClickListener(new OnClickListener()
	    {
	    	private String saveFileName;

			public void onClick(View v)
	    	{
	    		saveFileName = null;
	    		AlertDialog.Builder builder = new AlertDialog.Builder(ImageProcessing.this);
	    		LayoutInflater factory = LayoutInflater.from(ImageProcessing.this);
	    		final View textEntryView = factory.inflate(R.layout.input_dialog, null);
	    		builder.setTitle("������ļ���");
	    		builder.setView(textEntryView);
	    		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    				EditText filename = (EditText) textEntryView.findViewById(R.id.filename);
	    				saveFileName = filename.getText().toString();
	    				Bitmap bmp = getPicFromImageView();
	    				try {
							ImageUtil.saveMyBitmap(bmp, saveFileName);
						} catch (IOException e) {
							e.printStackTrace();
						}
	    				Toast.makeText(ImageProcessing.this, "Saved " + saveFileName + ".jpg!", Toast.LENGTH_SHORT).show();
	    			}
	    		});
	    		
	    		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    			}
	    		});
	    		builder.show();
	    	}
	    });
	    
	    cancel = (Button)findViewById(R.id.index);
	    cancel.setOnClickListener(new OnClickListener()
	    {			
			public void onClick(View v)
			{
				ImageCache.clear();
				Intent intent = new Intent();
				intent.setClass(ImageProcessing.this, FightMTXXActivity.class);
				startActivity(intent);
				finish();
			}
		});
	    
        effect = (TextView)findViewById(R.id.effect);
        list = (ListView)findViewById(R.id.list);
        method = (Button)findViewById(R.id.method);
        
        method.setOnClickListener(new OnClickListener()
        {
			private AlertDialog dialog;

			public void onClick(View arg0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ImageProcessing.this);
				
				LayoutInflater inflater = (LayoutInflater)ImageProcessing.this.getSystemService(LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.methodlist, null);
				list = (ListView)view.findViewById(R.id.list);
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(ImageProcessing.this,android.R.layout.simple_list_item_1,methodlist);
                list.setAdapter(adapter);
                
                builder.setView(view);
                dialog = builder.create();
                dialog = builder.show();
                
                list.setOnItemClickListener(new OnItemClickListener()
                {
                	private Bitmap effectimage;
                	
                	public void onItemClick(AdapterView<?> adapterview, View v, int position,
                			long id) {
                		
                		effectimage = bm;
                		
                		if (position == 17) {
                			Bundle bundle = new Bundle();
                			bundle.putString("path", path);
        					Intent intent = new Intent(ImageProcessing.this, ImageToneActivity.class);
        					intent.putExtras(bundle);
        					startActivity(intent);
        				}
                		
                		if (position == 18)
                		{
                			Bundle bundle = new Bundle();
                			bundle.putString("path", path);
        					Intent intent = new Intent(ImageProcessing.this, ImageWriterActivity.class);
        					intent.putExtras(bundle);
        					startActivity(intent);
                		}
      				
        				if (position == 16) {
        					File tempFile = new File(path);
        					startPhotoZoom(Uri.fromFile(tempFile));
        				}
                		
         				String processmethod = list.getItemAtPosition(position).toString();        				
         				
         				mBitmap = getPicFromImageView();				
         				if ( processmethod.equals("ԭͼ") )
         				{
         					effect.setText(processmethod);
         					effectimage = bm;
         				}
         				
         				else if ( processmethod.equals("��С") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.reduceBitmap(mBitmap);
         				}
         				
         				else if ( processmethod.equals("�Ŵ�") )
         				{ 
         					effect.setText(processmethod);
         					effectimage = ImageUtil.enlargeBitmap(mBitmap);
         				}
         				
         				else if ( processmethod.equals("Բ��") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.getRoundedCornerBitmap(mBitmap,(float)150);
         				}
         				
         				else if ( processmethod.equals("ˮƽ��ת") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.reverseBitmap(mBitmap, 0);
         				}
         				
         				else if ( processmethod.equals("��ֱ��ת") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.reverseBitmap(mBitmap, 1);
         				}
         				
         				else if ( processmethod.equals("��ת") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.postRotateBitamp(mBitmap, (float)90);
         				}
         				
         				else if ( processmethod.equals("��Ӱ") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.createReflectionImageWithOrigin(mBitmap);
         				}
         				
         				else if ( processmethod.equals("����") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.oldRemeber(mBitmap);
         				}
         				
         				else if ( processmethod.equals("��Ƭ") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.film(mBitmap);
         				}
         				
         				else if ( processmethod.equals("����") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.sketch(mBitmap);
         				}
         				
         				else if ( processmethod.equals("ģ��") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.blurImage(mBitmap);
         				}
         				
         				else if ( processmethod.equals("�ữ") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.blurImageAmeliorate(mBitmap);
         				}
         				
         				else if ( processmethod.equals("����") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.emboss(mBitmap);
         				}
         				
         				else if ( processmethod.equals("��") )
         				{
         					effect.setText(processmethod);
         					effectimage = ImageUtil.sharpenImageAmeliorate(mBitmap);
         				}
         				
         				else if ( processmethod.equals("����") )
         				{
         					effect.setText(processmethod);
         					effectimage = 	ImageUtil.sunshine(mBitmap, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
         				}
         				
         				ImageView imageview = (ImageView)findViewById(R.id.show);
         				imageview.setImageBitmap(effectimage);
         				
         				dialog.dismiss();
             	   }	   
                });                
			}     	
        });
    }
    
    public Bitmap getPicFromImageView()
    {
    	ImageView iv = (ImageView)findViewById(R.id.show);
		iv.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(iv.getDrawingCache());
		iv.setDrawingCacheEnabled(false);
		
		return bmp;
    }

    /**
	 * �ü�ͼƬ����ʵ��
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// ���òü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 2);
	}

	/**
	 * ����ü�֮���ͼƬ����
	 * 
	 * @param picdata
	 * @return 
	 */
	private Bitmap getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			return photo;
		}
		return null;
	}
	
}