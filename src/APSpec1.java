
public class APSpec1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid grid = new Grid();
		VehicleGenerator vGen = new VehicleGenerator(grid);

		grid.start();
		vGen.start();
	}

}
