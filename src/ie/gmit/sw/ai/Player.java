package ie.gmit.sw.ai;

public class Player {
	
	private int row;
	private int col;
	private double health = 10;
	private int sword = 0;
	private int hydrogenBombs = 0;
	private int bombs = 0;
	private double swordStrength = 0;
	
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Player(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public int getHydrogenBombs() {
		return hydrogenBombs;
	}

	public void addHydrogenBombs() {
		hydrogenBombs++;
	}

	public int getBombs() {
		return bombs;
	}

	public void addBombs() {
		this.bombs++;
	}

	public int getSword() {
		return sword;
	}

	public void addSword() {
		this.sword++;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getSwordStrength() {
		return swordStrength;
	}

	public void setSwordStrength(double swordStrength) {
		this.swordStrength = swordStrength;
	}

}
