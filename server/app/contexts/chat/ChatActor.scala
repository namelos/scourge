package contexts.chat

import akka.actor._

case class OutEvent(content: String)
case class InEvent(content: String)

object ChatActor {
  def props(out: ActorRef) = Props(new ChatActor(out))
}

class ChatActor(out: ActorRef) extends Actor {
  def receive: Receive = {
    case InEvent(msg) =>
      println(s"Get Event: $msg")
      out ! OutEvent(s"received: $msg")
    case a =>
      println(a)
      println("Did not matched...")
  }
}
