package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {

	private SpellChecker checker;
	private String word;
	private String misspelledWord;

	@Before
  public void setup() {
    checker = new SpellChecker();
    word = "bank";
		misspelledWord = "bamk";
  }

	// Test 1
	@Test
	void testgetNumWordsNoWords() {
		int words = checker.getNumWords();
		assertEquals(0, words);
	}

	// Test 2
	@Test
	void testgetNumWordsOneWord() {
		checker.addWord(word);
		int words = checker.getNumWords();
		assertEquals(1, words);
	}

	// Test 3
	@Test
	void testgetNumWordsDuplicateWords() {
		checker.addWord(word);
		checker.addWord(word);
		int words = checker.getNumWords();
		assertEquals(1, words);
	}

	// Test 4
	// COME BACK -- A spellchecker should be able to accept a properly spelled word and return an indication that it is properly spelled
	@Test
	void testProperSpelling() {
		boolean properSpelling = checker.checkSpelling(word); 
		assertTrue(properSpelling);
	}

	// Test 5
	@Test
	void testImproperSpelling() {
		boolean improperSpelling = checker.checkSpelling(misspelledWord);
		assertFalse(improperSpelling);
	}

	// Test 6
	@Test
	void testIgnoreCase() {
		String lowerCaseWord = "word";
		String upperCaseWord = "WORD";
		String mixCaseWord = "WoRd";
		checker.addWord(lowerCaseWord);
		checker.addWord(upperCaseWord);
		checker.addWord(mixCaseWord);
		boolean properSpellingLower = checker.checkSpelling(lowerCaseWord);
		assertTrue(properSpellingLower);
		boolean properSpellingUpper = checker.checkSpelling(upperCaseWord);
		assertTrue(properSpellingUpper);
		boolean properSpellingMix = checker.checkSpelling(mixCaseWord);
		assertTrue(properSpellingMix);
	}

	// Test 7
	@Test
	void testRecommendProperSpellingForImproperlySpelledWord() {
		checker.addWord(word);
		checker.addWord(misspelledWord);
		String suggestion = checker.recommendProperSpelling(misspelledWord);
		assertEquals(word, suggestion);
	}

	// Test 8
	@Test
	void testRecommendProperSpellingForProperlySpelledWord() {
		checker.addWord(word);
		String suggestion = checker.recommendProperSpelling(word);
		assertEquals(word, suggestion);
	}

	// Test 9 -- tests hyphenated words are counted as one word
	@Test
	void testHyphenatedWordsCountAsOneWord() {
		String hyphenatedWord = "sister-in-law";
		checker.addWord(hyphenatedWord);
		int words = checker.getNumWords();
		assertEquals(1, words);
	}

	// Test 10 -- singular and plural forms of a word should be counted as one word
	@Test
	void testSingularPluralWords() {
		String pluralWord = "banks";
		checker.addWord(word);
		checker.addWord(pluralWord);
		int words = checker.getNumWords();
		assertEquals(1, words);
	}
}
