package Entities;

public class FoodItem {
    private String name;
    private String type;
    private float price;
    private int quantityInCart = 1;
    public String getImageSrc() {
        return ImageSrc;
    }
    public void incrementQuantityInCart() {
        quantityInCart++;
    }
    public void decrementQuantityInCart() {
        quantityInCart--;
    }
    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }

    private String ImageSrc;


    public FoodItem(String name, float price, String type, String imageSrc) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.ImageSrc = imageSrc;
    }


    public String getName() {
        // Assuming you want to return the first name in the list, if not, modify this as needed
        return name;
    }


    public float getPrice() {
        return price;
    }


    public String getType() {
        return type;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }
}
