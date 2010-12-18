package org.statmantis.stat.common;

import org.statmantis.model.Play;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Sacrifice Hits",
        abbreviations = "SH",
        types = { StatisticType.BATTING, StatisticType.PITCHING })
public class SacrificeHits extends PitcherAndBatterBasedPlayStatistic<Integer> {

    private int sacrificeHits = 0;

    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.GROUND_OUT) {
            //check the modifiers
            if (play.getEvent().getModifiers() != null) {
                for (PlayModifier modifier : play.getEvent().getModifiers()) {
                    if (modifier.getType() == PlayModifierType.SACRIFICE_HIT) {
                        sacrificeHits++;
                    }
                }
            }
        }
    }
    
    @Override
    public Integer getCurrentValue() {
        return sacrificeHits;
    }
}
