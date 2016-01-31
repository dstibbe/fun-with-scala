package nl.dstibbe.explortation.implicits

class MyThing(msg: String) {
  override def toString(): String = msg
}

class ImplicitUser {
  def useArgument(myArg: MyThing) = {
    println("My thing was provided as an argument: " + myArg)
  }

  def useImplicit(implicit myInjectedThing: MyThing) = {
    println("My thing was injected as myInjectedThing and says: " + myInjectedThing)
  }

  def useImplicitWithZeroArguments()(implicit myInjectedThing: MyThing) = {
    println("My thing was injected in a method with zero arguments and says: " + myInjectedThing)
  }
  
   def useImplicitWithArguments(regularArgument:String)(implicit myInjectedThing: MyThing) = {
    println("My thing was injected in a method with argument [" +
            regularArgument + "] and says: " + myInjectedThing)
  }
}

object ImplicitFun {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    val myThing = new MyThing("Normal thing")
    implicit val myImplicitThing = new MyThing("Implicit thing")

    val myUser = new ImplicitUser()
    myUser.useArgument(myThing)
    myUser.useImplicit
    myUser.useImplicitWithZeroArguments()
    myUser.useImplicitWithArguments("my argument")
    
    println("Can also provide the implicit argument explicitly")
    myUser.useImplicit(myImplicitThing)
    myUser.useImplicitWithZeroArguments()(myImplicitThing)
    myUser.useImplicitWithArguments("my argument")(myImplicitThing)
  }

}
