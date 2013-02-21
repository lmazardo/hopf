package hopf.zipper.generic

abstract sealed class GCtx[H, X]

case class NullCtx[X]() extends GCtx[X, X]

case class ConsCtx[H, X, R, P](
  l: LSib[H=>R],
  r: RSib[R, P],
  p: GCtx[P, X]
) extends GCtx[H, X]