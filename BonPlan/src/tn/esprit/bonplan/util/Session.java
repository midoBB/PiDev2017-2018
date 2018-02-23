/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import tn.esprit.bonplan.UI.BonPlanController;

/**
 *
 * @author mohamed
 */
public class Session {
    private static Object passedParameter;
    private static Node lastView;
    /**
     * @return the mainController
     */
    public static BonPlanController getMainController() {
        return mainController;
    }

    /**
     * @param aMainController the mainController to set
     */
    public static void setMainController(BonPlanController aMainController) {
        mainController = aMainController;
    }
    private static BonPlanController mainController;

    /**
     * @return the lastView
     */
    public static Node getLastView() {
        return lastView;
    }

    /**
     * @param aLastView the lastView to set
     */
    public static void setLastView(Node aLastView) {
        lastView = aLastView;
    }

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
