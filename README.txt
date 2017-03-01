## NBAStats Collector ##

It can be used both as an executable and as an external development library for Java.
The system uses a base folder under "./nbastats" containing all the endpoints and their required parameters. 
If this path does not exists, the system will parse the endpoints once and generate it, along with other files like "parameters.txt", "players.txt", "seasons.txt" and "teams.txt".

It can be used by executing the following command in the command line:

	$ java -cp NBAStats-1.0-SNAPSHOT-jar-with-dependencies.jar com.nba.stats.main.Main <command> <argument>
	
Commands:

	-analyze_endpoint <arg>   Analyze the requirements of the given endpoint.
	-endpoint <arg>           The NBAStats endpoint you want to collect.
	-help                     Print all the possible commands.
	-output <arg>             Path to the generated .tsv results file. Current directory as a default path.
	-parameters <arg>         Path to the .json file containing the parameters for the search.
	-show_endpoints           Show all the possible endpoints.
	-show_parameters          Show all the possible parameters and their values.

	The collected data are stored in .tsv - tab seperated values format.
	Each endpoint's results are stored in folder named: <endpoint><time in milliseconds>.
	The parameter .json file contains the key-value pairs of the query parameters. For example if i want to collect the commonplayerinfo of Joe Smith the file is the following:
  
	{
		"playerid": "693"
	}
	
	Be careful all the values must be of String format, all the values must be contained in quotes.