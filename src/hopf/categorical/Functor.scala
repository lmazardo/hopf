package hopf.categorical

import hopf.structural._
import hopf.common.functional._

abstract class Functor[F[_]] {
  def fmap[A, B]: (A => B) => F[A] => F[B]
}

object Functor {  
  def apply[F[_]](P: Pointable[F], A: Applicable[F]) = new Functor[F] {
    def fmap[A, B] = f =>
      A.apply(P.point(f))(_)
  }
 
  implicit val list = new Functor[List] {
    def fmap[A, B] = f => {
      case Nil     => Nil
      case x :: xs => f(x) :: fmap(f)(xs)
    }
  }
  
  implicit val option = new Functor[Option] {
    def fmap[A, B] = f => {
      case None    => None
      case Some(x) => Some(f(x))
    }
  }
}