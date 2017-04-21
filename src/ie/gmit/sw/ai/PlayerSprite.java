package ie.gmit.sw.ai;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ie.gmit.sw.ai.Node;
import ie.gmit.sw.ai.PlayerNode;
import ie.gmit.sw.ai.traversers.DepthLimitedDFSTraversator;

public class PlayerSprite extends Sprite implements Runnable{
	
	private PlayerNode player;
	private Node[][] maze;
	private int row;
	private int col;
	public PlayerSprite(String name, String... images) throws Exception{
		super(name, images);
		
	}
	
	public PlayerSprite(Node[][] maze, PlayerNode player, int row, int col){
		super();
		this.player = player;
		this.maze = maze;
		this.row = row;
		this.col = col;
	}

	@Override
	public void run() {
		
		System.out.println("Sprite is moving");
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
}
