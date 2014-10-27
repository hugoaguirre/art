package hugoaguirre.git.art;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {
	
	private int MTX_WIDTH;
	private int MTX_HEIGHT;
	private int GRID_SEPARATION = 200;
	private int WINDOW_WIDTH = 1200;
	private int WINDOW_HEIGHT = 350;
	
	private JFrame fPrincipal;
	private JButton bDone, bClear;
	private Container cn;
	
	private JLabel[][] label_mtx;
	private JLabel[][] class_mtx1;
	private JLabel[][] class_mtx2;
	private JLabel[][] class_mtx3;
	private JLabel[][] class_mtx4;
	private JLabel[][] class_mtx5;
	
	private ART art;
	private int[][] box;
	private int i, j;
	
	public GUI(int classes, int width, int height) {
		
		this.MTX_WIDTH = width;
		this.MTX_HEIGHT = height;
		
		this.fPrincipal = new JFrame("ART - Adaptive Resonance Theory");
		this.fPrincipal.setBounds(new Rectangle(WINDOW_WIDTH,WINDOW_HEIGHT));
		
		this.label_mtx = new JLabel[MTX_WIDTH][MTX_HEIGHT];
		this.class_mtx1 = new JLabel[MTX_WIDTH][MTX_HEIGHT];
		this.class_mtx2 = new JLabel[MTX_WIDTH][MTX_HEIGHT];
		this.class_mtx3 = new JLabel[MTX_WIDTH][MTX_HEIGHT];
		this.class_mtx4 = new JLabel[MTX_WIDTH][MTX_HEIGHT];
		this.class_mtx5 = new JLabel[MTX_WIDTH][MTX_HEIGHT];
		
		this.bDone = new JButton("Done");
		this.bClear = new JButton("Clear");
		
		this.box = new int[MTX_WIDTH][MTX_HEIGHT];
		this.cn = fPrincipal.getContentPane();
		art = ART.getInstance(classes, width, height, this);
		build_gui();
	}

	private void build_gui() {
		int x = 0, y = 30;
		cn.setLayout(null);
		
		for(i = 0; i<MTX_WIDTH; i++) {
			x = 20;
			for(j = 0; j<MTX_HEIGHT; j++) {
				label_mtx[i][j] = new JLabel();
				label_mtx[i][j].setBounds(x, y, 20, 20);
				label_mtx[i][j].setBackground(new Color(232,167,124));
				label_mtx[i][j].setOpaque(true);
				
				class_mtx1[i][j] = new JLabel();
				class_mtx1[i][j].setBounds(x+GRID_SEPARATION, y, 20, 20);
				class_mtx1[i][j].setBackground(new Color(232,167,124));
				class_mtx1[i][j].setOpaque(true);
				
				class_mtx2[i][j] = new JLabel();
				class_mtx2[i][j].setBounds(x+(GRID_SEPARATION*2), y, 20, 20);
				class_mtx2[i][j].setBackground(new Color(232,167,124));
				class_mtx2[i][j].setOpaque(true);
				
				class_mtx3[i][j] = new JLabel();
				class_mtx3[i][j].setBounds(x+(GRID_SEPARATION*3), y, 20, 20);
				class_mtx3[i][j].setBackground(new Color(232,167,124));
				class_mtx3[i][j].setOpaque(true);
				
				class_mtx4[i][j] = new JLabel();
				class_mtx4[i][j].setBounds(x+(GRID_SEPARATION*4), y, 20, 20);
				class_mtx4[i][j].setBackground(new Color(232,167,124));
				class_mtx4[i][j].setOpaque(true);
				
				class_mtx5[i][j] = new JLabel();
				class_mtx5[i][j].setBounds(x+(GRID_SEPARATION*5), y, 20, 20);
				class_mtx5[i][j].setBackground(new Color(232,167,124));
				class_mtx5[i][j].setOpaque(true);
				
				
				label_mtx[i][j].addMouseListener(new MouseAdapter() {
					int li = i, lj = j;
					@Override
		            public void mouseEntered(MouseEvent evt) {
		                label_mtx[li][lj].setBackground(new Color(182,27,255));
		                box[li][lj] = 1;
		            }
				});
				x+=21;
				this.cn.add(label_mtx[i][j]);
				this.cn.add(class_mtx1[i][j]);
				this.cn.add(class_mtx2[i][j]);
				this.cn.add(class_mtx3[i][j]);
				this.cn.add(class_mtx4[i][j]);
				this.cn.add(class_mtx5[i][j]);
			}
			y += 21;
		}
		
		/* DONE button */
		bDone.setBounds(15, 240, 75, 30);
		bDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("BOX: ");
				for(int i = 0; i<MTX_WIDTH; i++)
					for(int j = 0; j<MTX_HEIGHT; j++)
						System.out.print(box[i][j] + "\t");
					System.out.println("");
				art.getImage(box);
				art.start();
			}
		});
		
		/* CLEAR button */
		bClear.setBounds(90, 240, 75, 30);
		bClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int k = 0; k<MTX_WIDTH; k++)
					for(int l = 0; l<MTX_HEIGHT; l++) {
						label_mtx[k][l].setBackground(new Color(232,167,124));
						box[k][l] = 0;
					}
				art.getImage(box);
			}
		});
		
		cn.add(bDone);
		cn.add(bClear);
		fPrincipal.setVisible(true);
		fPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint_image(int image, int[][] content)
	{
		JLabel[][] tmp = null;
		System.out.println("IndexXxXX: " +image);
		switch(image)
		{
			case 0:
				tmp = class_mtx1.clone();
				break;
			case 1:
				tmp = class_mtx2.clone();
				break;
			case 2:
				tmp = class_mtx3.clone();
				break;
			case 3:
				tmp = class_mtx4.clone();
				break;
			case 4:
				tmp = class_mtx5.clone();
				break;
		}
		
		for(int i = 0; i<MTX_WIDTH; i++)
			for(int j = 0; j<MTX_HEIGHT; j++)
			{
				if(content[i][j] == 1)
					tmp[i][j].setBackground(new Color(255,23,154));
				else
					tmp[i][j].setBackground(new Color(232,167,124));
			}
	}
}
