/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lda;

import jgibblda.Estimator;
import jgibblda.LDACmdOption;

/**
 *
 * @author dell-pc
 */
public class LDA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Specific
        LDACmdOption ldaOption = new LDACmdOption();   
        ldaOption.est = true;  
        ldaOption.estc = false;  
        ldaOption.modelName = "model-final";
        ldaOption.dir = "D:\\aaMyPro\\LDA\\SpecificResult";
        ldaOption.dfile = "Specific4.txt";  
        ldaOption.alpha = 0.5;  
        ldaOption.beta = 0.1;  
        ldaOption.K = 5;  
        ldaOption.twords = 10;
        ldaOption.niters = 1000;  
        Estimator estimator = new Estimator();  
        estimator.init(ldaOption);  
        estimator.estimate();  
        //Demand 
        ldaOption.est = true;  
        ldaOption.estc = false;  
        ldaOption.modelName = "model-final";
        ldaOption.dir = "D:\\aaMyPro\\LDA\\DemandResult";
        ldaOption.dfile = "Demand4.txt";  
        ldaOption.alpha = 0.5;  
        ldaOption.beta = 0.1;  
        ldaOption.K = 8;  
        ldaOption.twords = 10;
        ldaOption.niters = 1000;  
        estimator.init(ldaOption);  
        estimator.estimate();
        
        //Specific info
        ldaOption.est = true;  
        ldaOption.estc = false;  
        ldaOption.modelName = "model-final";
        ldaOption.dir = "D:\\aaMyPro\\LDA\\SpecificNoinfo";
        ldaOption.dfile = "Specific4info.txt";  
        ldaOption.alpha = 0.5;  
        ldaOption.beta = 0.1;  
        ldaOption.K = 5;  
        ldaOption.twords = 10;
        ldaOption.niters = 1000;  
        estimator.init(ldaOption);  
        estimator.estimate();  
        //Demand info
        ldaOption.est = true;  
        ldaOption.estc = false;  
        ldaOption.modelName = "model-final";
        ldaOption.dir = "D:\\aaMyPro\\LDA\\DemandNoinfo";
        ldaOption.dfile = "Demand4info.txt";  
        ldaOption.alpha = 0.5;  
        ldaOption.beta = 0.1;  
        ldaOption.K = 8;  
        ldaOption.twords = 10;
        ldaOption.niters = 1000;  
        estimator.init(ldaOption);  
        estimator.estimate();
    }
}
