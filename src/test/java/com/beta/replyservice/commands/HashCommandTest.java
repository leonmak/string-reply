package com.beta.replyservice.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashCommandTest {

  @Test
  public void testHashCommandInvalid() {
    assertThrows(RuntimeException.class, () -> {
      Command hashCommand = new HashCommand("1");
    });
  }

  @Test
  public void testHashCommand() {
    Command hashCommand = new HashCommand("MD5");
    String hexString = hashCommand.execute("kbzw9ru");
    assertEquals("0fafeaae780954464c1b29f765861fad", hexString, "Should return hash string");
  }

  @Test
  public void testHashCommandMultiple() {
    Command hashCommand = new HashCommand("MD5");
    String hexString = hashCommand.execute("kbzw9ru");
    assertEquals("0fafeaae780954464c1b29f765861fad", hexString, "Should return hash string");
    String hexString2 = hashCommand.execute("Welcome");
    assertEquals("83218ac34c1834c26781fe4bde918ee4", hexString2, "Should return hash string again");
  }
}
