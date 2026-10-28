package TechShopPet;

public class Laptop extends Product {
    protected int ram;

    public Laptop(String name, double price, int id, int ram) {
        super(name, "Laptop", price, id);
        if (ram <= 0 || ram > 128) {
            throw new IllegalArgumentException("RAM cannot be negative or exceed 128 GB");
        }

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
