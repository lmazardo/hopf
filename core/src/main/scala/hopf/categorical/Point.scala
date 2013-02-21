package hopf.categorical

trait Point[C[_]] {
  def apply[X](x: X): C[X]
}

object Point {
  implicit val list = new Point[List] {
    def apply[X](x: X) = List(x)
  }
}