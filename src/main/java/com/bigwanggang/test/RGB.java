package com.bigwanggang.test;

import java.util.Objects;

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
        return red == rgb.red &&
                green == rgb.green &&
                blue == rgb.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }
}
