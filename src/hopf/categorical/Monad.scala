package hopf.categorical

import hopf.structural._

abstract class Monad[M[_]] {
  def ret[A]: A => M[A] = applicative.pure  
  
  def bind[A, B]: M[A] => (A => M[B]) => M[B] =
    mx => mf => (ret(mf) <*> mx).join
  
  val pointable = new Pointable[M] {
    def point[A] = ret
  }
  
  val applicable = new Applicable[M] {
    def apply[A, B] = mf => mx =>
      mf >>= (f => mx >>= (f >> ret))
  }
  
  val joinable = new Joinable[M] {
    def join[A] = _ >>= identity
  }  
  
  val applicative = new Applicative[M] {
    override def pure[A] = ret    
    override def apply[A, B] = applicable.apply
  }
  
  private implicit val $M: Monad[M] = this  
  private implicit val $J = joinable
  private implicit val $A = applicative
}