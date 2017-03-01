package com.nba.stats.endpoints;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Endpoints {
    public static HashMap<String, URI> ENDPOINTS;

    static {
        Endpoints.ENDPOINTS = new HashMap<>();
        try {
            Endpoints.ENDPOINTS.put("allstarballotpredictor", new URI("http://stats.nba.com/stats/stats/allstarballotpredictor"));
            Endpoints.ENDPOINTS.put("boxscore", new URI("http://stats.nba.com/stats/boxscore"));
            Endpoints.ENDPOINTS.put("boxscoreadvanced", new URI("http://stats.nba.com/stats/boxscoreadvanced"));
            Endpoints.ENDPOINTS.put("boxscoreadvancedv2", new URI("http://stats.nba.com/stats/boxscoreadvancedv2"));
            Endpoints.ENDPOINTS.put("boxscorefourfactors", new URI("http://stats.nba.com/stats/boxscorefourfactors"));
            Endpoints.ENDPOINTS.put("boxscorefourfactorsv2", new URI("http://stats.nba.com/stats/boxscorefourfactorsv2"));
            Endpoints.ENDPOINTS.put("boxscoremisc", new URI("http://stats.nba.com/stats/boxscoremisc"));
            Endpoints.ENDPOINTS.put("boxscoremiscv2", new URI("http://stats.nba.com/stats/boxscoremiscv2"));
            Endpoints.ENDPOINTS.put("boxscoreplayertrackv2", new URI("http://stats.nba.com/stats/boxscoreplayertrackv2"));
            Endpoints.ENDPOINTS.put("boxscorescoring", new URI("http://stats.nba.com/stats/boxscorescoring"));
            Endpoints.ENDPOINTS.put("boxscorescoringv2", new URI("http://stats.nba.com/stats/boxscorescoringv2"));
            Endpoints.ENDPOINTS.put("boxscoresummaryv2", new URI("http://stats.nba.com/stats/boxscoresummaryv2"));
            Endpoints.ENDPOINTS.put("boxscoretraditionalv2", new URI("http://stats.nba.com/stats/boxscoretraditionalv2"));
            Endpoints.ENDPOINTS.put("boxscoreusage", new URI("http://stats.nba.com/stats/boxscoreusage"));
            Endpoints.ENDPOINTS.put("boxscoreusagev2", new URI("http://stats.nba.com/stats/boxscoreusagev2"));
            Endpoints.ENDPOINTS.put("commonTeamYears", new URI("http://stats.nba.com/stats/commonTeamYears"));
            Endpoints.ENDPOINTS.put("commonallplayers", new URI("http://stats.nba.com/stats/commonallplayers"));
            Endpoints.ENDPOINTS.put("commonplayerinfo", new URI("http://stats.nba.com/stats/commonplayerinfo"));
            Endpoints.ENDPOINTS.put("commonplayoffseries", new URI("http://stats.nba.com/stats/commonplayoffseries"));
            Endpoints.ENDPOINTS.put("commonteamroster", new URI("http://stats.nba.com/stats/commonteamroster"));
            Endpoints.ENDPOINTS.put("draftcombinedrillresults", new URI("http://stats.nba.com/stats/draftcombinedrillresults"));
            Endpoints.ENDPOINTS.put("draftcombinenonstationaryshooting", new URI("http://stats.nba.com/stats/draftcombinenonstationaryshooting"));
            Endpoints.ENDPOINTS.put("draftcombineplayeranthro", new URI("http://stats.nba.com/stats/draftcombineplayeranthro"));
            Endpoints.ENDPOINTS.put("draftcombinespotshooting", new URI("http://stats.nba.com/stats/draftcombinespotshooting"));
            Endpoints.ENDPOINTS.put("draftcombinestats", new URI("http://stats.nba.com/stats/draftcombinestats"));
            Endpoints.ENDPOINTS.put("drafthistory", new URI("http://stats.nba.com/stats/drafthistory"));
            Endpoints.ENDPOINTS.put("franchisehistory", new URI("http://stats.nba.com/stats/franchisehistory"));
            Endpoints.ENDPOINTS.put("homepageleaders", new URI("http://stats.nba.com/stats/homepageleaders"));
            Endpoints.ENDPOINTS.put("homepagev2", new URI("http://stats.nba.com/stats/homepagev2"));
            Endpoints.ENDPOINTS.put("leaderstiles", new URI("http://stats.nba.com/stats/leaderstiles"));
            Endpoints.ENDPOINTS.put("leaguedashlineups", new URI("http://stats.nba.com/stats/leaguedashlineups"));
            Endpoints.ENDPOINTS.put("leaguedashplayerbiostats", new URI("http://stats.nba.com/stats/leaguedashplayerbiostats"));
            Endpoints.ENDPOINTS.put("leaguedashplayerclutch", new URI("http://stats.nba.com/stats/leaguedashplayerclutch"));
            Endpoints.ENDPOINTS.put("leaguedashplayerptshot", new URI("http://stats.nba.com/stats/leaguedashplayerptshot"));
            Endpoints.ENDPOINTS.put("leaguedashplayershotlocations", new URI("http://stats.nba.com/stats/leaguedashplayershotlocations"));
            Endpoints.ENDPOINTS.put("leaguedashplayerstats", new URI("http://stats.nba.com/stats/leaguedashplayerstats"));
            Endpoints.ENDPOINTS.put("leaguedashptdefend", new URI("http://stats.nba.com/stats/leaguedashptdefend"));
            Endpoints.ENDPOINTS.put("leaguedashptteamdefend", new URI("http://stats.nba.com/stats/leaguedashptteamdefend"));
            Endpoints.ENDPOINTS.put("leaguedashteamclutch", new URI("http://stats.nba.com/stats/leaguedashteamclutch"));
            Endpoints.ENDPOINTS.put("leaguedashteamptshot", new URI("http://stats.nba.com/stats/leaguedashteamptshot"));
            Endpoints.ENDPOINTS.put("leaguedashteamshotlocations", new URI("http://stats.nba.com/stats/leaguedashteamshotlocations"));
            Endpoints.ENDPOINTS.put("leaguedashteamstats", new URI("http://stats.nba.com/stats/leaguedashteamstats"));
            Endpoints.ENDPOINTS.put("leagueleaders", new URI("http://stats.nba.com/stats/leagueleaders"));
            Endpoints.ENDPOINTS.put("playbyplay", new URI("http://stats.nba.com/stats/playbyplay"));
            Endpoints.ENDPOINTS.put("playbyplayv2", new URI("http://stats.nba.com/stats/playbyplayv2"));
            Endpoints.ENDPOINTS.put("playercareerstats", new URI("http://stats.nba.com/stats/playercareerstats"));
            Endpoints.ENDPOINTS.put("playercompare", new URI("http://stats.nba.com/stats/playercompare"));
            Endpoints.ENDPOINTS.put("playerdashboardbyclutch", new URI("http://stats.nba.com/stats/playerdashboardbyclutch"));
            Endpoints.ENDPOINTS.put("playerdashboardbygamesplits", new URI("http://stats.nba.com/stats/playerdashboardbygamesplits"));
            Endpoints.ENDPOINTS.put("playerdashboardbygeneralsplits", new URI("http://stats.nba.com/stats/playerdashboardbygeneralsplits"));
            Endpoints.ENDPOINTS.put("playerdashboardbylastngames", new URI("http://stats.nba.com/stats/playerdashboardbylastngames"));
            Endpoints.ENDPOINTS.put("playerdashboardbyopponent", new URI("http://stats.nba.com/stats/playerdashboardbyopponent"));
            Endpoints.ENDPOINTS.put("playerdashboardbyshootingsplits", new URI("http://stats.nba.com/stats/playerdashboardbyshootingsplits"));
            Endpoints.ENDPOINTS.put("playerdashboardbyteamperformance", new URI("http://stats.nba.com/stats/playerdashboardbyteamperformance"));
            Endpoints.ENDPOINTS.put("playerdashboardbyyearoveryear", new URI("http://stats.nba.com/stats/playerdashboardbyyearoveryear"));
            Endpoints.ENDPOINTS.put("playerdashptpass", new URI("http://stats.nba.com/stats/playerdashptpass"));
            Endpoints.ENDPOINTS.put("playerdashptreb", new URI("http://stats.nba.com/stats/playerdashptreb"));
            Endpoints.ENDPOINTS.put("playerdashptreboundlogs", new URI("http://stats.nba.com/stats/playerdashptreboundlogs"));
            Endpoints.ENDPOINTS.put("playerdashptshotdefend", new URI("http://stats.nba.com/stats/playerdashptshotdefend"));
            Endpoints.ENDPOINTS.put("playerdashptshotlog", new URI("http://stats.nba.com/stats/playerdashptshotlog"));
            Endpoints.ENDPOINTS.put("playerdashptshots", new URI("http://stats.nba.com/stats/playerdashptshots"));
            Endpoints.ENDPOINTS.put("playergamelog", new URI("http://stats.nba.com/stats/playergamelog"));
            Endpoints.ENDPOINTS.put("playerprofile", new URI("http://stats.nba.com/stats/playerprofile"));
            Endpoints.ENDPOINTS.put("playerprofilev2", new URI("http://stats.nba.com/stats/playerprofilev2"));
            Endpoints.ENDPOINTS.put("playersvsplayers", new URI("http://stats.nba.com/stats/playersvsplayers"));
            Endpoints.ENDPOINTS.put("playervsplayer", new URI("http://stats.nba.com/stats/playervsplayer"));
            Endpoints.ENDPOINTS.put("playoffpicture", new URI("http://stats.nba.com/stats/playoffpicture"));
            Endpoints.ENDPOINTS.put("scoreboard", new URI("http://stats.nba.com/stats/scoreboard"));
            Endpoints.ENDPOINTS.put("scoreboardV2", new URI("http://stats.nba.com/stats/scoreboardV2"));
            Endpoints.ENDPOINTS.put("shotchartdetail", new URI("http://stats.nba.com/stats/shotchartdetail"));
            Endpoints.ENDPOINTS.put("shotchartlineupdetail", new URI("http://stats.nba.com/stats/shotchartlineupdetail"));
            Endpoints.ENDPOINTS.put("teamdashboardbyclutch", new URI("http://stats.nba.com/stats/teamdashboardbyclutch"));
            Endpoints.ENDPOINTS.put("teamdashboardbygamesplits", new URI("http://stats.nba.com/stats/teamdashboardbygamesplits"));
            Endpoints.ENDPOINTS.put("teamdashboardbygeneralsplits", new URI("http://stats.nba.com/stats/teamdashboardbygeneralsplits"));
            Endpoints.ENDPOINTS.put("teamdashboardbylastngames", new URI("http://stats.nba.com/stats/teamdashboardbylastngames"));
            Endpoints.ENDPOINTS.put("teamdashboardbyopponent", new URI("http://stats.nba.com/stats/teamdashboardbyopponent"));
            Endpoints.ENDPOINTS.put("teamdashboardbyshootingsplits", new URI("http://stats.nba.com/stats/teamdashboardbyshootingsplits"));
            Endpoints.ENDPOINTS.put("teamdashboardbyteamperformance", new URI("http://stats.nba.com/stats/teamdashboardbyteamperformance"));
            Endpoints.ENDPOINTS.put("teamdashboardbyyearoveryear", new URI("http://stats.nba.com/stats/teamdashboardbyyearoveryear"));
            Endpoints.ENDPOINTS.put("teamdashlineups", new URI("http://stats.nba.com/stats/teamdashlineups"));
            Endpoints.ENDPOINTS.put("teamdashptpass", new URI("http://stats.nba.com/stats/teamdashptpass"));
            Endpoints.ENDPOINTS.put("teamdashptreb", new URI("http://stats.nba.com/stats/teamdashptreb"));
            Endpoints.ENDPOINTS.put("teamdashptshots", new URI("http://stats.nba.com/stats/teamdashptshots"));
            Endpoints.ENDPOINTS.put("teamgamelog", new URI("http://stats.nba.com/stats/teamgamelog"));
            Endpoints.ENDPOINTS.put("teaminfocommon", new URI("http://stats.nba.com/stats/teaminfocommon"));
            Endpoints.ENDPOINTS.put("teamplayerdashboard", new URI("http://stats.nba.com/stats/teamplayerdashboard"));
            Endpoints.ENDPOINTS.put("teamplayeronoffdetails", new URI("http://stats.nba.com/stats/teamplayeronoffdetails"));
            Endpoints.ENDPOINTS.put("teamplayeronoffsummary", new URI("http://stats.nba.com/stats/teamplayeronoffsummary"));
            Endpoints.ENDPOINTS.put("teamvsplayer", new URI("http://stats.nba.com/stats/teamvsplayer"));
            Endpoints.ENDPOINTS.put("teamyearbyyearstats", new URI("http://stats.nba.com/stats/teamyearbyyearstats"));
            Endpoints.ENDPOINTS.put("videoStatus", new URI("http://stats.nba.com/stats/videoStatus"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static class EndpointNotFound extends Exception {

        public EndpointNotFound() {
            super();
        }

        public EndpointNotFound(String message) {
            super(message);
        }

        public EndpointNotFound(String message, Throwable cause) {
            super(message, cause);
        }

        public EndpointNotFound(Throwable cause) {
            super(cause);
        }

    }
}
