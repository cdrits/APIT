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
	 * @param v: the vehicle
	 */
	public void addToGrid(Vehicle v) {
		grid[v.getRow()][v.getColumn()] = v;
	}

	
	/**
	 * Method to remove a Vehicle object from the grid Array.
	 * @param v: the vehicle
	 */
	private void removeFromGrid(Vehicle v) {
		grid[v.getRow()][v.getColumn()] = null;
	}

	
	/**
	 * Method to "move" vehicle from North to South
	 * @param v: the vehicle
	 */
	public void moveSouth(Vehicle v) {

		gridLock.lock();
		try {
			//if the vehicle is in the last row, remove it from the grid
			if (v.getRow()== this.row-1) {
				removeFromGrid(v);}
			//if the row that the vehicle is in, is not the last row of the grid
			else if (v.getRow()<this.row-1) { 

				//if next square is full, wait..
				while (grid[v.getRow()+1][v.getColumn()] !=null) {
					gridCondition.await();
				}

				//when we reach this point, the next square is empty
				//set next square = vehicle
				grid[v.getRow()+1][v.getColumn()] = v;

				//set current square = null
				if(v.getRow()>=0) {
					grid[v.getRow()][v.getColumn()]= null;
				}

				//change vehicle's row
				v.setRow((v.getRow()+1));
				gridCondition.signalAll();
			}
		}catch (InterruptedException e) {

			e.printStackTrace();
		}

		finally {gridLock.unlock();}
	}

	
	/**
	 * Move vehicle from West to East
	 * @param v: the vehicle
	 */
	public void moveEast(Vehicle v) {

		gridLock.lock();
		try {
			//if the vehicle is in the last column, remove it from the grid
			if (v.getColumn()== this.col-1) {
				removeFromGrid(v);}
			//if the column that the vehicle is in, is not the last column of the grid
			else if (v.getColumn()<this.col-1) { 

				//if next square is full, wait..
				while (grid[v.getRow()][v.getColumn()+1] !=null) {
					gridCondition.await();
				}

				//when we reach this point, the next square is empty
				//set next square = vehicle
				grid[v.getRow()][v.getColumn()+1] = v;

				//set current square = null
				if(v.getRow()>=0) {
					removeFromGrid(v);
				}

				//change vehicle's column
				v.setColumn((v.getColumn()+1));
				gridCondition.signalAll();
			}
		}catch (InterruptedException e) {

			e.printStackTrace();
		}

		finally {gridLock.unlock();}

	}


	/**
	 * Method to "move" vehicle from South to North (for Specification 2)
	 * @param v: the vehicle
	 */
	public void moveNorth(Vehicle v) {

		gridLock.lock();
		try {
			//if the vehicle is in the first row, remove it from the grid
			if (v.getRow()== 0) {
				removeFromGrid(v);}

			//if the row that the vehicle is in, is not the last row of the grid
			else if (v.getRow()>0) { 

				//if next square is full, wait..
				while (grid[v.getRow()-1][v.getColumn()] !=null) {
					gridCondition.await();
				}

				//when we reach this point, the next square is empty
				//set next square = vehicle
				grid[v.getRow()-1][v.getColumn()] = v;

				//set current square = null
				if(v.getRow()>=0) {
					grid[v.getRow()][v.getColumn()]= null;
				}

				//change vehicle's row
				v.setRow((v.getRow()-1));
				gridCondition.signalAll();
			}
		}catch (InterruptedException e) {

			e.printStackTrace();
		}

		finally {gridLock.unlock();}

	}

	
	/**
	 * Method to "move" vehicle from South to North (for Specification 2)
	 * @param v: the vehicle
	 */
	public void moveWest(Vehicle v) {

		gridLock.lock();
		try {
			//if the vehicle is in the first column, remove it from the grid
			if (v.getColumn()== 0) {
				removeFromGrid(v);}

			//if the column that the vehicle is in, is not the last column of the grid
			else if (v.getColumn()>0) { 

				//if next square is full, wait..
				while (grid[v.getRow()][v.getColumn()-1] !=null) {
					gridCondition.await();
				}

				//when we reach this point, the next square is empty
				//set next square = vehicle
				grid[v.getRow()][v.getColumn()-1] = v;

				//set current square = null
				if(v.getColumn()>=0) {
					grid[v.getRow()][v.getColumn()]= null;
				}

				//change vehicle's column
				v.setColumn((v.getColumn()-1));
				gridCondition.signalAll();
			}
		}catch (InterruptedException e) {

			e.printStackTrace();
		}

		finally {gridLock.unlock();}
	}

	
	/**
	 * The Grid thread prints the grid on the console.
	 */
	public void run() {

		//print the grid for 2000 times
		for (int l=0; l <= 2000; l++) {

			//every 20 milliseconds
			try {
				Thread.sleep(20);
				gridLock.lock();
				printGrid ();
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			finally {
				gridLock.unlock();
			}
		}

		//when the grid is printed 2000 times, raise the flag for the VehicleGenerator to stop as well.
		done = true;
	}


	/**
	 * Method that prints the current state of the grid
	 */
	private void printGrid () {
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
	}


	/**
	 * Setters
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
