package hopf.syntactical.ast

import scala.reflect.macros.Context
import hopf.functional.Endo
import hopf.zippers.list._

class Zippers[C <: Context] private (val context: C) {
  import context.universe.{treeCopy => tc, _}

  class StatementZipper private (
    block:      Block,
    prefix: List[Tree],
    elem:   Tree,
    suffix: List[Tree]
  )
  extends ListZipperTpl[Tree](prefix, elem, suffix) {

    type This = this.type

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

object Zippers {
  def inContext[C <: Context](context: C) = new Zippers[C](context)
}

object l {
  val Z = Zippers inContext ???
  import Z._
  val z = StatementZipper ofBlock ???
  val x = z.next.next

  /*
     [ macro-cps with zippers (dreamy ver.) ]

  def cpsStmts = StatementZipper.ofBlock(b).end
    . attach(extractShiftyTerms)
    . skipPrevWhile(_._2.bindings.isEmpty)
    . wrapSuffix(cpsMap(_, _))
    . loop {
      _ skipPrevGuardedWhile(_.bindings.isEmpty)
      . map(wrapSuffix(cpsFlatMap(_, _)))
    } toBlock

 */
}