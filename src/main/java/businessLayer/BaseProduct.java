package businessLayer;

public class BaseProduct extends MenuItem{
    private String title;
    private float ratings;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;

    public String getTitle() {
        return title;
    }
    public Object[] getAll(){
        Object[] items = new Object[7];
        items[0]=title;
        items[1]=String.valueOf(ratings);
        items[2]=String.valueOf(calories);
        items[3]=String.valueOf(proteins);
        items[4]=String.valueOf(fats);
        items[5]=String.valueOf(sodium);
        items[6]=String.valueOf(price);
        return items;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    float computePrice() {
        super.setPrice(this.price);
        return this.price;
    }
}
