
object Translator extends App {

 val inputHandler = new InputHandler()
 val outputHandler = new OutputHandler()

 val message = inputHandler.readInput("Please enter a message: ")
 outputHandler.printOutput(s"Your message is: $message")
}
