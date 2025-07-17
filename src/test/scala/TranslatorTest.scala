import org.scalatest.funsuite.AnyFunSuite

class TranslatorTest extends AnyFunSuite {

  test("should translate the sentence into Morse") {
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

  test("should translate from Morse to a sentence") {
    val morseCodeSentence = "--. --- --- -.. / .-.. .. ..-. ."
    val morse = new MorseCode
    assert(Translator.morseToEnglish(morseCodeSentence, morse.morseCode) == "GOOD LIFE")
  }

  test("should throw an error when Morse sentence is empty") {
    val emptyMorseCode = ""
    val morse = new MorseCode
    assertThrows[IllegalArgumentException] {
      Translator.morseToEnglish(emptyMorseCode, morse.morseCode)
    }
  }

  test("should translate sentence with punctuation correctly") {
    val sentence = "HELLO WORLD."
    val morse = new MorseCode
    val result = Translator.englishToMorse(sentence, morse.morseCode)
    assert(result.contains(".-.-.-"))
  }

  test("should translate sentence with numbers correctly") {
    val sentence = "HELLO WORLD10"
    val morse = new MorseCode
    val result = Translator.englishToMorse(sentence, morse.morseCode)
    assert(result.contains(".---- -----"))
  }

  test("should translate English to Morse and back consistently") {
    val sentence = "GOOD LIFE"
    val morse = new MorseCode
    val morseCode = Translator.englishToMorse(sentence, morse.morseCode)
    val backToEnglish = Translator.morseToEnglish(morseCode, morse.morseCode)
    assert(backToEnglish == sentence)
  }

  test("should handle multiple spaces") {
    val sentence = "GOOD  LIFE"
    val morse = new MorseCode
    val morseCode = Translator.englishToMorse(sentence, morse.morseCode)
    assert(morseCode.contains("/"))
  }

  test("should lowercase input correctly") {
    val sentence = "good life"
    val morse = new MorseCode
    val morseCode = Translator.englishToMorse(sentence, morse.morseCode)
    val backToEnglish = Translator.morseToEnglish(morseCode, morse.morseCode)
    assert(backToEnglish == "GOOD LIFE")
  }
}
