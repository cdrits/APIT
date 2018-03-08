import java.util.concurrent.locks.*;

public class Grid extends Thread{

	private final int row = 10;
	private final int col = 20;
	private Vehicle [][] grid;
	private boolean done = false;
	
	private ReentrantLock gridLock = new ReentrantLock();
	private Condition gridCondition = gridLock.newCondition();
	
	public Grid() {
		this.grid = new Vehicle [row][col];
	}
	
	public void addToGrid(Vehicle v) {
		grid[v.getRow()][v.getColumn()] = v;
	}
	
	public void moveVehicle(Vehicle v) {
		gridLock.lock();
		
	}
	
	
	public void run() {
		
		for (int l=0; l <= 5; l++) {
			gridLock.lock();

			try {
				Thread.sleep(500);

				System.out.println("\n-----------------------------------------");
				String sq = "";

				for (int i=0;i<row;) {
					System.out.print("|");
					for (int j =0; j<col;j++) {
						if (grid[i][j]==null) {
							sq = " ";
						}
						else sq = grid[i][j].getSymbol();

						System.out.print( sq+"|");
					}
					System.out.println();
					i++;
				}

				System.out.println("-----------------------------------------");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}	finally {gridLock.unlock();}
		}
		done = true;
	}
	
	
	/**
	 * Getters & Setters
	 * @return
	 */
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public boolean isDone() {
		return done;
	}
	
}
