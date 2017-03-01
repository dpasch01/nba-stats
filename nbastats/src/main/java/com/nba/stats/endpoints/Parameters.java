package com.nba.stats.endpoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parameters {
    public static TreeMap<String, String> PARAMETERS;
    public static HashMap<String, String> RANGES;

    private Map<String, String> values;

    static {
        Parameters.RANGES = new HashMap<>();
        Parameters.RANGES.put("AheadBehind", "'Ahead or Behind', 'Behind or Tied', 'Ahead or Tied'");
        Parameters.RANGES.put("ClutchTime", "'Last 5 Minutes', 'Last 4 Minutes', 'Last 3 Minutes', 'Last 2 Minutes', 'Last 1 Minute', 'Last 30 Seconds', 'Last 10 Seconds'");
        Parameters.RANGES.put("DefenseCategory", "'Overall', '3 Pointers', '2 Pointers', 'Less Than 6Ft', 'Less Than 10Ft', 'Greater Than 15Ft'");
        Parameters.RANGES.put("DistanceRange", "'5ft Range', '8ft Range', 'By Zone'");
        Parameters.RANGES.put("GameScope", "'Season', 'Last 10', 'Yesterday', 'Finals'");
        Parameters.RANGES.put("GameSegment", "'First Half', 'Overtime', 'Second Half'");
        Parameters.RANGES.put("IsOnlyCurrentSeason", "'0', '1'");
        Parameters.RANGES.put("LeagueID", "'00', '20'");
        Parameters.RANGES.put("Location", "'Home', 'Road'");
        Parameters.RANGES.put("MeasureType", "'Base', 'Advanced', 'Misc', 'Four Factors', 'Scoring', 'Opponent', 'Usage'");
        Parameters.RANGES.put("Month", "'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'");
        Parameters.RANGES.put("Outcome", "'W', 'L'");
        Parameters.RANGES.put("PaceAdjust", "'Y', 'N'");
        Parameters.RANGES.put("Period", "'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14'");
        Parameters.RANGES.put("PerMode", "PerGame, Totals, Per36");
        Parameters.RANGES.put("PlayerExperience", "'Rookie', 'Sophomore', 'Veteran'");
        Parameters.RANGES.put("PlayerOrTeam", "'Player', 'Team'");
        Parameters.RANGES.put("Scope", "'RS', 'S', 'Rookies'");
        Parameters.RANGES.put("PlayerPosition", "'F', 'C', 'G', 'C-F', 'F-C', 'F-G', 'G-F'");
        Parameters.RANGES.put("PlayerScope", "'All Players', 'Rookies'");
        Parameters.RANGES.put("PlusMinus", "'Y', 'N'");
        Parameters.RANGES.put("ContextMeasure", "'PTS', 'FGM', 'FGA', 'FG_PCT', 'FG3M', 'FG3A', 'FG3_PCT', 'PF', 'EFG_PCT', 'TS_PCT', 'PTS_FB', 'PTS_OFF_TOV', 'PTS_2ND_CHANCE', 'PF'");
        Parameters.RANGES.put("RangeType", "'0', '1', '2'");
        Parameters.RANGES.put("Rank", "'Y', 'N'");
        Parameters.RANGES.put("SeasonSegment", "'Post All-Star', 'Pre All-Star'");
        Parameters.RANGES.put("SeasonType", "'Regular Season', 'Pre Season', 'Playoffs'");
        Parameters.RANGES.put("Stat", "'PTS', 'REB', 'AST', 'FG_PCT', 'FT_PCT', 'FG3_PCT', 'STL', 'BLK'");
        Parameters.RANGES.put("ShotClockRange", "'24-22', '22-18 Very Early', '18-15 Early', '15-7 Average', '7-4 Late', '4-0 Very Late', 'ShotClock Off'");
        Parameters.RANGES.put("StarterBench", "'Starters', 'Bench'");
        Parameters.RANGES.put("StatCategory", "'Points', 'Rebounds', 'Assists', 'Defense', 'Clutch', 'Playmaking', 'Efficiency', 'Fast Break', 'Scoring Breakdown'");
        Parameters.RANGES.put("StatType", "'Traditional', 'Advanced', 'Tracking'");
        Parameters.RANGES.put("VsConference", "'East', 'West'");
        Parameters.RANGES.put("VsDivision", "'Atlantic', 'Central', 'Northwest', 'Pacific', 'Southeast', 'Southwest', 'East', 'West'");

        Parameters.PARAMETERS = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Parameters.PARAMETERS.put("AheadBehind", "Range");
        Parameters.PARAMETERS.put("ClutchTime", "Range");
        Parameters.PARAMETERS.put("DateFrom", "YYYY-MM-DD");
        Parameters.PARAMETERS.put("GameDate", "YYYY-MM-DD");
        Parameters.PARAMETERS.put("GraphStat ", "String");
        Parameters.PARAMETERS.put("DateTo", "YYYY-MM-DD");
        Parameters.PARAMETERS.put("DefenseCategory", "Range");
        Parameters.PARAMETERS.put("ContextMeasure", "Range");
        Parameters.PARAMETERS.put("DistanceRange", "Range");
        Parameters.PARAMETERS.put("EastPlayer1", "Number");
        Parameters.PARAMETERS.put("EastPlayer2", "Number");
        Parameters.PARAMETERS.put("EastPlayer3", "Number");
        Parameters.PARAMETERS.put("GraphStat", "String");
        Parameters.PARAMETERS.put("EastPlayer4", "Number");
        Parameters.PARAMETERS.put("EastPlayer5", "Number");
        Parameters.PARAMETERS.put("EndPeriod", "Number");
        Parameters.PARAMETERS.put("GameID", "Number");
        Parameters.PARAMETERS.put("EndRange", "Number");
        Parameters.PARAMETERS.put("StartRange", "Number");
        Parameters.PARAMETERS.put("ContextFilter", "String");
        Parameters.PARAMETERS.put("GameScope", "Range");
        Parameters.PARAMETERS.put("Scope", "Range");
        Parameters.PARAMETERS.put("Stat", "Range");
        Parameters.PARAMETERS.put("GameSegment", "Range");
        Parameters.PARAMETERS.put("GraphEndSeason", "YYYY-YY");
        Parameters.PARAMETERS.put("GROUP_ID", "Number");
        Parameters.PARAMETERS.put("GroupQuantity", "Number");
        Parameters.PARAMETERS.put("GraphStartSeason", "YYYY-YY");
        Parameters.PARAMETERS.put("IsOnlyCurrentSeason", "Range");
        Parameters.PARAMETERS.put("LastNGames", "Number ");
        Parameters.PARAMETERS.put("LeagueID", "Range");
        Parameters.PARAMETERS.put("Location", "Range");
        Parameters.PARAMETERS.put("MeasureType", "Range");
        Parameters.PARAMETERS.put("Month", "Range");
        Parameters.PARAMETERS.put("OpponentTeamID", "Number");
        Parameters.PARAMETERS.put("Outcome", "Range");
        Parameters.PARAMETERS.put("PaceAdjust", "Range");
        Parameters.PARAMETERS.put("Period", "Range");
        Parameters.PARAMETERS.put("PerMode", "Range");
        Parameters.PARAMETERS.put("PlayerExperience", "Range");
        Parameters.PARAMETERS.put("PlayerID", "Number");
        Parameters.PARAMETERS.put("PlayerID1", "Number");
        Parameters.PARAMETERS.put("PlayerID2", "Number");
        Parameters.PARAMETERS.put("PlayerID3", "Number");
        Parameters.PARAMETERS.put("PlayerID4", "Number");
        Parameters.PARAMETERS.put("PlayerID5", "Number");
        Parameters.PARAMETERS.put("PlayerIDList", "Number-List");
        Parameters.PARAMETERS.put("PlayerOrTeam", "Range");
        Parameters.PARAMETERS.put("PlayerPosition", "Range");
        Parameters.PARAMETERS.put("PlayerTeamID", "Number");
        Parameters.PARAMETERS.put("PlayerScope", "Range");
        Parameters.PARAMETERS.put("PlusMinus", "Range");
        Parameters.PARAMETERS.put("PointCap", "Number");
        Parameters.PARAMETERS.put("PointDiff", "Number");
        Parameters.PARAMETERS.put("PORound", "Number");
        Parameters.PARAMETERS.put("Rank", "Range");
        Parameters.PARAMETERS.put("RangeType", "Range");
        Parameters.PARAMETERS.put("RookieYear", "YYYY-YY");
        Parameters.PARAMETERS.put("Season", "YYYY-YY");
        Parameters.PARAMETERS.put("SeasonID", "Number");
        Parameters.PARAMETERS.put("SeasonSegment", "Range");
        Parameters.PARAMETERS.put("SeasonType", "Range");
        Parameters.PARAMETERS.put("SeasonYear", "YYYY-YY");
        Parameters.PARAMETERS.put("ShotClockRange", "Range");
        Parameters.PARAMETERS.put("StarterBench", "Range");
        Parameters.PARAMETERS.put("StartPeriod", "Number");
        Parameters.PARAMETERS.put("StatCategory", "Range");
        Parameters.PARAMETERS.put("StatType", "Range");
        Parameters.PARAMETERS.put("TeamID", "Number");
        Parameters.PARAMETERS.put("VsConference", "Range");
        Parameters.PARAMETERS.put("VsDivision", "Range");
        Parameters.PARAMETERS.put("VsPlayerID", "Number");
        Parameters.PARAMETERS.put("VsPlayerID1", "Number");
        Parameters.PARAMETERS.put("VsPlayerID2", "Number");
        Parameters.PARAMETERS.put("VsPlayerID3", "Number");
        Parameters.PARAMETERS.put("VsPlayerID4", "Number");
        Parameters.PARAMETERS.put("VsPlayerID5", "Number");
        Parameters.PARAMETERS.put("VsPlayerIDList", "Number-List");
        Parameters.PARAMETERS.put("VsTeamID", "Number");
        Parameters.PARAMETERS.put("WestPlayer1", "Number");
        Parameters.PARAMETERS.put("WestPlayer2", "Number");
        Parameters.PARAMETERS.put("WestPlayer3", "Number");
        Parameters.PARAMETERS.put("WestPlayer4", "Number");
        Parameters.PARAMETERS.put("WestPlayer5", "Number");
    }

    public Parameters(List<String> parameters) throws ParameterNotFound {
        this.values = new HashMap<>();

        for (String parameter : parameters) {
            if (Parameters.PARAMETERS.get(parameter) == null) {
                throw new ParameterNotFound("Parameter " + parameter + " not found.");
            } else {
                this.values.put(parameter, "");
            }
        }
    }

    public Parameters() {
        this.values = new HashMap<>();
    }

    public void addParameter(String parameter, String value) throws ParameterNotFound {
        if (Parameters.PARAMETERS.get(parameter) == null) {
            throw new ParameterNotFound("Parameter " + parameter + " not found.");
        } else {
            this.values.put(parameter, value);
        }
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setParameters(TreeMap<String, String> values) {
        this.values = values;
    }

    public static class ParameterNotFound extends Exception {

        public ParameterNotFound() {
            super();
        }

        public ParameterNotFound(String message) {
            super(message);
        }

        public ParameterNotFound(String message, Throwable cause) {
            super(message, cause);
        }

        public ParameterNotFound(Throwable cause) {
            super(cause);
        }

    }
}
