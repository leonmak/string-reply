package com.beta.replyservice;

import com.beta.replyservice.commands.Command;
import com.beta.replyservice.commands.HashCommand;
import com.beta.replyservice.commands.ReverseCommand;

import java.util.Optional;
import java.util.stream.Stream;

public enum Rule {
  REVERSE('1', new ReverseCommand()),
  MD5_HASH('2', new HashCommand("MD5"));

  private final char code;
  private final Command command;

  Rule(char code, Command command) {
    this.code = code;
    this.command = command;
  }

  public char getCode() {
    return code;
  }

  public Command getCommand() {
    return command;
  }

  public static Optional<Rule> getRule(char cmdChar) {
    return Stream.of(Rule.values())
        .filter(rule -> rule.getCode() == cmdChar)
        .findFirst();
  }
}
