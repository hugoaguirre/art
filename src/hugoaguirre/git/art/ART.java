package hugoaguirre.git.art;

public class ART 
{
	private static ART INSTANCE = null;
	private static int classes;
	private static int img_width, img_height;
	private static double[][][] weights;
	private double[] weights_values;
	private static int[][][] boxes;
	
	private int[][] tmp_image;
	
	private GUI gui;
	
	
	private ART(int classes, int img_width, int img_height, GUI gui)
	{
		this.gui = gui;
		this.classes = classes;
		this.img_width = img_width;
		this.img_height = img_height;
		this.weights_values = new double[classes];
		this.weights = new double[classes][img_width][img_height];
		this.boxes = new int[classes][img_width][img_height];
		init_mtxs();
	}
	
	public static ART getInstance(int classes, int img_width, int img_height, GUI gui)
	{
		if(ART.INSTANCE == null)
			INSTANCE = new ART(classes, img_width, img_height, gui);
			
		return INSTANCE;
	}
	
	public void getImage(int[][] gui_image)
	{
		this.tmp_image = gui_image;
	}
	
	public void start()
	{
		set_weight_values();
		int index = get_max_index();
		double boxes_sum = 0;
		
		for(int i = 0; i<img_width; i++)
		{
			for(int j = 0; j<img_height; j++)
			{
				if(tmp_image[i][j] == 1 && boxes[index][i][j] == 1)
				{
					boxes[index][i][j] = 1;
					boxes_sum += 1;
				}
				else
					boxes[index][i][j] = 0;
				System.out.print(boxes[index][i][j] + "\t");
			}
			System.out.println("");
		}
		boxes_sum += 0.5;
		System.out.println("MAX: " + index);
		System.out.println("Boxes sum: " + boxes_sum);
		
		for(int i = 0; i<img_width; i++)
		{
			for(int j = 0; j<img_height; j++)
			{
				weights[index][i][j] = (double) boxes[index][i][j] / boxes_sum;
			}
		}
		
		gui.paint_image(index, boxes[index]);
		
		
	}
	
	private void init_mtxs()
	{
		for(int k = 0; k<classes; k++)
			for(int i = 0; i<img_width; i++) { 
				for(int j = 0; j<img_height; j++)
				{
					weights[k][i][j] = (double) 1/(img_width * img_height);
					boxes[k][i][j] = 1;
				}
			}
	}
	
	private void set_weight_values()
	{
		double sum;
		for(int k = 0; k<classes; k++)
		{
			sum = 0;
			for(int i = 0; i<img_width; i++)
			{
				for(int j = 0; j<img_height; j++)
				{
					if(tmp_image[i][j] == 1) sum += weights[k][i][j];
				}
			}
			weights_values[k] = sum;
			System.out.println("M= " + k + " " + weights_values[k]); 
		}
	}
	
	private int get_max_index()
	{
		double max = 0;
		int index = 0;
		for(int i = 0; i<classes; i++)
		{
			if(weights_values[i] > max) {
				max = weights_values[i];
				index = i;
			}
		}
		return index;
		
	}
}
