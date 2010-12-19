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
import org.statmantis.stat.common.GroundedIntoDoublePlays;
import org.statmantis.stat.common.HitByPitches;
import org.statmantis.stat.common.Hits;
import org.statmantis.stat.common.IntentionalWalks;
import org.statmantis.stat.common.SacrificeFlies;
import org.statmantis.stat.common.SacrificeHits;
import org.statmantis.stat.common.StolenBases;
import org.statmantis.stat.common.TotalBases;

/**
 * This is the technical version of Runs Created (RC). See the Wikipedia reference
 * <a href="http://en.wikipedia.org/wiki/Runs_created#.22Technical.22_version_of_runs_created">here</a>.
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
 *                 <mo>+</mo>
 *                 <mi href="{@linkhref HitByPitches}">HBP</mi>
 *                 <mo>-</mo>
 *                 <mi href="{@linkhref GroundedIntoDoublePlays}">GIDP</mi>
 *                 <mo>)</mo>
 *             </mrow>
 *             <mo>&#215;</mo>
 *             <mrow>
 *                 <mo>(</mo>
 *                 <mi href="{@linkhref TotalBases}">TB</mi>
 *                 <mo>+</mo>
 *                 <mrow>
 *                     <mo>(</mo>
 *                     <mn>.26</mn>
 *                     <mo>&#215;</mo>
 *                     <mrow>
 *                         <mo>(</mo>
 *                         <mi href="{@linkhref BasesOnBalls}">BB</mi>
 *                         <mo>-</mo>
 *                         <mi href="{@linkhref IntentionalWalks}">IBB</mi>
 *                         <mo>+</mo>
 *                         <mi href="{@linkhref HitByPitches}">HBP</mi>
 *                         <mo>)</mo>
 *                     </mrow>
 *                     <mo>)</mo>
 *                 </mrow>
 *                 <mo>+</mo>
 *                 <mrow>
 *                     <mo>(</mo>
 *                     <mn>.52</mn>
 *                     <mo>&#215;</mo>
 *                     <mrow>
 *                         <mo>(</mo>
 *                         <mi href="{@linkhref SacrificeHits}">SH</mi>
 *                         <mo>+</mo>
 *                         <mi href="{@linkhref SacrificeFlies}">SF</mi>
 *                         <mo>+</mo>
 *                         <mi href="{@linkhref StolenBases}">SB</mi>
 *                         <mo>)</mo>
 *                     </mrow>
 *                     <mo>)</mo>
 *                 </mrow>
 *                 <mo>)</mo>
 *             </mrow>
 *         </mrow>
 *         <mrow>
 *             <mi href="{@linkhref AtBats}">AB</mi>
 *             <mo>+</mo>
 *             <mi href="{@linkhref BasesOnBalls}">BB</mi>
 *             <mo>+</mo>
 *             <mi href="{@linkhref HitByPitches}">HBP</mi>
 *             <mo>+</mo>
 *             <mi href="{@linkhref SacrificeHits}">SH</mi>
 *             <mo>+</mo>
 *             <mi href="{@linkhref SacrificeFlies}">SF</mi>
 *         </mrow>
 *     </mfrac>
 * </math>
 * @author Chad Retz
 */
@StatisticInfo(
        name = "Runs Created (technical)",
        abbreviations = "RC",
        types = StatisticType.BATTING,
        dependencies = {
                Hits.class,
                BasesOnBalls.class,
                CaughtStealing.class,
                HitByPitches.class,
                GroundedIntoDoublePlays.class,
                TotalBases.class,
                IntentionalWalks.class,
                SacrificeHits.class,
                SacrificeFlies.class,
                StolenBases.class
        })
public class RunsCreatedTechnical extends RunsCreated {

    @Override
    protected BigDecimal getOnBaseFactor() {
        return new BigDecimal(
                getDependency(Hits.class) +
                getDependency(BasesOnBalls.class) -
                getDependency(CaughtStealing.class) +
                getDependency(HitByPitches.class) -
                getDependency(GroundedIntoDoublePlays.class));
    }

    @Override
    protected BigDecimal getAdvancementFactor() {
        BigDecimal pieceOne = new BigDecimal(
                getDependency(BasesOnBalls.class) -
                getDependency(IntentionalWalks.class) +
                getDependency(HitByPitches.class)).multiply(
                        new BigDecimal("0.26"));
        BigDecimal pieceTwo = new BigDecimal(
                getDependency(SacrificeHits.class) +
                getDependency(SacrificeFlies.class) +
                getDependency(StolenBases.class)).multiply(
                        new BigDecimal("0.52"));
        return new BigDecimal(getDependency(TotalBases.class)).add(
                pieceOne).add(pieceTwo);
    }

    @Override
    protected BigDecimal getOpportunityFactor() {
        return new BigDecimal(getDependency(AtBats.class) +
                getDependency(BasesOnBalls.class) +
                getDependency(HitByPitches.class) +
                getDependency(SacrificeHits.class) +
                getDependency(SacrificeFlies.class));
    }

}
