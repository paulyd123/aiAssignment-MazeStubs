package ie.gmit.sw.ai.traversers;

import ie.gmit.sw.ai.*;

import java.util.*;
public class AStarTraversator implements Traversator{
	private Node goal;
	private SpiderSprite sprite;
	
	public AStarTraversator(Node goal, SpiderSprite sprite){
		
		this.goal = goal;
		this.sprite = sprite;
	}
	
	public void traverse(Node[][] maze, Node node) {
		
        long time = System.currentTimeMillis();
    	int visitCount = 0;
    	
		PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
		java.util.List<Node> closed = new ArrayList<Node>();
    	   	
		open.offer(node);
		node.setPathCost(0);		
		while(!open.isEmpty()){
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			visitCount++;
			
			if (node.isGoalNode()){
		        time = System.currentTimeMillis() - time; //Stop the clock
		        TraversatorStats.printStats(node, time, visitCount);
				break;
			}
			
			try { //Simulate processing each expanded node
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Process adjacent nodes
			Node[] children = node.adjacentNodes(maze);
			for (int i = 0; i < children.length; i++) {
				Node child = children[i];
				int score = node.getPathCost() + 1 + child.getHeuristic(goal);
				int existing = child.getPathCost() + child.getHeuristic(goal);
				
				if ((open.contains(child) || closed.contains(child)) && existing < score){
					try {
						sprite.moveSprite(child.getRow(), child.getCol());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}else{
					open.remove(child);
					closed.remove(child);
					child.setParent(node);
					child.setPathCost(node.getPathCost() + 1);
					open.add(child);
				}
			}									
		}
	}
}
