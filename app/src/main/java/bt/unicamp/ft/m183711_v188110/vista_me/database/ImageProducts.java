package bt.unicamp.ft.m183711_v188110.vista_me.database;

import java.util.HashMap;

import bt.unicamp.ft.m183711_v188110.vista_me.R;

public class ImageProducts {


    private HashMap<String, Integer> imageProducts = new HashMap<String, Integer>();

    public ImageProducts() {
        imageProducts.put("HIofXTzSHi62rhslpaYG", R.drawable.product1);
        imageProducts.put("jCBlwu0b3oQ0yWqitfgK", R.drawable.product2);
        imageProducts.put("oHtETxcOH1gdsINg7i3d", R.drawable.product3);
        imageProducts.put("4cEChYASvSqsDo23haNM", R.drawable.product4);
        imageProducts.put("w7x6hLvRbgvI2LHwVFyM", R.drawable.product5);
        imageProducts.put("wcXUbHO5TAhzUL2MdK80", R.drawable.product6);
        imageProducts.put("d8Yffey5zavPHwOJROFL", R.drawable.product7);
        imageProducts.put("B5PZ7oVuz3fKVkR4FOz7", R.drawable.product8);
        imageProducts.put("rWIoe3Nx0pD5GHc7Lnv9", R.drawable.product9);
        imageProducts.put("zhNrNDxBpYwRLWsZJMqW", R.drawable.product10);
        imageProducts.put("1c2yoVac1SWc4IreeOrv", R.drawable.product11);
        imageProducts.put("KZdF4LAB3O6IGRDfKdzR", R.drawable.product12);
        imageProducts.put("1Ww0pQ9APdEgbrQZpDkq", R.drawable.product13);
        imageProducts.put("al2NtPRb5A7Qp06RUR3F", R.drawable.product14);
        imageProducts.put("X08r69QuaxCeEsV69Xxe", R.drawable.product15);
        imageProducts.put("mnQKvfzxB2ItiO9qRogP", R.drawable.product16);
        imageProducts.put("CvvJQT1iPvKfFs5KfY5M", R.drawable.product17);
        imageProducts.put("YKPaJ2Kcxm3BHXmP6d2z", R.drawable.product18);
        imageProducts.put("7GRUrYCPUrYod4yXHY8j", R.drawable.product19);
        imageProducts.put("2sfAhoa2Lo88uvINk1aV", R.drawable.product20);
    }

    public int getImageDrawableByIdProduct(String idProduct){
        return imageProducts.get(idProduct);
    }

}

