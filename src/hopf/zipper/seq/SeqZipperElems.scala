package hopf.zipper.seq

trait SeqZipperElems[Elem] {
  type This <: SeqZipper
  type Repr[X]

  def prefix: Repr[Elem]
  def elem:   Elem
  def suffix: Repr[Elem]

  def skipPrevUntilE(pred: Elem => Boolean): This = skipPrevWhileE(!pred(_))
  def skipNextUntilE(pred: Elem => Boolean): This = skipNextWhileE(!pred(_))

  def skipPrevWhileE(pred: Elem => Boolean): This = skipPrevUntilE(!pred(_))
  def skipNextWhileE(pred: Elem => Boolean): This = skipNextUntilE(!pred(_))
}