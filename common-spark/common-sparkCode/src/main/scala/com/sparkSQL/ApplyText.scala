package com.sparkSQL

/**
  * Description: 
  *
  * @author linjh
  * @date 2018/10/24
  */
class ApplyText(val str: String) {
  val ljh = ApplyText.plan("hahah")
  def xx: String = "111"
}

object ApplyText {
  def apply(str: String): Demo = new Demo()

  val my = plan("1111");

  def plan(str: String): Unit = {
    println(str)
  }
}