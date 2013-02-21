package hopf.algebraic

trait Ring extends Semiring {
  override val sum: AbelianGroup { type Carrier = Ring.this.Carrier }
}