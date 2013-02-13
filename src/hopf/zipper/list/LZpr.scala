package hopf.zipper.list

import hopf.zipper.SeqZpr

final class LZpr[Elem] (
  val prefix: List[Elem],
  val elem:   Elem,
  val suffix: List[Elem]
)
extends LZprTpl[Elem] with SeqZpr.Functor[Elem] {
  type ThisPoly[X] = LZpr[X]
  type This        = ThisPoly[Elem]

  def self = this

  def map[X](f: Elem => X) = mkPoly[X](prefix.map(f), f(elem), suffix.map(f))

  def mkPoly[E](prefix: List[E], elem: E, suffix: List[E]) =
    new LZpr[E](prefix, elem, suffix)

  def mk(prefix: List[Elem], elem: Elem, suffix: List[Elem]) =
    mkPoly(prefix, elem, suffix)
}