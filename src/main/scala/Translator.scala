import scala.annotation.tailrec
import scala.util.{Failure, Success}

object Translator {

 def main(args: Array[String]): Unit = {
   val inputHandler = new InputHandler()
   val outputHandler = new OutputHandler()
   val morseCode = new MorseCode()

   loop(inputHandler, outputHandler, morseCode)
 }

  @tailrec
  def loop(inputHandler: InputHandler,
          outputHandler: OutputHandler,
          morseCode: MorseCode): Unit = {
   val input = inputHandler.readInput("Enter a sentence (or type 'exit' to quit): ")
   if (input.toLowerCase() == "exit") {
     outputHandler.printOutput("Exiting program. Goodbye!")
   } else {
     val morse = englishToMorse(input, morseCode.morseCode)
     val english = morseToEnglish(morse, morseCode.morseCode)

     outputHandler.printOutput(s"English to Morse: $morse")
     outputHandler.printOutput(s"Morse to English: $english")
     loop(inputHandler, outputHandler, morseCode)
   }
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

  def morseToEnglish(morseSentence: String, morseCode: Map[Char, String]): String = {
    if (morseSentence.isEmpty) "Morse sentence cannot be empty!"
    else {
     val reversedMorseCode = morseCode.map(_.swap)
     morseSentence
       .split(" / ")
       .map(word =>
         word.split(" ")
           .map(morseChar => reversedMorseCode.getOrElse(morseChar, ' '))
           .mkString
       )
       .mkString(" ")
    }
  }

 /*
 TODO:
 1. Implement the morseToEnglish functionality ✅
 2. Implement keep asking for input until "exit" is typed ✅
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
