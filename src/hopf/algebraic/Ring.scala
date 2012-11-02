package hopf.algebraic

import hopf.algebraic.properties._

abstract class Ring[R] extends Additivity[R] with Multiplicativity[R] {
      
  def add = sumGroup.op
  def mul = mulMonoid.op
  
  def sub = (a: R, b: R) => add(a, neg(b))
  
  def neg: R => R = sumGroup.inverse
    
  val zero: R = sumGroup.id
  val one:  R = mulMonoid.id
  
  implicit class RingEnriched(a: R) {
    def +(b: R): R = add(a, b)
    def *(b: R): R = mul(a, b)
  }
  
  lazy val sumGroup = new Group[R] with Commutativity {
    def op = add
    val id = zero
    def inverse = (x: R) => add(id, neg(x))
  }
  
  lazy val mulMonoid = new Monoid[R] {
    def op = mul
    val id = one
  }
}

object Ring {
  def apply[R](grp: Group[R] with Commutativity, mon: Monoid[R]) = new Ring[R] {
    override lazy val sumGroup  = grp
    override lazy val mulMonoid = mon      
  }
  
  val intRing = Ring(Group.intSum, Monoid.intMul)
}