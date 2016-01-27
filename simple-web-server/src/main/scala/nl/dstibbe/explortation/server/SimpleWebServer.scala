package nl.dstibbe.explortation.server

import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.http.scaladsl.Http
import akka.actor.ActorSystem
import scala.concurrent.Future


import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import java.io.File


object SimpleWebServer extends App {
	println("Args: " +  (args mkString ", "))
	
	implicit val system = ActorSystem()
	implicit val materializer = ActorMaterializer()
	 
	val rootLocation = if (args.isEmpty) "." else args(0)
		 
	println("Root location: " + rootLocation)
		 
	val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
	  Http().bind(interface = "localhost", port = 9000)
	
	val requestHandler: HttpRequest => HttpResponse = {
	  case HttpRequest(GET, uri:Uri, _, _, _) =>  
		uri match {
			case Uri.Path("/test") =>
				HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
				"<html><body>Hello world!</body></html>"))
		 
			case Uri.Path("/ping") =>
				HttpResponse(entity = "PONG!")
		 
			case Uri.Path("/crash") =>
				sys.error("BOOM!")
				
			case Uri.Path("/") => 
				HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
				"<html><body>" + (new File(rootLocation).listFiles() mkString "\n<br/>") + "</body></html>"))				
				
			case Uri.Path(path:String) =>
				HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
				"<html><body>Requested file: " + rootLocation + path + "</body></html>"))
			case _ =>
				HttpResponse(404, entity = "Unknown resource!")
		}
		
	  case _: HttpRequest =>
		HttpResponse(404, entity = "Unknown resource!")
	}

	
	val bindingFuture: Future[Http.ServerBinding] =
	  serverSource.to(Sink.foreach { connection => // foreach materializes the source
		println("Accepted new connection from " + connection.remoteAddress)
		
		 connection handleWithSyncHandler requestHandler
		
	  }).run()
	  
	  println("Server started")
}
