package dat.startcode.model.entities;

public class Materials {

    private int materialId;
    private String type;
    private int height;
    private int width;
    private int length;
    private String unit;
    private int categoryId;
    private int angle;
    private int rollLength;
    private int amountInBox;
    private double price;

    public Materials(int materialId, String type, int height, int width, int length, String unit, int categoryId, double price) {
        this.materialId = materialId;
        this.type = type;
        this.height = height;
        this.width = width;
        this.length = length;
        this.unit = unit;
        this.categoryId = categoryId;
        this.price = price;
    }

    public Materials(String type) {
        this.type = type;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getRollLength() {
        return rollLength;
    }

    public void setRollLength(int rollLength) {
        this.rollLength = rollLength;
    }

    public int getAmountInBox() {
        return amountInBox;
    }

    public void setAmountInBox(int amountInBox) {
        this.amountInBox = amountInBox;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "materialId=" + materialId +
                ", type='" + type + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", unit='" + unit + '\'' +
                ", categoryId=" + categoryId +
                ", angle=" + angle +
                ", rollLength=" + rollLength +
                ", amountInBox=" + amountInBox +
                ", price=" + price +
                '}';
    }
}
