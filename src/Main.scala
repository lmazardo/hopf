import hopf.common._
import hopf.categorical._
import hopf.categorical.Arrow._

import TypeSynonyms._
import IntShorthands._

object Main extends App {
  val cat = implicitly[Category[Fun]]
  import cat._
  
  val arr = implicitly[Arrow[Fun, Tup] with Split[Fun, Tup]]
  import arr._
  
  import Functor.listFunctor._
  
  def ys = List((1, 2), (2, 3))
    .fmap(add(1) *** mul(2))
    .foldLeft((0, 1)){ case (acc, (x, y)) =>
      (add(x) *** mul(y))(acc)
    }
  
  val moo = (add(1) *** mul(2)) >> println _
  
  moo(ys)
}