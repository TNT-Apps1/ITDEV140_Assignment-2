/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson_fedtaxcalculator;

import java.util.Scanner;

/**
 *
 * @author Grant Thompson
 * ITDEV 140 THUR EVE
 * ASSIGNMENT 2
 */
public class Controller {
    
    IRS_Model IRS_Model_1;
    TaxPayer_UI TaxPayer_1;
    Scanner scan1 = new Scanner(System.in);

    public Controller() {
        BeginTaxing();
    }

    private void BeginTaxing() {
        
        String loopControll ="y";
        
        do {
            
            IRS_Model_1 = new IRS_Model();
            TaxPayer_1 = new TaxPayer_UI(IRS_Model_1);
            
            System.out.println(IRS_Model_1.getName()+ ", would you like to try again? y:n");
            loopControll = scan1.nextLine().toLowerCase();
            
            
            
        } while (!loopControll.equals("n"));
        
    }
    
    
    
}
