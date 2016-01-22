/**
 * <!-- Versie 1.3.6
 *
 * -------------
 * - CHANGELOG -
 * -------------
 * Versie 1.3.5
 * 
 * +delimitters voor optionele modules
 * 
 * 
 * Versie 1.3.5
 * + fix issue #13, onduidelijkheid over GAME_END argumenten.
 * 
 * Versie 1.3.4
 * 
 * + correcte delimiter implemented binnen commando's - Rosalyn
 * 		-example: move & makemove
 * Versie 1.3.3
 * 
 * +verduidelijking commando server MOVE aangaande delimitter gebruik - Thomas & Niek
 * +verduidelijking volgorde stenen MOVE & MAKEMOVE
 * +SECURITY implementation toegevoegd
 * Versie 1.3.2
 * 
 * + fixed verkeerde delimitter MAKEMOVE - Rosalyn
 * + general errorcode 8 - JasperGerth & StephanMB
 *
 * 
 * Versie 1.3
 * + hallo commando overbodige tekst verwijderd
 * + error messages functionaliteit toegevoegd
 * + OKwaitfor functionaliteit uitgelegd
 * 
 * 
 * Versie 1.2
 * 	+ Chat commando updated 
 * 		+ CHAT_playerName_message --peter verzijl
 *  + Defined stone
 *  	+ elke kleur en vorm hebben nu een char toegewezen gekregen -- peter verzijl
 * Versie 1.1
 * 
 *  + consistentie voor de content
 *  + verschillende spelfouten weggewerkt
 * Versie 0.042
 *
 * + Eerste versie protocol
 * -->
*/

/**
 * <h1 id="protocol-ti-2">Protocol Group-6</h1>
 *
 * <p>
 * In dit bestand staat het protocol van werkgroep 4 zoals dat op woensdag.6
 * januari is afgesproken.
 * </p>
 * <p>
 * verdere documentatie is in het document overzicht protocollen programeren te
 * vinden
 * </p>
 *
 * <h2 id="1-over-standaarden">1. Over Standaarden</h2>
 *
 * <p>
 * Bij het afspreken van het protocol zijn de volgende standaarden afgesproken:
 * </p>
 *
 * <h3 id="printstream">Printstream</h3>
 *
 * <p>
 * Voor communicatie tussen de server en de client is er gekozen voor een
 * Printstream, dit is een voorlopige keuze sinds we dit niet tijdens de sessie
 * hebben besproken
 * </p>
 *
 * <ul>
 * <li>Hoewel er tijdens de protocolsessie geen afspraken zijn gemaakt over wat
 * voor stream er gebruikt gaat worden is er voor deze gekozen.</li>
 * <li>Een printstream is makkelijk te debuggen</li>
 * <li>Een printstream is makkelijk door mensen te lezen</li>
 * </ul>
 *
 * <p>
 * Tegen de printstream zijn de volgende argumenten ingebracht:
 * </p>
 *
 * <ul>
 * <li>Een printstream is inefficient in het uitlezen</li>
 * <li>Een printstream kan gemakkelijk zorgen voor type conflicts</li>
 * </ul>
 *
 * <h2 id="beslissingen">Beslissingen</h2>
 *
 *
 * <h3 id="board">Board</h3>
 *
 * <p>
 * Het bord wordt gedefinieerd doormiddel van twee integers
 * </p>
 *
 * <p>
 * de eerste steen wordt altijd neergelegd op steen 0,0
 * </p>
 * <p>
 * De origin van het bordt staat op 0,0 , waar de eerste waarde de colom
 * bijhoudt en de tweede de rij
 * </p>
 *
 * <p>
 * De colommen zijn positief aan de rechterhelft van het bord.
 * </p>
 *
 * <p>
 * De rijen zijjn positief aan de bovenste helft.
 * </p>
 *
 * <p>
 * het is mogelijk om een negatieve positie op het bord te hebben
 * </p>
 * 
 * <h3 id="Eerste Beurt">Eerste Beurt</h3>
 *
 * <p>
 * Bij de eerste beurt wordt de volgende volgorde doorlopen
 * <p>
 *
 * <ul>
 * <li>1. server stuurt addtohand naar alle spelers met hun eerste set stenen
 * </li>
 * <li>2. server stuurt naar elke speler individueel dat hij/zij aan de beurt is
 * </li>
 * <li>3. spelers sturen allemaal hun move naar de server</li>
 * <li>4. server legt de beste move neer en geeft de beurt aan de op een na
 * beste move.</li>
 * <li>5. het is in de normale spelstaat gekomen</li>
 * </ul>
 * bij een gelijke score wordt er een willekeurige speler gekozen.
 * <h3 id="player-names">Player Names</h3>
 * 
 *
 * <p>
 * Vanwege gebruiksgemak en het vergemakkelijken van het renderen heb ik
 * besloten om de maximale lengte van de naam van een player op 15 karakters te
 * zetten. Dit is in de meeste, zo niet alle, gevallen wel genoeg, en zal door
 * de maximale lengte ook geen problemen meer opleveren door veel te lange
 * usernames in bijvoorbeeld de chat.
 * </p>
 *
 * <p>
 * Punt van aandacht bij het programmeren: Players <strong>moeten</strong> een
 * unieke naam hebben: De naam wordt veel gebruikt voor identificatie.
 * </p>
 *
 *
 * <h3 id="leaderboard">Leaderboard</h3>
 *
 * <p>
 * Het leaderboard is een extra optie er worden de laatste 10 highscores gemeld
 * </p>
 * 
 * *
 * <h3 id="STENEN">STENEN</h3>
 *
 * <style type="text/css"> .tg {border-collapse:collapse;border-spacing:0;} .tg
 * td{font-family:Arial, sans-serif;font-size:14px;padding:10px
 * 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
 * .tg th{font-family:Arial,
 * sans-serif;font-size:14px;font-weight:normal;padding:10px
 * 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
 * .tg .tg-yw4l{vertical-align:top} </style>
 * <table class="tg">
 * <tr>
 * <th class="tg-031e">char</th>
 * <th class="tg-yw4l">colors</th>
 * <th class="tg-yw4l">Shapes</th>
 * </tr>
 * <tr>
 * <td class="tg-031e">A</td>
 * <td class="tg-yw4l">RED</td>
 * <td class="tg-yw4l">CIRCLE</td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">B</td>
 * <td class="tg-yw4l">ORANGE</td>
 * <td class="tg-yw4l">CROSS</td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">C</td>
 * <td class="tg-yw4l">YELLOW</td>
 * <td class="tg-yw4l">DIAMOND</td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">D</td>
 * <td class="tg-yw4l">GREEN</td>
 * <td class="tg-yw4l">SQUARE</td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">E</td>
 * <td class="tg-yw4l">BLUE</td>
 * <td class="tg-yw4l">STAR</td>
 * </tr>
 * <tr>
 * <td class="tg-yw4l">F</td>
 * <td class="tg-yw4l">PURPLE</td>
 * <td class="tg-yw4l">PLUS</td>
 * </tr>
 * </table>
 * <h3 id="errorcodes">Errorcodes</h3>
 *
 * <p>
 * er zijn verschillende errorcodes, heb je een goede reden om een extra aan te
 * vragen gooi het over de github
 * </p>
 *
 *
 * <h3 id="over-delimiters">Over Delimiters</h3>
 *
 * <p>
 * Ik heb gekozen voor een dubbele carriage return (<code>\n\n</code>) als
 * delimiter
 * <p>
 *
 * <p>
 * Als delimiter tussen argumenten gebruiken wij een underscore (<code>_<\code>)
 * omdat dit makkelijk en handig is.
 * </p>
 * <p>
 * Als tweede delimiter binnen een argument gebruiken wij een sterretje (<code>*
 * <\code>) omdat dit makkelijk en handig is.
 * </p>
 * de tweede delimitter wordt gebruikt om de integers in een makemove te
 * scheiden.
 *
 *
 * 
 * <h2 id="packets">Packets</h2>
 *
 * <p>
 * Hierop volgt een lijst met overeengekomen commando's. Deze zijn gesorteerd op
 * type en waar ze geimplementeerd moeten zijn.
 * </p>
 *
 * <p>
 * Per packet wordt ook het datatype erbijgegeven, dit om type conflicts tegen
 * te werken.
 * </p>
 *
 *
 *
 * /**
 * 
 * @author marti
 *
 */

public class Protocol {
	public static class Client {
		/**
		 * <h3 id="client">Client</h3>
		 */

		/**
		 * <p>
		 * Connect <br>
		 * Name: <code>HALLO</code> <br>
		 * Descriptie: Commando dat verstuurd wordt om te identificeren bij de
		 * server <br>
		 * Content: <code>HALLO_PlayerName_modulesSupported</code>
		 * </p>
		 * 
		 * example:
		 * HALLO_Piet_CHAT*SECURITY\n\n
		 * modules staan in willekeurige volgorde gedelimited door standaard delimiter 2
		 *
		 * 
		 */

		public static final String HALLO = "HALLO";

		/**
		 * <p>
		 * Quit <br>
		 * Name: <code>QUIT</code> <br>
		 * Descriptie: commando dat verzonden wordt om de verbinding te
		 * verbreken. <br>
		 * 
		 */

		public static final String QUIT = "QUIT";

		/**
		 * <p>
		 * Invite <br>
		 * Name: <code>INVITE_player\n\n</code> <br>
		 * Descriptie: Packet dat verzonden wordt om een spel te starten met de
		 * aangegeven tegenstander. <br>
		 * Content: <code>&lt;Opponent Name&gt;</code> [
		 * <code>&lt;BoardX&gt;</code> <code>&lt;BoardY&gt;</code> [
		 * <code>&lt;Settings&gt;</code>]]
		 * </p>
		 *
		 * <ul>
		 * <li><code>Opponent Name</code>: <code>String</code> (15) - De naam
		 * van de speler die de invite moet ontvangen.</li>
		 */

		public static final String INVITE = "INVITE";

		/**
		 * <p>
		 * Accept Invite <br>
		 * Name: <code>ACCEPT\n\n</code> <br>
		 * Descriptie: Packet door de uitgedaagde partij wordt verzonden om op
		 * een invite in te gaan. <br>
		 * Content: <code>&lt;Opponent Name&gt;</code>
		 * </p>
		 *
		 * <ul>
		 * <li><code>Opponent Name</code>: <code>String</code> (15) - De naam
		 * van degene die de uitgedaagde partij heeft uitgedaagd.</li>
		 * </ul>
		 */

		public static final String ACCEPTINVITE = "ACCEPTINVITE";

		/**
		 * <p>
		 * Decline Invite <br>
		 * Name: <code>DECLINE\n\n</code> <br>
		 * Descriptie: Packet die door de uitgedaagde partij wordt verzonden om
		 * een invite af te slaan. <br>
		 * Content: <code>&lt;Opponent Name&gt;</code>
		 * </p>
		 *
		 * <ul>
		 * <li><code>Opponent Name</code>: <code>String</code> (15) - De naam
		 * van degene die de uitgedaagde partij heeft uitgedaagd.</li>
		 * </ul>
		 */

		public static final String DECLINEINVITE = "DECLINEINVITE";

		/**
		 * <p>
		 * MAKEMOVE<br>
		 * Name: <code>MAKEMOVE</code> <br>
		 * Descriptie: de steen en locatie combinatie waar de steen neergelegd
		 * wordt <br>
		 * 
		 * <p>
		 * elke steen wordt bescheven als volgt:
		 * </p>
		 * <p>
		 * charchar*int*int
		 * </p>
		 * \ voorbeeld: Content: <code>charchar*int*int\n\n</code>
		 * </p>
		 * <code>MAKEMOVE_AF*11*6_BF*11*7\n\n<code> Als er meerdere stenen in
		 * een beurt worden gelegd is de de eerste steen de eerste in het
		 * commando en de laatste de laatste in het commando.
		 * 
		 */

		public static final String MAKEMOVE = "MAKEMOVE";

		/**
		 * <p>
		 * Chat <br>
		 * Name: <code>CHAT</code> <br>
		 * Descriptie: Bevat een chatmessage <br>
		 * Content: <code>CHAT_playerName_message\n\n;</code>
		 * </p>
		 *
		 */

		public static final String CHAT = "CHAT";

		/**
		 * <p>
		 * Request Game <br>
		 * Name: <code>REQUESTGAME</code> <br>
		 * Descriptie: Vraagt de server om een game te joinen van een aantal
		 * personen<br>
		 * Content: <code>REQUESTGAME_integer\n\n<code> Valide input voor de
		 * integer zijn: 0: Het maakt je niet uit hoeveel mensen je speelt 1:
		 * match tegen een computerspeler 2: match met 2 personen 3: match met 3
		 * personen 4: match met 4 personen voorbeeld: REQUESTGAME_int\n\n
		 * REQUESTGAME_4\n\n
		 */

		public static final String REQUESTGAME = "REQUESTGAME";

		/**
		 * <p>
		 * CHANGESTONE <br>
		 * Name: <code>CHANGESTONE</code> <br>
		 * Descriptie: Vraagt de server om een stenen in te wisselen<br>
		 * Content: <code>CHANGESTONE_steen_steen\n\n <code> example:
		 * CHANGESTONE_AE_AA\n\n
		 */

		public static final String CHANGESTONE = "CHANGESTONE";

		/**
		 * <p>
		 * GETLEADERBOARD<br>
		 * Name: <code>LEADERBOARD</code> <br>
		 * Descriptie: Vraag het leaderboard aan <br>
		 * Content: <code>GETLEADERBOARD\n\n<code>
		 */
		public static final String GETLEADERBOARD = "GETLEADERBOARD";
		/**
		 * <p>
		 * GETSTONESINBAG<br>
		 * Name: <code>STONESINBAG</code> <br>
		 * Descriptie: een commando waarmee de hoeveelheid stenen in de zak
		 * wordt gerequest <br>
		 * Content: <code>GETSTONESINBAG\n\n</code>
		 * </p>
		 *
		 */

		public static final String GETSTONESINBAG = "GETSTONESINBAG";

		/**
		 * <p>
		 * Error <br>
		 * Name: <code>ERROR</code><br/>
		 * Descriptie: Zend een error naar de server toe.<br/>
		 * Content: <code>ERROR_integer\n\n</code> er zijn nog geen errors
		 * gedefinieerd voor de speler.
		 */

		public static final String ERROR = "ERROR";

	}

	public static class Server {
		/**
		 * <h3 id="server">Server</h3>
		 */

		/**
		 * <p>
		 * HALLO <br>
		 * Name: <code>HALLO</code> <br>
		 * Descriptie: Signaal dat de connectie is gemaakt en het aangeven van
		 * welke functies allemaal zijn toegestaan op deze server. <br>
		 * Content: <code>HALLO_servernaam_features\n\n</code>
		 * </p>
		 * features worden gescheiden door een underscore
		 */

		public static final String HALLO = "HALLO";

		/**
		 * <p>
		 * Error <br>
		 * Name: <code>ERROR</code> <br>
		 * Descriptie: Een errormessage <br>
		 * Content: <code>ERROR_integer_message\n\n</code>
		 * </p>
		 * <ul>
		 * <li><code>Error Code</code>: <code>String</code> - De code is de
		 * header van het bericht waar de fout door is ontstaan.</li>
		 * <li><code>Message</code>: <code>String</code> (255) - Het bericht dat
		 * je aan je error hangt. Hierin kan je extra info tonen over wat er
		 * precies is foutgegaan.</li>
		 * </ul>
		 *
		 * errorcodes die beschikbaar zijn: 1: notyourturn 2: notyourstone 3:
		 * notthatmanystonesavailable 4: nameexists 5: notchallengable 6:
		 * challengerefused 7: invalidmove 8: generalerror
		 */

		public static final String ERROR = "ERROR";

		/**
		 * <p>
		 * OKWAITFOR <br>
		 * Name: <code>OKWAITFOR</code> <br>
		 * Descriptie: Geeft met de integer aan op hoeveel mensen de speler nog
		 * moet wachten tot het spel begint <br>
		 * Content: <code>OKWAITFOR_integer\n\n</code>
		 * </p>
		 * Op het moment dat een speler zich heeft aangemeld bij de server
		 * 
		 */

		public static final String OKWAITFOR = "OKWAITFOR";

		/**
		 * <p>
		 * Game Start <br>
		 * Name: <code>START</code> <br>
		 * Descriptie: Een packet dat naar de spelers wordt gestuurd om te laten
		 * weten dat het spel gestart is. <br>
		 * Content: <code>START_player_player\n\n</code>]
		 * </p>
		 *
		 * <ul>
		 * <li><code>Player 1</code>: <code>String</code> (15) - Naam van de
		 * eerste speler</li>
		 * <li><code>Player 2</code>: <code>String</code> (15) - Naam van de
		 * tweede speler</li>
		 * </ul>
		 */

		public static final String STARTGAME = "STARTGAME";

		/**
		 * <p>
		 * Game End <br>
		 * Name: <code>END</code> <br>
		 * Descriptie: Een packet dat naar de spelers wordt gestuurd om te laten
		 * weten dat het spel is gestopt en waarom.<br>
		 * Content: <code>END_TYPE_MESSAGE\n\n</code>]
		 * </p>
		 *
		 * <ul>
		 * <li><code>Type</code>: <code>String</code> &gt; <code>'WIN'</code>
		 * <code>'DISCONNECT'</code> <code>'DRAW'</code> - Type van einde spel
		 * </li>
		 * <li><code>Message</code>: <code>String</code> - Naam van de winnaar,
		 * of andere info over de reden van het einde.</li>
		 * </ul>
		 */

		public static final String GAME_END = "END";

		/**
		 * <p>
		 * MOVE<br>
		 * Name: <code>MOVE</code> <br>
		 * Descriptie: een commando om aan te geven welke move gemaakt door wie
		 * en welke speler nu aan de beurt is <br>
		 * Content:
		 * <p>
		 * MOVE_player_playernext_moves\n\n
		 * </p>
		 * Example:
		 * <p>
		 * MOVE_sjaak_piet_AF*11*6_BF*11*7\n\n Er kunnen meerdere moves gemaakt
		 * worden, deze worden gedelimit door standaarddelimiter De charchar
		 * worden gescheiden van de integers met standaarddelimter2 "*". De
		 * integers worden van elkaar gescheiden met standaarddelimiter2 "*" de
		 * individuele gelegde stenen worden gescheiden door standaarddelimter 1
		 * "_" . Als er meerdere stenen in een beurt worden gelegd is de de
		 * eerste steen de eerste in het commando en de laatste de laatste in
		 * het commando.
		 * 
		 */

		public static final String MOVE = "MOVE";

		/**
		 * <p>
		 * Chat <br>
		 * Name: <code>CHAT</code> <br>
		 * Descriptie: Een packet wat een chatbericht bevat <br>
		 * Content: <code>CHAT_bericht\n\n</code>
		 * </p>
		 * 
		 */

		public static final String CHAT = "CHAT";
		/**
		 * <p>
		 * ADDTOHAND <br>
		 * Name: <code>ADDTOHAND</code> <br>
		 * Descriptie: een commando waarmee stenen worden toegevoegd aan de hand
		 * van de speler <br>
		 * Content: <code>ADDTOHAND_steen_steen_steen\n\n</code>
		 * </p>
		 *
		 */

		public static final String ADDTOHAND = "ADDTOHAND";

		/**
		 * <p>
		 * STONESINBAG<br>
		 * Name: <code>STONESINBAG</code> <br>
		 * Descriptie: een commando waarmee de hoeveelheid stenen in de zak
		 * wordt gegeven <br>
		 * Content: <code>STONESINBAG_integer\n\n</code>
		 * </p>
		 *
		 */

		public static final String STONESINBAG = "STONESINBAG";

		/**
		 * <p>
		 * Leaderboard <br>
		 * Name: <code>LEADERBOARD</code> <br>
		 * Descriptie: Een packet waarin de statistieken van een aantal spelers
		 * worden verstuurd. <br>
		 * Content:
		 * <code>LEADERBOARD_playername*integer_playername*integer\n\n</code>
		 * </p>
		 *
		 * <ul>
		 * <li><code>Player Name</code>: <code>String</code> (15) - Naam van de
		 * speler in de betreffende statistiek</li>
		 * <li><code>Ranking</code>: <code>int</code> - Ranking op de server
		 * </li>
		 * </ul>
		 * </li>
		 * </ul>
		 */

		public static final String LEADERBOARD = "LEADERBOARD";

		public static class Features {

			/**
			 * <p>
			 * De verschillende features die optioneel zijn geimplementeerd
			 * kunnen worden.
			 * </p>
			 *
			 * <p>
			 * Let op! Het protocol voor <code>SECURITY</code> is nog niet
			 * vastgelegd. Dit wordt een SSL certificaat implementatie. Het
			 * certificaat wordt later bijgevoegd aan het protocol op github.
			 */
			public static final String CHAT = "CHAT";
			public static final String LEADERBOARD = "LEADERBOARD";
			public static final String SECURITY = "SECURITY";
			public static final String CHALLENGE = "CHALLENGE"; // Deze functie
																// wordt nog
																// niet verwacht
																// wordt dat
																// SSLsocket
																// gebruikt gaat
																// worden
		}

		/**
		 * <p>
		 * Invite <br>
		 * Name: <code>INVITE</code> <br>
		 * Descriptie: comando dat de server vraagt om iemand te challengen <br>
		 * Content: <code>INVITE_PLAYERNAME\n\n</code>
		 * </p>
		 *
		 * <ul>
		 * <li><code>Opponent Name</code>: <code>String</code> (15) - De naam
		 * van de tegenstander</li>
		 * </ul>
		 * op dit moment is er geen mogelijkheid gedefinieerd om meerdere mensen
		 * te challengen
		 */

		public static final String INVITE = "INVITE";

		/**
		 * <p>
		 * Decline invite<br>
		 * Name: <code>DECLINEINVITE</code> <br>
		 * Descriptie: De packet die waarmee een uitnodiging wordt afgewezen<br>
		 * Content: <code>DECLINEINVITE\n\n</code>
		 * </p>
		 */

		public static final String DECLINEINVITE = "DECLINEINVITE";

		public static class Settings {

			/**
			 * <p>
			 * De verschillende settings van het protocol.
			 * </p>
			 */

			/**
			 * <p>
			 * Het protocol heeft characterencoding UTF-16. Dit is de standaard
			 * encoding van een string in java, dus daar zouden geen problemen
			 * mee moeten zijn.
			 * </p>
			 */

			public static final String ENCODING = "UTF-16";

			/**
			 * <p>
			 * Het aantal seconden voordat een client timeout. Dit is in de
			 * opdracht vastgesteld, en zal dus niet veranderen.
			 * </p>
			 */

			public static final int TIMEOUTSECONDS = 15;

			/**
			 * <p>
			 * Default server port nummer. <br>
			 * <b>BELANGRIJK:</b> In de opdracht staat dat je bij het opstarten
			 * van de server een poortnummer moet invoeren. De waarde hier is
			 * dus niet een waarde die altijd opgaat.
			 * </p>
			 */

			public static final short DEFAULT_PORT = 4242;

			/**
			 * <p>
			 * Default delimiter tussen header en content, en tussen twee
			 * waarden in de content
			 * </p>
			 */

			public static final char DELIMITER = '_';
			/**
			 * <p>
			 * tweede delimiter om zaken te scheiden binenin een waarde in de
			 * content(bijvoorbeeld bij een steen locatie waarde)
			 * </p>
			 *
			 */
			public static final char DELIMITER2 = '*';
			/**
			 * <p>
			 * Teken dat aan het einde van elke packet moet zitten, en dus niet
			 * in de rest van de waarden mag zitten.
			 * </p>
			 */

			public static final String COMMAND_END = "\n\n";
		}
	}
}