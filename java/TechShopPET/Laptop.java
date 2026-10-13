package TechShopPet;


public class Laptop extends Product {
    protected int ram;

    public Laptop(String name, double price, int ram) {

        if (ram < 0 || Double.isNaN(ram) || Double.isInfinite(ram)|| ram > 128) {
            throw new IllegalArgumentException("RAM cannot be negative");
        }
        super(name, price);
        this.ram = ram;
    }

    public int getRam() {
        return ram;
    }

    @Override
    public String toString() {
        return super.toString() + ", | RAM : " + getRam();
    }
}
