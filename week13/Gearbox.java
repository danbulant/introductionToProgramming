package week13;

public class Gearbox {
  private int gear = 1;

  public static class IllegalGearChangeException extends RuntimeException {
  }

  void changeGear(int newGear) {
    if (newGear == gear)
      return;
    if (newGear != -1 && (newGear < 1 || newGear > 5))
      throw new IllegalArgumentException();
    // newGear is now either -1 or 1..=5
    var gearToCheck = newGear;
    if (gearToCheck == -1)
      gearToCheck++;
    if (Math.abs(gear - gearToCheck) != 1)
      throw new IllegalGearChangeException();
    gear = newGear;
  }
}
