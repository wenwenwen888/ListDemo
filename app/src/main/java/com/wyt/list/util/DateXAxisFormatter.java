package com.wyt.list.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Philipp Jahoda on 14/09/15.
 */
public class DateXAxisFormatter implements IAxisValueFormatter {

    protected String[] mMonths = new String[]{
            "8-20", "8-21", "8-22", "8-23", "8-24", "8-25", "8-26", "8-27", "8-28", "8-29", "8-30", "8-31"
    };

    public DateXAxisFormatter() {
        // maybe do something here or provide parameters in constructor

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

//        float percent = value / axis.mAxisRange;
//        return mMonths[(int) (mMonths.length * percent)];
        return mMonths[(int) value];
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
