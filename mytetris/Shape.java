package com.ssaurel.mytetris;

import java.util.Random;

public class Shape {
  private final static int CORD_COUNT = 4;
  private final static int COUNTER = 2;
  enum Tetrominos {
	//coordinates of shapes 
	NoShape(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 } , { 0, 0 } }), 
	ZShape(new int[][] { { 0,-1 }, { 0, 0 }, { -1, 0 } , { -1, 1 } }),
	SShape(new int[][] { { 0,-1 }, { 0, 0 }, { 1, 0 } , { 1, 1 } } ),
	LineShape(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 } , { 0, 2 } }),
	TShape(new int [][] { { -1, 0 }, { 0, 0 }, { 1, 0 } , { 0, 1 } }),
	SquareShape(new int [][] { { 0, 0 }, { 1, 0 }, { 0, 1 } , { 1, 1 } }),
	LShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 } , { 0, 1 } }),
	MirroredLShape(new int [][] { { 1 , -1 }, { 0, -1 }, { 0, 0 } , { 0, 1 } });	
		
  public int[][] coords;
		
  private Tetrominos(int[][] coords) {
	this.coords = coords;
    }
  }
	
  private Tetrominos pieceShape;
  private int[][] coords;
  
  //default constructor
  public Shape() {				
	coords = new int [CORD_COUNT][COUNTER];
	setShape(Tetrominos.NoShape); 
  }

  //create shape
  public void setShape(Tetrominos shape) {
	for(int i = 0; i < CORD_COUNT; i++) {
		for(int j = 0; j < COUNTER; ++j) {
			coords[i][j] = shape.coords[i][j];
	    }
    }		
		pieceShape = shape;
  }
	
  //establish coordinate by x
  private void setX(int index, int x) {
	coords[index][0] = x;
  }
 //establish coordinate by y
  private void setY(int index, int y) {
	coords[index][1] = y;
  }	
  public int x(int index) {
	return coords[index][0];		
  }	
  public int y(int index) {
	return coords[index][1];		
  }	
  //get current shape	
  public Tetrominos getShape() {
	return pieceShape;		
  }
  //generate random shape
  public void setRandomShape() {
	Random r = new Random();
	int x = Math.abs(r.nextInt()) % 7 + 1;
	Tetrominos[] values = Tetrominos.values();
	setShape(values[x]);
  }
	
  public int minX() {
	int m = coords[0][0];
		
	for(int i = 0; i < 4; i++) {
		m = Math.min(m, coords[i][0]);
	}		
	return m;
  }	
  public int minY() {
	int m = coords[0][1];		
	for(int i = 0; i < 4; i++) {
		m = Math.min(m, coords[i][1]);
	}		
	return m;
  }
  //rotate figure on left	
  public Shape rotateLeft() {
	if (pieceShape == Tetrominos.SquareShape) {
	  return this;
	}
    Shape result = new Shape();
	result.pieceShape = pieceShape;		
	for (int i = 0; i < 4; i++) {
	  result.setX(i, y(i));
	  result.setY(i, -x(i));			
	} 		
    return result;
  }
  //rotate figure on right
  public Shape rotateRight() {
	if (pieceShape == Tetrominos.SquareShape) {
		return this;
	}
	Shape result = new Shape();
	result.pieceShape = pieceShape;		
	for (int i = 0; i < 4; i++) {
		result.setX(i, -y(i));
		result.setY(i, x(i));			
	}		
	return result;
  }	
 }
