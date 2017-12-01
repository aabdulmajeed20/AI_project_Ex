// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  Abdulmajeed Fahad Altaweel
// ID: 435105646

import java.io.*;
import java.util.*;

public class State {

	// THE FOLLOWING IS AN EXAMPLE OF THE ATTRIBUTES 
	// THAT YOU NEED TO PUT IN A STATE.
	// YOU SHOULD CHANGE IT:
	
	private int x;		// THIS IS X (MAYBE NOT NEEDED)
	private int y;		// THIS IS Y (MAYBE NOT NEEDED)
	private int z; 		// THIS IS Z (MAYBE NOT NEEDED)
//	private Map map;	// THE MAP
	private char[][] map;	// define the map
	private int mapX;	// map's rows
	private int mapY;	// map's columns
	private int initX;	// initial X position of Robot
	private int initY;	// initial Y position of Robot
	private int Battery;
	// -----------------------------

	//THE FOLLOWING ARE THE CONSTRUCTORS
	// YOU CAN CHANGE OR REPALCE THEM.
	
	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A STATE FROM FILE.
	State(String fileName) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(fileName)); // Here we take the fileName from parameter
																			// and read it by bufferdReader
		
		mapY = Integer.parseInt(read.readLine()); 	// Here we read the number of rows
		mapX = Integer.parseInt(read.readLine());	// Here we read the number of columns
		Battery = 20;						// given number
		
		map = new char[mapX][mapY];		// Here we initialise the map which is 2D array
		
		// Here we add data to the map
		for(int i = 0; i < mapY; i++) {
			String s = read.readLine();
			for(int j = 0; j < mapX; j++) {
				if(s.charAt(j) == 'R') {		// search about the robot cell by cell then store its position
					initX = x = j;
					initY = y = i;
				}
				map[j][i] = s.charAt(j);
			}
		}
		
		
	}
	
	// CONSTRUCTOR 2:
	// THIS CONSTRUCTOR WILL CREATE A RANDOM STATE.
	State(int n, int m, int rseed) {
		// ...
	}
	
	
	// CONSTRUCTOR 3:
	// COPY CONSTRUCTOR.
	State( State s) {
		x = s.x;
		y = s.y;
		z = s.z;
		// ...
	}
	
	// -----------------------------
	
	// STATE GETTERS AND SETTERS
	// YOU CAN & SHOULD CHANGE THEM.
	// IF YOU HAVE QUESTIONS ASK THE INSTRUCTOR.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
//	public Map getMap() { 
//		return map;
//	}
	
	
	// METHOD THAT TELLS WHETHER THIS STATE IS EQUAL
	// TO ANOTHER STATE.
	public boolean equal(State s) {
		return ((x==s.x)&&(y==s.y)&&(z==s.z)&&(map.equals(s.map)));
	}
	
	// -----------------------------
	
	// THE ACTIONS:
	// YOU CAN CHANGE THE ACTIONS CONTENTS,
	// WHAT THE ACTIONS RETURN, ETC.
	
	// ACTION move North, it's moves the robot to the North
	// RETURNS BOOLEAN: 
	//     TRUE MEANS ACTION WAS APPLIED, 
	//     FALSE MEANS ACTOIN COULD NOT AND WAS NOT APPLIED.
	public boolean move_N() {
		Battery--;
		if(y == 0)						// checking if Robot is on the top of the map and return false if that correct
			return false;
		if(map[x][y-1] == 'B')		// checking if the above cell is block cell
			return false;
		if(map[x][y] == 'H')		// checking if Robot fallen in Hole
			return false;
		y--;							// if the above conditions not pass, moving Robot up and return true
		return true;
		
	}

	// ACTION move East, it's moves the robot to the East
	public boolean move_E() {		
		Battery--;
		if(x == mapX-1)
			return false;
		if(map[x+1][y] == 'B')					// Same of move_N
			return false;
		if(map[x][y] == 'H')
			return false;
		x++;
		return true;
		
	}
	
	// ACTION move South, it's moves the robot to the South
		public boolean move_S() {
			Battery--;
			if(y == mapY-1)
				return false;
			if(map[x][y+1] == 'B')				// Same of move_N
				return false;
			if(map[x][y] == 'H')
				return false;
			y++;
			return true;
			
		}
		
		// ACTION move West, it's moves the robot to the West
		public boolean move_W() {
			Battery--;
			if(x == 0)
				return false;
			if(map[x-1][y] == 'B')				// Same of move_N
				return false;
			if(map[x][y] == 'H')
				return false;
			x--;
			return true;
		}
		
		//Action charge which will increase battery of Robot
		public boolean charge() {
			if(map[x][y] == 'C') {				// if Robot on the station then charge will done
				Battery+=2;
				return true;
			}
			Battery--;								// if charge used in wrong place will decrease the battery
			return false;	
		}
		
		public boolean move_N_B() {
			if(Battery < 3)
				return false;
			Battery-=3;
			if(y == 0)						// checking if Robot is on the top of the map or less than by 1 and return false if that correct
				return false;
			if(map[x][y-1] == 'B')		// checking if the above cell or is block cell
				return false;
			if(map[x][y] == 'H')		// checking if Robot fallen in Hole
				return false;
			if(y == 1 || map[x][y-2] == 'B')
				y--;
			else
			y-=2;							// if the above conditions not pass, moving Robot to squares up and return true
			return true;
			
		}

		// ACTION move East, it's moves the robot to the East
		public boolean move_E_B() {		
			if(Battery < 3)
				return false;
			Battery-=3;
			if(x == mapX-1)
				return false;
			if(map[x+1][y] == 'B')					// Same of move_N_B
				return false;
			if(map[x][y] == 'H')
				return false;
			if(x == mapX-2 || map[x+2][y] == 'B')
				x++;
			else
			x+=2;
			return true;
			
		}
		
		// ACTION move South, it's moves the robot to the South
			public boolean move_S_B() {
				if(Battery < 3)
					return false;
				Battery-=3;
				if(y == mapY-1)
					return false;
				if(map[x][y+1] == 'B')				// Same of move_N_B
					return false;
				if(map[x][y] == 'H')
					return false;
				if(y == mapY-2 || map[x][y+2] == 'B')
					y++;
				else
				y+=2;
				return true;
				
			}
			
			// ACTION move West, it's moves the robot to the West
			public boolean move_W_B() {
				if(Battery < 3)
					return false;
				Battery-=3;
				if(x == 0)
					return false;
				if(map[x-1][y] == 'B')				// Same of move_N_B
					return false;
				if(map[x][y] == 'H')
					return false;
				if(x == 1 || map[x-2][y] == 'B')
					x--;
				else
				x-=2;
				return true;
			}
	


	// -----------------------------

	// GOAL TEST: THIS WILL 
	// TELL WHETHER THE TREASURE WAS FOUND.
	public boolean foundTreasure() {
		if(map[x][y] == 'T') {		//checking if Robot in the same cell of Treasure
			return true;
		}
		return false;
	}

	// -----------------------------


	// DISPLAY THE STATE
	public void display() {
		
	}
	
	
	// THIS METHOD WILL DO the GIVEN COMMAND
	// AND WILL RETURN THE LOG MESSAGE
	public String doCommandAndLog(String cmd) {		// Here we will move on every command and do its action, then we check
													// if the action returns true then log DONE
													// else if the action returns false then log FAIL
		String log="ERROR";
		if(Battery <= 0)
			log = "NO BATTERY";
		else {
		switch (cmd) {
		case "move-N":
			if(move_N()) 
				log = "DONE";
			else
				log = "FAIL";
			
			break;
			
		case "move-E":
			if(move_E())
				log = "DONE";
			else
				log = "FAIL";
			break;
			
		case "move-S":
			if(move_S())
				log = "DONE";
			else
				log = "FAIL";
			break;
			
		case "move-W":
			if(move_W())	
				log = "DONE";
			else
				log = "FAIL";
			break;
			
		case "move-N-B":
			if(move_N_B()) 
				log = "DONE";
			else
				log = "FAIL";
			
			break;
			
		case "move-E-B":
			if(move_E_B())
				log = "DONE";
			else
				log = "FAIL";
			break;
			
		case "move-S-B":
			if(move_S_B())
				log = "DONE";
			else
				log = "FAIL";
			break;
			
		case "move-W-B":
			if(move_W_B())	
				log = "DONE";
			else
				log = "FAIL";
			break;
			
		case "charge":
			if(charge())	
				log = "DONE";
			else
				log = "FAIL";
			break;
		}
		}
		return log;
	}
        

 	// This method will write logs of commands into the logFile
	public void writeLogs(String commandsFile, String logFile) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(commandsFile)); // Buffer to read from commandsFile
		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));	// Buffer to write into logFile
		
		String cmd;
		String check;
//		writer.append(String.valueOf(Battery));
		while((cmd = read.readLine()) != null) {		// Here we call doCommandAndLog() and write its value on logFile
			check = doCommandAndLog(cmd);
			writer.append(check);
			if(check == "DONE")
				if(map[x][y] == 'H') {				// checking if Robot fall in Hole, add log HOLE
					writer.newLine();
					writer.append("HOLE");
				}
				else if(foundTreasure()) {			// checking if Robot's position same Treasure position, add log GOAL
					writer.newLine();
					writer.append("GOAL");
				}
				else if(map[x][y] == 'Y') {			// checking if checking if Robot fall in Hole contains Treasure, add log GOAL then HOLE
					writer.newLine();
					writer.append("GOAL");
					writer.newLine();
					writer.append("HOLE");
				}
				
			writer.newLine();
		}
		writer.append("BATTERY: " + Battery);			// show battery remaining
		writer.close();
		read.close();
	}
	
	// This method will write the final map into finalMapFile
	public void writeFinalMap(String finalMapFile) throws IOException {
		map[initX][initY] = ' ';		// Make the initial Robot position empty because it moves
		if(foundTreasure())			// checking if Robot at the same cell of Treasure, then change the value
			map[x][y] = 'U';
		else if(map[x][y] == 'H')		// checking if Robot at the same cell of Hole, then change the value
			map[x][y] = 'X';
		else if(map[x][y] == 'Y')
			map[x][y] = 'Z';
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(finalMapFile)); //buffer to write into finalMapFile
		for(int i = 0; i<mapY; i++) {
			for(int j = 0; j<mapX; j++) {
				writer.append(map[j][i]);
			}
			writer.newLine();
		}
		writer.close();
	}

	
	// -----------------------------


 	// THIS METHOD WILL RETURN THE SUCCESSOR STATES 
	// OF COURSE, YOU CAN & SHOULD CHANGE IT
//        public State[] successors() {
//        	State children[] = new State[2]; // we have 2 actions
//		
//		// action 1		
//
//		children[0] = new State(this);
//		if (!children[0].action1())
//			children[0] = null;
//
//		// action 2
//
//		children[1] = new State(this);
//		if (!children[1].action1())
//			children[1] = null;
//		
//        	return children;
//        }
        
       	// -----------------------------

	// ADD EXTRAS HERE ...
		
}
