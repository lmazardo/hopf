import hopf.categorical._

import hopf.common.type_synonyms._
import hopf.common.numeric._

object Main extends App {  
  val arr = implicitly[Arrow[Fun, Tup] with Split[Fun, Tup]]
  import arr._
  
  def ys = List((1, 2), (2, 3))
    .fmap (1.add *** 2.mul)    
    .foldLeft (0, 1) { case (acc, (x, y)) =>
      (x.add *** y.mul)(acc)
    }
  
  val moo = (1.add *** 2.mul) >> println _
  moo(ys)
}