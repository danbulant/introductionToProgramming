public class GasolineCar implements Vehicle {
    /** liters */
    double fuel;
    /** km per liter */
    final double mileage;

    /**
     * @param mileage km per liter
     */
    GasolineCar(double mileage) {
        this.mileage = mileage;
    }

    @Override
    public int getRemainingRange() {
        return (int)Math.floor(fuel * mileage);
    }

    @Override
    public int drive(int kms) {
        var range = getRemainingRange();
        var toDrive = Math.min(range, kms);
        // l = 1/(km/l) * km
        var consumption = 1./mileage * toDrive;
        fuel -= consumption;

        return toDrive;
    }
}
