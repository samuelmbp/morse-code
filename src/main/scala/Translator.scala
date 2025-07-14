
object Translator {

 def main(args: Array[String]): Unit = {
  val inputHandler = new InputHandler()
  val outputHandler = new OutputHandler()
  val morseCode = new MorseCode()

  val message = inputHandler.readInput("Please enter a message: ")
  outputHandler.printOutput(s"Your message is: $message")

  val wordToMorse = englishToMorse(message, morseCode.morseCode)
  outputHandler.printOutput(s"Morse translation: $wordToMorse")

 }

 def englishToMorse(word: String, morseCode: Map[Char, String]): String = {
  val upperCasedLetters = word.toUpperCase()
  upperCasedLetters
    .map(letter => morseCode.getOrElse(letter, ""))
    .mkString(" ")
 }


 /*
 TODO:
 1. Implement the morseToEnglish functionality
 2. Implement keep asking for input until "exit" is typed
 3. Optionally include punctuation support
 4. Auto-detect input type
 5. Unit testing
    - English to Morse
    - Morse to English
    - Word separation
    - Invalid inputs
 6. Add some colors in the terminal for UI
  */
}
