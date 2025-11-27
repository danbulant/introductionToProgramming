package week13;

import common.StdRandom;

public class Printer {
  static class OutOfPaperException extends Exception {
    @Override
    public String getMessage() {
      return "No Paper pack found in printer.\n" +
          "Check that there's enough paper left for this print job, that the Paper pack is clean and that the paper pack chip is undamaged. "
          +
          "Please note that third-party Paper packs may not be supported. Check that the Paper pack is made for this model.";
    }
  }

  static class OutOfTonerException extends Exception {
    @Override
    public String getMessage() {
      return "Printing supply pack is empty or was not found.\n" +
          "Check that an official Printing supply pack is correctly inserted in the printer, try re-seating if necessary. "
          +
          "Check that Printing supply packs are inserted into the correct color coded slots. Check that your Printing resupply subscription is active. "
          +
          "Please note that third-party Printing supply packs may not be supported. Check that the Paper pack is made for this model.";
    }
  }

  static class PaperJamException extends Exception {
    @Override
    public String getMessage() {
      return "lp0 on fire.";
    }
  }

  private static Exception getExceptionFromErrorCode(int errorCode) {
    return switch (errorCode / 10) {
      case 0 -> new OutOfPaperException();
      case 1 -> new OutOfTonerException();
      case 2 -> new PaperJamException();
      default -> throw new IllegalArgumentException();
    };
  }

  public static void print() throws Exception {
    var rnd = StdRandom.uniformInt(30);
    var exception = getExceptionFromErrorCode(rnd);
    throw exception;
  }

  public static void main(String[] args) {
    try {
      print();
      System.out.println("Printed successfully, thanks for your patronage.");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
