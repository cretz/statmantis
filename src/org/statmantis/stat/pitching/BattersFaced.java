package org.statmantis.stat.pitching;

import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.common.AtBats;
import org.statmantis.stat.common.BasesOnBalls;
import org.statmantis.stat.common.HitByPitches;
import org.statmantis.stat.common.SacrificeFlies;
import org.statmantis.stat.common.SacrificeHits;
import org.statmantis.stat.common.TimesAtPlate;

@StatisticInfo(
        name = "Batters Faced",
        abbreviations = "BFP",
        types = StatisticType.PITCHING,
        dependencies = {
                AtBats.class,
                BasesOnBalls.class,
                HitByPitches.class,
                SacrificeFlies.class,
                SacrificeHits.class
        })
public class BattersFaced extends TimesAtPlate {

}
