package hopf.algebraic.properties

trait Invertibility[T] {
  def inverse: T => T
}