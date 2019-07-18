package com.spark

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/18
  */
object Test {
  val aa: Int = {
    def test():Int ={
      println("打印一个语句")
      11
    }

    println("外部被打印了" + test())
    11
  }
}
