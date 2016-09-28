package generateMap;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapUI extends JFrame {

	int[][] grid = new int[configure.row][configure.column];// 列数
	int[][] highway = new int[configure.row][configure.column];
	int direction;
	int endpoint_x;
	int endpoint_y;
	int highway_length;
	Random r = new Random();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MapUI().init();
	}

	public void init() {

		for (int i = 0; i < configure.row; i++){
			for (int j = 0; j < configure.column; j++) {
				grid[i][j] = 0;
				highway[i][j] = 0;
				
			}
		}

		this.setTitle("Map");
		this.setSize(1100, 780);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		//
		// this.setLayout(new BorderLayout());
		//
		// JPanel east = new JPanel();
		// east.setPreferredSize(new Dimension(125, 150));
		//// east.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		//
		// // 实例化一个注册按钮
		// Button blocked = new Button("createBlocked");
		//// register.setFont(new Font("宋体", Font.BOLD, 22));
		// // 设置按钮的动作命令
		// blocked.addActionListener(new BlockedListener(grid));
		//
		// east.add(blocked);
		//
		// // 实例化一个注册按钮
		// Button highway = new Button("createHighway");
		//// register.setFont(new Font("宋体", Font.BOLD, 22));
		// // 设置按钮的动作命令
		// highway.addActionListener(new HighwayListener(grid));
		//
		// east.add(highway);
		//
		// // 实例化一个注册按钮
		// Button Hardtraverse = new Button("createHardtraverse");
		//// register.setFont(new Font("宋体", Font.BOLD, 22));
		// // 设置按钮的动作命令
		// Hardtraverse.addActionListener(new HardtraverseListener(grid));
		//
		// east.add(Hardtraverse);
		//
		// this.add(east, BorderLayout.EAST);
		this.setVisible(true);
	}

	public void paint(Graphics g) {

		createHardtraverse();
		createBlocked();
		// 调用父类的重绘方法
		super.paint(g);
		// draw the grid cells
		for (int i = 0; i < configure.row; i++)
			for (int j = 0; j < configure.column; j++) {
				// System.out.println(configure.grid[i][j]+" ");
				if (grid[i][j] == 0) {
					g.setColor(Color.gray);
					g.drawRect(configure.X + configure.size * j, configure.Y + configure.size * i, configure.size,
							configure.size);
				}
				if (grid[i][j] == 1) {
					g.setColor(Color.gray);
					g.fillRect(configure.X + configure.size * j, configure.Y + configure.size * i, configure.size,
							configure.size);
				}
				if (grid[i][j] == 2) {
					g.setColor(Color.darkGray);
					g.fillRect(configure.X + configure.size * j, configure.Y + configure.size * i, configure.size,
							configure.size);
				}
				// if (grid[i][j] == 3) {
				// g.setColor(Color.darkGray);
				// g.fillRect(configure.X + configure.size * j, configure.Y +
				// configure.size * i, configure.size,
				// configure.size);
				// }
			}

	}

	public void createBlocked() {

		for (int i = 0; i < configure.row; i++) {
			for (int j = 0; j < configure.column; j++) {
				if (grid[i][j] != 3 && isblocked()) {
					grid[i][j] = 2;
				}
			}
		}

	}

	public void createHardtraverse() {

		for (int k = 0; k < 8; k++) {
			int y = r.nextInt(configure.row);
			int x = r.nextInt(configure.column);

			int left_y = 0;
			int right_y = 0;
			int left_x = 0;
			int right_x = 0;

			if (y < 16) {
				left_y = 0;
				right_y = y + 16;
			} else if (y > configure.row - 16) {
				left_y = y - 15;
				right_y = configure.row;
			} else {
				left_y = y - 15;
				right_y = y + 16;
			}

			if (x < 16) {
				left_x = 0;
				right_x = x + 16;
			} else if (x > configure.column - 16) {
				left_x = x - 15;
				right_x = configure.column;
			} else {
				left_x = x - 15;
				right_x = x + 16;
			}
			for (int i = left_y; i < right_y; i++) {
				for (int j = left_x; j < right_x; j++) {
					if (grid[i][j] != 1 && ishard()) {
						grid[i][j] = 1;
					}
					// System.out.print(grid[i][j]+" aaaaa ");
				}
			}

		}

	}

	public void initHighway() {

		//0-4 means up, right, down, left seperately
		int side = r.nextInt(4);
		int x;
		int y;
		highway_length = 20;
		
		//highway build from upper side
		if (side == 0) {
			x = r.nextInt(configure.column);
			for (int i = 0; i <= 20; i++)
				highway[i][x] = 3;
			direction = 2; 
			endpoint_y = 20;
			endpoint_x = x;
		}

		// highway start from right side
		if (side == 1) {
			y = r.nextInt(configure.row);
			for (int i = configue.column-1; i >= configue.column-20; i--)
				highway[y][i] = 3;
			direction = 3;
			endpoint_y = y;
			endpoint_x = 140;
		}

		// highway start from bottom
		if (side == 2) {
			x = r.nextInt(configure.column);
			for (int i = configue.row-1; i <= configue.row-20; i--)
				highway[i][x] = 3;
			direction = 4;
			endpoint_y = 100;
			endpoint_x = x;
		}

		// highway starts from left side
		if (side == 3) {
			for (int i = 0; i <20 ; i++)
				highway[y][i] = 3;
			direction = 1;
			endpoint_y = y;
			endpoint_x = 19;
		}
	}

	public void createHighway(){
		//Check if a highway reach an boundry
		if(endpoint_y == 0||endpoint_y == configure.row-1||endpoint_x==0 ||endpoint_x==configure.column-1){
			if(highway_length<100); //recall function to be continued 
			return;
		} 

		// If a highway doesn't reach the boundry
		int p;
		p = r.nextInt(5);

		// change direction according to the possibilities
		if(p == 0 || p == 1 || p == 2);
		if(p == 3){direction = (direction - 1) mod 4;} 
		if(p == 4){direction = (direction + 1) mod 4;}

		if (direction == 0)



	} 

	/**
	 * 
	 * @return
	 */
	private boolean ishard() {
		if (r.nextInt(2) == 1)
			return true;
		else
			return false;
	}

	private boolean isblocked() {
		if (r.nextInt(5) == 0)
			return true;
		else
			return false;
	}
}
