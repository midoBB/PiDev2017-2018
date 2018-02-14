/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.util;

/**
 *
 * @author mohamed
 */
public class Session {
    private static Object passedParameter;

    /**
     * @return the passedParameter
     */
    public static Object getPassedParameter() {
        return passedParameter;
    }

    /**
     * @param aPassedParameter the passedParameter to set
     */
    public static void setPassedParameter(Object aPassedParameter) {
        passedParameter = aPassedParameter;
    }
}
