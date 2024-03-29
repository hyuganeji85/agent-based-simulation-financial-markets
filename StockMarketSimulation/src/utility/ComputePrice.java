package utility;

import application.Periods;
import application.StockMarket;

import java.util.ArrayList;

public class ComputePrice {

    private IndexGenerator index;
    private double currentPrice;
    private Periods period;
    private double slope;

    public ComputePrice(){
        IndexGenerator index = new IndexGenerator(1,10);
    }


    public double currentPrice(int CompanyIndex, int RowIndex){

        //int currentIndexVal = 100;

        //you need to set this based on an index
        double currentIndexVal = index.getPrice(CompanyIndex,RowIndex);

        //herehere //index.getIndex();

        currentPrice = currentIndexVal*0.1;  // I am goin to work on a better way to do this
        //just figured i'd get the ball rolling.

        return currentPrice;
    }

    public double getPriceTrend(int periods){
        ArrayList<Periods> period = new ArrayList<Periods>();
        period = StockMarket.getInstance().getHistoryList().getLastNPeriods(periods);


        double x = 0.0;
        double y = 0.0;
        double xy = 0.0;
        double xSquared = 0.0;
        double xbar = 0.0;
        double ybar = 0.0;
        for(int i=0; i<period.size(); i++ ){
            x = x + period.get(i).getPeriod();
            y = y + period.get(i).getPrice();
            xy = xy + x * y;
            xSquared = xSquared + x * x;
        }

        xbar = x / period.size();
        ybar = y / period.size();

        slope = 0.0;
        slope = (xy - (period.size() * xbar * ybar) ) / (xSquared - (period.size()  * xbar * xbar));

        return slope;
    }







}
