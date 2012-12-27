package hopf.algebraic

trait Magma {
  type Carrier  
  def op: Carrier => Carrier => Carrier
  
  implicit class EnrichedWith_<>(x: Carrier) {
    def <>(y: Carrier) = Magma.this.op(x)(y)
  }
}

object Magma {
  def apply[C](fn: C => C => C) = new Magma {
    type Carrier = C
    def op = fn
  }
  
  val intMagma = Magma[Int] {
    x => y => x + y
  }
}