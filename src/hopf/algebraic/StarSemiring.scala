package hopf.algebraic

trait StarSemiring extends Semiring {
  def star: Carrier => Carrier

  implicit class Asteration(x: Carrier) {
    def \* : Carrier = star(x)
  }
}