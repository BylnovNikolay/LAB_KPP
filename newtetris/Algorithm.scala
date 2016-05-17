

class Algorithm {
  val I_SHAPE: Int = 0
  val J_SHAPE: Int = 1
  val L_SHAPE: Int = 2
  val O_SHAPE: Int = 3
  val S_SHAPE: Int = 4
  val T_SHAPE: Int = 5
  val Z_SHAPE: Int = 6  

  def getFigure(xs: Array[Int], toFind: Int): IndexedSeq[Int] =
    for (i <- 0 until xs.length if xs(i) == toFind) yield xs(i)

  def countIShape(xs: Array[Int]): Int = {
    getFigure(xs, I_SHAPE).length
  }

  def countJShape(xs: Array[Int]): Int = {
    getFigure(xs, J_SHAPE).length
  }
  
  def countLShape(xs: Array[Int]): Int = {
    getFigure(xs, L_SHAPE).length
  }

   def countOShape(xs: Array[Int]): Int = {
    getFigure(xs, O_SHAPE).length
  }
   
   def countSShape(xs: Array[Int]): Int = {
    getFigure(xs, S_SHAPE).length
  }
   
   def countTShape(xs: Array[Int]): Int = {
    getFigure(xs, T_SHAPE).length
  }
   
    def countZShape(xs: Array[Int]): Int = {
    getFigure(xs, Z_SHAPE).length
  }
   
  def checkIn(xs: String, ys: String): Boolean = {
    xs.contains(ys)
  }

  def findMaxRepeatedSeq(xs: Array[String]): String = {
    var min = 0
    for (elem <- xs) {
      if (elem.length < xs(min).length) min = xs.indexOf(elem)
    }
    var maxString = ""
    for (i <- 0 until xs(min).length)
      for (j <- i + 1 until xs(min).length) {
        var fl = true
        for (elem <- xs) {
          if (!checkIn(elem, xs(min).substring(i, j)))
            fl = false
        }
        if (fl && (maxString.length < xs(min).substring(i, j).length))
          maxString = xs(min).substring(i, j)
      }
    maxString
  }
}
