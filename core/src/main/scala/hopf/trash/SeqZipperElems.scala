package hopf.trash
/*
trait SeqZipperElems[Elem] { // self: SeqZipper =>
  type This <: SeqZipper with SeqZipperElems[Elem] { type This = SeqZipperElems.this.This }
  def self: This

  type Repr[X]

  def prefix: Repr[Elem]
  def elem:   Elem
  def suffix: Repr[Elem]

  /// / //             ////          //////
  // Element-dependent movement
  //
  def skipPrevUntilE(pred: Elem => Boolean): Option[This] = self.skipPrevUntil(z => pred(z.elem))
  def skipNextUntilE(pred: Elem => Boolean): Option[This] = self.skipNextUntil(z => pred(z.elem))

  def skipPrevWhileE(pred: Elem => Boolean): Option[This] = skipPrevUntilE(!pred(_))
  def skipNextWhileE(pred: Elem => Boolean): Option[This] = skipNextUntilE(!pred(_))

  /// / //             ////          //////
  // Wrappings
  //
  def wrapPrefix(f: Repr[Elem] => Elem)/*(implicit P: Point[Repr])*/: This
  def wrapSuffix(f: Repr[Elem] => Elem)/*(implicit P: Point[Repr])*/: This

  def wrapPrefixInclusive(f: Elem => Repr[Elem] => Elem): This
  def wrapSuffixInclusive(f: Elem => Repr[Elem] => Elem): This

  /// / //             ////          //////
  // Mappings
  //
  def mapElem  (f: Elem => Elem): This
  def mapPrefix(f: Elem => Elem): This
  def mapSuffix(f: Elem => Elem): This

  /// / //             ////          //////
  // Insertions
  //
  def insertPrev(x: Elem): This
  def insertNext(x: Elem): This

  def insertPrevMany(xs: Repr[Elem]): This
  def insertNextMany(xs: Repr[Elem]): This
}*/