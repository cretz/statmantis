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
package org.statmantis.mport.retro.event;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.statmantis.model.FieldCondition;
import org.statmantis.model.Game;
import org.statmantis.model.GameDayNumber;
import org.statmantis.model.Precipitation;
import org.statmantis.model.RetrosheetModel;
import org.statmantis.model.SkyCondition;
import org.statmantis.model.WindDirection;
import org.statmantis.mport.retro.RetrosheetContext;
import org.statmantis.mport.retro.RetrosheetRuntimeException;
import org.statmantis.util.EnumUtils;

/**
 * Enumerate for information records
 * 
 * @author Chad Retz
 */
public enum InformationRecord implements RetrosheetModel {

    VISITING_TEAM("visteam", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setVisitingTeam(context.getTeams().get(value));
            assert game.getVisitingTeam() != null : "Can't find visiting team " + value;
        }
    }),
    HOME_TEAM("hometeam", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setHomeTeam(context.getTeams().get(value));
            assert game.getHomeTeam() != null : "Can't find home team " + value;
        }
    }),
    DATE("date", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            try {
                game.setStartDate(new Date(new SimpleDateFormat(
                        RetrosheetContext.DATE_FORMAT).parse(value).getTime()));
            } catch (ParseException e) {
                throw new RetrosheetRuntimeException(e);
            }
        }
    }),
    NUMBER("number", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setDayNumber(GameDayNumber.NUMBERS.get(value));
            assert game.getDayNumber() != null : "Unrecognized 'number' value " + value;
        }
    }),
    START_TIME("starttime", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!value.startsWith("0:00")) {
                try {
                    game.setStartTime(new Time(new SimpleDateFormat(
                            RetrosheetContext.TIME_FORMAT).parse(value).getTime()));
                } catch (ParseException e) {
                    throw new RetrosheetRuntimeException(e);
                }
            }
        }
    }),
    DAY_NIGHT("daynight", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            assert "night".equals(value) || "day".equals(value) : 
                "Unrecognized 'daynight' value " + value;
            game.setDayGame(!"night".equalsIgnoreCase(value));
        }
    }),
    USE_DH("usedh", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setDesignatedHitterPresent(Boolean.valueOf(value));
        }
    }),
    PITCHES("pitches", null),
    UMP_HOME("umphome", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setUmpireHome(context.getPersons().get(value));
            assert game.getUmpireHome() != null : "Umpire home not recognized " + value;
        }
    }),
    UMP_FIRST("ump1b", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setUmpireFirst(context.getPersons().get(value));
            assert game.getUmpireFirst() != null : "Umpire first not recognized " + value;
        }
    }),
    UMP_SECOND("ump2b", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setUmpireSecond(context.getPersons().get(value));
            assert game.getUmpireSecond() != null : "Umpire second not recognized " + value;
        }
    }),
    UMP_THIRD("ump3b", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setUmpireThird(context.getPersons().get(value));
            assert game.getUmpireThird() != null : "Umpire third not recognized " + value;
        }
    }),
    UMP_LEFT("umplf", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setUmpireLeft(context.getPersons().get(value));
            assert game.getUmpireLeft() != null : "Umpire left not recognized " + value;
        }
    }),
    UMP_RIGHT("umprf", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setUmpireRight(context.getPersons().get(value));
            assert game.getUmpireRight() != null : "Umpire right not recognized " + value;
        }
    }),
    FIELD_CONDITION("fieldcond", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"unknown".equals(value)) {
                game.setFieldCondition(FieldCondition.CONDITIONS.get(value));
                assert game.getFieldCondition() != null : "Can't find field condition " +
                        value;
            }
        }
    }),
    PRECIPITATION("precip", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"unknown".equals(value)) {
                game.setPrecipitation(Precipitation.PRECIPITATIONS.get(value));
                assert game.getPrecipitation() != null : "Can't find precipitation " +
                        value;
            }
        }
    }),
    SKY_CONDITION("sky", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"unknown".equals(value)) {
                game.setSkyCondition(SkyCondition.CONDITIONS.get(value));
                assert game.getPrecipitation() != null : "Can't find sky condition " +
                        value;
            }
        }
    }),
    TEMPERATURE("temp", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"0".equals(value)) {
                game.setTemperature(Integer.valueOf(value));
            }
        }
    }),
    WIND_DIRECTION("visteam", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"unknown".equals(value)) {
                game.setWindDirection(WindDirection.DIRECTIONS.get(value));
                assert game.getPrecipitation() != null : "Can't find wind direction " +
                        value;
            }
        }
    }),
    WINDSPEED("windspeed", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"-1".equals(value)) {
                game.setWindspeed(Integer.valueOf(value));
            }
        }
    }),
    TIME_OF_GAME("timeofgame", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"0".equals(value)) {
                game.setTimeInMinutes(Integer.valueOf(value));
            }
        }
    }),
    ATTENDANCE("attendance", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            if (!"0".equals(value)) {
                game.setAttendance(Integer.valueOf(value));
            }
        }
    }),
    SITE("site", new InformationPopulater() {
        @Override
        public void populate(String value, Game game, RetrosheetContext context) {
            game.setPark(context.getParks().get(value));
            assert game.getPark() != null : "Can't find park " + value;
        }
    }),
    WINNING_PITCHER("wp", null),
    LOSING_PITCHER("lp", null),
    SAVING_PITCHER("save", null),
    GAME_WINNING_BATTER("gwrbi", null),
    EDIT_TIME("edittime", null),
    HOW_SCORED("howscored", null),
    INPUT_PROGRAM_VERSION("inputprogvers", null),
    INPUTTER("inputter", null),
    INPUT_TIME("inputtime", null),
    SCORER("scorer", null),
    TRANSLATOR("translator", null);
    
    public static final Map<String, InformationRecord> RECORDS;
    
    static {
        RECORDS = EnumUtils.mapEnumerates(InformationRecord.class);
    }
    
    private final String retroId;
    private final InformationPopulater populater;
    
    private InformationRecord(String retroId, InformationPopulater populater) {
        this.retroId = retroId;
        this.populater = populater;
    }
    
    @Override
    public String getRetroId() {
        return retroId;
    }
    
    void populate(String value, GameContext context) {
        if (populater != null) {
            populater.populate(value, context.getCurrentGame(), context.getRetroContext());
        }
    }
    
    /**
     * Simple iface for population
     * 
     * @author Chad Retz
     */
    private static interface InformationPopulater {
        void populate(String value, Game game, RetrosheetContext context);
    }
}
