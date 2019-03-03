package xmls

import scala.util.Try

trait BenchmarkSupport {
  def logMinTime[T](
    name: String,
    iterations: Int,
    log: String => Unit)(f: () => T): Option[T] = {
    var minDuration = Long.MaxValue
    var currentResult: Option[T] = None

    for (_ <- 1 to iterations) {
      val startTime = System.currentTimeMillis()
      currentResult = Try(f()).toOption
      val currentDuration = System.currentTimeMillis() - startTime

      if (currentDuration < minDuration) {
        minDuration = currentDuration
      }
    }

    log(s"minimum duration time ($name): $minDuration ms")

    currentResult
  }
}
