package nl.dstibbe.explortation.monads.original


trait Maybe[+A]{
  def bind[B](f: A => Maybe[B] ): Maybe[B]
}

case class Just[+A](a: A) extends Maybe[A]{
  override def bind[B](f: A => Maybe[B]) = f(a)
}

case object None extends Maybe[Nothing] {
  override def bind[B](f: Nothing => Maybe[B]) = None
}