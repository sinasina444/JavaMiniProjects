package Homework2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* Hint, to compute the future value of an annuity
* FVa = P * [ (((1 + r)^t) - 1) / r ]
*  where P is the payment amount
*  where r is the interest rate
*  and where t is the time in years (e.g., 6 months t=0.5)
*
* Hint, to compute the future value of an annuity with compounding
* FVac = P * [ (((1 + (r / m))^(m * t)) - 1) / (r / m) ]
*  where P is the payment amount
*  where r is the interest rate
*  where m is the number of compounding periods in a year (e.g., annually m=1, semiannually m=2, quarterly m=4, monthly m=12)
*  and where t is the time in years (e.g., 6 months t=0.5)
*/
public class AnnuityCalculator {
	 /**
     * Use this scale when doing BigDecimal division.
     */
    private static final int DEFAULT_SCALE = 10;

    /**
     * Use this rounding mode when doing BigDecimal division.
     */
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    //FVa = P * [ (((1 + r)^t) - 1) / r ]
	//where P is the payment amount
	//*  where r is the interest rate
	//*  and where t is the time in years (e.g., 6 months t=0.5)
	//BigDecimal bdTemp = BigDecimal.valueOf(bdannualInterestRate.doubleValue());
    public BigDecimal computeFutureValueOfAnnuityIn15Years(double annuityAmount, double annualInterestRateInPercent) {
    	BigDecimal bdAnnuityAmount = BigDecimal.valueOf(annuityAmount);
    	BigDecimal bdannualInterestRateInPercent = BigDecimal.valueOf(annualInterestRateInPercent);
    	BigDecimal bdannualInterestRate = bdannualInterestRateInPercent.divide(BigDecimal.valueOf(100), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	BigDecimal bdTemp = bdannualInterestRate;
    	bdTemp = bdTemp.add(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.pow(15);
    	bdTemp = bdTemp.subtract(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.divide(bdannualInterestRate, DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	return bdTemp.multiply(bdAnnuityAmount).setScale(10);
    }

    public BigDecimal computeFutureValueOfAnnuityIn30Years(double annuityAmount, double annualInterestRateInPercent) {
    	BigDecimal bdAnnuityAmount = BigDecimal.valueOf(annuityAmount);
    	BigDecimal bdannualInterestRateInPercent = BigDecimal.valueOf(annualInterestRateInPercent);
    	BigDecimal bdannualInterestRate = bdannualInterestRateInPercent.divide(BigDecimal.valueOf(100), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	BigDecimal bdTemp = bdannualInterestRate;
    	bdTemp = bdTemp.add(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.pow(30);
    	bdTemp = bdTemp.subtract(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.divide(bdannualInterestRate, DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	return bdTemp.multiply(bdAnnuityAmount).setScale(10);
    }

    public BigDecimal computeMonthlyCompoundedFutureValueOfAnnuityIn15Years(double annuityAmount, double annualInterestRateInPercent) {
    	/* FVac = P * [ (((1 + (r / m))^(m * t)) - 1) / (r / m) ]
    			 *  where P is the payment amount
    			 *  where r is the interest rate
    			 *  where m is the number of compounding periods in a year (e.g., annually m=1, semiannually m=2, quarterly m=4, monthly m=12)
    			 *  and where t is the time in years (e.g., 6 months t=0.5)
    			 */
    	BigDecimal bdAnnuityAmount = BigDecimal.valueOf(annuityAmount);
    	BigDecimal bdannualInterestRateInPercent = BigDecimal.valueOf(annualInterestRateInPercent);
    	BigDecimal bdannualInterestRate = bdannualInterestRateInPercent.divide(BigDecimal.valueOf(100), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	BigDecimal bdTemp = bdannualInterestRate.divide(BigDecimal.valueOf(12), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	bdTemp = bdTemp.add(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.pow(12*15);
    	bdTemp = bdTemp.subtract(BigDecimal.valueOf(1));
    	BigDecimal bddivisor = bdannualInterestRate.divide(BigDecimal.valueOf(12), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	bdTemp = bdTemp.divide(bddivisor, DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	return bdTemp.multiply(bdAnnuityAmount).setScale(10);
    }

    public BigDecimal computeMonthlyCompoundedFutureValueOfAnnuityIn30Years(double annuityAmount, double annualInterestRateInPercent) {
       	BigDecimal bdAnnuityAmount = BigDecimal.valueOf(annuityAmount);
    	BigDecimal bdannualInterestRateInPercent = BigDecimal.valueOf(annualInterestRateInPercent);
    	BigDecimal bdannualInterestRate = bdannualInterestRateInPercent.divide(BigDecimal.valueOf(100), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	BigDecimal bdTemp = bdannualInterestRate.divide(BigDecimal.valueOf(12), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	bdTemp = bdTemp.add(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.pow(12*30);
    	bdTemp = bdTemp.subtract(BigDecimal.valueOf(1));
    	BigDecimal bddivisor = bdannualInterestRate.divide(BigDecimal.valueOf(12), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	bdTemp = bdTemp.divide(bddivisor, DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	return bdTemp.multiply(bdAnnuityAmount).setScale(10);
    }

    public BigDecimal computeFutureValueOfAnnuity(double annuityAmount, double annualInterestRateInPercent, int years) {
    	BigDecimal bdAnnuityAmount = BigDecimal.valueOf(annuityAmount);
    	BigDecimal bdannualInterestRateInPercent = BigDecimal.valueOf(annualInterestRateInPercent);
    	BigDecimal bdannualInterestRate = bdannualInterestRateInPercent.divide(BigDecimal.valueOf(100), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	BigDecimal bdTemp = bdannualInterestRate;
    	bdTemp = bdTemp.add(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.pow(years);
    	bdTemp = bdTemp.subtract(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.divide(bdannualInterestRate, DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	return bdTemp.multiply(bdAnnuityAmount).setScale(10);
    }

    public BigDecimal computeMonthlyCompoundedFutureValueOfAnnuity(double annuityAmount, double annualInterestRateInPercent, int years) {
       	BigDecimal bdAnnuityAmount = BigDecimal.valueOf(annuityAmount);
    	BigDecimal bdannualInterestRateInPercent = BigDecimal.valueOf(annualInterestRateInPercent);
    	BigDecimal bdannualInterestRate = bdannualInterestRateInPercent.divide(BigDecimal.valueOf(100), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	BigDecimal bdTemp = bdannualInterestRate.divide(BigDecimal.valueOf(12), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	bdTemp = bdTemp.add(BigDecimal.valueOf(1));
    	bdTemp = bdTemp.pow(12*years);
    	bdTemp = bdTemp.subtract(BigDecimal.valueOf(1));
    	BigDecimal bddivisor = bdannualInterestRate.divide(BigDecimal.valueOf(12), DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	bdTemp = bdTemp.divide(bddivisor, DEFAULT_SCALE,DEFAULT_ROUNDING_MODE);
    	return bdTemp.multiply(bdAnnuityAmount).setScale(10);
    }
}
