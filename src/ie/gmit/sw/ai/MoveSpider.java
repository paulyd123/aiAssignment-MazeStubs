package ie.gmit.sw.ai;

import java.util.*;
import java.util.concurrent.*;


public class MoveSpider extends Node {

    
    private Node[][] maze = null;
    private Random rand = new Random();
    private Object lock = null;
    private ExecutorService executor = Executors.newFixedThreadPool(1);
    private Node lastNode = null;
    private volatile int moveNumber = 0;
    private long spiderspeed = 2000;
    

    public MoveSpider(int row, int col, int id, Node[][] maze, Object lock) {
    	
        super(row, col, id);
        
        this.lock = lock;
        this.maze = maze;

        //Move spider
        executor.submit(() -> {
            while (true) {
            	
                try {               
                    Thread.sleep(spiderspeed);   
                    //Moves spider
                    move();

                } catch (Exception ex) {

                } 
            } 
        });
    } 
    
    
    //Spider movement in a thread
    private void move(){

        synchronized (lock) {
        	

  

            Node[] adjacentNodes = null;
            List<Node> movementTo = new ArrayList<>();

            
            adjacentNodes = adjacentNodes(maze);

            for (int i = 0; i < adjacentNodes.length; i++) {
				Node n = adjacentNodes[i];
				
				if (n.getId() == -1) {
				
				 	movementTo.add(n);
				}
			}



            if (movementTo.size() > 0) {
			
                int newX, newY, oldX, oldY;

                oldX = getRow();
                oldY = getCol();

                
                int index = rand.nextInt(movementTo.size());

                newX = movementTo.get(index).getRow();
                newY = movementTo.get(index).getCol();

                
                setRow(newX);//Sets new row
                setCol(newY);//Sets new column
                
                movementTo.get(index).setRow(oldX);
                movementTo.get(index).setCol(oldY);

                
                lastNode = movementTo.get(index);
                maze[newX][newY] = (MoveSpider)this;
                maze[oldX][oldY] = movementTo.get(index);

            } 
            
        }       
    } 
} 
