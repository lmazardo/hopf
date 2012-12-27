package hopf.algebraic

trait Monoid extends Semigroup {
  def id: Carrier
}

object Monoid {
  def apply[C](fn: C => C => C, _1: C) = new Monoid {
    type Carrier = C
    def op = fn
    def id = _1
  }
}