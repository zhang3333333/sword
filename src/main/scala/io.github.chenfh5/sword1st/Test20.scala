package io.github.chenfh5.sword1st

import scala.collection.mutable.ListBuffer


/**
  * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印每一个数字
  */
// L0054_SpiralOrder
object Test20 {

  var result = ListBuffer[Int]()

  def printMatrixClockWisely(arr: Array[Array[Int]]): Unit = {
    if (arr == null) return
    var row, col = 0

    while (row * 2 < arr.length && col * 2 < arr.head.length) {
      //左+右，上+下各往返2次，折返时已经把对面的打印了，所以这里要折半
      printMatrixInCircle(arr, row, col) //打印外围一圈
      row += 1
      col += 1
    }
  }

  def printMatrixInCircle(arr: Array[Array[Int]], row: Int, col: Int): Unit = {
    val (rows, cols) = (arr.length, arr.head.length)

    //left->right
    for (i <- col until cols - col) {
      print(arr(row)(i) + "_")
      result += arr(row)(i)
    }

    //top -> bottom
    for (i <- row + 1 until rows - row) {
      print(arr(i)(cols - col - 1) + "_")
      result += arr(i)(cols - col - 1)
    }

    //right->left
    for (i <- Range.inclusive(cols - col - 1 - 1, col, -1)) {
      print(arr(rows - row - 1)(i) + "_")
      result += arr(rows - row - 1)(i)
    }

    //bottom->top
    for (i <- Range.inclusive(rows - row - 1 - 1, row + 1, -1)) {
      print(arr(i)(col) + "_")
      result += arr(i)(col)
    }
  }

  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    import scala.collection.mutable.ListBuffer
    val res = ListBuffer[Int]()
    if (matrix == null || matrix.length < 1 || matrix.head.length < 1) return res.toList

    var (top, right, bottom, left) = (0, matrix.head.length - 1, matrix.length - 1, 0) // clockwise
    var done = false
    while (!done) {
      // left -> right
      for (i <- Range(left, right).inclusive) res += matrix(top)(i)
      top += 1
      done = isDone(top, right, bottom, left)

      // top -> bottom
      if (!done) {
        for (i <- Range(top, bottom).inclusive) res += matrix(i)(right) // since the upper level have add/minus 1, therefore inclusive
        right -= 1
        done = isDone(top, right, bottom, left)
      }

      // right -> left
      if (!done) {
        for (i <- Range(right, left, -1).inclusive) res += matrix(bottom)(i)
        bottom -= 1
        done = isDone(top, right, bottom, left)
      }

      // bottom -> top
      if (!done) {
        for (i <- Range(bottom, top, -1).inclusive) res += matrix(i)(left)
        left += 1
        done = isDone(top, right, bottom, left)
      }
    }
    res.toList
  }

  def isDone(top: Int, right: Int, bottom: Int, left: Int): Boolean = {
    if (left > right || top > bottom) true
    else false
  }

  def main(args: Array[String]): Unit = {
    /*
     *  int[][] matrix = {
             {1, 2, 3, 4, 5},
             {6, 7, 8, 9, 10},
             {11, 12, 13, 14, 15},
             {16, 17, 18, 19, 20}
     };
    */
    val matrix = Array(Array(1, 2, 3, 4, 5), Array(6, 7, 8, 9, 10), Array(11, 12, 13, 14, 15), Array(16, 17, 18, 19, 20))
    printMatrixClockWisely(matrix)
    println()
    println(result.mkString(","))
  }

}
