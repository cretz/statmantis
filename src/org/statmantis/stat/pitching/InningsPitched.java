package org.statmantis.stat.pitching;

import java.math.BigDecimal;

import org.statmantis.model.Play;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Innings Pitched",
        abbreviations = "IP",
        types = StatisticType.PITCHING)
public class InningsPitched extends PitcherBasedPlayStatistic<BigDecimal> {

    private int outsPitched;

    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().isOut()) {
            outsPitched++;
        }
    }

    @Override
    public BigDecimal getCurrentValue() {
        return new BigDecimal(outsPitched).divide(new BigDecimal(3));
    }
    
}