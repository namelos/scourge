package controllers

import javax.inject._

import akka.actor._
import akka.stream.Materializer
import contexts.chat.ChatActor
import play.api.libs.json._
import play.api.libs.streams.ActorFlow
import play.api.mvc._

@Singleton
class SocketController @Inject()(cc: ControllerComponents)(
                              implicit system: ActorSystem,
                              materializer: Materializer
) extends AbstractController(cc) {
  def socket: WebSocket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      ChatActor.props(out)
    }
  }
}
