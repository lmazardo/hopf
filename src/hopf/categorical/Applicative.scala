package hopf.categorical

import hopf.common.TypeSynonyms._
import hopf.structural._

abstract class Applicative[F[_]] {
  def pure[A](x: A): F[A]
  
  def apply[A, B](f: F[A => B], x: F[A]): F[B]
  
  def seqDropLeft [A, B](a: F[A], b: F[B]): F[B]
  def seqDropRight[A, B](a: F[A], b: F[B]): F[A]
  
  implicit class ApplyApplicativeEnriched[A, B](f: F[A => B]) {
    def <*>(x: F[A]) = apply(f, x)
  }
  
  implicit class SeqApplicativeEnriched[A](a: F[A]) {
    def *>[B](b: F[B]) = seqDropLeft (a, b)
    def <*[B](b: F[B]) = seqDropRight(a, b)    
  }
  
  implicit lazy val asPoint = new Point[F] {
    def point[A](x: A) = pure(x)
  }    
  
  implicit lazy val asApply = new Apply[F] {
    def apply[A, B](f: F[A => B], x: F[A]) = Applicative.this.apply(f, x)
  }
  
  implicit lazy val asFunctor = Functor.fromPointApply(asPoint, asApply)
}

object Applicative {  
  implicit def fromPointApplyFunctor[F[_]]
      (implicit P: Point[F], A: Apply[F], F: Functor[F])
    =
  new Applicative[F] {
    import F._
    
    override lazy val asPoint   = P    
    override lazy val asApply   = A
    override lazy val asFunctor = F
    
    def pure[A](x: A) = P.point(x)
    def apply[A, B](f: F[A => B], x: F[A]) = A.apply(f, x)
    def seqDropLeft [A, B](a: F[A], b: F[B]) = a.fmap(x => (y:B) => y) <*> b
    def seqDropRight[A, B](a: F[A], b: F[B]) = a.fmap(x => (y:B) => x) <*> b
  }
}


