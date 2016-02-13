package nl.dstibbe.explortation.monads.original

/**
  * Created by bigmem on 2016-02-12.
  */

object Calculators {
  def divide100( divisor: Double):  Maybe[Double] = {
    if (divisor == 0) {
      None
    }else{
      Just(100/divisor)
    }
  }


  def sqrt( x: Double): Maybe[Double] = {
    if( x<0){
      None
    }else{
      Just( scala.math.pow(x, 0.5))
    }
  }
}
