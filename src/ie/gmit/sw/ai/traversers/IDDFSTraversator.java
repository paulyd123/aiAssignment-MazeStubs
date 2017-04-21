package ie.gmit.sw.ai.traversers;

import java.awt.Color;
import java.awt.Component;

import ie.gmit.sw.ai.*;
public class IDDFSTraversator implements Traversator{
	
	private Node[][] maze;
	private boolean keepRunning = true;
	private long time = System.currentTimeMillis();
	private int visitCount = 0;
	private Component viewer;
	
	public void traverse(Node[][] maze, Node start, Component viewer) {
		this.maze = maze;
		int limit = 1;
		this.viewer = viewer;
		
		while(keepRunning){
			dfs(start, 0, limit);
			
			if (keepRunning){
				//try { //Pause before next iteration
					//Thread.sleep(500);
		      		limit++;       		
		      		unvisit();	
				//} catch (InterruptedException e) {
				//	e.printStackTrace();
				//}			
			}
      	}
	}

	private void dfs(Node node, int depth, int limit){
		if (!keepRunning || depth > limit) return;		
		node.setVisited(true);	
		visitCount++;
		viewer.repaint();
		System.out.println("looking for goalNode");
		
		if (node.isGoalNode()){
	        time = System.currentTimeMillis() - time; //Stop the clock
	        TraversatorStats.printStats(node, time, visitCount);
	        viewer.repaint();
	        keepRunning = false;
			return;
		}
		
		try { //Simulate processing each expanded node
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Node[] children = node.adjacentNodes(maze);
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null && !children[i].isVisited()){
				children[i].setParent(node);
				dfs(children[i], depth + 1, limit);
			}
		}
	} 
		
	private void unvisit(){
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				maze[i][j].setVisited(false);
				maze[i][j].setParent(null);
				maze[i][j].setColor(Color.BLACK);
			}
		}
	}

	@Override
	public void traverse(Node[][] maze, Node start) {
		// TODO Auto-generated method stub
		
	}
}