package hopf.zippers.list

import hopf.zipper.seq.SeqZipperMap
import hopf.categorical.Functorial

trait ListZipperMapTpl[Elem] extends SeqZipperMap[Elem] {
  self: ListZipperTpl[Elem] =>

  abstract override def mk(prefix: List[Elem], elem: Elem, suffix: List[Elem]) =
    mkG(prefix, elem, suffix)

  def mkG[E](prefix: List[E], elem: E, suffix: List[E]): ThisG[E]

  def map[X](f: Elem => X) = mkG[X](
    self.prefix.map(f),
    f(self.elem),
    self.suffix.map(f)
  )
}