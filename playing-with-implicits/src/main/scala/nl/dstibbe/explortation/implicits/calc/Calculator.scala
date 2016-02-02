package nl.dstibbe.explortation.implicits.calc

import nl.dstibbe.explortation.implicits.calc.Calculator.CalculationV2


trait Calculation{
  def compute(input:Number): Number
}

object Calculator {
  implicit class CalculationV2(val compute: Number => Number)
}

/**
  * Created by bigmem on 2016-02-02.
  */
class Calculator {
    def add(left:Number, right:Number) =  Number(left.value + right.value)
    def performCustomCalc( input:Number)(implicit calc: Calculation ) =  calc.compute(input)
    def performCustomCalc2( input:Number)(implicit calc: CalculationV2 ) =  calc.compute(input)
}
