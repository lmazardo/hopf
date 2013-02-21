package hopf.algebraic

trait Semiring {
  type Carrier

  val sum: AbelianMonoid { type Carrier = Semiring.this.Carrier }
  val mul: Semigroup     { type Carrier = Semiring.this.Carrier }

  // prop distr[mul/sum]

  implicit class Ops(x: Carrier) {
    def <+>(y: Carrier): Carrier = sum.op(x, y)
    def <*>(y: Carrier): Carrier = mul.op(x, y)
  }
}