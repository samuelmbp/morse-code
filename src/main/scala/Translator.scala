import scala.annotation.tailrec
import scala.util.{Failure, Success}

object Translator {

 def main(args: Array[String]): Unit = {
   val inputHandler = new InputHandler()
   val outputHandler = new OutputHandler()
   val morseCode = new MorseCode()

   runTranslator(inputHandler, outputHandler, morseCode)
 }

  @tailrec
  def runTranslator(inputHandler: InputHandler,
                    outputHandler: OutputHandler,
                    morseCode: MorseCode): Unit = {
    val input = inputHandler.readInput(
      s"${Colors.YELLOW}Enter a sentence (or type 'exit' to quit): ${Colors.RESET}").toUpperCase()

    if (input.toLowerCase() == "exit") {
      outputHandler.printOutput(s"${Colors.RED}Exiting program. Goodbye!${Colors.RESET}")
    } else {
      val morse = englishToMorse(input, morseCode.morseCode)
      val english = morseToEnglish(morse, morseCode.morseCode)

      outputHandler.printOutput(s"${Colors.GREEN}The word '$input' in Morse is: $morse${Colors.RESET}")

      val viewMorseCodeToEnglish = inputHandler.readInput(
        s"${Colors.YELLOW}Would you like to see the Morse code translated back into English? (yes/no): ${Colors.RESET}"
      )

      if (viewMorseCodeToEnglish.toLowerCase() == "yes")
        outputHandler.printOutput(s"${Colors.CYAN}The Morse code '$morse' translated into English is: '$english'${Colors.RESET}")
      else
        outputHandler.printOutput(s"${Colors.MAGENTA}Okay, no worries!${Colors.RESET}")

      val continue = inputHandler.readInput(s"${Colors.YELLOW}Would you like to translate another sentence? (yes/no): ${Colors.RESET}")
      if (continue.toLowerCase() == "yes") runTranslator(inputHandler, outputHandler, morseCode)
      else outputHandler.printOutput(s"${Colors.RED}Goodbye!${Colors.RESET}")
    }
  }

  def englishToMorse(sentence: String, morseCode: Map[Char, String]): String = {
    if (sentence.isEmpty) "Sentence cannot be empty!"
    else {
      sentence.toUpperCase()
        .split(" ")
        .map(word =>
          word.map(letter => morseCode.getOrElse(letter, letter.toString)).mkString(" ")
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
 3. Optionally include punctuation support ✅
 4. Auto-detect input type
 5. Unit testing
    - English to Morse
    - Morse to English
    - Word separation
    - Invalid inputs
 6. Add some colors in the terminal for UI ✅
 7. Add punctuation and numbers for the code morse map ✅
 8. Add func to ask user if want to translate from 1. EN to Morse or 2. Morse to EN
  */
}
