package hopf.structural

import hopf.common.type_synonyms._

trait Applicable[S[_]] {
  def apply[A, B]: S[A => B] => S[A] => S[B]  
}

object Applicable {
  implicit val list = new Applicable[List] {
    def apply[A, B] = fs => xs =>
      fs.map(f => xs.map(f)).join
  }
  
  implicit val option = new Applicable[Option] {
    def apply[A, B] = of => ox =>
      for (f <- of; x <- ox) yield f(x)
  }
}