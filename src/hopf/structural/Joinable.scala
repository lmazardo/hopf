package hopf.structural

trait Joinable[S[_]] {
  def join[A]: S[S[A]] => S[A]
}

object Joinable {
  implicit val list = new Joinable[List] {
    def join[A] = _.flatten
  }
  
  implicit val option = new Joinable[Option] {
    def join[A] = _ match {
      case Some(x:Option[A]) => x
      case None              => None
    }
  }
}