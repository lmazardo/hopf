package hopf.algebraic

trait Semigroup extends Magma

object Semigroup {
  def apply[C](fn: (C, C) => C) = new Semigroup {
    type Carrier = C
    val op = fn
  }

  class Properties(s: Semigroup) extends Magma.Properties(s) {
    // prop assoc
  }

  implicit class AsSemigroup[C](f: (C, C) => C) {
    def asSemigroup = Semigroup[C](f)
  }
}