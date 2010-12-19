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
