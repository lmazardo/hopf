import hopf.zipper.generic._

case class T(a: Int, b: Int)

object Main extends App {
  val l = ConsLS(UnitLS{a: Int => b: Int => T(a, b)}, 1)
  val c = ConsCtx(l, NullRS(), NullCtx[Int]())
  val z = GZpr(2, c)

  import hopf.macros.zipper.ast._
  // println(hopf.macros.zipper.ast.declsOf[T])
  // println(hopf.macros.zipper.ast.test)
  println(fff)
  //println(hopf.macros.zipper.ast.showRawTree((x: Any) => x))
  /*println(hopf.macros.zipper.ast.showRawTree(new MkLSib[T] {
    def apply(t: T) = ConsLS(ConsLS(
      UnitLS{a: Int => b: Int => T(a, b)},
      12),
      24)
  }))*/

  // println(z.x)
  // println(z.up.map(_.x))
}