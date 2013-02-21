package hopf.zipper.ast

import scala.reflect.macros.Context
import hopf.zipper.list.LZprTpl

case class StmtZprWithin[C <: Context](ctx: C) {
  import ctx.universe._

  case class StmtZpr (
    block:  Block,
    prefix: List[Tree],
    elem:   Tree,
    suffix: List[Tree]
  ) extends LZprTpl[Tree]
  {
    type This = StmtZpr
    def  self = this

    def mk(p: List[Tree], e: Tree, s: List[Tree]) = StmtZpr(block, p, e, s)

    def toBlock = {
      val stmts = toList
      treeCopy.Block(block, stmts.init, stmts.last)
    }
  }

  object StmtZpr {
    def ofBlock(b: Block) =
      StmtZpr(b, Nil, b.stats.head, b.stats.tail :+ b.expr)
  }
}