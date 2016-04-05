package Board_Tetris;

  public class CleanLines {
    private static int elemNr;
	private static int[] cleanLines;
	private static int cleanLineIndex;
	private static boolean[][] xRayPointer;
	private static int y;
	private static int HEIGHT = 20;
	private static int WIDTH = 10;
    private final static int SIDE = 4;
    private final static int WHITE = 100;
	private static int scored;

	public static int[] giveCleanedLines() {
	  return cleanLines;
	}

	public static void cleanLinesIfNeeded(Board board) {
	  elemNr = 0;
	  cleanLines = new int[SIDE];
	  cleanLines[0] = WHITE;
	  cleanLines[1] = WHITE;
	  cleanLines[2] = WHITE;
	  cleanLines[3] = WHITE;
	  cleanLineIndex = 0;
	  xRayPointer = Board.getBlackWhite();

	  scored = 0;
	  // STORING THE NEEDED ROWS
	  // scan all rows
	  for (int i = 0; i < HEIGHT; i++) {	      
	    // scan all columns
		for (elemNr = 0; elemNr < WIDTH; elemNr++) {
	      // if one is empty - break the loop
		  if (xRayPointer[i][elemNr] == false) {														 
		    elemNr = WHITE;
	      }
		}
		// if 10 elements are true in a row, store the number of the row;
		if (elemNr == WIDTH) {								
		  cleanLines[cleanLineIndex] = i;
	      cleanLineIndex++;
		  // score
		  scored++;
		}
	  }
	  // DELETE BLOCKS AND REMOVE FROM X-RAY
	  for (int i = 0; i < SIDE; i++) {
		// cleanLines[i] - number of the line  that is full
		if (cleanLines[i] != WHITE) { 										
		  deleteBlocks(cleanLines[i], board);
	      for (int j = 0; j < WIDTH; j++) {
		    Board.getBlackWhite()[cleanLines[i]][j] = false;
		  }
		}
	  }
	  // REMOVE FIGURES IF THEY HAVE NO BLOCKS
	  for (int i = 0; i < board.getFiguresOnBoard().size(); i++) {
		if (board.getFiguresOnBoard().get(i).getNrOfBlocks() == 0) {
		  board.getFiguresOnBoard().remove(i);
		  i--; // restart the index;
		}
	  }
    }
	
	public static void deleteBlocks(int lineNr, Board board) {
	  y = lineNr;
	  
	  for (int i = 0; i < board.getFiguresOnBoard().size(); i++) {
	    // all figures on board
		for (int j = 0; j < board.getFiguresOnBoard().get(i)
				.getNrOfBlocks(); j++) {
		  // all figure's blocks
		  if (y == board.getFiguresOnBoard().get(i).getBlock(j).getPosY()) {
			// if a block has it's y coordinate as the line that needs
			// to be clean
			board.getFiguresOnBoard().get(i).removeBlock(j);
			// restart j/nrOfBlocks has changed and their index!
			j = -1;
		  }
		}
	  }
	}
	
	public static void putDownBlocksIfNeeded(Board board) {
	  for (int i = 0; i < SIDE; i++) {
	    if (cleanLines[i] != WHITE) {
	      // scan all figures
		  for (int j = 0; j < board.getFiguresOnBoard().size(); j++) {
		    for (int j2 = 0; j2 < board.getFiguresOnBoard().get(j)
					.getNrOfBlocks(); j2++) { // scan all blocks
			  int currentYofBlock = board.getFiguresOnBoard().get(j)
						.getBlock(j2).getPosY();
			  // INCREASE Y POSITION OF EACH BLOCK HIGHER THAN DELETED LINE
			  if (currentYofBlock < cleanLines[i]) {																
			    board.getFiguresOnBoard().get(j).getBlock(j2)
							.setPosY((currentYofBlock + 1));
			  }
			}
		  }
		  // HERE WE'LL HAVE TO CLEAR THE X-RAY AND SET IT UP AGAIN!
		  board.refreshX_Ray();
		}
	  }
	}

	public static int getScoreNr() {
	  return scored;
	}
  }
