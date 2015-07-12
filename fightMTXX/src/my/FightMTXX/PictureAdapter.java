package my.FightMTXX;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

    public class PictureAdapter extends BaseAdapter 
    {  
    	private LayoutInflater inflater;  
    	private List<Picture> pictures;  

    	public PictureAdapter(String[] titles, String[] images, Context context) 
    	{  
    		super();  
    		pictures = new ArrayList<Picture>();  
    		inflater = LayoutInflater.from(context);  
    		for (int i = 0; i < images.length; i++) 
    		{  
    			Picture picture = new Picture(titles[i], images[i]);  
    			pictures.add(picture);  
    		}  
    	}  
    	 
    	public int getCount() 
    	{  
    		if (null != pictures) 
    		{  
    			return pictures.size();  
    	    } 
    		else 
    		{  
    	         return 0;  
    	    }  
    	}  
   
    	public Object getItem(int position) 
    	{  
    	    return pictures.get(position);  
    	}  
    	
    	public long getItemId(int position) 
    	{  
    	   return position;  
    	}  

    	public View getView(int position, View convertView, ViewGroup parent) 
    	{  
    		ViewHolder viewHolder;  
    	    if (convertView == null) 
    	    {  
    	    	convertView = inflater.inflate(R.layout.item, null);  
    	    	viewHolder = new ViewHolder();  
//    	    	viewHolder.title = (TextView) convertView.findViewById(R.id.title);  
    	    	viewHolder.image = (ImageView) convertView.findViewById(R.id.image);  
    	    	convertView.setTag(viewHolder);  
    	    } 
    	    else 
    	    {  
    	    	viewHolder = (ViewHolder) convertView.getTag();  
    	    }  
//    	    viewHolder.title.setText(pictures.get(position).getTitle());
    	    String path = "/sdcard/images/" + pictures.get(position).getTitle();
    	    viewHolder.image.setImageBitmap(BitmapFactory.decodeFile(path));  
    	    return convertView;
    	}  
 
    }

	class ViewHolder 
	{  
//	   public TextView title;  
	   public ImageView image;  
	}  