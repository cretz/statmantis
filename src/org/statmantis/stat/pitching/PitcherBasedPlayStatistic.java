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

import org.statmantis.model.Person;
import org.statmantis.model.Play;
import org.statmantis.stat.AbstractStatistic;
import org.statmantis.stat.PitcherBasedStatistic;
import org.statmantis.stat.PlayBasedStatistic;

public abstract class PitcherBasedPlayStatistic<T extends Number> 
        extends AbstractStatistic<T> 
        implements PlayBasedStatistic, PitcherBasedStatistic {

    protected Person pitcher;

    @Override
    public void setCurrentPitcher(Person pitcher) {
        this.pitcher = pitcher;
    }
    
    protected boolean isPlayApplicable(Play play) {
        return pitcher == null || pitcher.equals(play.getPitcher().getPlayer());
    }
}
