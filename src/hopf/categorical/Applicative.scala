package hopf.categorical

import hopf.common.type_synonyms._
import hopf.structural._

abstract class Applicative[F[_]] {
  def pure[A]: A => F[A] = pointable.point  
  def apply[A, B]: F[A => B] => F[A] => F[B] = applicable.apply
  
  def seqDropLeft [A, B]: (F[A], F[B]) => F[B] = functor.fmap((x:A) => (y:B) => y)(_) <*> _
  def seqDropRight[A, B]: (F[A], F[B]) => F[A] = functor.fmap((x:A) => (y:B) => x)(_) <*> _
  
  val pointable = new Pointable[F] {
    def point[A] = pure
  }
  
  val applicable = new Applicable[F] {
    def apply[A, B] = Applicative.this.apply
  }
  
  val functor = Functor(pointable, applicable)
  
  private implicit val $A: Applicative[F] = this
}

object Applicative {  
  def apply[F[_]](P: Pointable[F], A: Applicable[F], F: Functor[F]) = new Applicative[F] {
    override val pointable  = P    
    override val applicable = A
    override val functor    = F
  }
  
  implicit def fromPAF[F[_]](implicit P: Pointable[F], A: Applicable[F], F: Functor[F]) = Applicative(P, A, F)
}


