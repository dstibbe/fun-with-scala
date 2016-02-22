package nl.dstibbe.explortation.monads.scala

trait Maybe[+A]{
  def flatMap[B](f: A => Maybe[B] ): Maybe[B]
  def map[B](f: A=>B) : Maybe[B]
}

case class Just[+A](a: A) extends Maybe[A]{
  override def flatMap[B](f: A => Maybe[B]) = f(a)
  override def map[B](f: A => B) = Just(f(a))
}

case object None extends Maybe[Nothing] {
  override def flatMap[B](f: Nothing => Maybe[B]) = None
  override def map[B](f: Nothing => B) = None
}