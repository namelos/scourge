package contexts.chat

import akka.actor._

object ChatActor {
  def props(out: ActorRef) = Props(new ChatActor(out))
}

class ChatActor(out: ActorRef) extends Actor {
  def receive: Receive = {
    case msg: String =>
      out ! ("I receive your message: " + msg)
  }
}
