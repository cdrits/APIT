import java.util.Random;

public class VehicleGenerator extends Thread{

	//class instance variable
	private Grid grid;


	/**
	 * VehicleGenerator object constructor
	 * @param grid
	 */
	VehicleGenerator(Grid grid){
		this.grid = grid;
	}


	/**
	 * Method to determine what happens when the thread executes. 
	 * Generates a Vehicle object and a Thread to move it every 0.25 seconds
	 */
	public void run() {
		/*
		 * while the thread that prints the grid is running, this thread continues 
		 * to produce Vehicles and threads
		 */
		while (!grid.isDone()) {

			//produce Vehicle objects and threads every 0.5 second
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			//and then create a Vehicle and a Thread to move it
			generateVehicle();
		}
	}


	/**
	 * Method that generates a Vehicle (model) object, with randomly generated characteristics. 
	 * It then creates a thread according to the direction of the Vehicle, to which the Vehicle object is given.
	 */
	private void generateVehicle() {

		/*
		 * First create a Vehicle object
		 */
		//set a random direction for the new Vehicle
		int direction = randomDirection();		

		//create a new Vehicle object, passing it the direction
		Vehicle v = new Vehicle(direction);

		//determine Vehicle's starting position based on it's direction
		setStartingPosition(v, direction);

		//add the Vehicle object to the grid
		grid.addToGrid(v);

		//create and iginite a thread according to the direction of the vehicle  
		switch (v.getDirection()) 
		{
		case 0: { //the vehicle will move on the vertical axis, North to South
			VerticalVehicle vert = new VerticalVehicle(grid,v);
			vert.start();
			break;
		}
		case 1: { //the vehicle will move on the horizontal axis, West to East
			HorizontalVehicle hor = new HorizontalVehicle(grid,v);
			hor.start();
			break;
		}
		case 2: { //the vehicle will move on the vertical axis, South to North
			VerticalVehicle vert = new VerticalVehicle(grid,v);
			vert.start();
			break;
		}
		case 3:{ //the vehicle will move on the horizontal axis, East to West
			HorizontalVehicle hor = new HorizontalVehicle(grid,v);
			hor.start();
			break;
		}
		}
	}


	/**
	 * Method creates a random number that represents the direction of the vehicle
	 * @return direction: the random direction of the vehicle
	 */
	private int randomDirection() {
		int direction;
		Random rn = new Random();
		direction = rn.nextInt(2); //only create values 0 or 1 since these are the requirements of Spec1

		return direction;
	}


	/**
	 * Method that determines the starting position of the vehicle in accordance with it's direction
	 * @param v: the Vehicle
	 * @param direction: the direction of the Vehicle
	 */
	private void setStartingPosition(Vehicle v, int direction) {

		switch(direction)
		{
		case 0: { //the vehicle is heading South
			v.setRow(0);
			v.setColumn(randomColumn());
			break;
		}
		case 1: { //the vehicle is heading East
			v.setColumn(0);
			v.setRow(randomRow());
			break;
		}
		case 2: { //the vehicle is heading North
			v.setColumn(randomColumn());
			v.setRow(grid.getRow()-1);
			break;
		}
		case 3:{ //the vehicle is heading West
			v.setColumn(grid.getCol()-1);
			v.setRow(randomRow());
			break;
		}
		}
	}


	/**
	 * Method that creates a random number for the starting column of the Vehicle
	 * @return col: the random column number
	 */
	private int randomColumn() {
		int col;
		Random rn = new Random();
		col = rn.nextInt(grid.getCol());

		return col;
	}


	/**
	 * Method that creates a random number for the starting row of the Vehicle
	 * @return row: the random row number
	 */
	private int randomRow() {
		int row;
		Random rn = new Random();
		row = rn.nextInt(grid.getRow());

		return row;
	}
	
}
