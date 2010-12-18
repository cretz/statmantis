package org.statmantis.stat.common;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;

public abstract class BaseRuns extends AbstractStatistic<BigDecimal> {

    protected abstract BigDecimal getBaserunners();
    
    protected abstract BigDecimal getAdvancement();
    
    protected abstract BigDecimal getOuts();
    
    protected abstract BigDecimal getGuaranteedRuns();
    
    @Override
    public BigDecimal getCurrentValue() {
        BigDecimal baserunners = getBaserunners();
        BigDecimal advancement = getAdvancement();
        BigDecimal outs = getOuts();
        BigDecimal guaranteedRuns = getGuaranteedRuns();
        BigDecimal denom = getAdvancement().add(outs);
        if (denom.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return baserunners.multiply(advancement).divide(denom).
                add(guaranteedRuns);
    }

}
