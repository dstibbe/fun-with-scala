package nl.dstibbe.explortation.monads.original

import Calculators._
import org.specs2.mutable

/**
  * Created by dstibbe on 2016-02-02.
  */
class CalculatorTest extends mutable.Specification {
  "Calculator" should {
    "*  Be able to calculate sqrt" in {
      sqrt(25) must beEqualTo( Just(5))
    }
    "*  Be able to divide 100" in {
      divide100(10) must beEqualTo( Just(10))
    }
    "*  Be able to chain calculations" in {
      sqrt(100).bind(divide100) must beEqualTo( Just(10))
    }
    "*  Be able to completely chain calculations" in {
      Just(100.toDouble).bind(sqrt).bind(divide100) must beEqualTo( Just(10))
    }
    "*  Be able to chain function" in {
      Just(100.toDouble) bind{
        x => Just(x*2) bind {
          y => Just(y*10)
        }
      } must beEqualTo( Just(2000))
    }
  }
}
