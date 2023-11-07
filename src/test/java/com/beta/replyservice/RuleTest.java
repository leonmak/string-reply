package com.beta.replyservice;

import com.beta.replyservice.commands.Command;
import com.beta.replyservice.commands.HashCommand;
import com.beta.replyservice.commands.ReverseCommand;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RuleTest {

  @Test
  public void testGetCode() {
    assertEquals('1', Rule.REVERSE.getCode(), "REVERSE code should be '1'");
    assertEquals('2', Rule.MD5_HASH.getCode(), "MD5_HASH code should be '2'");
  }

  @Test
  public void testGetCommand() {
    Command reverseCommand = Rule.REVERSE.getCommand();
    assertNotNull((ReverseCommand) reverseCommand, "Reverse command should not be null");

    Command hashCommand = Rule.MD5_HASH.getCommand();
    assertNotNull((HashCommand) hashCommand, "MD5 hash command should not be null");
  }

  @Test
  public void testGetRule() {
    Optional<Rule> reverseRule = Rule.getRule('1');
    assertTrue(reverseRule.isPresent(), "Should have Rule");
    assertEquals(Rule.REVERSE, reverseRule.get(), "Should return REVERSE Rule for '1'");

    Optional<Rule> hashRule = Rule.getRule('2');
    assertTrue(hashRule.isPresent(), "Should have Rule");
    assertEquals(Rule.MD5_HASH, hashRule.get(), "Should return MD5_HASH Rule for '2'");

    Optional<Rule> invalidRule = Rule.getRule('0');
    assertFalse(invalidRule.isPresent(), "Should be empty for invalid character");
  }
}
