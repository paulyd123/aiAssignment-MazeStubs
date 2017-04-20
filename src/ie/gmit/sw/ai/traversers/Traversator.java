package ie.gmit.sw.ai.traversers;

import ie.gmit.sw.ai.Node;

//Taken from ai-maze-algos


public interface Traversator {
	public void traverser(Node[][] maze, Node start);
	public Node getNextNode();

}
