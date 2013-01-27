package hopf.zippers.ast

import scala.reflect.macros.Context
import hopf.zippers.list._

final class AstZippers[C <: Context] private (val context: C) {
  import context.universe.{treeCopy => tc, _}

  final class StatementZipper private (
    block:      Block,
    val prefix: List[Tree],
    val elem:   Tree,
    val suffix: List[Tree]
  )
  extends ListZipperTemplate[Tree] {
    type This = StatementZipper

    def self = this

    def mk(prefix: List[Tree], elem: Tree, suffix: List[Tree]) =
      new StatementZipper(block, prefix, elem, suffix)

    def toBlock = {
      val stmts = toList
      tc.Block(block, stmts.init, stmts.last)
    }
  }

  object StatementZipper {
    def ofBlock(block: Block) = new StatementZipper(
      block, Nil, block.stats.head,
      block.stats.tail :+ block.expr
    )
  }
}

object AstZippers {
  def withinContext[C <: Context](context: C) = new AstZippers[C](context)
}

object l {
  val Z = AstZippers.withinContext(???)
  val z = Z.StatementZipper.ofBlock(???)
  val x = z.next.map(_.next)

  /*
     [ Macro-CPS with Zippers (dreamy ver.) ]

  def cpsStmts = StatementZipper.ofBlock(b).end
    . attach(extractShiftyTerms).guarded {
    _ skipPrevWhileA(_.bindings.isEmpty).map {
    _ wrapSuffix(cpsMap(_, _))
    . loop {
      _ skipPrevWhileA(_.bindings.isEmpty).map {
      _ wrapSuffix(cpsFlatMap(_, _))
    }}}} toBlock

     [ Add log call after every ValDef in Block ]

   StatementZipper.ofBlock(b).loop {
     _ skipNextUntil(_.isValDef).map {
     _ insertNext(someLogCall)
   }} toBlock

 */
}