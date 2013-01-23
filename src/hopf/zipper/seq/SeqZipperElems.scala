package hopf.zipper.seq

import hopf.categorical.Point

trait SeqZipperElems[Elem] { self: SeqZipper =>
  type Repr[X]

  def prefix: Repr[Elem]
  def elem:   Elem
  def suffix: Repr[Elem]

  def skipPrevUntilE(pred: Elem => Boolean): This = skipPrevWhileE(!pred(_))
  def skipNextUntilE(pred: Elem => Boolean): This = skipNextWhileE(!pred(_))

  def skipPrevWhileE(pred: Elem => Boolean): This = skipPrevUntilE(!pred(_))
  def skipNextWhileE(pred: Elem => Boolean): This = skipNextUntilE(!pred(_))

  def wrapPrefix(f: Repr[Elem] => Elem)/*(implicit P: Point[Repr])*/: This
  def wrapSuffix(f: Repr[Elem] => Elem)/*(implicit P: Point[Repr])*/: This

  def wrapPrefixInclusive(f: Elem => Repr[Elem] => Elem): This
  def wrapSuffixInclusive(f: Elem => Repr[Elem] => Elem): This

  def mapElem  (f: Elem => Elem): This
  def mapPrefix(f: Elem => Elem): This
  def mapSuffix(f: Elem => Elem): This
}