package HeadPackage;

class Algorithm {
  val I_SHAPE: Int = 0
  val J_SHAPE: Int = 1
  val L_SHAPE: Int = 2
  val O_SHAPE: Int = 3
  val S_SHAPE: Int = 4
  val T_SHAPE: Int = 5
  val Z_SHAPE: Int = 6  

  def getFigure(ls: Array[Int], toFind: Int): IndexedSeq[Int] =
    for (i <- 0 until ls.length if ls(i) == toFind) yield ls(i)

  def countIShape(ls: Array[Int]): Int = {
    getFigure(ls, I_SHAPE).length
  }

  def countJShape(ls: Array[Int]): Int = {
    getFigure(ls, J_SHAPE).length
  }
  
  def countLShape(ls: Array[Int]): Int = {
    getFigure(ls, L_SHAPE).length
  }

   def countOShape(ls: Array[Int]): Int = {
    getFigure(ls, O_SHAPE).length
  }
   
   def countSShape(ls: Array[Int]): Int = {
    getFigure(ls, S_SHAPE).length
  }
   
   def countTShape(ls: Array[Int]): Int = {
    getFigure(ls, T_SHAPE).length
  }
   
    def countZShape(ls: Array[Int]): Int = {
    getFigure(ls, Z_SHAPE).length
  }
   
  def checkIn(ls: String, ys: String): Boolean = {
    ls.contains(ys)
  }
  def convert(ls:String):String = {
   val info = " "
   val LEFT = 'L';
   val RIGHT = 'R';
   val DOWN = 'D';
   val DROP = 'P';
   val ROTATE = 'V';
   for (elem <- ls) {
     if(elem == LEFT) {
        info + "LEFT ";
      }
      if(elem == RIGHT) {
        info + "RIGHT ";
      }
      if(elem == DOWN) {
    	info + "DOWN ";
      }
      if(elem == DROP) {
    	info + "DROP ";    		
      }
      if(elem == ROTATE) {
        info + "ROTATE ";
      }
   }
   info
  }
  
  def findLine(ls: Array[String]): String = {    
    var Max = ""
    var min = 0
    for (elem <- ls) {
      if (elem.length < ls(min).length) min = ls.indexOf(elem)
    }    
    for (i <- 0 until ls(min).length)
      for (j <- i + 1 until ls(min).length) {
        var fl = true
        for (elem <- ls) {
          if (!checkIn(elem, ls(min).substring(i, j)))
            fl = false
        }
        if (fl && (Max.length < ls(min).substring(i, j).length))
          Max = ls(min).substring(i, j)
      }
    Max
  }
}
