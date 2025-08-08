package com.heroku.java.concerto;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.java.utils.AuthenticationService;
import com.heroku.java.utils.SQLFormatter;
import com.heroku.java.utils.RandomUtils;

@Service
public class ConcertoService {
    private final DataSource dataSource;
    private final AuthenticationService authenticationService;

    @Autowired 
    public ConcertoService(DataSource dataSource, AuthenticationService authenticationService) {
        this.dataSource = dataSource;
        this.authenticationService = authenticationService;
    }

    private void saveArtists(Connection connection, Artist[] artists) throws SQLException {
        String query = "INSERT INTO artist (id, name, genre, image, image_offset, description, tour) VALUES ";
        String[] values = new String[artists.length];
        for (int i = 0; i < artists.length; i++) {
            Artist artist = artists[i];
            values[i] = "(" + SQLFormatter.formatUUID(artist.getId()) + ", " + SQLFormatter.formatString(artist.getName()) + ", " + SQLFormatter.formatString(artist.getGenre()) + ", " + SQLFormatter.formatString(artist.getImage()) + ", " + artist.getImageOffset() + ", " + SQLFormatter.formatString(artist.getDescription()) + ", " + SQLFormatter.formatString(artist.getTour()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Artist[] generateArtists(Connection connection) throws SQLException {
        final Artist taylorSwift = new Artist().setId(UUID.randomUUID()).setName("Taylor Swift").setDescription("").setGenre("Pop").setImage("/images/concerto/artists/Taylor_Swift.jpg").setImageOffset(15).setTour("Eras Tour");

        final Artist dublinPhilharmonic = new Artist().setId(UUID.randomUUID()).setName("Dublin Philharmonic").setDescription("").setGenre("Classical").setImage("/images/concerto/artists/Philharmonic.jpg").setImageOffset(30).setTour("An Evening With Mozart");

        final Artist metallica = new Artist().setId(UUID.randomUUID()).setName("Metallica").setDescription("").setGenre("Heavy Metal").setImage("/images/concerto/artists/Metallica.jpg").setImageOffset(5).setTour("Time Marches On");

        final Artist adele = new Artist().setId(UUID.randomUUID()).setName("Adele").setDescription("").setGenre("Soul").setImage("/images/concerto/artists/Adele.png").setImageOffset(5).setTour("Weekends with Adele");

        final Artist edSheeran = new Artist().setId(UUID.randomUUID()).setName("Ed Sheeran").setDescription("").setGenre("Pop").setImage("/images/concerto/artists/Ed_Sheeran.jpg").setImageOffset(5).setTour("Mathematics tour");

        final Artist daftPunk = new Artist().setId(UUID.randomUUID()).setName("Daft Punk").setDescription("").setGenre("Electronic").setImage("/images/concerto/artists/Daft_Punk.jpg").setImageOffset(10).setTour("Interstella 5555");

        final Artist mariachiHuenachi = new Artist().setId(UUID.randomUUID()).setName("Mariachi Huenachi").setDescription("").setGenre("Latin").setImage("/images/concerto/artists/Mariachi_Huenachi.jpg").setImageOffset(10).setTour("Musica de la noche");

        final Artist bhangraBoys = new Artist().setId(UUID.randomUUID()).setName("Bhangra Boys").setDescription("").setGenre("Punjabi").setImage("/images/concerto/artists/Bhangra.jpg").setImageOffset(15).setTour("North American Tour");

        final Artist flamencoAndalusia = new Artist().setId(UUID.randomUUID()).setName("Flamenco Andalusia").setDescription("").setGenre("Latin").setImage("/images/concerto/artists/Flamenco.jpg").setImageOffset(10).setTour("Torneo de las rosas");

        final Artist popsInThePark = new Artist().setId(UUID.randomUUID()).setName("Pops in the Park").setDescription("").setGenre("Classical").setImage("/images/concerto/artists/4th_of_July.jpg").setImageOffset(20).setTour("John Williams'' Greatest Hits");

        final Artist chrisStapleton = new Artist().setId(UUID.randomUUID()).setName("Chris Stapleton").setDescription("").setGenre("Country").setImage("/images/concerto/artists/Chris_Stapleton.jpg").setImageOffset(15).setTour("All-American Road Show Tour");

        final Artist hulaGals = new Artist().setId(UUID.randomUUID()).setName("The Hula Gals").setDescription("").setGenre("Polynesian").setImage("/images/concerto/artists/Polynesian.jpg").setImageOffset(10).setTour("Songs of my ancestors");

        final Artist[] artists = {taylorSwift, dublinPhilharmonic, metallica, adele, edSheeran, daftPunk, mariachiHuenachi, bhangraBoys, flamencoAndalusia, popsInThePark, chrisStapleton, hulaGals};

        saveArtists(connection, artists);

        return artists;
    }

    private void saveCities(Connection connection, City[] cities) throws SQLException {
        String query = "INSERT INTO city (id, image, image_offset, name, state, locations) VALUES ";
        String[] values = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            City city = cities[i];
            values[i] = "(" + SQLFormatter.formatUUID(city.getId()) + ", " + SQLFormatter.formatString(city.getImage()) + ", " + city.getImageOffset() + ", " + SQLFormatter.formatString(city.getName()) + ", " + SQLFormatter.formatString(city.getState()) + ", " + SQLFormatter.formatStringArray(city.getLocations()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private City[] generateCities(Connection connection) throws SQLException {
        final String[] seattleLocations = {"Space Needle", "Pike''s Place"};
        final City seattle = new City().setId(UUID.randomUUID()).setName("Seattle").setState("WA").setLocations(seattleLocations).setImage("/images/concerto/cities/Seattle.jpeg").setImageOffset(15);

        final String[] sanFranciscoLocations = {"Dolores Park", "Golden Gate Park"};
        final City sanFrancisco = new City().setId(UUID.randomUUID()).setName("San Francisco").setState("CA").setLocations(sanFranciscoLocations).setImage("/images/concerto/cities/Golden_Gate.jpg").setImageOffset(20);
        
        final String[] losAngelesLocations = {"Hollywood Bowl", "Griffith Observatory"};
        final City losAngeles = new City().setId(UUID.randomUUID()).setName("Los Angeles").setState("CA").setLocations(losAngelesLocations).setImage("/images/concerto/cities/Los_Angeles.jpg").setImageOffset(30);

        final String[] newYorkLocations = {"Madison Square Garden", "Times Square"};
        final City newYork = new City().setId(UUID.randomUUID()).setName("New York").setState("NY").setLocations(newYorkLocations).setImage("/images/concerto/cities/Statue_of_Liberty.jpg").setImageOffset(10);

        final String[] orlandoLocations = {"Disneyworld", "Universal Studios"};
        final City orlando = new City().setId(UUID.randomUUID()).setName("Orlando").setState("FL").setLocations(orlandoLocations).setImage("/images/concerto/cities/Disney.jpg").setImageOffset(20);

        final String[] charlestonLocations = {"Charleston Music Hall", "The Charleston Pour House"};
        final City charleston = new City().setId(UUID.randomUUID()).setName("Charleston").setState("SC").setLocations(charlestonLocations).setImage("/images/concerto/cities/Charleston.jpg").setImageOffset(20);

        final String[] austinLocations = {"Mohawk Austin"};
        final City austin = new City().setId(UUID.randomUUID()).setName("Austin").setState("TX").setLocations(austinLocations).setImage("/images/concerto/cities/Austin.jpg").setImageOffset(20);

        final String[] honoluluLocations = {"Blue Note Hawaii", "Waikiki Shell"};
        final City honolulu = new City().setId(UUID.randomUUID()).setName("Honolulu").setState("HI").setLocations(honoluluLocations).setImage("/images/concerto/cities/Kuaui.jpg").setImageOffset(30);
        
        final String[] phoenixLocations = {"PHX Arena", "The Van Buren"};
        final City phoenix = new City().setId(UUID.randomUUID()).setName("Phoenix").setState("AZ").setLocations(phoenixLocations).setImage("/images/concerto/cities/Saguaro.jpg").setImageOffset(20);

        final String[] denverLocations = {"Red Rock Amphitheater"};
        final City denver = new City().setId(UUID.randomUUID()).setName("Denver").setState("CO").setLocations(denverLocations).setImage("/images/concerto/cities/Red_Rocks_Amphitheatre.jpg").setImageOffset(20);

        final String[] bostonLocations = {"MGM Music Hall"};
        final City boston = new City().setId(UUID.randomUUID()).setName("Boston").setState("MA").setLocations(bostonLocations).setImage("/images/concerto/cities/Boston.jpg").setImageOffset(50);

        final String[] nashvilleLocations = {"Ryman Auditorium", "Grand Ole Opry"};
        final City nashville = new City().setId(UUID.randomUUID()).setName("Nashville").setState("TN").setLocations(nashvilleLocations).setImage("/images/concerto/cities/Nashville.jpg").setImageOffset(20);

        final City[] cities = {seattle, sanFrancisco, losAngeles, newYork, orlando, charleston, austin, honolulu, phoenix, denver, boston, nashville};

        saveCities(connection, cities);

        return cities;
    }

    private void saveTickets(Connection connection, Ticket[] tickets) throws SQLException {
        String query = "INSERT INTO ticket (id, amount_available, price, seat_group) VALUES ";
        String[] values = new String[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            Ticket ticket = tickets[i];
            values[i] = "(" + SQLFormatter.formatUUID(ticket.getId()) + ", " + ticket.getAmountAvailable() + ", " + ticket.getPrice() + ", " + SQLFormatter.formatString(ticket.getSeatGroup()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Ticket[] generateTickets(Connection connection) throws SQLException {
        final String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        final Ticket[] tickets = new Ticket[letters.length];

        for (int i = 0; i < letters.length; i++) {
            tickets[i] = new Ticket().setId(UUID.randomUUID()).setAmountAvailable(RandomUtils.randomInteger(100)).setPrice((letters.length - i) * 10 + RandomUtils.randomInteger(150)).setSeatGroup("Group " + letters[i]);
        }

        saveTickets(connection, tickets);

        return tickets;
    }

    private void saveVenues(Connection connection, Venue[] venues) throws SQLException {
        String query = "INSERT INTO venue (id, city_id, location, timestamp) VALUES ";
        String[] values = new String[venues.length];
        for (int i = 0; i < venues.length; i++) {
            Venue venue = venues[i];
            values[i] = "(" + SQLFormatter.formatUUID(venue.getId()) + ", " + SQLFormatter.formatUUID(venue.getCityId()) + ", " + SQLFormatter.formatString(venue.getLocation()) + ", " + SQLFormatter.formatTimestamp(venue.getTimestamp()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Venue[] generateVenues(Connection connection, City[] cities) throws SQLException {
        final int millisInDay = 24 * 60 * 60 * 1000;
        Arrays.sort(cities, (city1, city2) -> RandomUtils.randomInteger(10) < 5 ? -1 : 1);
        Date date = new Date();
        date.setTime(date.getTime() + RandomUtils.randomInteger(millisInDay));
        final Venue[] venues = new Venue[10 * cities.length];

        for (int i = 0; i < cities.length; i++) {
            String[] locations = cities[i].getLocations();
            String location = locations[RandomUtils.randomInteger(locations.length)];
            for (int j = 0; j < 10; j++) {
                date.setTime(date.getTime() + millisInDay);
                venues[10 * i + j] = new Venue().setId(UUID.randomUUID()).setCityId(cities[i].getId()).setLocation(location).setTimestamp(new Date(date.getTime()));
            }
            date.setTime(date.getTime() + RandomUtils.randomInteger(millisInDay));
        }

        saveVenues(connection, venues);

        return venues;
    }

    private void saveEvents(Connection connection, Event[] events) throws SQLException {
        String query = "INSERT INTO event (id, artist_id, ticket_ids, title, venue_id) VALUES ";
        String[] values = new String[events.length];
        for (int i = 0; i < events.length; i++) {
            Event event = events[i];
            values[i] = "(" + SQLFormatter.formatUUID(event.getId()) + ", " + SQLFormatter.formatUUID(event.getArtistId()) + ", " + SQLFormatter.formatUUIDArray(event.getTicketIds()) + ", " + SQLFormatter.formatString(event.getTitle()) + ", " + SQLFormatter.formatUUID(event.getVenueId()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Event[] generateEvents(Connection connection, Artist[] artists, Venue[] venues) throws SQLException {
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < artists.length; i++) {
            for (int j = 0; j < venues.length; j++) {
                Ticket[] tickets = generateTickets(connection);
                UUID[] ticketIds = new UUID[tickets.length];
                for (int k = 0; k < tickets.length; k++) {
                    ticketIds[k] = tickets[k].getId();
                }
                Event event = new Event().setId(UUID.randomUUID()).setArtistId(artists[i].getId()).setVenueId(venues[j].getId()).setTitle(artists[i].getTour()).setTicketIds(ticketIds);
                events.add(event);
            }
        }
        Event[] eventsArray = new Event[events.size()];
        for (int i = 0; i < events.size(); i++) {
            eventsArray[i] = events.get(i);
        }
        saveEvents(connection, eventsArray);
        return eventsArray;
    }

    public void generateData(String password) throws SQLException {
        if (authenticationService.checkAdminPrivilege(password)) {
            Connection connection = dataSource.getConnection();
            final var statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS event CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS venue CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS ticket CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS city CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS artist CASCADE");

            statement.executeUpdate("CREATE TABLE artist (id UUID PRIMARY KEY, name TEXT, genre TEXT, image TEXT, image_offset INT, description TEXT, tour TEXT)");
            statement.executeUpdate("CREATE TABLE city (id UUID PRIMARY KEY, image TEXT, image_offset INT, locations TEXT[], name TEXT, state TEXT)");
            statement.executeUpdate("CREATE TABLE ticket (id UUID PRIMARY KEY, amount_available INT, price DECIMAL, seat_group TEXT)");
            statement.executeUpdate("CREATE TABLE venue (id UUID PRIMARY KEY, city_id UUID REFERENCES city(id), location TEXT, timestamp TIMESTAMPTZ)");
            statement.executeUpdate("CREATE TABLE event (id UUID PRIMARY KEY, artist_id UUID REFERENCES artist(id), ticket_ids UUID[], title TEXT, venue_id UUID REFERENCES venue(id))");

            Artist[] artists = generateArtists(connection);
            City[] cities = generateCities(connection);
            Venue[] venues = generateVenues(connection, cities);
            generateEvents(connection, artists, venues);
            connection.close();
        }
    }

    public ArrayList<Artist> getArtists() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM artist");
        final ArrayList<Artist> output = new ArrayList<>();

        while (resultSet.next()) {
            final Artist artist = new Artist().setId(resultSet.getObject("id", UUID.class)).setName(resultSet.getString("name")).setGenre(resultSet.getString("genre")).setImage(resultSet.getString("image")).setImageOffset(resultSet.getInt("image_offset")).setDescription(resultSet.getString("description")).setTour(resultSet.getString("tour"));
            
            output.add(artist);
        }

        connection.close();

        return output;
    }

    public ArrayList<City> getCities() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM city");
        final ArrayList<City> output = new ArrayList<>();

        while (resultSet.next()) {
            final City city = new City().setId(resultSet.getObject("id", UUID.class)).setImage(resultSet.getString("image")).setImageOffset(resultSet.getInt("image_offset")).setLocations((String[]) resultSet.getArray("locations").getArray()).setName(resultSet.getString("name")).setState(resultSet.getString("state"));
            
            output.add(city);
        }

        connection.close();

        return output;
    }

    public ArrayList<Ticket> getTickets() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM ticket");
        final ArrayList<Ticket> output = new ArrayList<>();

        while (resultSet.next()) {
            final Ticket ticket = new Ticket().setId(resultSet.getObject("id", UUID.class)).setAmountAvailable(resultSet.getInt("amount_available")).setPrice(resultSet.getDouble("price")).setSeatGroup(resultSet.getString("seat_group"));
            
            output.add(ticket);
        }

        connection.close();

        return output;
    }

    public ArrayList<Venue> getVenues() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM venue");
        final ArrayList<Venue> output = new ArrayList<>();

        while (resultSet.next()) {
            final Venue venue = new Venue().setId(resultSet.getObject("id", UUID.class)).setCityId(resultSet.getObject("city_id", UUID.class)).setLocation(resultSet.getString("location")).setTimestamp(resultSet.getTimestamp("timestamp"));
            
            output.add(venue);
        }

        connection.close();

        return output;
    }

    public ArrayList<EventDTO> getEvents() throws SQLException {
        final ArrayList<Ticket> tickets = getTickets(); 
        final HashMap<UUID, Ticket> ticketById = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            ticketById.put(tickets.get(i).getId(), tickets.get(i));
        }

        final ArrayList<Venue> venues = getVenues(); 
        final HashMap<UUID, Venue> venueById = new HashMap<>();
        for (int i = 0; i < venues.size(); i++) {
            venueById.put(venues.get(i).getId(), venues.get(i));
        }

        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM event");
        final ArrayList<EventDTO> output = new ArrayList<>();

        while (resultSet.next()) {
            UUID[] ticketIds = (UUID[]) resultSet.getArray("ticket_ids").getArray();
            UUID venueId = resultSet.getObject("venue_id", UUID.class);

            Ticket[] eventTickets = new Ticket[ticketIds.length];
            for (int i = 0; i < ticketIds.length; i++) {
                eventTickets[i] = ticketById.get(ticketIds[i]);
            }

            final EventDTO event = new EventDTO().setId(resultSet.getObject("id", UUID.class)).setArtistId(resultSet.getObject("artist_id", UUID.class)).setTickets(eventTickets).setTitle(resultSet.getString("title")).setVenue(venueById.get(venueId));

            output.add(event);
        }

        connection.close();

        return output;
    }
}