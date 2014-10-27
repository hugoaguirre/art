package hugoaguirre.git.art;

public class Box 
{
	private int width;
	private int height;
	private int[][] box;
	
	public Box(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.box = new int[this.width][this.height];
	}
	
	public int[][] getContent()
	{
		return this.box;
	}
	
	public void setContent(int[][] newBox)
	{
		this.box = newBox;
	}
}
