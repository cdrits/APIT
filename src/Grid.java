import java.util.concurrent.locks.*;

public class Grid extends Thread{

	//class instance variables
	private final int row = 10; //variable for the number of rows: it is set to 10 adhering to the specifications
	private final int col = 20; //variable for the number of columns: it is set to 10 adhering to the specifications
	private Vehicle [][] grid; //a 2D array representing the junction
	private boolean done = false; //a boolean flag variable to indicate that the printing thread has finished executing

	private ReentrantLock gridLock = new ReentrantLock(); //variable for the ReentrantLock
	private Condition gridCondition = gridLock.newCondition(); //variable for the Condition of the Lock

	
	/**
	 * Grid constructor. Creates a rows x columns 2D array
	 */
	public Grid() {
		this.grid = new Vehicle [row][col];
	}

	
	/**
	 * Method to add a Vehicle object in a position in the grid Array.
	 * @param v
	 */
	public void addToGrid(Vehicle v) {
		grid[v.getRow()][v.getColumn()] = v;
	}


	/**
	 * Method to "move" vehicle from North to South
	 * @param v: the vehicle
	 */
	public void moveVerticalVehicle(Vehicle v) {

		gridLock.lock();

		//if the row that the vehicle is in, is not the last row of the grid
		if (v.getRow()<this.row-1) {
			try {
				//if next square is full, wait..
				while (grid[v.getRow()+1][v.getColumn()] !=null) {
					gridCondition.await();
				}

				//when we reach this point, the next square is empty
				//set next block = vehicle
				grid[v.getRow()+1][v.getColumn()] = v;

				//set current block = null
				if(v.getRow()>=0) {
					grid[v.getRow()][v.getColumn()]= null;
				}

				//change vehicle's row
				v.setRow((v.getRow()+1));
				gridCondition.signalAll();

			}catch (InterruptedException e) {

				e.printStackTrace();
			}

			finally {gridLock.unlock();}
			//if we are out of rows, remove vehicle from grid	
		}else {
			grid[v.getRow()][v.getColumn()]= null;
			gridLock.unlock();
		}

	}

	/**
	 * Move vehicle from West to East
	 * @param v: the vehicle
	 */
	public void moveHorizontalVehicle(Vehicle v) {

		gridLock.lock();

		// if the row that the vehicle is in, is not the last row of the grid
		if (v.getColumn()<this.col-1) {
			try {
				//while next block is full, wait
				while (grid[v.getRow()][v.getColumn()+1] !=null) {
					gridCondition.await();
				}

				//when next block is empty
				//set next block = vehicle
				grid[v.getRow()][v.getColumn()+1] = v;

				//set current block = null
				if(v.getColumn()>=0) {
					grid[v.getRow()][v.getColumn()]= null;
				}

				//change vehicle's row
				v.setColumn((v.getColumn()+1));
				gridCondition.signalAll();

			}catch (InterruptedException e) {

				e.printStackTrace();
			}

			finally {gridLock.unlock();}
			//if we are out of rows, remove vehicle from grid	
		}else {
			grid[v.getRow()][v.getColumn()]= null;
			gridLock.unlock();
		}

	}


	public void run() {

		for (int l=0; l <= 2000; l++) {

			try {
				Thread.sleep(20);

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
				
			}
		}
		
		done = true;
		//System.exit(0);
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
