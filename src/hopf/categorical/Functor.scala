package hopf.categorical

import hopf.structural._
import hopf.common.functional._

abstract class Functor[F[_]] {
  def fmap[A, B]: (A => B) => F[A] => F[B]
}

object Functor {
  def fromPointApply[F[_]](P: Pointable[F], A: Applicable[F]) = new Functor[F] {
    def fmap[A, B] = f =>
      A.apply(P.point(f), _)
  }
 
  implicit val listFunctor = new Functor[List] {
    def fmap[A, B] = f => {
      case Nil     => Nil
      case x :: xs => f(x) :: fmap(f)(xs) // xs.fmap(f)
    }
  }
  
  implicit val optionFunctor = new Functor[Option] {
    def fmap[A, B] = f => {
      case None    => None
      case Some(x) => Some(f(x))
    }
  }
    //implicit class FunctorEnrichedMorphism[A, B](f: A => B) {
  //  def ^>(x: F[A]) = Functor.this.fmap(f)(x)
  //}
  

}