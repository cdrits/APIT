
public class HorizontalVehicle extends Thread{
	private int row;
	private int column;
	private Vehicle v;
	private Grid grid;
		
	HorizontalVehicle(Grid grid, Vehicle v){
		this.grid= grid;
		this.v= v;
		
	}
	
	public void run() {
		while (v.getColumn()<= grid.getCol()) {
			try {
				Thread.sleep(v.getSpeed());
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			grid.moveHorizontalVehicle(v);
		}
	}
}
