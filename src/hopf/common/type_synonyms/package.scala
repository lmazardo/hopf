package hopf.common

package object type_synonyms {
  type Fun[-A, +B] = A => B  
  type Tup[A, B] = (A, B)
}