package org.statmantis.stat;

import java.math.BigDecimal;

import com.google.common.collect.ClassToInstanceMap;

public abstract class AbstractStatistic<T extends Number> implements Statistic<T> {
    
    protected static final BigDecimal NINE = new BigDecimal(9); 

    private ClassToInstanceMap<Statistic<? extends Number>> dependencies;
    
    @Override
    public final void setCurrentDependencies(
            ClassToInstanceMap<Statistic<? extends Number>> dependencies) {
        this.dependencies = dependencies;
    }
    
    protected <U extends Number> U getDependency(Class<? extends Statistic<U>> statistic) {
        Statistic<U> stat = dependencies.getInstance(statistic);
        if (stat == null) {
            throw new IllegalArgumentException("Statistic " + statistic + " not found");
        }
        return stat.getCurrentValue();
    }
}
