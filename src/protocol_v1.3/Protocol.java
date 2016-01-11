

/**
 * <!-- Versie 1.0
 *
 * -------------
 * - CHANGELOG -
 * -------------
 *
 * Versie 0.042
 *
 * + Eerste versie protocol
 * -->
*/

/**
 * <h1 id="protocol-ti-2">Protocol Group-6</h1>
 *
 * <p>In dit bestand staat het protocol van werkgroep 4 zoals dat op woensdag.6 januari is afgesproken. </p>
 *<p> verdere documentatie is in het document overzicht protocollen programeren te vinden </p>
 *
 * <h2 id="1-over-standaarden">1. Over Standaarden</h2>
 *
 * <p>Bij het afspreken van het protocol zijn de volgende standaarden afgesproken:</p>
 *
 * <h3 id="printstream">Printstream</h3>
 *
 * <p>Voor communicatie tussen de server en de client is er gekozen voor een Printstream, dit is een voorlopige keuze sinds we dit niet tijdens de sessie hebben besproken</p>
 *
 * <ul>
 * 		<li> Hoewel er tijdens de protocolsessie geen afspraken zijn gemaakt over wat voor stream er gebruikt gaat worden is er voor deze gekozen.</li>
 *     <li>Een printstream is makkelijk te debuggen</li>
 *     <li>Een printstream is makkelijk door mensen te lezen</li>
 * </ul>
 *
 * <p>Tegen de printstream zijn de volgende argumenten ingebracht:</p>
 *
 * <ul>
 *     <li>Een printstream is inefficient in het uitlezen</li>
 *     <li>Een printstream kan gemakkelijk zorgen voor type conflicts</li>
 * </ul>
 *
  * <h2 id="beslissingen">Beslissingen</h2>
 *
 *
 * <h3 id="board">Board</h3>
 *
 * <p>Het bord wordt gedefinieerd doormiddel van twee integers</p>
 *
 * <p>de eerste steen wordt altijd neergelegd op steen 0,0</p>
 * <p>De origin van het bordt staat op 0,0 , waar de eerste waarde de colom bijhoudt en de tweede de rij</p>
 *
 * <p>De colommen zijn positief aan de rechterhelft van het bord.</p>
 *
 * <p>De rijen zijjn positief aan de bovenste helft.</p>
 *
 * <p>het is mogelijk om een negatieve positie op het bord te hebben</p>
 *
 *
 * <h3 id="player-names">Player Names</h3>
 *
 * <p>Vanwege gebruiksgemak en het vergemakkelijken van het renderen heb ik besloten om de maximale lengte van de naam van een player op 15 karakters te zetten. Dit is in de meeste, zo niet alle, gevallen wel genoeg, en zal door de maximale lengte ook geen problemen meer opleveren door veel te lange usernames in bijvoorbeeld de chat.</p>
 *
 * <p>Punt van aandacht bij het programmeren: Players <strong>moeten</strong> een unieke naam hebben: De naam wordt veel gebruikt voor identificatie.</p>
 *
 *
 * <h3 id="leaderboard">Leaderboard</h3>
 *
 * <p>Het leaderboard is een extra optie er worden de laatste 10 highscores gemeld</p>
 *
 *
 * <h3 id="errorcodes">Errorcodes</h3>
 *
 * <p> er zijn verschillende errorcodes, heb je een goede reden om een extra aan te vragen gooi het over de  </p>
 *
 *
 * <h3 id="over-delimiters">Over Delimiters</h3>
 *
 * <p>Ik heb gekozen voor een dubbele carriage return (<code>\n\n</code>) als delimiter <p>
 *
 * <p>Als delimiter tussen argumenten gebruiken wij een underscore (<code>_<\code>) omdat dit makkelijk en handig is.</p>
 * <p>Als delimiter binnen een argument gebruiken wij een sterretje (<code>*<\code>) omdat dit makkelijk en handig is.</p>


 *
 *
 * <h2 id="packets">Packets</h2>
 *
 * <p>Hierop volgt een lijst met overeengekomen commando's. Deze zijn gesorteerd op type en waar ze geimplementeerd moeten zijn. </p>
 *
 * <p>Per packet wordt ook het datatype erbijgegeven, dit om type conflicts tegen te werken.</p>
 *
 *
 *
 /**
 * @author marti
 *
 */
public class Protocol {
	public static class Client {
		/**
		 * <h3 id="client">Client</h3>
		 */

		/**
		 * <p>Connect <br>
		 *     Name: <code>HALLO</code> <br>
		 *     Descriptie: Commando dat verstuurd wordt om te identificeren bij de server <br>
		 *     Content: <code>Player Name_</code> <code>modulesSupported_</code></p>
		 *
		 *     <ul>
		 *         <li><code>Player Name</code>: <code>String</code> (15) - De naam van de speler die wil connecten.</li>
		 *         <li><code>modulesSupported</code>: <code>Feature[]</code> - Een lijst van ondersteunde <code>modules</code>s, met standaard delimitter van elkaar gescheiden. <br>
		 *             <code>Feature</code>: <code>String</code> (15) - Een unieke naam voor een feature. De volgende features zijn afgesproken:
		 *             <p>chat_</p>
		 *             <p>challenge_</p>
		 *            <p> security_</p>
		 *             <p>leaderboard_</p>
		 *             <p>elk van deze features wordt los toegelicht.</p>
		 *         </li>
		 *     </ul>
		 */

		public static final String HALLO = "HALLO";

		/**
		 * <p>Quit <br>
		 *     Name: <code>QUIT</code> <br>
		 *     Descriptie: commando dat verzonden wordt om de verbinding te verbreken. <br>
		 
		 */

		public static final String QUIT = "QUIT";

		/**
		 * <p>Invite <br>
		 *     Name: <code>INVITE</code> <br>
		 *     Descriptie: Packet dat verzonden wordt om een spel te starten met de aangegeven tegenstander. <br>
		 *     Content: <code>&lt;Opponent Name&gt;</code>  [<code>&lt;BoardX&gt;</code> <code>&lt;BoardY&gt;</code> [<code>&lt;Settings&gt;</code>]]</p>
		 *
		 *     <ul>
		 *         <li><code>Opponent Name</code>: <code>String</code> (15) - De naam van de speler die de invite moet ontvangen.</li>
		 *         <li><code>BoardX</code>: <code>short</code> - De breedte van het spelbord. Standaardwaarde is 7. Minimaal 4.</li>
		 *         <li><code>BoardY</code>: <code>short</code> - De hoogte van het spelbord. Standaardwaarde is 6. Minimaal 4.</li>
		 *         <li><code>Settings</code>: Eigen inbreng - Verdere settings die je mee wilt geven in het spel. </li>
		 *     </ul>
		 */

		public static final String INVITE = "INVITE";

		/**
		 * <p>Accept Invite <br>
		 *     Name: <code>ACCEPT</code> <br>
		 *     Descriptie: Packet door de uitgedaagde partij wordt verzonden om op een invite in te gaan. <br>
		 *     Content: <code>&lt;Opponent Name&gt;</code></p>
		 *
		 *     <ul>
		 *         <li><code>Opponent Name</code>: <code>String</code> (15) - De naam van degene die de uitgedaagde partij heeft uitgedaagd.</li>
		 *     </ul>
		 */

		public static final String ACCEPT_INVITE = "ACCEPT";

		/**
		 * <p>Decline Invite <br>
		 *     Name: <code>DECLINE</code> <br>
		 *     Descriptie: Packet die door de uitgedaagde partij wordt verzonden om een invite af te slaan. <br>
		 *     Content: <code>&lt;Opponent Name&gt;</code></p>
		 *
		 *     <ul>
		 *         <li><code>Opponent Name</code>: <code>String</code> (15) - De naam van degene die de uitgedaagde partij heeft uitgedaagd.</li>
		 *     </ul>
		 */

		public static final String DECLINE_INVITE = "DECLINE";

		/**
		 * <p>Move <br>
		 *     Name: <code>MAKEMOVE</code> <br>
		 *     Descriptie: de steen en locatie combinatie waar de steen neergelegd wordt <br>
		 *     <p>elke steen wordt bescheven als volgt:</p>
		 *     <p>charchar*int*int</p>\
		 *     voorbeeld:
		 *     Content: <code>charchar*int*int</code></p>
		 *     <code>MAKEMOVE_AF*11*6_BF*11*7\n\n<code>
		 *	
		 */

		public static final String MAKEMOVE = "MAKEMOVE";

		/**
		 * <p>Chat <br>
		 *     Name: <code>CHAT</code> <br>
		 *     Descriptie: Bevat een chatmessage <br>
		 *     Content: <code>&lt;Chat&gt;</code></p>
		 *
		 *     <ul>
		 *         <li><code>Chat</code>: <code>String</code> (512) - De boodschap aan de server chat</li>
		 *     </ul>
		 */

		public static final String CHAT = "CHAT";

		/**
		 * <p>Request Game <br>
		 *     Name: <code>REQUESTGAME</code> <br>
		 *     Descriptie: Vraagt de server om een game te joinen van een aantal personen<br>
		 *     Content: integer</p>
		 *     Valide input voor de integer zijn:
		 *     0: Het maakt je niet uit hoeveel mensen je speelt
		 *     1: match tegen een computerspeler
		 *     2: match met 2 personen
		 *     3: match met 3 personen
		 *     4: match met 4 personen
		 *     voorbeeld:
		 *     REQUESTGAME_int\n\n
		 *     REQUESTGAME_4\n\n
		 */

		public static final String REQUEST_GAME = "REQUESTGAME";

		/**
		 * <p>CHANGESTONE <br>
		 *     Name: <code>CHANGSTONE</code> <br>
		 *     Descriptie: Vraagt de server om een stenen in te wisselen<br>
		 *     Content: <code>CHANGESTONE_steen_steen\n\n <code>

		 */

		public static final String CHANGESTONE = "CHANGESTONE";


		/**
		 * <p>GETLEADERBOARD<br>
		 *     Name: <code>LEADERBOARD</code> <br>
		 *     Descriptie: Vraag het leaderboard aan <br>
		 *     Content: none</p>
		 */
		public static final String GETLEADERBOARD = "GETLEADERBOARD";
		/**
		 * <p>GETSTONESINBAG<br>
		 *     Name: <code>STONESINBAG</code> <br>
		 *     Descriptie: een commando waarmee de hoeveelheid stenen in de zak wordt gerequest <br>
		 *     Content: <code>STONESINBAG\n\n</code></p>
		 *
		 */

		public static final String GETSTONESINBAG = "GETSTONESINBAG";

		

		/**
		 * <p>Error <br>
		 *     Name: <code>ERROR</code><br/>
		 *     Descriptie: Zend een error naar de server toe.<br/>
		 *     Content: <code>&lt;Error Code&gt;</code> <code>&lt;Message&gt;</code></p>
		 *	er zijn nog geen errors gedefinieerd voor de speler.
		 */

		public static final String ERROR = "ERROR";

	}

	public static class Server {
		/**
		 * <h3 id="server">Server</h3>
		 */

		/**
		 * <p>HALLO <br>
		 *     Name: <code>HALLO</code> <br>
		 *     Descriptie: Signaal dat de connectie is gemaakt en het aangeven van welke functies allemaal zijn toegestaan op deze server. <br>
		 *     Content: <code>HALLO_servernaam_features\n\n</code></p>
		 *	features worden gescheiden door een underscore
		 */

		public static final String HALLO = "HALLO";

		/**
		 * <p>Error <br>
		 *     Name: <code>ERROR</code> <br>
		 *     Descriptie: Een errormessage <br>
		 *     Content: <code>ERROR_integer\n\n</code></p>
		 *     <ul>

		 *     errorcodes die beschikbaar zijn:
		 *     1: notyourturn
		 *     2: notyourstone
		 *     3: notthatmanystonesavailable
		 *     4: nameexists
		 *     5: notchallengable
		 *     6: challengerefused
		 *     7: invalidmove
		 */

		public static final String ERROR = "ERROR";

		/**
		 * <p>OKWAITFOR <br>
		 *     Name: <code>OKWAITFOR</code> <br>
		 *     Descriptie: Een lijst met mensen die zich op het moment in de lobby bevinden. Wordt door de server verstuurd bij Connect/Disconnect van een speler, en wanneer aangevraagd. <br>
		 *     Content: <code>OKWAITFOR_integer\n\n</code></p>
		 *	
		*/

		public static final String OKWAITFOR = "OKWAITFOR";

		/**
		 * <p>Game Start <br>
		 *     Name: <code>START</code> <br>
		 *     Descriptie: Een packet dat naar de spelers wordt gestuurd om te laten weten dat het spel gestart is. <br>
		 *     Content: <code>START_player_player\n\n</code>]</p>
		 *
		 *     <ul>
		 *         <li><code>Player 1</code>: <code>String</code> (15) - Naam van de eerste speler</li>
		 *         <li><code>Player 2</code>: <code>String</code> (15) - Naam van de tweede speler</li>
		 *     </ul>
		 */

		public static final String STARTGAME = "STARTGAME";

		/**
		 * <p>Game End <br>
		 *     Name: <code>END</code> <br>
		 *     Descriptie: Een packet dat naar de spelers wordt gestuurd om te laten weten dat het spel is gestopt <br>
		 *     Content: <code>END\n\n</code>]</p>
		 *
		 *     <ul>
		 *         <li><code>Type</code>: <code>String</code> &gt; <code>'WIN'</code> <code>'DISCONNECT'</code> <code>'DRAW'</code> - Type van einde spel</li>
		 *         <li><code>Winner Name</code>: <code>String</code> (15) - Naam van de winnaar, of andere info over de reden van het einde.</li>
		 *     </ul>
		 */

		public static final String GAME_END = "END";

		/**
		 * <p>MOVE<br>
		 *     Name: <code>REQUEST</code> <br>
		 *     Descriptie: een commando om aan te geven welke move gemaakt door wie en welke speler nu aan de beurt is <br>
		 *     Content: <p>MOVE_player_playernext_moves\n\n</p>
		 *     er kunnen meerdere moves gemaakt worden, deze worden gedelimit door standaarddelimiter
		 *     de informatie in de move wordt gedelimit door standaarddelimiter2
		 */

		public static final String MOVE = "MOVE";


		/**
		 * <p>Chat <br>
		 *     Name: <code>CHAT</code> <br>
		 *     Descriptie: Een packet wat een chatbericht bevat <br>
		 *     Content: <code>CHAT_bericht\n\n</code></p>

		 */

		public static final String CHAT = "CHAT";
		/**
		 * <p>ADDTOHAND <br>
		 *     Name: <code>ADDTOHAND</code> <br>
		 *     Descriptie: een commando waarmee stenen worden toegevoegd aan de hand van de speler <br>
		 *     Content: <code>ADDTOHAND_steen_steen_steen\n\n</code></p>
		 *
		 */

		public static final String ADDTOHAND = "ADDTOHAND";
		
		/**
		 * <p>STONESINBAG<br>
		 *     Name: <code>STONESINBAG</code> <br>
		 *     Descriptie: een commando waarmee de hoeveelheid stenen in de zak wordt gegeven <br>
		 *     Content: <code>STONESINBAG_integer\n\n</code></p>
		 *
		 */

		public static final String STONESINBAG = "STONESINBAG";

		/**
		 * <p>Leaderboard <br>
		 *     Name: <code>LEADERBOARD</code> <br>
		 *     Descriptie: Een packet waarin de statistieken van een aantal spelers worden verstuurd. <br>
		 *     Content: <code>&lt;Player Statistics&gt;</code></p>
		 *
		 *     <ul>
 		 *                 <li><code>Player Name</code>: <code>String</code> (15) - Naam van de speler in de betreffende statistiek</li>
		 *                 <li><code>Ranking</code>: <code>int</code> - Ranking op de server</li>
		 *             </ul>
		 *         </li>
		 *     </ul>
		 */

		public static final String LEADERBOARD = "GETLEADERBOARD";



	public static class Features {

		/**
		 * <p>De verschillende features die optioneel zijn geimplementeerd kunnen worden.</p>
		 *
		 * <p>Let op! Het protocol voor <code>SECURITY</code> is nog niet vastgelegd.
*/
		public static final String CHAT = "CHAT";
		public static final String LEADERBOARD = "LEADERBOARD";
		public static final String SECURITY = "SECURITY";
		public static final String CHALLENGE = "CHALLENGE"; // Deze functie wordt nog niet verwacht wordt dat SSLsocket gebruikt gaat worden
	}

		

		/**
		 * <p>Invite <br>
		 *     Name: <code>INVITE</code> <br>
		 *     Descriptie: comando dat de server vraagt om iemand te challengen <br>
		 *     Content: <code>INVITE_PLAYERNAME\n\n</code></p>
		 *
		 *     <ul>
		 *         <li><code>Opponent Name</code>: <code>String</code> (15) - De naam van de tegenstander</li>
		 *     </ul>
		 *     op dit moment is er geen mogelijkheid gedefinieerd om meerdere mensen te challengen
		 */

		public static final String INVITE = "INVITE";

		/**
		 * <p>Decline invite<br>
		 *     Name: <code>DECLINEINVITE</code> <br>
		 *     Descriptie: De packet die waarmee een uitnodiging wordt afgewezen<br>
		 *     Content: <code>DECLINEINVITE\n\n</code></p>
		 */

		public static final String DECLINEINVITE = "DECLINEINVITE";

	public static class Settings {

		/**
		 * <p>De verschillende settings van het protocol.</p>
		 */

		/**
		 * <p>Het protocol heeft characterencoding UTF-16. Dit is de standaard encoding van een string in java, dus daar zouden geen problemen mee moeten zijn.</p>
		 */

		public static final String ENCODING = "UTF-16";

		/**
		 * <p>Het aantal seconden voordat een client timeout. Dit is in de opdracht vastgesteld, en zal dus niet veranderen.</p>
		 */

		public static final int TIMEOUTSECONDS = 15;

		/**
		 * <p>Default server port nummer. <br>
		 *     <b>BELANGRIJK:</b> In de opdracht staat dat je bij het opstarten van de server een poortnummer moet invoeren. De waarde hier is dus niet een waarde die altijd opgaat. </p>
		 */

		public static final short DEFAULT_PORT = 4242;

		/**
		 * <p>Default delimiter tussen header en content, en tussen twee waarden in de content</p>
		 */

		public static final char DELIMITER = '_';
/**
 * <p>tweede delimiter om zaken te scheiden binenin een waarde in de content(bijvoorbeeld bij een steen locatie waarde)</p>
 *
 */
		public static final char DELIMITER2 = '*';
		/**
		 * <p>Teken dat aan het einde van elke packet moet zitten, en dus niet in de rest van de waarden mag zitten.</p>
		 */

		public static final String COMMAND_END = "\n\n";
	}
}
}