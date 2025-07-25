import scala.annotation.tailrec

object Translator {

 def main(args: Array[String]): Unit = {
   val inputHandler = new InputHandler()
   val outputHandler = new OutputHandler()
   val morseCode = new MorseCode()

   runTranslator(inputHandler, outputHandler, morseCode)
 }

  @tailrec
  def runTranslator(inputHandler: InputHandler, outputHandler: OutputHandler, morseCode: MorseCode): Unit = {
    val input = inputHandler.readInput(
      s"${Colors.YELLOW}Enter a sentence (or type 'exit' to quit): ${Colors.RESET}").toUpperCase()

    if (input.toLowerCase() == "exit") {
      outputHandler.printOutput(s"${Colors.RED}Exiting program. Goodbye!${Colors.RESET}")
    } else if (input.toLowerCase() == "") {
      outputHandler.printOutput(s"${Colors.RED}Input cannot be empty. Please enter a sentence.${Colors.RESET}")
      runTranslator(inputHandler, outputHandler, morseCode)
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
    if (sentence.isEmpty)
      throw new IllegalArgumentException("Sentence cannot be empty!")
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
    if (morseSentence.isEmpty)
      throw new IllegalArgumentException("Morse sentence cannot be empty!")
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
}
