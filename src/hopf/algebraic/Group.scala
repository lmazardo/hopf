package hopf.algebraic

import hopf.algebraic.properties._

abstract class Group[T] extends Monoid[T] with Invertibility[T]

object Group {
  def apply[T](m: Monoid[T], inv: T => T) = new Group[T] {
    def op = m.op
    val id = m.id
    def inverse = inv
  }
  
  def apply[T](m: Monoid[T], inv: T => T, commPrf: Commutativity.Proof) = new Group[T] with Commutativity {
    def op = m.op
    val id = m.id
    def inverse = inv
  }
  
  val intSum = Group(Monoid.intSum, -(_:Int), Commutativity.Dummy())
}