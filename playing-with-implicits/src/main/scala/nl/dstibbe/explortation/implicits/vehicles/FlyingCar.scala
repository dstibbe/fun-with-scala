package nl.dstibbe.explortation.implicits.vehicles


object FlyingCar {
  implicit def convertCar2SubmarineCar(car: Car): FlyingCar = {
    println("James Bond pressed the button. Car converts")
    return new FlyingCar()
  }
}


/**
  * Created by bigmem on 2016-02-02.
  */
class FlyingCar {
  def fly() = "The car is flying"
}
