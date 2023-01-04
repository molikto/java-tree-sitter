package ai.serenade.treesitter;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Parser implements AutoCloseable {
  private long pointer;

  Parser(long pointer) {
    this.pointer = pointer;
  }

  public Parser() {
    this(TreeSitter.parserNew());
  }

  public void setLanguage(long language) {
    TreeSitter.parserSetLanguage(pointer, language);
  }

  public Tree parseString(byte[] bytes) throws UnsupportedEncodingException {
    return new Tree(TreeSitter.parserParseBytes(pointer, bytes, bytes.length));
  }

  @Override
  public void close() {
    TreeSitter.parserDelete(pointer);
  }
}
