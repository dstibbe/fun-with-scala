package nl.dstibbe.explortation.implicits.calc

/**
  * Companion object for Number
  */
object Number{
  implicit def convertString2Number(value:String) = {
    println(" -- converting String \"" + value + "\" to Number -- ")
    Number( value.toInt )
  }
}


/**
  * Yes, a Number class, there are many like it, but this one's mine.
  *
  * @param value what do you think?
  */
case class Number(value:Int)
