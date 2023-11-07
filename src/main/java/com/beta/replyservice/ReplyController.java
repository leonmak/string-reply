package com.beta.replyservice;

import com.beta.replyservice.commands.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	private ResponseEntity invalidInputResponse() {
		ReplyMessage replyMessage = new ReplyMessage("Invalid input");
		return ResponseEntity.badRequest().body(replyMessage);
	}

	@GetMapping("/v2/reply/{code}-{message}")
	public ResponseEntity replying(@PathVariable String code, @PathVariable String message) {
		String data = message;
		if (code.length() != 2) {
			return invalidInputResponse();
		}
		for (final char c : code.toCharArray()) {
			Optional<Command> command = Rule.getRule(c).map(Rule::getCommand);
			if (!command.isPresent()) {
				return invalidInputResponse();
			}
			data = command.get().execute(data);
		}
		ReplyData body = new ReplyData(data);
		return ResponseEntity.ok(body);
	}
}