public class HybridCar implements Vehicle {
    /** liters */
    double fuel;
    /** km per liter */
    final double fuelMileage;
    double electricalEnergy;
    /** km per el energy point */
    final double electricityMileage;

    HybridCar(double fuelMileage, double electricityMileage) {
        this.fuelMileage = fuelMileage;
        this.electricityMileage = electricityMileage;
    }

    @Override
    public int getRemainingRange() {
        return (int)(fuel * fuelMileage + electricalEnergy * electricityMileage);
    }

    @Override
    public int drive(int kms) {
        // km
        var range = getRemainingRange();
        // km
        var toDrive = Math.min(kms, range);
        // km
        var rangeEl = (int)(electricalEnergy * electricityMileage);
        // km
        var toDriveEl = Math.min(toDrive, rangeEl);
        // km
        var toDriveGas = toDrive - toDriveEl;
        // l = 1/(km/l) * km
        var consumptionGas = 1./fuelMileage * toDriveGas;
        var consumptionEl = 1./electricityMileage * toDriveEl;
        fuel -= consumptionGas;
        electricalEnergy -= consumptionEl;
        return toDrive;
    }
}
