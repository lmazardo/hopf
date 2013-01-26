package hopf.zipper.seq

import hopf.functional.Endo

trait SeqZipper {
  // this is actually ok, just abstype variant of F-bound
  // with type-arg it becomes SeqZipper[This <: SeqZipper[This]],
  // which looks a bit cuter, but much harder to handle in downward hierarchy
  type This <: SeqZipper { type This = SeqZipper.this.This }
  def self: This

  /// / //             ////          //////
  // Movement
  //
  def start: This
  def end:   This

  def prev: Option[This]
  def next: Option[This]

  def skipPrev(n: Int): Option[This] = Endo[This](_.prev).times(n)(self)
  def skipNext(n: Int): Option[This] = Endo[This](_.next).times(n)(self)

  def skipPrevUntil(pred: This => Boolean): Option[This] = Endo[This](_.skipPrev(1)).until(pred)(self)
  def skipNextUntil(pred: This => Boolean): Option[This] = Endo[This](_.skipNext(1)).until(pred)(self)

  def skipPrevWhile(pred: This => Boolean): Option[This] = skipPrevUntil(!pred(_))
  def skipNextWhile(pred: This => Boolean): Option[This] = skipNextUntil(!pred(_))

  /// / //             ////          //////
  // Bounded movement
  //
  def prevBounded: This = prev.getOrElse(self)
  def nextBounded: This = next.getOrElse(self)

  /// / //             ////          //////
  // Other
  //
  def isStart: Boolean
  def isEnd:   Boolean

  def loop(f: This => Option[This]): This = f(self).map(_.loop(f)).getOrElse(self)
}