
object Translator {

 def main(args: Array[String]): Unit = {
  val inputHandler = new InputHandler()
  val outputHandler = new OutputHandler()
  val morseCode = new MorseCode()

  val sentence = inputHandler.readInput("Please enter a sentence: ")
  val wordConvertedToMorse = englishToMorse(sentence, morseCode.morseCode)

  outputHandler.printOutput(s"English to morse: $wordConvertedToMorse")
 }

 def englishToMorse(sentence: String, morseCode: Map[Char, String]): String = {
  if (sentence.isEmpty) "Sentence cannot be empty!"
  else {
    sentence.toUpperCase()
      .split(" ")
      .map(word =>
        word.map(letter => morseCode.getOrElse(letter, "")).mkString(" ")
      )
      .mkString(" / ")
  }
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
