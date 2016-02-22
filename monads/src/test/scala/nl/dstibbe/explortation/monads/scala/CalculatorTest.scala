package nl.dstibbe.explortation.monads.scala

import nl.dstibbe.explortation.monads.scala.Calculators._
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
      sqrt(100).flatMap(divide100) must beEqualTo( Just(10))
    }
    "*  Be able to completely chain calculations" in {
      Just(100.toDouble).flatMap(sqrt).flatMap(divide100) must beEqualTo( Just(10))
    }
    "*  Be able to chain function" in {
      val result =
      Just(100.toDouble) flatMap {
        x => Just(x*2) flatMap{
          y => Just(y*10) map {
            z => z * 3
          }
        }
      }

      result must beEqualTo(Just(6000))
    }
    "*  Be able to chain using for" in {
      val result =
        for {
        x <- Just(100.toDouble)
        y <- Just(x*2)
        z <- Just(y*10)
      }
      yield  z * 3

      result must beEqualTo(Just(6000))
    }

    "*  Be able to chain with None result" in {
      val result =
        for {
        x <- Just(-100.toDouble)
        y <- sqrt(x)  //==None
        z <- Just(y*10)
      }
        yield  z * 3

      result must beEqualTo(None)
    }
  }
}
