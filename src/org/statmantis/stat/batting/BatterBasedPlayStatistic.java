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

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.BatterBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;

/**
 * Base statistic for batter based statistics that simply stores the batter.
 *
 * @author Chad Retz
 */
public abstract class BatterBasedPlayStatistic<T extends Number> 
        extends AbstractStatistic<T> 
        implements PlayBasedStatistic, BatterBasedStatistic {

    protected Person batter;

    @Override
    public void setCurrentBatter(Person batter) {
        this.batter = batter;
    }
    
    /**
     * Returns true if the play is applicable to this batter
     * 
     * @param play
     * @return
     */
    protected boolean isPlayApplicable(Play play) {
        return batter == null || batter.equals(play.getPitcher().getPlayer());
    }
}
