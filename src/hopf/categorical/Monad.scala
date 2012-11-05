package hopf.categorical

import hopf.structural._

abstract class Monad[M[_]] {
  def ret[A]: A => M[A] = applicative.pure  
  
  def bind[A, B]: M[A] => (A => M[B]) => M[B] = mx => mf =>
    (ret(mf) <*> mx).join     

  def joinable = new Joinable[M] {
    def join[A] = _ >>= identity
  }
  
  def applicative = new Applicative[M] {
    override def pure[A] = ret
    override def apply[A, B] = mf => mx =>
      mf >>= (f => mx >>= (f >> ret))
  }
  
  implicit class BindEnriched[A](a: M[A]) {
    def >>=[B](f: A => M[B]) = bind(a)(f)
  }
  
  private implicit def $M: Monad[M] = this
  private implicit def $J: Joinable[M] = joinable
  private implicit def $A: Applicative[M] = applicative
}

object Monad {
  def apply[M[_]](J: Joinable[M], A: Applicative[M]) = new Monad[M] {
    override def joinable    = J
    override def applicative = A
  }
  
  implicit def infer[M[_]](implicit J: Joinable[M], A: Applicative[M]) = Monad(J, A)
}