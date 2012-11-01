package hopf.algebraic.properties

import hopf.algebraic.properties._

trait Multiplicativity[T] {
  def mul: (T, T) => T
}