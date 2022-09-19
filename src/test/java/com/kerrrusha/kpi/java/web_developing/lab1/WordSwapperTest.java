package com.kerrrusha.kpi.java.web_developing.lab1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordSwapperTest {
	@Test
	void swapWordsTest() {
		class TestPair {
			private final String origin, expected;
			public TestPair(String origin, String expected) {
				this.origin = origin;
				this.expected = expected;
			}
		}

		List<TestPair> testPairs = new ArrayList<>(List.of(
				new TestPair("end of passion", "passion of end"),
				new TestPair(":some.word;-here.", ":here.word;-some."),
				new TestPair("", ""),
				new TestPair("test", "test")
		));

		testPairs.forEach(testPair ->
				assertEquals(
						testPair.expected,
						WordSwapper.swapWords(testPair.origin),
						"Incorrect swap."
				)
		);
	}
}
