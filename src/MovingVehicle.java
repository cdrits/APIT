
public class MovingVehicle extends Thread{

	private int currentRow;
	private int currentCol;
	private Grid grid;
	private Vehicle v;

	MovingVehicle(Vehicle vehicle){
		this.v = vehicle;
		grid.addToGrid(v);
	}

	public void run() {
		for (int row = 0; row <= grid.getRow(); row++) {
			try {
				Thread.sleep(v.getSpeed());
				//να κουνηθεί μέσα στο grid με τo method του grid
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			grid.moveVehicle(v);
			//			System.out.println("Vehicle " + vehicle.getvName() + " has moved.");
		}
	}
}
