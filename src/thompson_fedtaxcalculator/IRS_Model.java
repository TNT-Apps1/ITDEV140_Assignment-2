/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thompson_fedtaxcalculator;

/**
 *
 * @author Grant Thompson
 * ITDEV 140 THUR EVE
 * ASSIGNMENT 2
 */
public class IRS_Model {
    
    private String name;
    private int exemptions;
    private int finalExemptions;
    private double grossIncome;
    private double MAX_VALUE = Double.MAX_VALUE;
    private int filingStatus=0;
    private boolean age=false;
    private boolean blind=false;
    private boolean dependent_No1 = false;
    private boolean dependent_No2 = false;
    
    private double taxableIncome = 0.00;
    private double taxableIncome_0 = 0.00;
    private double fixedTaxDeduction = 0.00;
    private double fixedRate = 0.00;
    private double standardPercentage = 0.00;
    private double taxBill = 0.00;
    private double grossIncomeCalculated=0.0;
    private double personalExemption = 0.00;
    
    private double PERSONAL_EXEMPTION_AMOUNT = 4050;
    
    /*2016 Income Tax Brackets
    The Federal income tax has 7 brackets: 10%, 15%, 25%, 28%, 33%, 35%, and 39.6%. The amount of tax you owe depends on your income level and filing status.
    It’s important to understand that moving into a higher tax bracket does not mean that all of your income will be taxed at a higher rate. 
    Instead, only the money that you earn within a particular bracket is subject to that particular tax rate.
 */   
    
    private String[] FILING_STATUS_ARRAY = {"Single","Married Filing Jointly","Qualified Widow(er)","Married Filing Separately","Head of Household"};
    
    //https://www.irs.com/articles/2016-federal-tax-rates-personal-exemptions-and-standard-deductions
    private double[][][] FILING_CHART ={
        {{0,9275,0,0.10,0},{9276,37650,927.50,0.15,9275},{37651,91150,5183.75,0.25,37650},{91151,190150,18558.75,0.28,91150},{190151,413350,46278.75,0.33,190150},{413351,415050,119934.75,0.35,413350},{415051,MAX_VALUE,120529.75,0.396,415050}},
        {{0,18550,0,0.10,0},{18551,75300,1855,0.15,18550},{75301,151900,10367.50,0.25,75300},{151901,231450,29517.50,0.28,151900},{231451,413350,51791.50,0.33,231450},{413351,466950,111818.50,0.35,413350},{466951,MAX_VALUE,130578.50,0.396,466950}},
        {{0,18550,0,0.10,0},{18551,75300,1855,0.15,18550},{75301,151900,10367.50,0.25,75300},{151901,231450,29517.50,0.28,151900},{231451,413350,51791.50,0.33,231450},{413351,466950,111818.50,0.35,413350},{466951,MAX_VALUE,130578.50,0.396,466950}},
        {{0,9275,0,0.10,0},{9276,37650,927.50,0.15,9275},{37651,75950,5183.75,0.25,37650},{75951,115725,14758.75,0.28,75950},{115726,206675,25,895.75,0.33,115725},{206676,233475,55909.25,0.35,206675},{233476,MAX_VALUE,65289.25,0.396,233475}},
        {{0,13250,0,0.10,0},{13251,50400,1325,0.15,13250},{50401,130150,6897.50,0.25,50400},{130151,210800,26835,0.28,130150},{210801,413350,49417,0.33,210800},{413351,441000,116258.50,0.35,413350},{441000,MAX_VALUE,125936,0.396,441000}}
    };
    
    
   /*2016 Personal Exemption Amounts
    For tax year 2016, the personal exemption amount is $4,050 (compared to $4,000 in 2015).
    
    You are allowed to claim one personal exemption for yourself and one for your spouse (if married). 
    However, if somebody else can list you as a dependent on their tax return, you are not permitted to claim a personal exemption for yourself.
    
    The personal exemption amount “phases out” for taxpayers with higher incomes. The Personal Exemption Phaseout (PEP) thresholds are as follows:*/
    
    
    private double[][] PEP_THRESHOLDS = {{259400,381900},{311300,433800},{311300,433800},{155650,216900},{285350,407850}};
    
    
    /*2016 Standard Deduction Amounts
    There are two main types of tax deductions: the standard deduction and itemized deductions. You can claim one type of deduction on your tax return, but not both.
    
    For example, if you claim the standard deduction, you cannot itemize deductions – and vice versa (if you itemize deductions, you cannot claim the standard deduction). 
    You are allowed to use whichever type of deduction results in the lowest tax.
    
    The standard deduction is subtracted from your Adjusted Gross Income (AGI), thereby reducing your taxable income. 
    For tax year 2016, the standard deduction amounts are as follows:*/
    
    /*Note that there is an additional standard deduction for elderly or blind taxpayers, which is $1,250 for tax year 2016. The additional
    standard deduction amount increases to $1,550 if the individual is also unmarried and not a qualifying widow(er).*/
    
    private double[] STANDARD_DEDUCTION ={6300, 12600, 12600, 6300, 9300};

    public double getStandardPercentage() {
        return standardPercentage;
    }
    
    

    public double getFixedRate() {
        return fixedRate;
    }
    
    public double getTaxBill() {
        return taxBill;
    }
    
    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getTaxableIncome_0() {
        return taxableIncome_0;
    }
    
    public void setDependent_No1(boolean dependent_No1) {
        this.dependent_No1 = dependent_No1;
    }

    public void setDependent_No2(boolean dependent_No2) {
        this.dependent_No2 = dependent_No2;
    }    

    public double getPersonalExemption() {
        return personalExemption;
    }
    
    public double getSTANDARD_DEDUCTION_AMOUNT(){
        return STANDARD_DEDUCTION[filingStatus];
    }

    public int getFILING_STATUS_ARRAY_Length() {
        return FILING_STATUS_ARRAY.length;
    }

    public String getFILING_STATUS_ARRAY(int arg1) {
        return FILING_STATUS_ARRAY[arg1];
    }

    public void setBlind(boolean blind) {
        this.blind = blind;
    }

    
    public void setAge(boolean age) {
        this.age = age;
    }

    public int getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(int filingStatus) {
        this.filingStatus = filingStatus;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getFixedTaxDeduction() {
        return fixedTaxDeduction;
    }
    
    
    public void doTaxBill(){
        grossIncomeCalculated = grossIncome;
        double additionalDeduction = 0.00;
        double taxableIncome_1 = 0.00;
        
        
        
        /*2016 Personal Exemption Amounts*/
        
        //if gross amount dosen't fall inside the threshold include personal exemption
        if(grossIncomeCalculated<PEP_THRESHOLDS[filingStatus][0]||grossIncomeCalculated>PEP_THRESHOLDS[filingStatus][1]){
            
            //if married and neither are dependents of another taxpayer double personal exemption
            if((filingStatus==1||filingStatus==2)&&(!dependent_No1&&!dependent_No2)){
                personalExemption = PERSONAL_EXEMPTION_AMOUNT*2;
                grossIncomeCalculated=grossIncomeCalculated-personalExemption;
                //else add single exemption if no a dependent of another taxpayer
            }else if(!dependent_No1){
                personalExemption = PERSONAL_EXEMPTION_AMOUNT;
               grossIncomeCalculated=grossIncomeCalculated-personalExemption;
            }
        }
            


       
        //if blind or senior citizen add an additional deduction
        if(age||blind){
            if(filingStatus==0){//if blind (or senior) and single; add additional deduction
                additionalDeduction = 1550.00;
                grossIncomeCalculated=grossIncomeCalculated-additionalDeduction;
            }else{//if blind (or senior) and married; add additional deduction
                additionalDeduction = 1250.00;
                grossIncomeCalculated=grossIncomeCalculated-additionalDeduction;
            }
        }
        
        
        taxableIncome_0=grossIncomeCalculated; //set properties of taxable Income_0   
        personalExemption=personalExemption+additionalDeduction;//set properties of personalExemption
        
        
        /*2016 Standard Deduction Amounts*/
        grossIncomeCalculated=grossIncomeCalculated-STANDARD_DEDUCTION[filingStatus];
        
        taxableIncome_1=taxableIncome=grossIncomeCalculated;//set taxableIncome property
        //taxableIncome_1=taxableIncome;
        
        if(taxableIncome_1<FILING_CHART[filingStatus][0][1]){
            fixedRate=FILING_CHART[filingStatus][0][2];
            standardPercentage = FILING_CHART[filingStatus][0][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][0][4];
        }else if(taxableIncome_1<FILING_CHART[filingStatus][1][1]){
            fixedRate=FILING_CHART[filingStatus][1][2];
            standardPercentage = FILING_CHART[filingStatus][1][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][1][4];
        }else if(taxableIncome_1<FILING_CHART[filingStatus][2][1]){
            fixedRate=FILING_CHART[filingStatus][2][2];
            standardPercentage = FILING_CHART[filingStatus][2][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][2][4];
        }else if(taxableIncome_1<FILING_CHART[filingStatus][3][1]){
            fixedRate=FILING_CHART[filingStatus][3][2];
            standardPercentage = FILING_CHART[filingStatus][3][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][3][4];
        }else if(taxableIncome_1<FILING_CHART[filingStatus][4][1]){
            fixedRate=FILING_CHART[filingStatus][4][2];
            standardPercentage = FILING_CHART[filingStatus][4][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][4][4];
        }else if(taxableIncome_1<FILING_CHART[filingStatus][5][1]){
            fixedRate=FILING_CHART[filingStatus][5][2];
            standardPercentage = FILING_CHART[filingStatus][5][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][5][4];
        }else{
            fixedRate=FILING_CHART[filingStatus][6][2];
            standardPercentage = FILING_CHART[filingStatus][6][3];
            fixedTaxDeduction=FILING_CHART[filingStatus][6][4];
        }
        
        
        taxableIncome_1=(taxableIncome_1-fixedTaxDeduction)*standardPercentage;
        taxBill=taxableIncome_1+fixedRate;//set taxBill property
        
        
    }
    
    public double getTaxRate(){
        return (getTaxableIncome()-getFixedTaxDeduction())*getStandardPercentage();
    }
    
    public double getAfterTaxDeduction(){
        return getTaxableIncome()-getFixedTaxDeduction();
    }
    
    
}
