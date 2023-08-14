package businessLayer;

import java.nio.charset.Charset;
import java.util.Objects;

public abstract class MenuItem {
    public float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        return this.getTitle().compareTo(((MenuItem) o).getTitle()) == 0;
    }


    abstract float computePrice();

    public abstract Object getAll();

    public abstract String getTitle();

    public abstract float getRatings();

    public abstract float getCalories();

    public abstract float getProteins();

    public abstract float getFats();

    public abstract float getSodium();
}
