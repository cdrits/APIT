import java.util.Random;

public class Vehicle {

	private String symbol;
	private int row;
	private int column;
	private int direction; //(0: south, 1: east, 2: north, 3: west)
	private int speed;

	Vehicle(int direction){		
		this.symbol = setSymbol(direction);
		this.speed = setSpeed();
	}

	

	

	private String setSymbol(int direction) {
		switch(direction) {
		case 1:return "v";
		case 2: return ">";
		case 3: return "^";
		case 4: return "<";
		default: return "";
		}
	}

	private int setSpeed() {
		int speed=0;
		Random rSpeed = new Random();
		speed = rSpeed.nextInt(100);

		if (speed <0)
			speed = speed*(-1);

		return speed;
	}

	public void move() {
		
	}
	
	/**
	 * Getters & Setters
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	

	public void setColumn(int column) {
		this.column = column;
	}
}
