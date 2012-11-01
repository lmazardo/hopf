package hopf.structural

import hopf.common.TypeSynonyms._
import hopf.categorical.Functor._

trait Apply[S[_]] {
  def apply[A, B](f: S[A => B], x: S[A]): S[B]  
}

object Apply {
  implicit val funListApply = new Apply[List] {
    def apply[A, B](fs: List[A => B], xs: List[A]) = fs.zip(xs).map{case (f,x) => f(x)}
  }
  
  implicit val funOptionApply = new Apply[Option] {
    def apply[A, B](f: Option[A => B], x: Option[A]) = for (g <- f; y <- x) yield g(y)
  }
}