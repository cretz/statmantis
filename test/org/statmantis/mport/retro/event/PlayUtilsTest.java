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

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.statmantis.RetrosheetContextAwareTestBase;
import org.statmantis.model.Base;
import org.statmantis.model.FieldLocation;
import org.statmantis.model.Game;
import org.statmantis.model.GamePlayer;
import org.statmantis.model.PlayEvent;
import org.statmantis.model.PlayModifier;
import org.statmantis.model.PlayModifierType;
import org.statmantis.model.PlayType;
import org.statmantis.mport.retro.event.EventInformation;
import org.statmantis.mport.retro.event.GameContext;
import org.statmantis.mport.retro.event.PlayUtils;
import org.statmantis.xml.XmlUnmarshaller;
import org.w3c.dom.Document;

/**
 * Tests for the {@link PlayUtils} class
 *
 * @author Chad Retz
 */
public class PlayUtilsTest extends RetrosheetContextAwareTestBase {
    
    private GameContext buildGameContext(boolean playerOnFirst,
            boolean playerOnSecond, boolean playerOnThird) throws Exception {
        GameContext context = new GameContext(retroContext, 
                new EventInformation());
        //new game
        context.newGame("meh");
        //set the game as what we load
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().
            parse(PlayUtilsTest.class.getResourceAsStream("game.xml"));
        context.setCurrentGame((Game) new XmlUnmarshaller().unmarshal(doc.getDocumentElement()));
        //map all those current players
        for (GamePlayer player : context.getCurrentGame().getHomeInfo().getGamePlayers()) {
            context.getCurrentPlayers().put(player.getPlayer().getRetroId(), player);
        }
        for (GamePlayer player : context.getCurrentGame().getVisitorInfo().getGamePlayers()) {
            context.getCurrentPlayers().put(player.getPlayer().getRetroId(), player);
        }
        //set the home team as batting
        context.setHomeTeamBatting(true);
        //the batter at the plate will always be the cruz missle
        context.getOnBase().put(Base.HOME, context.getCurrentPlayers().get("cruzn002"));
        if (playerOnFirst) {
            //the impaler
            context.getOnBase().put(Base.FIRST, context.getCurrentPlayers().get("guerv001"));
        }
        if (playerOnSecond) {
            //hambone
            context.getOnBase().put(Base.SECOND, context.getCurrentPlayers().get("hamij003"));
        }
        if (playerOnThird) {
            //face
            context.getOnBase().put(Base.THIRD, context.getCurrentPlayers().get("younm003"));
        }
        //we should be good now
        return context;
    }
    
    private void assertModifier(GameContext context, PlayEvent event, int index, Base base,
            String fielder, FieldLocation location, PlayModifierType type) {
        assertTrue(event.getModifiers().size() >= index + 1);
        PlayModifier modifier = event.getModifiers().get(index);
        assertEquals(base, modifier.getBase());
        if (fielder != null) {
            assertEquals(context.getCurrentPlayers().get(fielder), modifier.getFielder());
        }
        assertEquals(location, modifier.getLocation());
        assertEquals(type, modifier.getType());
    }
    
    //although the starting lineups are Rangers opening day 2k10, lots of these
    //  tests are made up
    
    @Test
    public void testFlyOut() throws Exception {
        GameContext context = buildGameContext(false, false, false);
        //cruz flies out to center fielder in left center
        PlayEvent event = PlayUtils.buildPlayEvent("8/F78", context);
        assertEquals(1, event.getFielders().size());
        //face's friend
        assertEquals(context.getCurrentPlayers().get("wellv001"),
                event.getFielders().get(0));
        assertEquals(PlayType.FLY_OUT, event.getPlayType());
        assertModifier(context, event, 0, null, null, 
                FieldLocation.LEFT_CENTER, PlayModifierType.FLY);
    }
    
    @Test
    public void testSacrificeFlyOut() throws Exception {
        //face on third
        GameContext context = buildGameContext(false, false, true);
        //cruz sac flies to right fielder, face tags
        PlayEvent event = PlayUtils.buildPlayEvent("9/SF", context);
        assertEquals(1, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("bautj002"),
                event.getFielders().get(0));
        assertEquals(PlayType.FLY_OUT, event.getPlayType());
        assertModifier(context, event, 0, null, null, 
                null, PlayModifierType.SACRIFICE_FLY);
    }
    
    @Test
    public void testGroundOut() throws Exception {
        GameContext context = buildGameContext(false, false, false);
        //cruz simple 6-3
        PlayEvent event = PlayUtils.buildPlayEvent("63/G6M", context);
        assertEquals(2, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("gonza002"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("overl001"),
                event.getFielders().get(1));
        assertEquals(PlayType.GROUND_OUT, event.getPlayType());
        assertModifier(context, event, 0, null, null, 
                FieldLocation.SHORTSTOP_MIDDLE, PlayModifierType.GROUND_BALL);
    }
    
    @Test
    public void testThreePersonGroundOut() throws Exception {
        GameContext context = buildGameContext(false, false, false);
        //cruz 1-4-3
        PlayEvent event = PlayUtils.buildPlayEvent("143/G1", context);
        assertEquals(3, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("marcs001"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("hilla001"),
                event.getFielders().get(1));
        assertEquals(context.getCurrentPlayers().get("overl001"),
                event.getFielders().get(2));
        assertEquals(PlayType.GROUND_OUT, event.getPlayType());
        assertModifier(context, event, 0, null, null, 
                FieldLocation.MOUND, PlayModifierType.GROUND_BALL);
    }
    
    @Test
    public void testNonNormalBaseGroundOut() throws Exception {
        GameContext context = buildGameContext(true, false, false);
        //cruz 5-4 (but got him at first)
        PlayEvent event = PlayUtils.buildPlayEvent("54(B)/BG25/SH", context);
        assertEquals(2, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("encae001"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("hilla001"),
                event.getFielders().get(1));
        assertEquals(PlayType.GROUND_OUT, event.getPlayType());
        assertEquals(context.getCurrentPlayers().get("cruzn002"),
                event.getPrimaryBaseRunner());
        assertEquals(Base.HOME, event.getPrimaryRunningFrom());
        assertEquals(Base.FIRST, event.getPrimaryRunningTo());
        assertModifier(context, event, 0, null, null, 
                FieldLocation.HOME_THIRD, PlayModifierType.GROUND_BALL_BUNT);
        assertModifier(context, event, 1, null, null, null, 
                PlayModifierType.SACRIFICE_HIT);
    }
    
    @Test
    public void testForceOutGroundOut() throws Exception {
        GameContext context = buildGameContext(true, false, false);
        //cruz 5-4 (got player at second)
        PlayEvent event = PlayUtils.buildPlayEvent("54(1)/FO/G5", context);
        assertEquals(2, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("encae001"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("hilla001"),
                event.getFielders().get(1));
        assertEquals(PlayType.GROUND_OUT, event.getPlayType());
        assertEquals(context.getCurrentPlayers().get("guerv001"),
                event.getPrimaryBaseRunner());
        assertEquals(Base.FIRST, event.getPrimaryRunningFrom());
        assertEquals(Base.SECOND, event.getPrimaryRunningTo());
        assertModifier(context, event, 0, null, null, null, 
                PlayModifierType.FORCE_OUT);
        assertModifier(context, event, 1, null, null, 
                FieldLocation.THIRD, PlayModifierType.GROUND_BALL);
    }
    
    @Test
    public void testDoublePlay() throws Exception {
        GameContext context = buildGameContext(true, false, false);
        //cruz GIDP w/ runner on first initial out
        PlayEvent event = PlayUtils.buildPlayEvent("64(1)3/GDP/G6", context);
        assertEquals(3, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("gonza002"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("hilla001"),
                event.getFielders().get(1));
        assertEquals(context.getCurrentPlayers().get("overl001"),
                event.getFielders().get(2));
        assertEquals(PlayType.GROUND_OUT_DOUBLE_PLAY, event.getPlayType());
        assertEquals(context.getCurrentPlayers().get("guerv001"),
                event.getPrimaryBaseRunner());
        assertEquals(Base.FIRST, event.getPrimaryRunningFrom());
        assertEquals(Base.SECOND, event.getPrimaryRunningTo());
        assertModifier(context, event, 0, null, null, null, 
                PlayModifierType.GROUND_OUT_DOUBLE_PLAY);
        assertModifier(context, event, 1, null, null, 
                FieldLocation.SHORTSTOP, PlayModifierType.GROUND_BALL);
    }
    
    @Test
    public void testTouchBagDoublePlay() throws Exception {
        GameContext context = buildGameContext(true, false, false);
        //cruz GIDP (second base unassisted) w/ runner on first initial out
        PlayEvent event = PlayUtils.buildPlayEvent("4(1)3/G4/GDP", context);
        assertEquals(2, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("hilla001"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("overl001"),
                event.getFielders().get(1));
        assertEquals(PlayType.GROUND_OUT_DOUBLE_PLAY, event.getPlayType());
        assertEquals(context.getCurrentPlayers().get("guerv001"),
                event.getPrimaryBaseRunner());
        assertEquals(Base.FIRST, event.getPrimaryRunningFrom());
        assertEquals(Base.SECOND, event.getPrimaryRunningTo());
        assertModifier(context, event, 0, null, null, 
                FieldLocation.SECOND, PlayModifierType.GROUND_BALL);
        assertModifier(context, event, 1, null, null, null, 
                PlayModifierType.GROUND_OUT_DOUBLE_PLAY);
    }
    
    @Test
    public void testLinedIntoDoublePlay() throws Exception {
        GameContext context = buildGameContext(false, true, false);
        //cruz lined into DP (face's friend to second)
        PlayEvent event = PlayUtils.buildPlayEvent("8(B)84(2)/LDP/L8", context);
        assertEquals(3, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("wellv001"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("wellv001"),
                event.getFielders().get(1));
        assertEquals(context.getCurrentPlayers().get("hilla001"),
                event.getFielders().get(2));
        assertEquals(PlayType.LINE_OUT_DOUBLE_PLAY, event.getPlayType());
        assertEquals(context.getCurrentPlayers().get("cruzn002"),
                event.getPrimaryBaseRunner());
        assertEquals(Base.HOME, event.getPrimaryRunningFrom());
        assertEquals(Base.FIRST, event.getPrimaryRunningTo());
        assertEquals(context.getCurrentPlayers().get("hamij003"),
                event.getSecondaryBaseRunner());
        assertEquals(Base.SECOND, event.getSecondaryRunningFrom());
        assertEquals(Base.THIRD, event.getSecondaryRunningTo());
        assertModifier(context, event, 0, null, null, null, 
                PlayModifierType.LINE_DRIVE_DOUBLE_PLAY);
        assertModifier(context, event, 1, null, null, 
                FieldLocation.CENTER, PlayModifierType.LINE_DRIVE);
    }
    
    @Test
    public void testTriplePlay() throws Exception {
        GameContext context = buildGameContext(true, true, false);
        //cruz ground out triple play
        PlayEvent event = PlayUtils.buildPlayEvent("1(B)16(2)63(1)/LTP/L1", context);
        assertEquals(5, event.getFielders().size());
        assertEquals(context.getCurrentPlayers().get("marcs001"),
                event.getFielders().get(0));
        assertEquals(context.getCurrentPlayers().get("marcs001"),
                event.getFielders().get(1));
        assertEquals(context.getCurrentPlayers().get("gonza002"),
                event.getFielders().get(2));
        assertEquals(context.getCurrentPlayers().get("gonza002"),
                event.getFielders().get(3));
        assertEquals(context.getCurrentPlayers().get("overl001"),
                event.getFielders().get(4));
        assertEquals(PlayType.LINE_OUT_TRIPLE_PLAY, event.getPlayType());
        assertEquals(context.getCurrentPlayers().get("cruzn002"),
                event.getPrimaryBaseRunner());
        assertEquals(Base.HOME, event.getPrimaryRunningFrom());
        assertEquals(Base.FIRST, event.getPrimaryRunningTo());
        assertEquals(context.getCurrentPlayers().get("hamij003"),
                event.getSecondaryBaseRunner());
        assertEquals(Base.SECOND, event.getSecondaryRunningFrom());
        assertEquals(Base.THIRD, event.getSecondaryRunningTo());
        assertEquals(context.getCurrentPlayers().get("guerv001"),
                event.getTertiaryBaseRunner());
        assertEquals(Base.FIRST, event.getTertiaryRunningFrom());
        assertEquals(Base.SECOND, event.getTertiaryRunningTo());
        assertModifier(context, event, 0, null, null, null, 
                PlayModifierType.LINE_DRIVE_TRIPLE_PLAY);
        assertModifier(context, event, 1, null, null, 
                FieldLocation.MOUND, PlayModifierType.LINE_DRIVE);
    }
}
