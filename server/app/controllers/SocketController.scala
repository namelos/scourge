package controllers

import javax.inject._

import akka.actor._
import akka.stream.Materializer
import contexts.chat._
import play.api.libs.json._
import play.api.libs.streams.ActorFlow
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc._

trait EventFormatter {
  implicit val inEventFormat = Json.format[InEvent]
  implicit val outEventFormat = Json.format[OutEvent]

  implicit val messageFlowTransformer =
    MessageFlowTransformer.jsonMessageFlowTransformer[InEvent, OutEvent]
}

@Singleton
class SocketController @Inject()(cc: ControllerComponents)(
                              implicit system: ActorSystem,
                              materializer: Materializer
) extends AbstractController(cc) with EventFormatter {
  def socket: WebSocket = WebSocket.accept[InEvent, OutEvent] { request =>
    ActorFlow.actorRef { out =>
      ChatActor.props(out)
    }
  }
}
