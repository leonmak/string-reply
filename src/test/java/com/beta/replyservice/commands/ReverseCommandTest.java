package com.beta.replyservice.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseCommandTest {

  @Test
  public void testReverseCommand() {
    Command revCommand = new ReverseCommand();
    String reversedString = revCommand.execute("example");
    assertEquals("elpmaxe", reversedString, "Should return reversed string");
  }
}
