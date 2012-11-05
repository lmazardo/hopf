package hopf.contextual

import hopf.categorical._
import hopf.util.types._
import hopf.util.types.unapply._

case class Stateful[M[_], S, A](val run: S => M[(A, S)])

object Stateful {

  /*
  def get[M, S](implicit M: Unapply[Monad, M]) =
    Stateful{s: S => M.tc.ret(s, s)}
  
  def put[M, S](s: S)(implicit M: Unapply[Monad, M]) =
    Stateful{_: S => M.tc.ret((), s)}

  implicit def monad[M, S](implicit M: Unapply[Monad, M])
  =   
  new Monad[({type L[X] = Stateful[M.Con, S, X]})#L] {
    
    override def ret[A] = x =>
      Stateful(s => M.tc.ret(x, s))
      
    override def bind[A, B] = stc => f =>
      Stateful { s =>
        stc.run(s) >>= {case (r,ns) => f(r).run(ns)}
      }
  } */
  // ^ here i finally decided to stop this madness until scala get higher order constructor unification  
  
  /*
  implicit def joinable[S]    = monad[S].joinable
  implicit def applicative[S] = monad[S].applicative
  implicit def pointable[S]   = applicative[S].pointable
  implicit def functor[S]     = applicative[S].functor
  */
}