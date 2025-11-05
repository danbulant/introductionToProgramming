interface Vehicle {
    int getRemainingRange();
    int drive(int kms);

    public static void main(String[] args) {
        // 100km / 5l
        var gasCar = new GasolineCar(100. / 5.);
        gasCar.fuel = 10; // 200km fuel
        // 100km / 5l; 10 km / "energy unit"
        var hybridCar = new HybridCar(100. / 5., 10.);
        hybridCar.fuel = 5; // 100km fuel
        hybridCar.electricalEnergy = 5; // 50km eletricity

        assert gasCar.getRemainingRange() == 200;
        assert gasCar.drive(100) == 100;
        assert gasCar.getRemainingRange() == 100;
        assert gasCar.drive(200) == 100;
        assert gasCar.getRemainingRange() == 0;

        assert hybridCar.getRemainingRange() == 150;
        assert hybridCar.drive(50) == 50;
        assert hybridCar.electricalEnergy == 0;
        assert hybridCar.fuel == 5;
        assert hybridCar.drive(200) == 100;
        assert hybridCar.getRemainingRange() == 0;
    }
}
