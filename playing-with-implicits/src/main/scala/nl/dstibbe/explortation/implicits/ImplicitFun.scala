package nl.dstibbe.explortation.implicits

import ImplicitUser._

object ImplicitFun extends App{

    val myThing = new MyThing("Normal thing")
    implicit val myImplicitThing = new MyThing("Implicit thing")

    val myUser = new ImplicitUser()
    
    println("==[ Usng Implicit instances ]==")
    myUser.useArgument(myThing)
    myUser.useImplicit
    myUser.useImplicitWithZeroArguments()
    myUser.useImplicitWithArguments("my argument")
    
    println("==[ Provide the implicit instant as an explicit argument ]==")
    myUser.useImplicit(myImplicitThing)
    myUser.useImplicitWithZeroArguments()(myImplicitThing)
    myUser.useImplicitWithArguments("my argument")(myImplicitThing)
   
    
    println("==[ Using implicit methods ]==")
    //String will be converted to MyThing
    myUser.useImplicit("this is a regular String")    

}
