import org.scalatest.funsuite.AnyFunSuite

class TranslatorTest extends AnyFunSuite {
  
  test("should transform the sentence into Morse") {
    val sentence = "London"
    val morse = new MorseCode
    assert(Translator.englishToMorse(sentence, morse.morseCode) == ".-.. --- -. -.. --- -." )
  }
  
  test("should throw an error when sentence is empty") {
    val sentence = ""
    val morse = new MorseCode
    assertThrows[IllegalArgumentException] {
      Translator.englishToMorse(sentence, morse.morseCode)
    }
  }
}
