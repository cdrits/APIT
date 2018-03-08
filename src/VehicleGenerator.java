import java.util.Random;

public class VehicleGenerator extends Thread{

	private Grid grid;
	private boolean stop = false;

	VehicleGenerator(Grid grid){
		this.grid = grid;
	}

	/**
	 * Method that generates a Vehicle object and a thread to which the Vehicle object is given
	 */
	public void generateVehicle() {
		
		//First create a Vehicle object
		
		//set a random direction for the new Vehicle
		int direction = randomDirection();		
		
		//create a new Vehicle object, passing it the direction
		Vehicle v = new Vehicle(direction);

		//determine Vehicle's starting position based on it's direction
		setStartingPosition(v,direction);

		/*
		 *print vehicle stats for testing
		 */
		String s = "name: " + getName() + " starting position: (" + v.getRow() + ", "+ v.getColumn() + ") " + "/speed: " + v.getSpeed() + "/ direction: " + v.getDirection()+ "/ symbol: " + v.getSymbol();
		System.out.println(s);

		//Then create a MovingVehicle object (thread) which will be responsible for moving the Vehicle object
		System.out.println(v);
		MovingVehicle movingVehicle = new MovingVehicle(v);
		
	}

	public void run() {

		while (!grid.isDone()) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			generateVehicle();
		}
	}


	private void setStartingPosition(Vehicle v, int direction) {
		switch(direction) {

		case 0: { //the vehicle is heading South
			v.setRow(0);
			v.setColumn(randomColumn());
			break;

		}case 1: { //the vehicle is heading East
			v.setColumn(0);
			v.setRow(randomRow());
			break;

		}case 2: { //the vehicle is heading North
			v.setColumn(grid.getCol());
			v.setRow(randomRow());
			break;

		}case 3:{ //the vehicle is heading West
			v.setColumn(randomColumn());
			v.setRow(grid.getRow());
			break;
		}
		}
	}

	private int randomDirection() {
		int direction;
		Random rn = new Random();
		direction = rn.nextInt(2);

		return direction;
	}

	private int randomColumn() {
		int col;
		Random rn = new Random();
		col = rn.nextInt(grid.getCol());

		System.err.println("random col: "+ col);
		return col;
	}

	private int randomRow() {
		int row;
		Random rn = new Random();
		row = rn.nextInt(grid.getRow());

		System.err.println("random row: "+ row);
		return row;
	}

}
