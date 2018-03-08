
public class APSpec1 {

	public static void main(String[] args) {
		Grid grid = new Grid();
		VehicleGenerator vGen = new VehicleGenerator(grid);

		grid.start();
		vGen.start();
	}

}
