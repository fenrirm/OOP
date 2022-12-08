package app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "Beer")
public class Beer {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Manufacturer", required = true)
    protected String manufacturer;
    @XmlElement(name = "PerformanceCharacteristics", required = true)
    protected Chars chars;
    @XmlElement(name = "Ingredients", required = true)
    protected String ingredients;
    @XmlElement(name = "Al", required = true)
    protected boolean al;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected Integer id;

    public void setCharacteristic(String characteristic, String value) {
        switch (characteristic) {
            case "Name":
                setName(value);
                break;
            case "Material":
                getChars().getPour().setMaterial(value);
                break;
            case "Volume":
                getChars().getPour().setVolume(Double.valueOf(value));
                break;
            case "Manufacturer":
                setManufacturer(value);
                break;
            case "Al":
                setAl(Boolean.parseBoolean(value));
                break;
            case "Ingredients":
                setIngredients(value);
                break;
            case "Opacity":
                getChars().setOpacity(Integer.parseInt(value));
                break;
            case "Calories":
                getChars().setCalories(Integer.parseInt(value));
                break;
            case "Rotations":
                getChars().setRotations(Double.parseDouble(value));
                break;
        }
    }

    public void setAl(boolean parseBoolean) {
        this.al = parseBoolean;
    }

    public void setIngredients(String value) {
        this.ingredients = value;
    }

    public List<String> getCharacteristicNames() {
        List<String> result = new ArrayList<>();
        result.add("Name");
        result.add("Volume");
        result.add("Material");
        result.add("Manufacturer");
        result.add("Al");
        result.add("Ingredients");
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    public Chars getChars() {
        return chars;
    }

    public void setChars(Chars value) {
        this.chars = value;
    }

    public Boolean isAlcoholic() {
        return al;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "opacity",
        "calories",
        "rotations"
    })
    public static class Chars {

        public Chars(){
            pour = new Pour();
        }

        @XmlElement(name = "Pour", required = true)
        protected Pour pour;
        @XmlElement(name = "Opacity", required = true)
        protected Integer opacity;
        @XmlElement(name = "Calories")
        protected int calories;
        @XmlElement(name = "Rotations")
        protected double rotations;

        public List<String> getCharacteristicNames() {
            List<String> result = new ArrayList<>();
            result.add("Pour");
            result.add("Opacity");
            result.add("Calories");
            result.add("Rotations");
            result.add("Material");
            result.add("Volume");
            return result;
        }

        public Beer.Pour getPour() {
            return pour;
        }
        public int getOpacity() {
            return opacity;
        }
        public void setOpacity(Integer value) {
            this.opacity = value;
        }
        public double getCalories() {
            return calories;
        }
        public void setCalories(int value) {
            this.calories = value;
        }
        public double getRotations() {
            return rotations;
        }
        public void setRotations(double value) {
            this.rotations = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Chars chars = (Chars) o;
            return calories == chars.calories && Double.compare(chars.rotations, rotations) == 0 && Objects.equals(pour, chars.pour) && Objects.equals(opacity, chars.opacity);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pour, opacity, calories, rotations);
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "material",
            "volume"
    })
    public static class Pour {

            @XmlElement(name = "Volume", required = true)
            protected Double volume;
            @XmlElement(name = "Material")
            protected String material;

            public Double getVolume() {
                return volume;
            }
            public void setVolume(Double value) {
                this.volume = value;
            }

            public String getMaterial() {
                return material;
            }
            public void setMaterial(String value) {
                this.material = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pour pour = (Pour) o;
                return Objects.equals(volume, pour.volume) && Objects.equals(material, pour.material);
            }

            @Override
            public int hashCode() {
                return Objects.hash(volume, material);
            }
        }

        public static class IdComparator implements Comparator<Beer> {

            @Override
            public int compare(Beer g1, Beer g2) {
                return g1.getId().compareTo(g2.getId());
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Beer beer = (Beer) o;
            return al == beer.al &&
                    Objects.equals(name, beer.name) &&
                    Objects.equals(manufacturer, beer.manufacturer) &&
                    Objects.equals(chars, beer.chars) &&
                    Objects.equals(ingredients, beer.ingredients) &&
                    Objects.equals(id, beer.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, manufacturer, ingredients, al, id);
        }
}
