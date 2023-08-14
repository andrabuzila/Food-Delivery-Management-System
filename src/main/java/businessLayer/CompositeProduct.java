package businessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    private List<MenuItem> products = new ArrayList<>();
    private String title;
    private float ratings;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    @Override
    public void setPrice(float price) {
        this.price = computePrice();
    }

    void addItemToList(MenuItem item){
        products.add(item);
    }
    @Override

    float computePrice() {
        float newPrice=0;
        for(MenuItem p: products){
            newPrice+=p.computePrice();
        }
        super.setPrice(newPrice);
        return newPrice;
    }

    @Override
    public float getPrice() {
        return computePrice();
    }

    @Override
    public Object getAll() {
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

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public float getRatings() {
        return ratings;
    }

    @Override
    public float getCalories() {
        return calories;
    }

    @Override
    public float getProteins() {
        return proteins;
    }

    @Override
    public float getFats() {
        return fats;
    }

    @Override
    public float getSodium() {
        return sodium;
    }
}
