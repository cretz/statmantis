package org.statmantis.stat.common;

import org.statmantis.stat.AbstractStatistic;

public abstract class TimesAtPlate extends AbstractStatistic<Integer> {

    @Override
    public Integer getCurrentValue() {
        //AB+BB+HBP+SF+SH
        return getDependency(AtBats.class) +
                getDependency(BasesOnBalls.class) +
                getDependency(HitByPitches.class) +
                getDependency(SacrificeFlies.class) +
                getDependency(SacrificeHits.class);
    }

}
