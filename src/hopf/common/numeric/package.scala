package hopf.common

package object numeric {
  implicit class CurriedPlusEnrichedInt(x: Int) {
    def add : Int => Int = x + _
    def sub : Int => Int = x - _
    def mul : Int => Int = x * _
  }
}