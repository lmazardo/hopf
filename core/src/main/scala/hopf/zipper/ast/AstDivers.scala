package hopf.zipper.ast

import scala.reflect.macros.Context
import hopf.zipper.generic._

case class AstDivers[C <: Context](ctx: C) {
  import ctx.universe.{treeCopy => tc, _}

  implicit val select = new MkLSib[Select] {
    def apply(s: Select) = ConsLS(ConsLS(
      UnitLS{q: Tree => nm: Name => tc.Select(s, q, nm)},
      s.qualifier),
      s.name)
  }

  implicit val typeApply = new MkLSib[TypeApply] {
    def apply(t: TypeApply) = ConsLS(ConsLS(
      UnitLS{f: Tree => a: List[Tree] => tc.TypeApply(t, f, a)},
      t.fun),
      t.args)
  }
}