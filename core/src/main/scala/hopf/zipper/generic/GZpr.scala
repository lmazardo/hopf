package hopf.zipper.generic

case class GZpr[H, R](x: H, c: GCtx[H, R]) {
  def left = c match {
    case NullCtx()                   => None
    case ConsCtx(UnitLS(_), _, _)    => None
    case ConsCtx(ConsLS(l, y), r, p) =>
      Some(GZpr(y, ConsCtx(l, ConsRS(x, r), p)))
  }

  def right = c match {
    case NullCtx()                   => None
    case ConsCtx(_, NullRS(), _)     => None
    case ConsCtx(l, ConsRS(y, r), p) =>
      Some(GZpr(y, ConsCtx(ConsLS(l, x).asInstanceOf, r, p)))
  }

  def up = c match {
    case NullCtx()        => None
    case ConsCtx(l, r, p) =>
      Some(GZpr(GZpr.combine(l, x, r), p))
  }

  def down(implicit mkLSib: MkLSib[H]) = mkLSib(x) match {
    case UnitLS(_)    => None
    case ConsLS(l, x) => ???
  }
}

object GZpr {
  def combine[H, R, P](l: LSib[H => R], h: H, r: RSib[R, P]) =
    r.unpack(l.unpack(h))
}