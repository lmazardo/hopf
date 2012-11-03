package hopf.structural

trait Pointable[P[_]] {
  def point[A]: A => P[A]
}

object Pointable {
  implicit val list = new Pointable[List] {
    def point[A] = List(_)
  }
  
  implicit val option = new Pointable[Option] {
    def point[A] = Some(_)
  }
}