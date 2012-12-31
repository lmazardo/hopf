package hopf.algebraic

trait Ring {
  type Carrier
  val sum: Abelian   { type Carrier = Ring.this.Carrier }
  val mul: Semigroup { type Carrier = Ring.this.Carrier }
}