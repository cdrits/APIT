import java.util.Random;

public class Vehicle {

	//class instance variables
	private String symbol; //the visual representation of the vehicle
	private int row;
	private int column;
	private int direction; //(0: south, 1: east, 2: north, 3: west)
	private int speed; //the speed of the vehicle (the time that the vehicle waits before it attemps to move)

	
	/**
	 * Constructor
	 * @param direction
	 */
	Vehicle(int direction){		
		this.direction = direction;
		this.symbol = setSymbol(direction);
		this.speed = setSpeed();
	}


	/**
	 * Method that sets the Vehicle object visual representation symbol, according to its direction
	 * @param direction
	 * @return the symbol
	 */
	private String setSymbol(int direction) {
		switch(direction) {
		case 0:return "v";
		case 1: return ">";
		case 2: return "^";
		case 3: return "<";
		default: return "";
		}
	}


	/**
	 * Method that randomly creates a number for the speed of the vehicle
	 * @return speed: the random number for the speed
	 */
	private int setSpeed() {
		int speed=0;
		Random rSpeed = new Random();
		speed = rSpeed.nextInt(200);
		speed = (500 - speed); //in order to achieve "slower" speeds, which is useful for testing

		return speed;
	}


	/**
	 * Getters & Setters
	 */
	public void setRow(int row) {
		this.row = row;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
