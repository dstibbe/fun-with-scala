import nl.dstibbe.explortation.implicits.vehicles.{Car,FlyingCar}
import nl.dstibbe.explortation.implicits.vehicles.FlyingCar._
import org.specs2._

class ImplicitObjectConversionTest extends mutable.Specification {
  "The implicit conversion method" should {
    "should do nothing on regular usage" in{
      val myRegularCar:Car = new Car()
        myRegularCar.drive() must beEqualTo("The car is driving")
    }
      "create the JamesBond Flying car when necessary" in{
        val myRegularCar:Car = new Car()

        //Because you use 'fly()', the car is converted to FlyingCar by scala
        myRegularCar.fly() must beEqualTo("The car is flying")
      }
  }
}