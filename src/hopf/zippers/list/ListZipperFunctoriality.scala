package hopf.zippers.list

import hopf.zipper.seq._

trait ListZipperFunctoriality[Elem] extends SeqZipperFunctoriality[Elem] { self: ListZipperTemplate[Elem] =>

  def mk(prefix: List[Elem], elem: Elem, suffix: List[Elem]) =
    mkPoly(prefix, elem, suffix)

  def mkPoly[E](prefix: List[E], elem: E, suffix: List[E]): ThisPoly[E]

  def map[X](f: Elem => X) = mkPoly[X](prefix.map(f), f(elem), suffix.map(f))
}