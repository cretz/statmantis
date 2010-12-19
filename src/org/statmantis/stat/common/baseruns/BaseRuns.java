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
package org.statmantis.stat.common.baseruns;

import java.math.BigDecimal;

import org.statmantis.stat.AbstractStatistic;

public abstract class BaseRuns extends AbstractStatistic<BigDecimal> {

    protected abstract BigDecimal getBaserunners();
    
    protected abstract BigDecimal getAdvancement();
    
    protected abstract BigDecimal getOuts();
    
    protected abstract BigDecimal getGuaranteedRuns();
    
    @Override
    public BigDecimal getCurrentValue() {
        BigDecimal baserunners = getBaserunners();
        BigDecimal advancement = getAdvancement();
        BigDecimal outs = getOuts();
        BigDecimal guaranteedRuns = getGuaranteedRuns();
        BigDecimal denom = getAdvancement().add(outs);
        if (denom.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return baserunners.multiply(advancement).divide(denom).
                add(guaranteedRuns);
    }

}
