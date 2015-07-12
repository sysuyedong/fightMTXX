package my.FightMTXX;

class Picture
{
	private String title;
	private String imageId;
	
	public Picture()
	{
		super();
	}
	
	public Picture(String title, String imageId)
	{
		super();
		this.title = title;
		this.imageId = imageId;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getImageId()
	{
		return imageId;
	}
	
	public void setImage(String imageId)
	{
		this.imageId = imageId;
	}
}