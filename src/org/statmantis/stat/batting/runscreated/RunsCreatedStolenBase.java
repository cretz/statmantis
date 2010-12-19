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
package org.statmantis.stat.batting.runscreated;

import java.math.BigDecimal;

import org.statmantis.stat.StatisticInfo;
import org.statmantis.stat.StatisticType;
import org.statmantis.stat.common.AtBats;
import org.statmantis.stat.common.BasesOnBalls;
import org.statmantis.stat.common.CaughtStealing;
import org.statmantis.stat.common.Hits;
import org.statmantis.stat.common.StolenBases;
import org.statmantis.stat.common.TotalBases;

/**
 * This is the stolen base version of Runs Created (RC). See the Wikipedia reference
 * <a href="http://en.wikipedia.org/wiki/Runs_created#.22Stolen_base.22_version_of_runs_created">here</a>.
 * This is a batting only statistic. It is calculated as:
 * <p>
 * <math style="font-size: 200%">
 *     <mfrac>
 *         <mrow>
 *             <mrow>
 *                 <mo>(</mo>
 *                 <mi href="{@linkhref Hits}">H</mi>
 *                 <mo>+</mo>
 *                 <mi href="{@linkhref BasesOnBalls}">BB</mi>
 *                 <mo>-</mo>
 *                 <mi href="{@linkhref CaughtStealing}">CS</mi>
 *                 <mo>)</mo>
 *             </mrow>
 *             <mo>&#215;</mo>
 *             <mrow>
 *                 <mo>(</mo>
 *                 <mi href="{@linkhref TotalBases}">TB</mi>
 *                 <mo>+</mo>
 *                 <mrow>
 *                     <mo>(</mo>
 *                     <mn>.55</mn>
 *                     <mo>&#215;</mo>
 *                     <mi href="{@linkhref StolenBases}">SB</mi>
 *                     <mo>)</mo>
 *                 </mrow>
 *                 <mo>)</mo>
 *             </mrow>
 *         </mrow>
 *         <mrow>
 *             <mi href="{@linkhref AtBats}">AB</mi>
 *             <mo>+</mo>
 *             <mi href="{@linkhref BasesOnBalls}">BB</mi>
 *         </mrow>
 *     </mfrac>
 * </math>
 *
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Runs Created (stolen base)",
        abbreviations = "RC",
        types = StatisticType.BATTING,
        dependencies = {
                Hits.class,
                BasesOnBalls.class,
                CaughtStealing.class,
                TotalBases.class,
                StolenBases.class,
                AtBats.class
        })
public class RunsCreatedStolenBase extends RunsCreatedBasic {

    @Override
    protected BigDecimal getOnBaseFactor() {
        return new BigDecimal(getDependency(Hits.class) +
                getDependency(BasesOnBalls.class) -
                getDependency(CaughtStealing.class));
    }

    @Override
    protected BigDecimal getAdvancementFactor() {
        return new BigDecimal(getDependency(TotalBases.class)).add(
                new BigDecimal("0.55").multiply(new BigDecimal(
                        getDependency(StolenBases.class))));
    }

}
