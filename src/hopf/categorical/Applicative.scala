package hopf.categorical

import hopf.common.type_synonyms._
import hopf.structural._

abstract class Applicative[F[_]] extends Applicable[F] {
  def pure[A]: A => F[A] = pointable.point  
  def apply[A, B] = applicable.apply
  
  def seqDropLeft [A, B]: (F[A], F[B]) => F[B] = functor.fmap((x:A) => (y:B) => y)(_) <*> _
  def seqDropRight[A, B]: (F[A], F[B]) => F[A] = functor.fmap((x:A) => (y:B) => x)(_) <*> _
  
  implicit class ApplyApplicativeEnriched[A, B](f: F[A => B]) {
    def <*>(x: F[A]) = apply(f, x)
  }
  
  implicit class SeqApplicativeEnriched[A](a: F[A]) {
    def *>[B](b: F[B]) = seqDropLeft (a, b)
    def <*[B](b: F[B]) = seqDropRight(a, b)    
  }
  
  lazy val pointable = new Pointable[F] {
    def point[A] = pure
  }
  
  lazy val applicable = new Applicable[F] {
    def apply[A, B] = Applicative.this.apply
  }
  
  lazy val functor = Functor.fromPointApply(pointable, applicable)
}

object Applicative {
  /*
  implicit def fromPointApplyFunctor[F[_]]
      (implicit P: Pointable[F], A: Applicable[F], F: Functor[F])
    =
  new Applicative[F] {
    import F._
    
    override lazy val asPoint   = P    
    override lazy val applicable   = A
    override lazy val asFunctor = F
    
    def pure[A](x: A) = P.point(x)
    def apply[A, B](f: F[A => B], x: F[A]) = A.apply(f, x)
    def seqDropLeft [A, B](a: F[A], b: F[B]) = a.fmap(x => (y:B) => y) <*> b
    def seqDropRight[A, B](a: F[A], b: F[B]) = a.fmap(x => (y:B) => x) <*> b
  }*/
}


