import nl.dstibbe.explortation.implicits.calc.Calculator.CalculationV2
import nl.dstibbe.explortation.implicits.calc.{Calculation, Number, Calculator}
import org.specs2.mutable

/**
  * Created by dstibbe on 2016-02-02.
  */
class CalculatorTest extends mutable.Specification {
  "Calculator" should {
    "*  Be able to add" in {
      val calc = new Calculator()

      val a = Number(12)
      val b = Number(3)

      calc.add(a,b) must beEqualTo(Number(15))
    }
  }
  "Automatic parameter conversion" should {

    "*  Convert from String to Number implicitly" in {
      val calc = new Calculator()

      val a = "12" //A string, not a Number
      val b = "3"  //A string, not a Number

      calc.add(a,b) must beEqualTo(Number(15))
    }
  }


  "Calculator parameters" should {

    "*  Accept explicitly converted Number from String " in {
      val calc = new Calculator()

      val a = Number.convertString2Number("12")
      val b = Number.convertString2Number("3")

      calc.add(a,b) must beEqualTo(Number(15))
    }

    "*  Convert from String to Number implicitly" in {
      val calc = new Calculator()

      val a = "12" //A string, not a Number
      val b = "3"  //A string, not a Number

      calc.add(a,b) must beEqualTo(Number(15))
    }

    "*  Accept explicit calculation parameter using a trait" in {
      implicit val addTwelve: Calculation = new Calculation {
        override def compute(input: Number) = Number(input.value + 12)
      }
      val calc = new Calculator()

      val result = calc.performCustomCalc(Number(4))(addTwelve)
      result must beEqualTo(Number(16))
    }

    "*  Accept implicit calculation parameter using a trait" in {
      implicit val addTwelve: Calculation = new Calculation {
        override def compute(input: Number) = Number(input.value + 12)
      }

      val calc = new Calculator()

      val result = calc.performCustomCalc(Number(4))
      result must beEqualTo(Number(16))
    }

    "*  Accept implicit calculation parameter using an implicit class" in {
      implicit val addTwelve:CalculationV2 = (input: Number) => Number(input.value + 12)

      val calc = new Calculator()

      val result = calc.performCustomCalc2(Number(4))
      result must beEqualTo(Number(16))
    }
  }
}
