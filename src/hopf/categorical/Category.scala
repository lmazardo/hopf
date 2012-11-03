package hopf.categorical

import hopf.common.type_synonyms._

abstract class Category[->[_, _]] {
  def id[A]: A -> A
  def compose[A, B, C]: (A -> B) => (B -> C) => (A -> C)
}

object Category {  
  private implicit def polyFun[A, B] = new Category[Fun] {
    def id[A] = identity
    def compose[A, B, C] = f => g => x => g(f(x)) 
  }
  
  implicit val function = implicitly[Category[Fun]]
}