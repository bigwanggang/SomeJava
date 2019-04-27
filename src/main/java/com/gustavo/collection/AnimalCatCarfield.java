package com.gustavo.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustaov on 2019/1/12.
 */
public class AnimalCatCarfield {
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<Animal>();
        List<Cat> cats = new ArrayList<Cat>();
        List<Garfield> garfields = new ArrayList<Garfield>();

        animalList.add(new Animal());
        cats.add(new Cat());
        garfields.add(new Garfield());

//        List<? extends Cat> extendsCatFromAnimal = animalList;
        List<? super Cat> superCatFromAnimal = animalList;

        List<? extends Cat> extendsCatFromCat = cats;
        List<? super Cat> supercatFromCat = cats;

        List<? extends Cat> extendsCatFromGarfield = garfields;
//        List<? super Cat> superCatFromGarfield = garfields;

//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());
//        extendsCatFromCat.add(new Object());

        supercatFromCat.add(new Cat());
//        supercatFromCat.add(new Animal());
        supercatFromCat.add(new Garfield());


    }
}
