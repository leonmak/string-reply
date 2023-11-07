package com.beta.replyservice.commands;

public class ReverseCommand implements Command {

  @Override
  public String execute(String data) {
    return new StringBuilder(data).reverse().toString();
  }

}
