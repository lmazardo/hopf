package hopf.algebraic

import hopf.algebraic.properties._
import scala.math.Numeric

abstract class Semigroup[T] extends Magma[T] with Associativity

object Semigroup {
  val intSum = new Semigroup[Int] { def op = _ + _ }  
  val intMul = new Semigroup[Int] { def op = _ * _ }  
}