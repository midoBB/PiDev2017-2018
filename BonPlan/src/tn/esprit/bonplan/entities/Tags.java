package tn.esprit.bonplan.entities;


public class Tags {

    private int _Ref;
    private int _RefEtablissement;
    private String _Valeur;

    public int getRef() {
        return _Ref;
    }

    public void setRef(int value) {
        _Ref = value;
    }

    public int getRefEtablissement() {
        return _RefEtablissement;
    }

    public void setRefEtablissement(int value) {
        _RefEtablissement = value;
    }

    public String getValeur() {
        return _Valeur;
    }

    public void setValeur(String value) {
        _Valeur = value;
    }
}
