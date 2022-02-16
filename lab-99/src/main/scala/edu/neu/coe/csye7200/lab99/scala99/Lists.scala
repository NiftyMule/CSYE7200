/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.csye7200.lab99.scala99

import scala.annotation.tailrec

object P00 {
  def flatten[X](xss: List[List[X]]): List[X] = {
    @scala.annotation.tailrec
    def inner(r: List[X], wss: List[List[X]]): List[X] = wss match {
      case Nil => r
      case h :: t => inner(r ++ h, t)
    }

    inner(Nil, xss)
  }

  def fill[X](n: Int)(x: X): List[X] = {
    @scala.annotation.tailrec
    def inner(r: List[X], l: Int): List[X] = if (l <= 0) r else inner(r :+ x, l - 1)

    inner(Nil, n)
  }
}

object P01 {

//  @scala.annotation.tailrec
  def last[X](xs: List[X]): X = xs match {
    case Nil => throw new NoSuchElementException
    case h :: Nil => h
    case h :: t => last(t)
  }
}

object P02 {

//  @scala.annotation.tailrec
  def penultimate[X](xs: List[X]): X = xs match {
    case Nil => throw new NoSuchElementException
    case _ :: Nil => throw new NoSuchElementException
    case p :: _ :: Nil => p
    case _ :: t => penultimate(t)
  }
}

object P03 {

//  @scala.annotation.tailrec
  def kth[X](k: Int, xs: List[X]): X = if (k < 0) throw new NoSuchElementException else xs match {
    case Nil => throw new NoSuchElementException
    case h :: t => if (k == 0) h else kth(k - 1, t)
  }
}

object P04 {

  def length[X](xs: List[X]): Int = {
    @scala.annotation.tailrec
    def inner(xs: List[X], l: Int): Int = xs match {
      case Nil => l
      case _ :: t => inner(t, l + 1)
    }

    inner(xs, 0)
  }
}

object P05 {

  def reverse[X](xs: List[X]): List[X] = {
    @scala.annotation.tailrec
    def inner(ys: List[X], acc: List[X]): List[X] = ys match {
      case Nil => acc
      case h :: t => inner(t, h :: acc)
    }

    inner(xs, Nil)
  }
}

object P06 {

//@tailrec
def isPalindrome[X](xs: List[X]): Boolean = xs == xs.reverse
//def isPalindrome[X](xs: List[X]): Boolean = xs.take(xs.length/2) == xs.drop(xs.length/2).reverse
}

object P07 {

  type ListAny = List[Any]

  def flatten(xs: ListAny): ListAny = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P08 {

  def compress[X](xs: List[X]): List[X] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P09 {

  def pack[X](xs: List[X]): List[List[X]] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P10 {

  def encode[X](xs: List[X]): List[(Int, X)] = ??? // TO BE IMPLEMENTED
}

object P11 {

  def encodeModified[X](xs: List[X]): List[Any] = ??? // TO BE IMPLEMENTED
}

object P12 {

  def decode[X](xIs: List[(Int, X)]): List[X] = ??? // TO BE IMPLEMENTED
}

object P13 {

  def encodeDirect[X](xs: List[X]): List[(Int, X)] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P14 {

  def duplicate[X](xs: List[X]): List[X] = {
    // TO BE IMPLEMENTED
    ???
  }
}

object P15 {

  def duplicateN[X](n: Int, xs: List[X]): List[X] = {
    // TO BE IMPLEMENTED
    ???
  }
}
