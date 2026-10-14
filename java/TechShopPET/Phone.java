package TechShopPet;

public class Phone extends Product {
    protected int cameraCount;

    public Phone(String name, double price, int ID, int cameraCount) {
        super(name, price, ID);
        if (cameraCount < 0 || cameraCount > 10) {
            throw new IllegalArgumentException("Camera count cannot be negative or greater than 10");
        }
        this.cameraCount = cameraCount;
    }

    public int getCameraCount() {
        return cameraCount;
    }

    @Override
    public String toString() {
        return super.toString() + ", | Count of cameras : " + getCameraCount();
    }
}
    