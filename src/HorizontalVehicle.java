
public class HorizontalVehicle extends Thread{

	//class instance variables
	private Vehicle v; //instance variable for the vehicle
	private Grid grid; //instance variable for the grid


	/**
	 * Constructor Method. Creates a HorizontalVehicle object passing it a grid and a Vehicle object.
	 * @param grid
	 * @param v: Vehicle object
	 */
	HorizontalVehicle(Grid grid, Vehicle v){
		this.grid= grid;
		this.v= v;

	}


	/**
	 * Method to determine what happens when the thread executes
	 */
	public void run() {

		//while in the grid boundaries
		while (v.getColumn()<= grid.getCol()) {
			//wait until the vehicle is ready to move (speed implementation)
			try { 
				Thread.sleep(v.getSpeed());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//move the Vehicle in the grid
			grid.moveHorizontalVehicle(v);
		}
	}
}
