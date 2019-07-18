package datastream

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
  *
  * @author linjh
  * @date 2019/2/13 11:26
  */
object WindowWordCount {
  def main(args: Array[String]):Unit ={

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("flu03",12019)
    // 需要隐式转换，否则flatMap会报错
    import org.apache.flink.api.scala._
    val counts = text.flatMap(_.toLowerCase.split("\\W+") filter(_.nonEmpty))
      .map((_,1))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)
    counts.print()
    env.execute("window stream wordcount")
  }
}
