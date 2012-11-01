package hopf.categorical

import hopf.common.TypeSynonyms._

abstract class Category[->[_, _]] {
  def id[A]: A -> A
  def compose[A, B, C](f: A -> B, g: B -> C): A -> C
  
  implicit class ComposeEnriched[A, B](val f: A -> B) {
    def >>[C](g: B -> C): A -> C = compose(f, g)
  }
}

object Category {  
  implicit def funCat[A, B] = new Category[Fun] {
    def id[A] = (x: A) => x
    def compose[A, B, C](f: A => B, g: B => C) = (x: A) => g(f(x)) 
  }
}