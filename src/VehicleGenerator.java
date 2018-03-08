import java.util.Random;

public class VehicleGenerator {

	private Grid grid;
	
	VehicleGenerator(Grid grid){
		this.grid = grid;
	}

	public void generateVehicle() {
		int direction = randomDirection();		
		Vehicle v = new Vehicle(direction);
		switch(direction) {
		case 0: v.setRow(0); v.setColumn(randomColumn());
		}
	}

	private int randomDirection() {
		int direction;
		Random rn = new Random();
		direction = rn.nextInt(1);



		return direction;
	}

	private int randomColumn() {
		int col;
		Random rn = new Random();
		col = rn.nextInt(grid.getRow());

		return col;
	}

}
