package com.drypot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFirstJUnitJupiterTests {

  private final Calculator c = new Calculator();

  @Test
  void addition() {
    assertEquals(2, c.add(1, 1));
  }

  @Test
  void nullIsNull() {
    assertNull(null, "should be null.");
    //assertNull("abc", "should be null.");
  }

  @Test
  void shouldFail() {
    //fail("this test should fail.");
  }

}
