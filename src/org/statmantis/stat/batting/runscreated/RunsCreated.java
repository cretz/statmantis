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

import org.statmantis.stat.AbstractStatistic;

/**
 * This is the base class for all Runs Created (RC) statistics. See the 
 * Wikipedia reference <a href="http://en.wikipedia.org/wiki/Runs_created">here</a>.
 * This is a batting only statistic. Abstractly, this is calculated as:
 * <p>
 * <math style="font-size: 200%">
 *     <mfrac>
 *         <mrow>
 *             <mi>A</mi>
 *             <mo>&#215;</mo>
 *             <mi>B</mi>
 *         </mrow>
 *         <mi>C</mi>
 *     </mfrac>
 * </math>
 * <p>
 * Where A is {@link #getOnBaseFactor()}, B is {@link #getAdvancementFactor()}, and
 * C is {@link #getOpportunityFactor()}.
 *
 * @author Chad Retz
 */
public abstract class RunsCreated extends AbstractStatistic<BigDecimal> {

    protected abstract BigDecimal getOnBaseFactor();
    
    protected abstract BigDecimal getAdvancementFactor();
    
    protected abstract BigDecimal getOpportunityFactor();
    
    @Override
    public BigDecimal getCurrentValue() {
        BigDecimal opp = getOpportunityFactor();
        if (opp.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return getOnBaseFactor().multiply(getAdvancementFactor()).divide(opp);
    }

}
