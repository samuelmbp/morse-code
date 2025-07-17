# Morse Code Translator – Scala CLI App

Command-line Scala application that translates English sentences to Morse code and translates Morse code back to English interactively.

## The Translator:

- ✅ Accepts user input from the console.
- ✅ Translates English sentences into Morse code.
- ✅ Allows users to view the Morse code translated back into English.
- ✅ Supports multiple translations in a single session.
- ✅ Provides clear error handling for empty input.


## 🛠️ How It Works

1. User is prompted: `Enter a sentence (or type 'exit' to quit):`

2. The sentence is translated into Morse code and displayed.

3. User is asked:`Would you like to see the Morse code translated back into English? (yes/no)`

4. If `"yes"`, the Morse code is translated back to English and displayed.

5. If `"no"`, user is shown the message: `Okay, no worries!`

6. User is asked: `Would you like to translate another sentence? (yes/no)`

7. If `"yes"`, the translator repeats; otherwise, it exits the program.


## Future Improvements
- Unit Test with Mocks `runTranslate` method
