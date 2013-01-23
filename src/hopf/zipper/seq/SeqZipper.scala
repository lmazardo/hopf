package hopf.zipper.seq

import hopf.functional.Endo

trait SeqZipper {
  // this is actually ok, just abstype variant of F-bound
  // with type-arg it becomes SeqZipper[This <: SeqZipper[This]],
  // which looks a bit cuter, but much harder to handle in downward hierarchy
  type This <: SeqZipper { type This = SeqZipper.this.This }
  def self: This

  def isStart: Boolean
  def isEnd:   Boolean

  /// / //             ////          //////
  // Movement
  //
  def start: This
  def end:   This

  def prev: This
  def next: This

  def skipPrev(n: Int): This
  def skipNext(n: Int): This

  def skipPrevUntil(pred: This => Boolean): This = Endo[This](_.skipPrev(1)).until(pred)(self)
  def skipNextUntil(pred: This => Boolean): This = Endo[This](_.skipNext(1)).until(pred)(self)

  def skipPrevWhile(pred: This => Boolean): This = skipPrevUntil(!pred(_))
  def skipNextWhile(pred: This => Boolean): This = skipNextUntil(!pred(_))

  /// / //             ////          //////
  // Unbound movement
  //
  def prevUnbound: Option[This] = if (isStart) None else Some(prev)
  def nextUnbound: Option[This] = if (isEnd)   None else Some(next)

  /// / //             ////          //////
  // Fixpoint
  //
  def loop(f: This => Option[This]): This = f(self).map(_.loop(f)).getOrElse(self)
}