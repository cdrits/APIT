
public class APSpec1 {

	public static void main(String[] args) {

		//create a new Grid object
		Grid grid = new Grid();

		//create a new vehicleGenerator (thread generator), passing it the grid
		VehicleGenerator vGen = new VehicleGenerator(grid);

		//start both threads
		grid.start();
		vGen.start();

	}
}
