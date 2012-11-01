import hopf.common.TypeSynonyms._
import hopf.categorical._

object Main extends App {
  val cat = implicitly[Category[Fun]]
  import cat._
    
  val kokoko = ((x: Int) => 1 + x) >> (_ * 2) >> { z =>    
    println(z)
    println("i see dead people")
  }
  
  val c = (x: Int) => (x, x)
  
  kokoko(3)
}