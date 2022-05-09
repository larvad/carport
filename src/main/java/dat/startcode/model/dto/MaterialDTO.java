package dat.startcode.model.dto;

public class MaterialDTO {

    int materialID;
    int quantity;

    public MaterialDTO(int materialID, int quantity) {
        this.materialID = materialID;
        this.quantity = quantity;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
