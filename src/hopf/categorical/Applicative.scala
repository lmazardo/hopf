package hopf.categorical

import hopf.util.types._
import hopf.structural._

abstract class Applicative[F[_]] {
  def pure[A]: A => F[A] = pointable.point  
  def apply[A, B]: F[A => B] => F[A] => F[B] = applicable.apply
  
  def seqDropLeft [A, B]: (F[A], F[B]) => F[B] = functor.fmap((x:A) => (y:B) => y)(_) <*> _
  def seqDropRight[A, B]: (F[A], F[B]) => F[A] = functor.fmap((x:A) => (y:B) => x)(_) <*> _
  
  def pointable = new Pointable[F] {
    def point[A] = pure
  }
  
  def applicable = new Applicable[F] {
    def apply[A, B] = Applicative.this.apply
  }
  
  def functor = Functor(pointable, applicable)
  
  private implicit def $A: Applicative[F] = this
}

object Applicative {  
  def apply[F[_]](P: Pointable[F], A: Applicable[F], F: Functor[F]) = new Applicative[F] {
    override def pointable  = P    
    override def applicable = A
    override def functor    = F
  }
  
  implicit def infer[F[_]](implicit P: Pointable[F], A: Applicable[F], F: Functor[F]) = Applicative(P, A, F)
}


