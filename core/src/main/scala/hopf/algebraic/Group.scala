package hopf.algebraic

trait Group extends Monoid {
  val inv: Carrier => Carrier
  // prop inv
}

object Group {
  def apply[C](_1: C)(_inv: C => C)(fn: (C, C) => C) = new Group {
    type Carrier = C
    val op  = fn
    val id  = _1
    val inv = _inv
  }

  implicit class AsGroup[C](f: (C, C) => C) {
    def asGroup(_1: C)(_inv: C => C) = Group[C](_1)(_inv)(f)
  }
}