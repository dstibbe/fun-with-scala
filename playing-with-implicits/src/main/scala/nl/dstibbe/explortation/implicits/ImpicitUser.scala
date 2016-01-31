package nl.dstibbe.explortation.implicits

object ImplicitUser {
  implicit def convertString2MyThing(toConvert:String):MyThing = {
    println(">> converting [" + toConvert + "] to MyThing")
    new MyThing(toConvert)
  }
}

class ImplicitUser {
  def useArgument(myArg: MyThing) = {
    println("> My thing was provided as an argument: " + myArg)
  }

  def useImplicit(implicit myInjectedThing: MyThing) = {
    println("> My thing was injected as myInjectedThing and says: " + myInjectedThing)
  }

  def useImplicitWithZeroArguments()(implicit myInjectedThing: MyThing) = {
    println("> My thing was injected in a method with zero arguments and says: " + myInjectedThing)
  }
  
  def useImplicitWithArguments(regularArgument:String)(implicit myInjectedThing: MyThing) = {
    println("> My thing was injected in a method with argument [" +
            regularArgument + "] and says: " + myInjectedThing)
  } 
  
  
}
