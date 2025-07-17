import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfter

class TranslatorTest extends AnyFunSuite with BeforeAndAfter {

  var morse: MorseCode = _

  before {
    morse = new MorseCode
  }

  test("should translate the sentence into Morse") {
    val sentence = "London"
    assert(Translator.englishToMorse(sentence, morse.morseCode) == ".-.. --- -. -.. --- -." )
  }

  test("should throw an error when sentence is empty") {
    val sentence = ""
    val morse = new MorseCode
    assertThrows[IllegalArgumentException] {
      Translator.englishToMorse(sentence, morse.morseCode)
    }
  }

  test("should translate from Morse to a sentence") {
    val morseCodeSentence = "--. --- --- -.. / .-.. .. ..-. ."
    assert(Translator.morseToEnglish(morseCodeSentence, morse.morseCode) == "GOOD LIFE")
  }

  test("should throw an error when Morse sentence is empty") {
    val emptyMorseCode = ""
    assertThrows[IllegalArgumentException] {
      Translator.morseToEnglish(emptyMorseCode, morse.morseCode)
    }
  }

  test("should translate sentence with punctuation correctly") {
    val sentence = "HELLO WORLD."
    val result = Translator.englishToMorse(sentence, morse.morseCode)
    assert(result.contains(".-.-.-"))
  }

  test("should translate sentence with numbers correctly") {
    val sentence = "HELLO WORLD10"
    val result = Translator.englishToMorse(sentence, morse.morseCode)
    assert(result.contains(".---- -----"))
  }

  test("should translate English to Morse and back consistently") {
    val sentence = "GOOD LIFE"
    val morseCode = Translator.englishToMorse(sentence, morse.morseCode)
    val backToEnglish = Translator.morseToEnglish(morseCode, morse.morseCode)
    assert(backToEnglish == sentence)
  }

  test("should handle multiple spaces") {
    val sentence = "GOOD  LIFE"
    val morseCode = Translator.englishToMorse(sentence, morse.morseCode)
    assert(morseCode.contains("/"))
  }

  test("should lowercase input correctly") {
    val sentence = "good life"
    val morseCode = Translator.englishToMorse(sentence, morse.morseCode)
    val backToEnglish = Translator.morseToEnglish(morseCode, morse.morseCode)
    assert(backToEnglish == "GOOD LIFE")
  }
}
