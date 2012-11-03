package hopf.categorical

import hopf.common.type_synonyms._

abstract class Category[->[_, _]] {
  def id[A]: A -> A
  def compose[A, B, C](f: A -> B, g: B -> C): A -> C
}

object Category {  
  private implicit def polyFun[A, B] = new Category[Fun] {
    def id[A] = (x: A) => x
    def compose[A, B, C](f: A => B, g: B => C) = (x: A) => g(f(x)) 
  }
  
  implicit val function = implicitly[Category[Fun]]
}