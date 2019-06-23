package com.gustavo.test;


/**
 * Created by gustaov on 2018/12/19.
 */
public class RGB {
    int red;
    int green;
    int blue;

    public RGB(int reg, int green, int blue) {
        this.red = reg;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "RGB{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RGB rgb = (RGB) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(red, rgb.red)
                .append(green, rgb.green)
                .append(blue, rgb.blue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(red)
                .append(green)
                .append(blue)
                .toHashCode();
    }
}
