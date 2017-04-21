package ie.gmit.sw.ai;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ie.gmit.sw.ai.traversers.DepthLimitedDFSTraversator;

public class SpiderSprite extends Sprite implements Runnable{
	private PlayerNode p1;
	private Node[][] maze;
	private int row; 
	private int col;
	private double strength;
	private int id;
	
	public SpiderSprite(String name, String... images) throws Exception{
		super(name, images);
	}
	
	public SpiderSprite(Node[][] maze, PlayerNode p1, int row, int col, double strength, int id){
		super();
		this.p1 = p1;
		this.maze = maze;
		this.row = row;
		this.col = col;
		this.strength = strength;
		this.setId(id);
	}

	@Override
	public void run() {
		
		System.out.println("Sprite is moving");
		DepthLimitedDFSTraversator dt = new DepthLimitedDFSTraversator(10, this, p1);		
		dt.traverse(maze, maze[row][col]);

	}
	
	public void moveSprite(int newX, int newY) throws InterruptedException {
		if (maze[newX][newY].getNodeType() != '0') {
			maze[this.row][this.col].setNodeType('\u0020');
			maze[newX][newY].setNodeType('\u0036');
			this.row = newX;
			this.col = newY;
			System.out.println("row : " + newX);
			System.out.println("row : " + newY);
			
		}
		
	}
	
	public void engageFuzzy(){
		FuzzyEngageable ef = new FuzzyEngageable();
		p1.setHealth(ef.fight(p1.getSwordPower(), p1.getHealth(), this.strength));
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
