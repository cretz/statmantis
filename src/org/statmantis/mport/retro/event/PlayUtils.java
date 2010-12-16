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

import java.util.ArrayList;
import java.util.List;

import org.statmantis.model.Base;
import org.statmantis.model.FieldLocation;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.GamePosition;
import org.statmantis.model.PlayEvent;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayRunnerAdvancement;
import org.statmantis.model.PlayType;
import org.statmantis.model.Position;
import org.statmantis.mport.retro.RetrosheetRuntimeException;

/**
 * Utilities for play events
 * 
 * @author Chad Retz
 */
final class PlayUtils {
    
    public static GamePlayer getPlayerByFielderLocation(int position, GameContext context) {
        Position pos = Position.fromRetroId(position);
        GamePlayer found = null;
        Integer lastInningFound = null;
        List<GamePlayer> players = context.isHomeTeamBatting() ? 
                context.getCurrentGame().getVisitorInfo().getGamePlayers() : 
                    context.getCurrentGame().getHomeInfo().getGamePlayers();
        for (GamePlayer player : players) {
            if (player.getPositions() != null) {
                for (GamePosition playerPos : player.getPositions()) {
                    if (playerPos.getPosition() == pos && (lastInningFound == null ||
                            lastInningFound < playerPos.getBeginningInning())) {
                        found = player;
                        lastInningFound = playerPos.getBeginningInning();
                    }
                }
            }
        }
        if (found == null) {
            throw new RetrosheetRuntimeException("Unable to find " + 
                    (context.isHomeTeamBatting() ? "visitor" : "home") + 
                    " player at position " + pos);
        }
        return found;
    }
    
    public static PlayEvent buildPlayEvent(String evt, GameContext context) {
        PlayType type = null;
        //let's break off the secondary event
        String secondaryEvent = null;
        int plusSign = evt.indexOf('+');
        if (plusSign != -1) {
            secondaryEvent = evt.substring(plusSign + 1);
            evt = evt.substring(0, plusSign);
        }
        //and break off those modifiers
        String mods = null;
        int firstSlash = evt.indexOf('/');
        if (firstSlash != -1) {
            mods = evt.substring(firstSlash + 1);
            evt = evt.substring(0, firstSlash);
        }
        //TODO: really...iterate every one every 
        //  time w/ regex matching...really?
        for (PlayType playType : PlayType.values()) {
            if (evt.matches(playType.getRetroId())) {
                type = playType;
                break;
            }
        }
        if (type == null) {
            throw new RetrosheetRuntimeException(
                    "Unable to find play type for " + evt);
        }
        PlayEvent event = new PlayEvent();
        event.setPlayType(type);
        event.setFielders(new ArrayList<GamePlayer>());
        switch (type) {
        case FLY_OUT:
            event.getFielders().add(getPlayerByFielderLocation(
                    Integer.parseInt(evt.substring(0, 1)), context));
            event.setOut(true);
            break;
        case GROUND_OUT:
        case GROUND_OUT_DOUBLE_PLAY:
        case LINE_OUT_DOUBLE_PLAY:
        case GROUND_OUT_TRIPLE_PLAY:
        case LINE_OUT_TRIPLE_PLAY:
            event.setOut(true);
            boolean nextCharBaseRunner = false;
            for (int i = 0; i < evt.length(); i++) {
                char chr = evt.charAt(i);
                if (chr == '(') {
                    nextCharBaseRunner = true;
                } else if (nextCharBaseRunner) {
                    nextCharBaseRunner = false;
                    Base runningFrom = Base.BASES.get(String.valueOf(chr));
                    Base runningTo = runningFrom.nextBase();
                    GamePlayer baseRunner = context.getOnBase().get(runningFrom);
                    if (baseRunner == null) {
                        throw new RetrosheetRuntimeException(
                                "Base runner not found: " + evt);
                    }
                    if (event.getPrimaryBaseRunner() == null) {
                        event.setPrimaryRunningFrom(runningFrom);
                        event.setPrimaryRunningTo(runningTo);
                        event.setPrimaryBaseRunner(baseRunner);                        
                    } else if (event.getSecondaryBaseRunner() == null) {
                        event.setSecondaryRunningFrom(runningFrom);
                        event.setSecondaryRunningTo(runningTo);
                        event.setSecondaryBaseRunner(baseRunner);
                    } else {
                        event.setTertiaryRunningFrom(runningFrom);
                        event.setTertiaryRunningTo(runningTo);
                        event.setTertiaryBaseRunner(baseRunner);
                    }
                } else if (chr != ')') {
                    event.getFielders().add(getPlayerByFielderLocation(
                            Integer.valueOf(String.valueOf(
                            chr)), context));
                }
            }
            break;
        case INTERFERENCE:
            //do nothing?
            break;
        case SINGLE:
        case DOUBLE:
        case TRIPLE:
            for (int i = 1; i < evt.length(); i++) {
                event.getFielders().add(getPlayerByFielderLocation(
                        Integer.valueOf(String.valueOf(
                        evt.charAt(i))), context));
            }
            break;
        case GROUND_RULE_DOUBLE:
            //do nothing?
            break;
        case ERROR:
        case ERROR_FOUL_FLY:
            event.getFielders().add(getPlayerByFielderLocation(
                    Integer.valueOf(String.valueOf(evt.charAt(evt.length() - 1))), 
                    context));
            break;
        case FIELDERS_CHOICE:
            event.setOut(true);
            event.getFielders().add(getPlayerByFielderLocation(
                    Integer.valueOf(String.valueOf(evt.charAt(2))), 
                    context));
            break;
        case HOME_RUN:
            event = new PlayEvent();
            char endingChar = evt.charAt(evt.length() - 1);
            if (Character.isDigit(endingChar)) {
                event.getFielders().add(getPlayerByFielderLocation(
                        Integer.valueOf(String.valueOf(endingChar)), 
                        context));
            }
            break;
        case HIT_BY_PITCH:
            //do nothing?
            break;
        case STRIKE_OUT:
            for (int i = 1; i < evt.length(); i++) {
                event.getFielders().add(getPlayerByFielderLocation(
                        Integer.valueOf(String.valueOf(evt.charAt(i))), 
                        context));
            }
            break;
        //TODO: case NO_PLAY:
        case INTENTIONAL_WALK:
        case WALK:
            //do nothing?
            break;
        case BALK:
            //do nothing?
            break;
        case CAUGHT_STEALING:
            event.setPrimaryRunningTo(Base.BASES.get(String.valueOf(evt.charAt(2))));
            event.setPrimaryRunningFrom(event.getPrimaryRunningTo().previousBase());
            event.setPrimaryBaseRunner(context.getOnBase().get(event.getPrimaryRunningFrom()));
            event.setOut(true);
            for (int i = 4; i < evt.length() - 1; i++) {
                char chr = evt.charAt(i);
                if (Character.isDigit(chr)) {
                    event.getFielders().add(getPlayerByFielderLocation(
                        Integer.valueOf(String.valueOf(chr)), 
                        context));
                } else {
                    //TODO: figure out how to represent error here
                    event.setOut(false);
                }
            }
            break;
        case DEFENSIVE_INDIFFERENCE:
            //do nothing?
            break;
        case RUNNER_ADVANCEMENT:
            //do nothing?
            break;
        case PASSED_BALL:
        case WILD_PITCH:
            //do nothing?
            break;
        case PICKOFF:
        case PICKOFF_CAUGHT_STEALING:
            int baseIndex = type == PlayType.PICKOFF ? 2 : 4;
            event.setPrimaryRunningFrom(Base.BASES.get(String.valueOf(evt.charAt(baseIndex))));
            event.setPrimaryBaseRunner(context.getOnBase().get(event.getPrimaryRunningFrom()));
            event.setOut(true);
            for (int i = baseIndex + 2; i < evt.length() - 1; i++) {
                char chr = evt.charAt(i);
                if (Character.isDigit(chr)) {
                    event.getFielders().add(getPlayerByFielderLocation(
                        Integer.valueOf(String.valueOf(chr)), 
                        context));
                } else {
                    //TODO: figure out how to represent error here
                    event.setOut(false);
                }
            }
            break;
        case STOLEN_BASE:
            event = new PlayEvent();
            event.setPrimaryRunningTo(Base.BASES.get(String.valueOf(evt.charAt(2))));
            event.setPrimaryRunningFrom(event.getPrimaryRunningTo().previousBase());
            event.setPrimaryBaseRunner(context.getOnBase().get(event.getPrimaryRunningFrom()));
            //TODO: handle multiple of these as delimited by a ';'
            break;
        }
        //handle the secondary event
        if (secondaryEvent != null) {
            event.setSecondaryEvent(buildPlayEvent(secondaryEvent, context));
        }
        //handle the mods
        if (mods != null) {
            event.setModifiers(new ArrayList<PlayModifier>());
            for (String mod : mods.split("/")) {
                event.getModifiers().add(buildPlayModifier(mod, context));
            }
        }
        return event;
    }
    
    public static PlayModifier buildPlayModifier(String mod, GameContext context) {
        PlayModifier modifier = new PlayModifier();
        //first we try simple hit locations
        for (FieldLocation location : FieldLocation.values()) {
            if (location.equals(mod)) {
                modifier.setLocation(location);
                //and that's all she wrote
                return modifier;
            }
        }
        //now actual modifiers
        String modToMatch = mod;
        for (int i = 1; i < mod.length(); i++) {
            if (Character.isDigit(mod.charAt(i))) {
                modToMatch = mod.substring(0, i);
                break;
            }
        }
        if (modToMatch == null) {
            throw new RetrosheetRuntimeException("Unable to find modifier for " + mod);
        }
        for (PlayModifierType type : PlayModifierType.values()) {
            for (String possible : type.getPossibleNames()) {
                if (modToMatch.equals(possible)) {
                    modifier.setType(type);
                    break;
                }
            }
        }
        //now some of these have special stuff
        switch (modifier.getType()) {
        case ERROR:
        case RELAY_THROW:
            if (mod.length() == 2) {
                modifier.setFielder(getPlayerByFielderLocation(Integer.valueOf(
                        String.valueOf(mod.charAt(1))), context));
            }
            break;
        case THROW:
            if (mod.length() == 3) {
                modifier.setBase(Base.BASES.get(String.valueOf(mod.charAt(2))));
            }
            break;
        default:
            //everything else may have a location at the end
            if (modToMatch.length() < mod.length()) {
                modifier.setLocation(FieldLocation.LOCATIONS.get(
                        mod.substring(modToMatch.length())));
            }
        }
        return modifier;
    }
    
    public static PlayRunnerAdvancement buildPlayRunnerAdvancement(String adv, GameContext context) {
        PlayRunnerAdvancement advancement = new PlayRunnerAdvancement();
        boolean modifiersStarted = false;
        int lastOpeningParenIndex = -1;
        for (int i = 0; i < adv.length(); i++) {
            char chr = adv.charAt(i);
            if (chr == '(') {
                lastOpeningParenIndex = i;
                modifiersStarted = true;
                advancement.setFielders(new ArrayList<GamePlayer>());
                advancement.setModifiers(new ArrayList<PlayModifier>());
            } else if (!modifiersStarted) {
                if (chr == 'X') {
                    advancement.setOut(true);
                } else if (chr != '-') {
                    if (advancement.getBaseFrom() == null) {
                        advancement.setBaseFrom(Base.BASES.get(
                                String.valueOf(chr)));
                    } else {
                        advancement.setBaseTo(Base.BASES.get(
                                String.valueOf(chr)));                        
                    }
                }
            } else if (chr == ')') {
                for (String possible : adv.substring(lastOpeningParenIndex + 1, i).split("/")) {
                    //I figure we handle digits as fielders until we handle a modifier
                    for (int j = 0; j < possible.length(); j++) {
                        char val = possible.charAt(j);
                        if (Character.isDigit(val)) {
                            advancement.getFielders().add(getPlayerByFielderLocation(
                                    Integer.valueOf(String.valueOf(val)), context));
                        } else {
                            advancement.getModifiers().add(buildPlayModifier(
                                    possible.substring(j), context));
                            break;
                        }
                    }
                }
            }
        }
        return advancement;
    }

    private PlayUtils() {
    }
}
