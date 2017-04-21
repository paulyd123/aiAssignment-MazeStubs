package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import ie.gmit.sw.ai.SpiderSprite;
import ie.gmit.sw.ai.ResourceSprite;
import ie.gmit.sw.ai.PlayerSprite;
import ie.gmit.sw.ai.Sprite;

public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 50;
	private static final int IMAGE_COUNT = 14;
	private GameView view;
	private Maze model;
	private int currentRow;
	private int currentCol;
	
	private PlayerNode p1;
	private SpiderSprite sprite;
	
	public GameRunner() throws Exception{
		currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
		p1 = new PlayerNode(currentRow, currentCol);
		model = new Maze(MAZE_DIMENSION, p1);
    	view = new GameView(model);
    	
    	placePlayer();
    	///placeGoalNode();
    	Sprite[] sprites = getSprites();
    	view.setSprites(sprites);
    	
    	
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
	}
	
	private void placePlayer(){   	
    	model.set(currentRow, currentCol, '5'); //A Spartan warrior is at index 5
    	updateView(); 		
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	private boolean isValidMove(int row, int col){
		if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0020'){
			model.set(currentRow, currentCol, '\u0020');
			model.set(row, col, '5');
			return true;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0031')){
			model.getMaze()[row][col].setNodeType('0');
			p1.addSword();
			p1.setSwordPower(10);
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0032')){
			model.getMaze()[row][col].setNodeType('0');
			//p1.setHealth(p1.getHealth()+30);
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0033')){
			model.getMaze()[row][col].setNodeType('0');
			p1.addBombs();
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0034')){
			model.getMaze()[row][col].setNodeType('0');
			p1.addHydrogenBomb();
			return false;
		}
		else if((row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == '\u0036')){
			
			sprite = model.getSpriteId(row, col);
			sprite.engageFuzzy();
			if(p1.getHealth() > 0){
				sprite.setId(-1);
				model.set(currentRow, currentCol, '\u0020');
				model.set(row, col, '0');
			}
			else {
				System.exit(0);
			}
			return false;
		}
		else {
			return false; //Can't move
		}
	}
	
	private Sprite[] getSprites() throws Exception{
		//Read in the images from the resources directory as sprites. Note that each
		//sprite will be referenced by its index in the array, e.g. a 3 implies a Bomb...
		//Ideally, the array should dynamically created from the images... 
		Sprite[] sprites = new Sprite[IMAGE_COUNT];
		sprites[0] = new ResourceSprite("Hedge", "resources/hedge.png");
		sprites[1] = new ResourceSprite("Sword", "resources/sword.png");
		sprites[2] = new ResourceSprite("Help", "resources/help.png");
		sprites[3] = new ResourceSprite("Bomb", "resources/bomb.png");
		sprites[4] = new ResourceSprite("Hydrogen Bomb", "resources/h_bomb.png");
		sprites[5] = new ResourceSprite("Spartan Warrior", "resources/spartan_1.png", "resources/spartan_2.png");
		sprites[6] = new SpiderSprite("Black Spider", "resources/black_spider_1.png", "resources/black_spider_2.png");
		sprites[7] = new SpiderSprite("Blue Spider", "resources/blue_spider_1.png", "resources/blue_spider_2.png");
		sprites[8] = new SpiderSprite("Brown Spider", "resources/brown_spider_1.png", "resources/brown_spider_2.png");
		sprites[9] = new SpiderSprite("Green Spider", "resources/green_spider_1.png", "resources/green_spider_2.png");
		sprites[10] = new PlayerSprite("Grey Spider", "resources/grey_spider_1.png", "resources/grey_spider_2.png");
		sprites[11] = new PlayerSprite("Orange Spider", "resources/orange_spider_1.png", "resources/orange_spider_2.png");
		sprites[12] = new PlayerSprite("Red Spider", "resources/red_spider_1.png", "resources/red_spider_2.png");
		sprites[13] = new PlayerSprite("Yellow Spider", "resources/yellow_spider_1.png", "resources/yellow_spider_2.png");
		return sprites;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}