/*
 * Copyright 2010 Chad Retz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.statmantis.stat.batting;

import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.common.AtBats;
import org.statmantis.stat.common.BasesOnBalls;
import org.statmantis.stat.common.HitByPitches;
import org.statmantis.stat.common.SacrificeFlies;
import org.statmantis.stat.common.SacrificeHits;
import org.statmantis.stat.common.TimesAtPlate;
import org.statmantis.stat.pitching.BattersFaced;

/**
 * This statistic counts the number of Plate Appearances (PA) for a batter. See
 * the Wikipedia reference <a href="http://en.wikipedia.org/wiki/Plate_appearance">here</a>.
 * This is the same as its base {@link TimesAtPlate}, and its pitching counterpart
 * {@link BattersFaced}. This is a batting only statistic and is calculated as:
 * <p>
 * <math style="font-size: 200%">
 *     <mi href="{@linkhref AtBats}">AB</mi>
 *     <mo>+</mo>
 *     <mi href="{@linkhref BasesOnBalls}">BB</mi>
 *     <mo>+</mo>
 *     <mi href="{@linkhref HitByPitches}">HBP</mi>
 *     <mo>+</mo>
 *     <mi href="{@linkhref SacrificeFlies}">SF</mi>
 *     <mo>+</mo>
 *     <mi href="{@linkhref SacrificeHits}">SH</mi>
 * </math>
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Plate Appearances",
        abbreviations = "PA",
        types = StatisticType.BATTING,
        dependencies = {
                AtBats.class,
                BasesOnBalls.class,
                HitByPitches.class,
                SacrificeFlies.class,
                SacrificeHits.class
        })
public class PlateAppearances extends TimesAtPlate {

}
