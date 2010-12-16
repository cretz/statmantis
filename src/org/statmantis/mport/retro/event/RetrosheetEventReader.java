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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statmantis.model.Person;
import org.statmantis.model.Roster;
import org.statmantis.mport.retro.RetrosheetContext;
import org.statmantis.mport.retro.RetrosheetReader;
import org.statmantis.mport.retro.RetrosheetRuntimeException;
import org.statmantis.util.CsvUtils;

/**
 * Reader for event log ZIP files
 * 
 * @author Chad Retz
 */
public class RetrosheetEventReader extends RetrosheetReader<EventInformation> {
    
    private static final Logger logger = LoggerFactory.getLogger(RetrosheetEventReader.class);
    
    private final int year;
    private final String directory;
    private final RetrosheetContext retroContext;

    /**
     * Reader for pulling from URL
     * 
     * @param year
     * @param context
     */
    public RetrosheetEventReader(int year, RetrosheetContext context) {
        this(year, null, context);
    }
    
    /**
     * Reader from pulling from local file
     * 
     * @param year
     * @param directory
     * @param retroContext
     */
    public RetrosheetEventReader(int year, String directory,
            RetrosheetContext retroContext) {
        this.year = year;
        this.directory = directory;
        this.retroContext = retroContext;
    }

    @Override
    public EventInformation read() throws IOException {
        if (year <= 1996) {
            EventInformation info = readOne(year + "al.zip");
            if (info != null) {
                info.merge(readOne(year + "nl.zip"));
            } else {
                info = readOne(year + "nl.zip");
            }
            return info;
        } else {
            return readOne(year + "ml.zip");
        }
    }
    
    public EventInformation readOne(String name) {
        try {
            if (directory != null) {
                return read(new BufferedInputStream(new FileInputStream(
                        directory + File.separator + name)));
            } else {
                HttpClient client = new DefaultHttpClient();
                HttpEntity entity = client.execute(new HttpGet(
                        "http://www.retrosheet.org/" + year + "/" +
                        name)).getEntity();
                if (entity == null) {
                    return null;
                } else {
                    return read(new BufferedInputStream(entity.getContent()));
                }
            }
        } catch (Exception e) {
            throw new RetrosheetRuntimeException("Error reading file " + name, e);
        }
    }

    @Override
    protected EventInformation read(InputStream stream) throws IOException {
        ZipInputStream zis = new ZipInputStream(stream);
        try {
            EventInformation info = new EventInformation();
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(zis));
                    if (entry.getName().equals("TEAM" + year)) {
                        readTeamFile(entry.getName(), reader, info);
                    } else if (entry.getName().endsWith(".ROS")) {
                        readRosterFile(entry.getName(), reader, info);
                    } else if (entry.getName().endsWith(".EVA") || 
                            entry.getName().endsWith(".EVN") ||
                            entry.getName().endsWith(".eba") ||
                            entry.getName().endsWith(".ebn")) {
                        readEventFile(entry.getName(), reader, info);
                    } else {
                        throw new RetrosheetRuntimeException("Unknown zip entry: " +
                                entry);
                    }
                } catch (Exception e) {
                    throw new RetrosheetRuntimeException(
                            "Error reading zip entry " + entry.getName(), e);
                }
                entry = zis.getNextEntry();
            }
            return info;
        } finally {
            try {
                zis.close();
            } catch (Exception e) {
                //no-op
            }
        }
    }
    
    private Roster createBlankRoster(String team) {
        Roster roster = new Roster();
        roster.setPlayers(new ArrayList<Person>());
        roster.setTeam(retroContext.getTeams().get(team));
        if (roster.getTeam() == null) {
            throw new RetrosheetRuntimeException("Unable to find team " + team);
        }
        roster.setYear(year);
        return roster;
    }
    
    private void readTeamFile(String name, BufferedReader reader, 
            EventInformation info) throws IOException {
        List<Map<TeamColumn, String>> lines = CsvUtils.readCsv(TeamColumn.class, 
                reader, false);
        for (int i = 0; i < lines.size(); i++) {
            try {
                Map<TeamColumn, String> line = lines.get(i);
                String team = line.get(TeamColumn.TEAM_ID);
                Roster roster = info.getRosters().get(team);
                if (roster == null) {
                   roster = createBlankRoster(team);
                   info.getRosters().put(roster.getTeam().getRetroId(), roster);
                }
                String teamName = line.get(TeamColumn.CITY) + " " +
                        line.get(TeamColumn.NAME);
                if (!roster.getTeam().getName().equals(teamName)) {
                    List<String> otherNames = roster.getTeam().getOtherNames();
                    if (otherNames == null || !otherNames.contains(teamName)) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Team name '" + teamName + "' for " + 
                                    roster.getTeam() + " not found; adding...");
                        }
                        if (otherNames == null) {
                            otherNames = new ArrayList<String>();
                            roster.getTeam().setOtherNames(otherNames);
                        }
                        otherNames.add(teamName);
                    }
                }
            } catch (Exception e) {
                throw new RetrosheetRuntimeException("Error reading line " + i, e);
            }
        }
    }
    
    private void readRosterFile(String name, BufferedReader reader,
            EventInformation info) throws IOException {
        List<Map<RosterColumn, String>> lines = CsvUtils.readCsv(RosterColumn.class, 
                reader, false);
        for (int i = 0; i < lines.size(); i++) {
            try {
                Map<RosterColumn, String> line = lines.get(i);
                String team = line.get(RosterColumn.TEAM_ID);
                Roster roster = info.getRosters().get(team);
                if (roster == null) {
                    roster = createBlankRoster(team);
                    info.getRosters().put(roster.getTeam().getRetroId(), roster);
                }
                Person person = retroContext.getPersons().get(line.get(RosterColumn.PERSON_ID));
                if (person == null) {
                    throw new RetrosheetRuntimeException("Unable to find player " + 
                            line.get(RosterColumn.PERSON_ID));
                }
                roster.getPlayers().add(person);
            } catch (Exception e) {
                throw new RetrosheetRuntimeException("Error reading line " + i, e);
            }
        }
    }
    
    private void readEventFile(String name, BufferedReader reader, 
            EventInformation info) throws IOException {
        List<String[]> lines = CsvUtils.readCsv(reader, false);
        GameContext context = new GameContext(retroContext, info);
        for (int i = 0; i < lines.size(); i++) {
            try {
                EventFileUtils.parseLine(lines.get(i), context);
            } catch (Exception e) {
                throw new RetrosheetRuntimeException("Error reading line " + i, e);
            }
        }
    }
}
