package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.gmit.sw.ai.SpiderSprite;


public class Maze {
	private Node[][] maze;
	private PlayerNode p1;
	private List<SpiderSprite> sprites = new ArrayList<>();	
	
	private ExecutorService ex = Executors.newFixedThreadPool(100);
	
	
	public Maze(int dimension, PlayerNode player){
		maze = new Node[dimension][dimension];
		
		this.p1 = player;
		
		init();
		buildMaze();
		
		int featureNumber = 20;
		addFeature('\u0031', '0', featureNumber); //1 is a sword, 0 is a hedge
		addFeature('\u0032', '0', featureNumber); //2 is help, 0 is a hedge
		addFeature('\u0033', '0', featureNumber); //3 is a bomb, 0 is a hedge
		addFeature('\u0034', '0', featureNumber); //4 is a hydrogen bomb, 0 is a hedge
		
		featureNumber = 30;
		addFeature('\u0036', '0', featureNumber); //6 is a Black Spider, 0 is a hedge

	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row, col);
				maze[row][col].setNodeType('0');
				p1 = getPlayer();
			}
		}
	}
	
	private void addFeature(char feature, char replace, int number){
		int counter = 0;
		while (counter < number){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() == replace){
				maze[row][col].setNodeType(feature);
				if(maze[row][col].getNodeType() == feature){
					maze[row][col].setNodeType(feature);

					if(number > 0){
						SpiderSprite sprite = new SpiderSprite(maze, p1, row, col, 30, counter);
						sprites.add(sprite);
						ex.execute(sprite);
					}
				}
				counter++;
			}
		}
	}
	
	
	private void buildMaze(){ 
		for (int row = 1; row < maze.length - 1; row++){
			for (int col = 1; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num > 5 && col + 1 < maze[row].length - 1){
					maze[row][col + 1].setNodeType('\u0020'); 
				}else{
					if (row + 1 < maze.length - 1)maze[row + 1][col].setNodeType('\u0020');
				}
			}
		}		
	}
	
	public Node[][] getMaze(){
		return this.maze;
	}
	
	public Node get(int row, int col){
		return this.maze[row][col];
	}
	
	public void set(int row, int col, char c){
		this.maze[row][col].setNodeType(c);
	}
	
	public void setPlayer(PlayerNode p1){
		this.p1 = p1;
	}
	
	public PlayerNode getPlayer(){
		return this.p1;
	}
	
	public int size(){
		return this.maze.length;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public SpiderSprite getSpriteId(int row, int col){
		for(SpiderSprite s : sprites ){
			if (maze[row][col].getRow() == row && maze[row][col].getCol() == col){
				return s;
			}
		}
		return null;
	}
}