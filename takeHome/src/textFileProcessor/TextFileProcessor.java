package textFileProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TextFileProcessor {
	private Stack<String> textStack;
	private Stack<String> performedOperations;
	private List<String> commands;

	private String textFile;

	private static final String OPEN_SYMBOL = "<";
	private static final String CLOSE_SYMBOL = ">";
	private static final String UNDO = "undo";
	private static final String BACKSPACE = "bksp";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String ADD = "add";

	public TextFileProcessor(String textFile, String commandFile) {
		this.textFile = textFile;

		textStack = new Stack<>();
		performedOperations = new Stack<>();

		textStack.addAll(getFileContent(textFile));
		commands = getFileContent(commandFile);
	}

	public List<String> getFileContent(String fileName) {
		Path p = Paths.get(fileName);

		try {
			List<String> lines = Files.readAllLines(p);
			List<String> content = new ArrayList<>();

			for (String line : lines) {
				for (int i = 0; i < line.length(); i++) {
					content.add(line.substring(i, i + 1));
				}
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void execute() throws InvalidUndoOperationException {
		boolean isSpecialCommand = false;
		String specialCommand = "";

		for (String c : commands) {
			if (isSpecialCommand) {
				if (c.equals(CLOSE_SYMBOL)) {
					if (specialCommand.equals(UNDO)) {
						executeUndo();
					} else if (specialCommand.equals(BACKSPACE)) {
						executeBackspace();
					}
					isSpecialCommand = false;
					specialCommand = EMPTY;
				} else {
					specialCommand += c;
				}
			}

			else {
				if (c.equals(OPEN_SYMBOL)) {
					isSpecialCommand = true;
				} else {
					textStack.add(c);
					performedOperations.add(ADD + SPACE + c);
				}
			}
		}
		System.out.println(textStack);
		addToFile();
	}

	public void executeBackspace() {
		if (textStack.isEmpty()) {
			return;
		} else {
			String deletedText = textStack.pop();
			performedOperations.add(BACKSPACE + SPACE + deletedText);
		}
	}

	public void executeUndo() throws InvalidUndoOperationException {
		if (performedOperations.isEmpty()) {
			throw new InvalidUndoOperationException("No operations performed yet!");
		} else {
			String prevOperation = performedOperations.pop();
			String[] arr = prevOperation.split(" ");
			String command = arr[0];
			String text = arr[1];

			if (command.equals(ADD)) {
				textStack.pop();
			} else {
				textStack.add(text);
			}
		}
	}

	public void addToFile() {
		String text = "";
		for (String s : textStack) {
			text += s;
		}

		Path p = Paths.get(textFile);
		try {
			Files.writeString(p, text, StandardOpenOption.WRITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
