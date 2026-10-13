package TechShopPet;

public class Phone extends Product {
    protected int cameraCount;

    public Phone(String name, double price, int cameraCount) {
        if (cameraCount < 0 || Double.isNaN(cameraCount) || Double.isInfinite(cameraCount)|| cameraCount > 10) {
            throw new IllegalArgumentException("Camera count cannot be negative");
        }
        super(name, price);
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
