package hopf.categorical

import hopf.structural._
import Category.function._

abstract class Monad[M[_]] extends Joinable[M] {
  def ret[A]: A => M[A] = applicative.pure  
  
  def bind[A, B]: (M[A], A => M[B]) => M[B] = {
    import applicative._
    (mx, mf) => join(ret(mf) <*> mx)
  }
  
  def join[A] = joinable.join
  
  implicit class BindEnriched[A](a: M[A]) {
    def >>=[B](f: A => M[B]) = bind(a, f)
  }
  
  implicit class JoinEnriched[A](a: M[M[A]]) {
    def join = Monad.this.join(a)
  }
  
  lazy val pointable = new Pointable[M] {
    def point[A] = ret
  }
  
  lazy val applicable = new Applicable[M] {
    def apply[A, B] = (mf, mx) => mf >>= (f => mx >>= (f >> ret))
  }
  
  lazy val joinable = new Joinable[M] {
    def join[A] = _ >>= identity
  }
  
  lazy val applicative = new Applicative[M] {
    override def pure[A] = ret    
    override def apply[A, B] = applicable.apply
  }
}