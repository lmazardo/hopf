import hopf.categorical._
import hopf.structural._

import hopf.common.type_synonyms._
import hopf.common.functional._
import hopf.common.numeric._

object Main extends App {  
  val arr = implicitly[Arrow[Fun, Tup] with Split[Fun, Tup]]
  import arr._
  
  val ys = List((1, 2), (2, 3))
    .fmap (1.add *** 2.mul)    
    .foldLeft (0, 1) { case (acc, (x, y)) =>
      (x.add *** y.mul)(acc)
    }
  
  val moo = (1.add *** 2.mul) >> println _
  
  val fibs: Stream[Int] = 0 #:: 1 #:: fibs.zip(fibs.tail).map{case (x,y) => x + y}
  
  def digits(x: Int) = x.iterate(_ / 10).takeWhile(_ > 0).map(_ % 10).reverse
  
  
  
  // println(digits(1234))
}