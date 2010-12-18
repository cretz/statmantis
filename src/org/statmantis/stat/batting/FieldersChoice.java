package org.statmantis.stat.batting;

import org.statmantis.model.Play;
import org.statmantis.model.PlayType;
import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;

@StatisticInfo(
        name = "Fielders Choice",
        abbreviations = "FC",
        types = StatisticType.BATTING)
public class FieldersChoice extends BatterBasedPlayStatistic<Integer> {

    private int fieldersChoice = 0;
    
    @Override
    public void applyPlay(Play play) {
        if (isPlayApplicable(play) && play.getEvent().
                getPlayType() == PlayType.FIELDERS_CHOICE) {
            fieldersChoice++;
        }
    }

    @Override
    public Integer getCurrentValue() {
        return fieldersChoice;
    }

}
