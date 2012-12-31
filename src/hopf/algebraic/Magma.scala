package hopf.algebraic

trait Magma {
  type Carrier
  val op: (Carrier, Carrier) => Carrier
  
  implicit class EnrichedWith_<>(x: Carrier) {
    def <>(y: Carrier) = Magma.this.op(x, y)
  }
}

object Magma {
  def apply[C](fn: (C, C) => C) = new Magma {
    type Carrier = C
    val op = fn
  }
  
  class Properties(m: Magma)
  
  implicit class AsMagma[C](f: (C, C) => C) {
    def asMagma = Magma[C](f)
  }
}