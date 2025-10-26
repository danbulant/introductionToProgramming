class ShoppingCart {
    public String ownerName;
    // assume maximum of 100 items in a cart.
    private String[] itemNames = new String[100];
    private double[] itemPrices = new double[100];
    private int itemCount = 0;
    public double discount = 0.;

    ShoppingCart(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getTotal() {
        var price = 0.;
        for(int i = 0; i < this.itemCount; i++) {
            price += this.itemPrices[i];
        }
        return price * (1. - discount);
    }

    public void add(String name, double price) {
        assert this.itemCount <= this.itemNames.length;
        this.itemNames[this.itemCount] = name;
        this.itemPrices[this.itemCount] = price;
        this.itemCount++;
    }

    /**
     * Removes an item with a given name and price combination from the shopping cart.
     * Returns boolean of whether such item existed before (and was removed).
     */
    public boolean remove(String name, double price) {
        for (int i = 0; i < this.itemCount; i++) {
            if (this.itemNames[i].equals(name)) {
                if(this.itemPrices[i] != price) continue;
                // overwrite the removed item by the next element
                // results in array with no gaps
                for (int j = i; j < this.itemCount - 1; j++) {
                    this.itemNames[j] = this.itemNames[j + 1];
                    this.itemPrices[j] = this.itemPrices[j + 1];
                }
                // deinitialize last value to prevent data corruption
                // in case of logic errors with e.g. not using itemCount later
                this.itemNames[this.itemCount - 1] = null;
                this.itemPrices[this.itemCount - 1] = 0.;
                this.itemCount -= 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Applies a new discount, overwriting any previously set discount.
     * @param newDiscount as real value, for example '0.1' meaning a 10% discount
     */
    public void setDiscount(double newDiscount) {
        assert newDiscount >= 0;
        this.discount = newDiscount;
    }
}