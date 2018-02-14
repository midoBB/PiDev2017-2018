/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.enumerations;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mohamed
 */
public enum CategorieEtablissement {
    Restaurent(0),
    Bar(1),
    Hotel(2),
    Cafe(3);

    private int value;
    private static Map map = new HashMap<>();

    private CategorieEtablissement(int value) {
        this.value = value;
    }

    static {
        for (CategorieEtablissement pageType : CategorieEtablissement.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static CategorieEtablissement valueOf(int pageType) {
        return (CategorieEtablissement) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}