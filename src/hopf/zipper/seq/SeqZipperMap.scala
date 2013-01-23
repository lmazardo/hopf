package hopf.zipper.seq

import hopf.categorical._

trait SeqZipperMap[Elem] {
  type ThisG[X] <: SeqZipper

  def map[X](f: Elem => X): ThisG[X]
}

/*
trait SeqZipperMap[Elem] {
  this: SeqZipperElems[Elem] {
    type Repr[X] <: Functorial[X, Repr]
  } =>

  def map[X](f: Elem => X): This[X]
}
*/