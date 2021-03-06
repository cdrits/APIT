
public class VerticalVehicle extends Thread{

	//class instance variables
	private Vehicle v; //instance variable for the vehicle
	private Grid grid; //instance variable for the grid


	/**
	 * Constructor Method. Creates a VerticalVehicle object passing it a grid and a Vehicle object.
	 * @param grid
	 * @param v: Vehicle object
	 */
	VerticalVehicle(Grid grid, Vehicle v){
		this.grid= grid;
		this.v= v;
	}

	/**
	 * Method to determine what happens when the thread executes
	 */
	public void run() {

		//select movement according to the direction of the vehicle
		if (v.getDirection() == 0) {
			//while in the grid boundaries
			for (int i = 0; i< grid.getRow();i++) { 
				//wait until the vehicle is ready to move (speed implementation)
				try {
					Thread.sleep(v.getSpeed());
					//move the Vehicle in the grid
					grid.moveSouth(v);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else if (v.getDirection() == 2)
		{
			//while in the grid boundaries
			for (int i = grid.getRow(); i > 0; i--) { 
				//wait until the vehicle is ready to move (speed implementation)
				try {
					Thread.sleep(v.getSpeed());
					//move the Vehicle in the grid
					grid.moveNorth(v);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
