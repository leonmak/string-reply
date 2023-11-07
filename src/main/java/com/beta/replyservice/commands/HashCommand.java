package com.beta.replyservice.commands;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCommand implements Command {
  private final MessageDigest messageDigest;

  public HashCommand(String algo) {
    try {
      this.messageDigest = MessageDigest.getInstance(algo);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException();
    }
  }

  @Override
  public String execute(String data) {
    this.messageDigest.update(data.getBytes());
    final byte[] digest = messageDigest.digest();
    final String hexString = DatatypeConverter.printHexBinary(digest);
    return (hexString.toLowerCase());
  }
}
