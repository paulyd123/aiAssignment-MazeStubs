package ie.gmit.sw.ai.traversers;

import ie.gmit.sw.ai.*;
public class DepthLimitedDFSTraversator implements Traversator{
	private Node[][] maze;
	private int limit;
	private boolean keepRunning = true;
	private long time = System.currentTimeMillis();
	private int visitCount = 0;
	private SpiderSprite sprite;
	private PlayerNode player;
	
	public DepthLimitedDFSTraversator(int limit, SpiderSprite sprite, PlayerNode player){
		
		this.limit = limit;
		this.sprite = sprite;
		this.player = player;
	
	}
	
	public void traverse(Node[][] maze, Node node) {
		
		this.maze = maze;
		
		System.out.println("Search with limit " + limit);
		dfs(node, 1);
	}
	
	private void dfs(Node node, int depth){
		
		if (!keepRunning || depth > limit) return;
		
		node.setVisited(true);	
		visitCount++;
		
		if (node.isGoalNode()){
	        time = System.currentTimeMillis() - time; //Stop the clock
	        TraversatorStats.printStats(node, time, visitCount);
	        keepRunning = false;
			return;
		}
		
		try { //Simulate processing each expanded node
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Node[] children = node.adjacentNodes(maze);

		for (int i = 0; i < children.length; i++) {
			
			if (children[i] != null && !children[i].isVisited()){
				children[i].setParent(node);
				try {

					if(sprite.getId() != -1){
						System.out.println(sprite.getId());
					sprite.moveSprite(node.getRow(), node.getCol());
					}
					else
						break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dfs(children[i], depth + 1);
			}
		}
	}
}