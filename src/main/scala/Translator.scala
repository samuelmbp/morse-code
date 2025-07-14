
object Translator {

 def main(args: Array[String]): Unit = {
  val inputHandler = new InputHandler()
  val outputHandler = new OutputHandler()
  val morseCode = new MorseCode()

  val message = inputHandler.readInput("Please enter a message: ")
  outputHandler.printOutput(s"Your message is: $message")

  println(morseCode.morseCode)
 }
}
