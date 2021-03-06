package io.github.chenfh5.sword1st

/**
  * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
  */
// L0169_MajorityElement
object Test29 {

  def findTargetNum(arr: Array[Int]): Int = {
    if (arr == null || arr.length < 1) throw new RuntimeException("invalid array size")

    var targetNum = arr.head
    var count = 0

    //找出最多的targetNum
    for (i <- arr.slice(1, arr.length)) {
      if (count == 0) {
        targetNum = i //key point
        count = 1
      }
      else if (targetNum == i) count += 1
      else count -= 1
    }

    //
    count = 0
    for (i <- arr) {
      if (targetNum == i) count += 1
    }

    //
    if (count > arr.length / 2) targetNum
    else throw new IllegalArgumentException("invalid input array")
  }

  def main(args: Array[String]): Unit = {
    val inputArray = Array(1, 2, 3, 2, 2, 2, 5, 4, 2)
    assert(findTargetNum(inputArray) == 2)
  }

}
