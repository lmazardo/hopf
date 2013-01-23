package hopf.zipper.seq.product

import hopf.zipper.seq.SeqZipper

abstract class SeqZipperProduct[Z1 <: SeqZipper[Z1],
                                Z2 <: SeqZipper[Z2],
                                Z  <: SeqZipperProduct[Z1, Z2, Z]]
  (fst: Z1, snd: Z2)
extends SeqZipper[SeqZipperProduct[Z1, Z2, Z]] with Product {

  def build(fst: Z1, snd: Z2): Z

  def productElement(n: Int) = n match {
    case 0 => fst
    case 1 => snd
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  def productArity = 2
}