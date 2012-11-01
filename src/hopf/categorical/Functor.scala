package hopf.categorical

import hopf.structural._

abstract class Functor[F[_]] {
  def fmap[A, B](f: A => B): F[A] => F[B]
  
  implicit class FunctorEnrichedPoint[A](x: F[A]) {
    def fmap[B](f: A => B) = Functor.this.fmap(f)(x)
  }
  
  implicit class FunctorEnrichedMorphism[A, B](f: A => B) {
    def ^>(x: F[A]) = Functor.this.fmap(f)(x)
  }
}

object Functor {
  def fromPointApply[F[_]](P: Point[F], A: Apply[F]) = new Functor[F] {
    def fmap[A, B](f: A => B) = A.apply(P.point(f), _)
  }
  
  implicit val listFunctor = new Functor[List] {
    def fmap[A, B](f: A => B) = {
      case Nil     => Nil
      case x :: xs => f(x) :: xs.fmap(f)
    }
  }
  
  implicit val optionFunctor = new Functor[Option] {
    def fmap[A, B](f: A => B) = {
      case None    => None
      case Some(x) => Some(f(x))
    }
  }
}