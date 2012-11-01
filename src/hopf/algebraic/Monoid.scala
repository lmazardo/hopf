package hopf.algebraic

import hopf.algebraic.properties._

abstract class Monoid[T] extends Semigroup[T] with Identity[T]

object Monoid {
  def apply[T](s: Semigroup[T], i: T) = new Monoid[T] {
    def op = s.op
    val id = i
  }
  
  val intSum = Monoid(Semigroup.intSum, 0)
  val intMul = Monoid(Semigroup.intMul, 1)
}