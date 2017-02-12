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
public class TaxPayer_UI {

    private int booleanAgeHolder=0;
    private int booleanBlindHolder=0;
    private int dependent_No1Holder=0;
    private int dependent_No2Holder=0;
    private Scanner scanIn = new Scanner(System.in);
    private IRS_Model taxModel;
    
    public TaxPayer_UI(IRS_Model taxModel) {
        this.taxModel = taxModel;
        Begin();
    }// end constructor

    private void Begin() {
        formFeed(2);
        System.out.println("Please enter your name.");
        System.out.print("> ");
        taxModel.setName(scanIn.nextLine());
        formFeed(2);
        
        
        
        System.out.println(taxModel.getName()+ ", please select your filing status.");
        for(int i = 0; i<taxModel.getFILING_STATUS_ARRAY_Length();i++){
            System.out.printf("\t%d) %s\n",(i+1),taxModel.getFILING_STATUS_ARRAY(i));
        }
        System.out.print("> ");
        taxModel.setFilingStatus(scanIn.nextInt()-1);
        formFeed(2);
        
        
        System.out.println(taxModel.getName()+ ", are you a dependent to another tax filer?");
        System.out.println("\t1) No");
        System.out.println("\t2) Yes");
        System.out.print("> ");
        dependent_No1Holder = scanIn.nextInt();
        formFeed(2);
        
        if(dependent_No1Holder==1)
            taxModel.setDependent_No1(false);
        else
            taxModel.setDependent_No1(true);
        
        if(taxModel.getFilingStatus()!=0){
            System.out.println(taxModel.getName()+ ", is your spouse a dependent to another tax filer?");
            System.out.println("\t1) No");
            System.out.println("\t2) Yes");
            System.out.print("> ");
            dependent_No2Holder = scanIn.nextInt();
            formFeed(2);
            
            if(dependent_No2Holder==1)
            taxModel.setDependent_No2(false);
        else
            taxModel.setDependent_No2(true);
        }
        
        
        
        System.out.println(taxModel.getName()+ ", please select age bracket.");
        System.out.println("\t1) 16-54");
        System.out.println("\t2) 55+");
        System.out.print("> ");
        booleanAgeHolder = scanIn.nextInt();
        formFeed(2);
        
        if(booleanAgeHolder==1)
            taxModel.setAge(false);
        else
            taxModel.setAge(true);
        
        
        //if senior(>55) = false
        if(booleanAgeHolder==1){
        if(taxModel.getFilingStatus()==0)
            System.out.println(taxModel.getName()+ ", are you blind?");
        else
            System.out.println(taxModel.getName()+ ", are either you or your spouse blind?");
        System.out.println("\t1) No");
        System.out.println("\t2) Yes");
        System.out.print("> ");
        booleanBlindHolder = scanIn.nextInt();}
        formFeed(2);
        
        System.out.println(taxModel.getName()+ ", please enter your Gross income.");
        System.out.print("> ");
        taxModel.setGrossIncome(scanIn.nextDouble());
        formFeed(2);
       
        if(booleanBlindHolder==1)
            taxModel.setBlind(false);
        else
            taxModel.setBlind(true);
        
        
        
        //calculate the taxes for the taxpayerUI
        taxModel.doTaxBill();
        
        
        //Name
        System.out.printf("Filers name:\t\t\t%s\n", taxModel.getName());
        //Filing Status
        System.out.printf("Filing Status:\t\t\t%s\n\n", taxModel.getFILING_STATUS_ARRAY(taxModel.getFilingStatus()));
        //Gross Income
        System.out.printf("Taxable Income_0 (Gross Income):%,17.2f\n",taxModel.getGrossIncome());
        //Personal Examption Total
        System.out.printf("Minus Personal Examption:\t%,17.2f\n",taxModel.getPersonalExemption());
        //new Taxable Income
        System.out.printf("Taxable Income_1:\t\t%,17.2f\n",taxModel.getTaxableIncome_0());
        //Personal Examption Total
        System.out.printf("Minus Standard Deduction:\t%,17.2f\n",taxModel.getSTANDARD_DEDUCTION_AMOUNT());
        //Taxable Income
        System.out.printf("Taxable Income_2:\t\t%,17.2f\n",taxModel.getTaxableIncome());
        //Standard Deduction
        System.out.printf("Minus Standard Deduction Total:\t%,17.2f\n",taxModel.getFixedTaxDeduction());
        //tax rate
        System.out.printf("Taxable Income_3:\t\t%,17.2f\n",taxModel.getAfterTaxDeduction());
        //Taxable Income
        System.out.printf("Times Tax Rate%% :\t\t%,17.3f\n",taxModel.getStandardPercentage());
        //times taxrate
        System.out.printf("Taxed at rate%%:\t\t\t%,17.2f\n",taxModel.getTaxRate());
        //Plus standard tax
        System.out.printf("Plus Standard tax:\t\t%,17.2f\n",taxModel.getFixedRate());
        //Tax Amount
        System.out.printf("Final Tax Bill:\t\t\t%,17.2f\n\n",taxModel.getTaxBill());

    }
    
    private void formFeed(int arg1){
        for(int i =0; i<arg1 ; i++){
            System.out.println("");
        }
    }
    
    
    
}
