
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
		
		//select movement according to the direction of the vehicle
		if (v.getDirection() == 1) {
			//while in the grid boundaries
			for (int i = 0; i< grid.getCol();i++) {
				//wait until the vehicle is ready to move (speed implementation)
				try {
					Thread.sleep(v.getSpeed());
					//move the Vehicle in the grid
					grid.moveEast(v);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else if (v.getDirection() == 3)
		{
			//while in the grid boundaries
			for (int i = grid.getCol(); i > 0; i--) { 
				//wait until the vehicle is ready to move (speed implementation)
				try {
					Thread.sleep(v.getSpeed());
					//move the Vehicle in the grid
					grid.moveWest(v);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
