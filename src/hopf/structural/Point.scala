package hopf.structural

trait Point[P[_]] {
  def point[A](x: A): P[A]
  
  implicit class PointEnriched[A](x: A) {
    def point = Point.this.point(x)
  }
}

object Point {
  implicit val listPoint = new Point[List] {
    def point[A](x: A) = List(x)
  }
  
  implicit val optionPoint = new Point[Option] {
    def point[A](x: A) = Some(x)
  }
}

trait Copoint[P[_]] {
  def copoint[A](x: P[A]): A
  
  implicit class CopointEnriched[A](x: P[A]) {
    def copoint = Copoint.this.copoint(x)
  }
}