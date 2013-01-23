package hopf.zipper.seq

import hopf.functional.Endo

trait SeqZipper {
  type This <: this.type

  def self: This

  def start: This
  def end:   This

  def isStart: Boolean
  def isEnd:   Boolean

  def prev: This
  def next: This

  def skipPrev(n: Int): This
  def skipNext(n: Int): This

  def skipPrevUntil(pred: This => Boolean): This = Endo[This](_.skipPrev(1)).until(pred)(self)
  def skipNextUntil(pred: This => Boolean): This = Endo[This](_.skipNext(1)).until(pred)(self)

  def skipPrevWhile(pred: This => Boolean): This = skipPrevUntil(!pred(_))
  def skipNextWhile(pred: This => Boolean): This = skipNextUntil(!pred(_))
}