package nl.dstibbe.explortation.server

import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.http.scaladsl.Http
import akka.actor.ActorSystem
import scala.concurrent.Future


import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._



object SimpleWebServer extends App {

	implicit val system = ActorSystem()
	implicit val materializer = ActorMaterializer()
	 
	val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
	  Http().bind(interface = "localhost", port = 9000)
	
	val requestHandler: HttpRequest => HttpResponse = {
	  case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
		HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
		  "<html><body>Hello world!</body></html>"))
	 
	  case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
		HttpResponse(entity = "PONG!")
	 
	  case HttpRequest(GET, Uri.Path("/crash"), _, _, _) =>
		sys.error("BOOM!")
	 
	  case _: HttpRequest =>
		HttpResponse(404, entity = "Unknown resource!")
	}

	
	val bindingFuture: Future[Http.ServerBinding] =
	  serverSource.to(Sink.foreach { connection => // foreach materializes the source
		println("Accepted new connection from " + connection.remoteAddress)
		
		 connection handleWithSyncHandler requestHandler
		
	  }).run()
}
