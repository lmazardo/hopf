package hopf.structural

trait Pointable[P[_]] {
  def point[A]: A => P[A]
  
  implicit class PointEnriched[A](x: A) {
    def point = Pointable.this.point(x)
  }
}

object Pointable {
  implicit val list = new Pointable[List] {
    def point[A] = List(_)
  }
  
  implicit val option = new Pointable[Option] {
    def point[A] = Some(_)
  }
}

trait Copoint[P[_]] {
  def copoint[A]: P[A] => A
  
  implicit class CopointEnriched[A](x: P[A]) {
    def copoint = Copoint.this.copoint(x)
  }
}