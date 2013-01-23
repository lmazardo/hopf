package hopf.zipper.seq.product

import hopf.zipper.seq.SeqZipper

abstract class SeqZipperWiredProduct[Z1 <: SeqZipper[Z1], Z2 <: SeqZipper[Z2]]
  (fst: Z1, snd: Z2)
extends SeqZipperProduct[Z1, Z2, SeqZipperWiredProduct[Z1, Z2]](fst, snd) {

  def start = build(fst.start, snd.start)
  def end   = build(fst.end,   snd.end  )

  def isStart = fst.isStart
  def isEnd   = fst.isEnd

  def prev = build(fst.prev, snd.prev)
  def next = build(fst.next, snd.next)

  def skipPrev(n: Int) = build(fst.skipPrev(n), snd.skipPrev(n))
  def skipNext(n: Int) = build(fst.skipNext(n), snd.skipNext(n))
}