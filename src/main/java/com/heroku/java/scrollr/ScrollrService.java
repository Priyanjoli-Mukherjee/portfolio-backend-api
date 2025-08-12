package com.heroku.java.scrollr;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.java.utils.ListUtils;
import com.heroku.java.utils.AuthenticationService;
import com.heroku.java.utils.RandomUtils;
import com.heroku.java.utils.SQLFormatter;

@Service
public class ScrollrService {
    private final DataSource dataSource;
    private final AuthenticationService authenticationService;

    @Autowired 
    public ScrollrService(DataSource dataSource, AuthenticationService authenticationService) {
        this.dataSource = dataSource;
        this.authenticationService = authenticationService;
    }

    private void saveUsers(Connection connection, User[] users) throws SQLException {
        String query = "INSERT INTO scrollr_user (id, name, twitter_handle, image) VALUES ";
        String[] values = new String[users.length];
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            values[i] = "(" + SQLFormatter.formatUUID(user.getId()) + ", " + SQLFormatter.formatString(user.getName()) + ", " + SQLFormatter.formatString(user.getTwitterHandle()) + ", " + SQLFormatter.formatString(user.getImage()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private User[] generateUsers(Connection connection) throws SQLException {
        final User user1 = new User().setId(UUID.randomUUID()).setName("Ethel").setTwitterHandle("@oldtimerethel").setImage("/images/scrollr/ethel.jpeg");
        final User user2 = new User().setId(UUID.randomUUID()).setName("JimBob").setTwitterHandle("@jimbobWW2").setImage("/images/scrollr/jimbob.jpeg");
        final User user3 = new User().setId(UUID.randomUUID()).setName("Gertrude").setTwitterHandle("@Gertrude1920").setImage("/images/scrollr/gertrude.jpeg");
        final User user4 = new User().setId(UUID.randomUUID()).setName("Priya").setTwitterHandle("@Priya").setImage("/images/scrollr/priya.jpeg");

        final User[] users = {user1, user2, user3, user4};

        saveUsers(connection, users);

        return users;
    }

    private String randomHashtag() {
        WeightedHashtag mondayMotivation = new WeightedHashtag().setTag("MondayMotivation").setWeight(5);
        WeightedHashtag tacoTuesday = new WeightedHashtag().setTag("TacoTuesday").setWeight(4);
        WeightedHashtag workoutWednesday = new WeightedHashtag().setTag("WorkoutWednesday").setWeight(1);
        WeightedHashtag throwbackThursday = new WeightedHashtag().setTag("ThrowbackThursday").setWeight(9);
        WeightedHashtag fridayVibes = new WeightedHashtag().setTag("FridayVibes").setWeight(6);
        WeightedHashtag sillySaturday = new WeightedHashtag().setTag("SillySaturday").setWeight(4);
        WeightedHashtag sundayFunday = new WeightedHashtag().setTag("SundayFunday").setWeight(2);
        WeightedHashtag weekendVibes = new WeightedHashtag().setTag("WeekendVibes").setWeight(7);
        WeightedHashtag fitnessMotivation = new WeightedHashtag().setTag("FitnessMotivation").setWeight(4);
        WeightedHashtag ai = new WeightedHashtag().setTag("AI").setWeight(20);
        WeightedHashtag[] weightedHashtags = {mondayMotivation, tacoTuesday, workoutWednesday, throwbackThursday, fridayVibes, sillySaturday, sundayFunday, weekendVibes, fitnessMotivation, ai};
        ArrayList<String> hashtags = new ArrayList<>();
        for (int i = 0; i < weightedHashtags.length; i++) {
            WeightedHashtag hashtag = weightedHashtags[i];
            for (int j = 0; j < hashtag.getWeight(); j++) {
                hashtags.add("#" + hashtag.getTag());
            }
        }
        return RandomUtils.randomListElement(hashtags);
    }

    private void saveTweets(Connection connection, Tweet[] tweets) throws SQLException {
        String query = "INSERT INTO tweet (id, message, replying_to, timestamp, user_id) VALUES ";
        String[] values = new String[tweets.length];
        for (int i = 0; i < tweets.length; i++) {
            Tweet tweet = tweets[i];
            values[i] = "(" + SQLFormatter.formatUUID(tweet.getId()) + ", " + SQLFormatter.formatString(tweet.getMessage()) + ", " + SQLFormatter.formatUUID(tweet.getReplyingTo()) + ", " + SQLFormatter.formatTimestamp(tweet.getTimestamp()) + ", " + SQLFormatter.formatUUID(tweet.getUserId()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Tweet[] generateTweets(Connection connection, User[] users) throws SQLException {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            UUID userId = RandomUtils.randomArrayElement(users).getId();
            String message = RandomUtils.randomText();
            int numHashtags = RandomUtils.randomInteger(5);
            ArrayList<String> hashtags = new ArrayList<>();
            for (int j = 0; j < numHashtags; j++) {
                hashtags.add(randomHashtag());
            }
            message += String.join(" ", ListUtils.unique(hashtags));
            final Tweet tweet = new Tweet().setId(UUID.randomUUID()).setMessage(message).setTimestamp(RandomUtils.randomDate()).setUserId(userId);
            tweets.add(tweet);
        }

        tweets.sort((tweet1, tweet2) -> tweet1.getTimestamp().compareTo(tweet2.getTimestamp()));

        Collections.reverse(tweets);

        for (int i = 0; i < tweets.size(); i++) {
            Tweet tweet = tweets.get(i);
            if (RandomUtils.randomBoolean()) {
                tweet.setReplyingTo(tweets.get(RandomUtils.randomInteger(i)).getId());
            }
        }

        Collections.reverse(tweets);

        Tweet[] tweetsArray = new Tweet[tweets.size()];
        for (int i = 0; i < tweets.size(); i++) {
            tweetsArray[i] = tweets.get(i);
        }

        saveTweets(connection, tweetsArray);

        return tweetsArray;
    }

    private void saveMessages(Connection connection, Message[] messages) throws SQLException {
        String query = "INSERT INTO message (id, text, twitter_handle, timestamp) VALUES ";
        String[] values = new String[messages.length];
        for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            values[i] = "(" + SQLFormatter.formatUUID(message.getId()) + ", " + SQLFormatter.formatString(message.getText()) + ", " + SQLFormatter.formatString(message.getTwitterHandle()) + ", " + SQLFormatter.formatTimestamp(message.getTimestamp()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Message[] generateMessages(Connection connection, User user1, User user2) throws SQLException {
        Message[] messages = new Message[10];
        for (int i = 0; i < messages.length; i++) {
            User user = RandomUtils.randomBoolean() ? user1 : user2;
            Message message = new Message().setId(UUID.randomUUID()).setText(RandomUtils.randomText()).setTimestamp(RandomUtils.randomDate()).setTwitterHandle(user.getTwitterHandle());
            messages[i] = message;
        }
        saveMessages(connection, messages);
        return messages;
    }

    private void saveConversation(Connection connection, Conversation[] conversations) throws SQLException {
        String query = "INSERT INTO conversation (id, message_ids, user_ids) VALUES ";
        String[] values = new String[conversations.length];
        for (int i = 0; i < conversations.length; i++) {
            Conversation conversation = conversations[i];
            values[i] = "(" + SQLFormatter.formatUUID(conversation.getId()) + ", " + SQLFormatter.formatUUIDArray(conversation.getMessageIds()) + ", " + SQLFormatter.formatUUIDArray(conversation.getUserIds()) + ")";
        }
        query += String.join(", ", values);
        final var statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    private Conversation[] generateConversations(Connection connection, User[] users) throws SQLException {
        ArrayList<Conversation> conversations = new ArrayList<>();
        for (int i = 0; i < users.length - 1; i++) {
            Message[] messages = generateMessages(connection, users[i], users[users.length - 1]);
            UUID[] messageIds = new UUID[messages.length];
            for (int j = 0; j < messages.length; j++) {
                messageIds[j] = messages[j].getId();
            }
            UUID[] userIds = {users[i].getId(), users[users.length - 1].getId()};
            Conversation conversation = new Conversation().setId(UUID.randomUUID()).setMessageIds(messageIds).setUserIds(userIds);
            conversations.add(conversation);
        }
        Conversation[] convos = new Conversation[conversations.size()];
        for (int i = 0; i < conversations.size(); i++) {
            convos[i] = conversations.get(i);
        }
        saveConversation(connection, convos);
        return convos;
    }

    public void generateData(String password) throws SQLException {
        if (authenticationService.checkAdminPrivilege(password)) {
            Connection connection = dataSource.getConnection();
            final var statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS scrollr_user CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS tweet CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS message CASCADE");
            statement.executeUpdate("DROP TABLE IF EXISTS conversation CASCADE");

            statement.executeUpdate("CREATE TABLE scrollr_user (id UUID PRIMARY KEY, name TEXT, twitter_handle TEXT, image TEXT)");
            statement.executeUpdate("CREATE TABLE tweet (id UUID PRIMARY KEY, message TEXT, replying_to UUID REFERENCES tweet(id), timestamp TIMESTAMPTZ, user_id UUID REFERENCES scrollr_user(id))");
            statement.executeUpdate("CREATE TABLE message (id UUID PRIMARY KEY, text TEXT, twitter_handle TEXT, timestamp TIMESTAMPTZ)");
            statement.executeUpdate("CREATE TABLE conversation (id UUID PRIMARY KEY, message_ids UUID[], user_ids UUID[])");

            User[] users = generateUsers(connection);
            generateTweets(connection, users);
            generateConversations(connection, users);
            connection.close();
        }
    }

    public ArrayList<User> getUsers() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM scrollr_user");
        final ArrayList<User> output = new ArrayList<>();

        while (resultSet.next()) {
            final User user = new User().setId(resultSet.getObject("id", UUID.class)).setName(resultSet.getString("name")).setTwitterHandle(resultSet.getString("twitter_handle")).setImage(resultSet.getString("image"));
            
            output.add(user);
        }

        connection.close();

        return output;
    }

    public User getCurrentUser() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM scrollr_user WHERE twitter_handle = '@Priya'");
        User output = null;

        if (resultSet.next()) {
            output = new User().setId(resultSet.getObject("id", UUID.class)).setName(resultSet.getString("name")).setTwitterHandle(resultSet.getString("twitter_handle")).setImage(resultSet.getString("image"));
        }

        connection.close();

        return output;
    }

    public ArrayList<TweetDTO> getTweets() throws SQLException {
        final ArrayList<User> users = getUsers();
        final HashMap<UUID, User> userById = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            userById.put(users.get(i).getId(), users.get(i));
        }

        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM tweet ORDER BY timestamp DESC");
        final ArrayList<TweetDTO> output = new ArrayList<>();

        while (resultSet.next()) {
            UUID userId = resultSet.getObject("user_id", UUID.class);
            User user = userById.get(userId);

            final TweetDTO tweet = new TweetDTO().setId(resultSet.getObject("id", UUID.class)).setName(user.getName()).setTwitterHandle(user.getTwitterHandle()).setImage(user.getImage()).setMessage(resultSet.getString("message")).setReplyingTo(resultSet.getObject("replying_to", UUID.class)).setTimestamp(resultSet.getTimestamp("timestamp")).setUserId(userId);

            output.add(tweet);
        }

        connection.close();

        return output;
    }

    private ArrayList<Message> getMessages() throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM message");
        final ArrayList<Message> output = new ArrayList<>();

        while (resultSet.next()) {
            final Message message = new Message().setId(resultSet.getObject("id", UUID.class)).setText(resultSet.getString("text")).setTwitterHandle(resultSet.getString("twitter_handle")).setTimestamp(resultSet.getTimestamp("timestamp"));
            
            output.add(message);
        }

        connection.close();

        return output;
    }

    public ArrayList<ConversationDTO> getConversations() throws SQLException {
        final ArrayList<User> users = getUsers();
        final HashMap<UUID, User> userById = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            userById.put(users.get(i).getId(), users.get(i));
        }

        final ArrayList<Message> messages = getMessages();
        final HashMap<UUID, Message> messageById = new HashMap<>();
        for (int i = 0; i < messages.size(); i++) {
            messageById.put(messages.get(i).getId(), messages.get(i));
        }

        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM conversation");
        final ArrayList<ConversationDTO> output = new ArrayList<>();

        while (resultSet.next()) {
            UUID[] messageIds = (UUID[]) resultSet.getArray("message_ids").getArray();
            
            Message[] conversationMessages = new Message[messageIds.length];
            for (int i = 0; i < messageIds.length; i++) {
                conversationMessages[i] = messageById.get(messageIds[i]);
            }
            
            UUID[] userIds = (UUID[]) resultSet.getArray("user_ids").getArray();
            
            User[] conversationUsers = new User[userIds.length];
            for (int i = 0; i < userIds.length; i++) {
                conversationUsers[i] = userById.get(userIds[i]);
            }
            
            final ConversationDTO conversation = new ConversationDTO().setId(resultSet.getObject("id", UUID.class)).setMessages(conversationMessages).setUsers(conversationUsers);

            output.add(conversation);
        }

        connection.close();

        return output;
    }

    public TweetDTO createTweet(TweetDTO tweet) throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        UUID id = UUID.randomUUID();
        tweet.setId(id);
        String sql = "INSERT INTO tweet (id, message, replying_to, timestamp, user_id) VALUES (" + SQLFormatter.formatUUID(id) + ", " + SQLFormatter.formatString(tweet.getMessage()) + ", " + SQLFormatter.formatUUID(tweet.getReplyingTo()) + ", " + SQLFormatter.formatTimestamp(tweet.getTimestamp()) + ", " + SQLFormatter.formatUUID(tweet.getUserId()) + ")";
        statement.executeUpdate(sql);
        connection.close();
        return tweet;
    }

    private void appendMessageToConversation(UUID messageId, UUID conversationId) throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        final var resultSet = statement.executeQuery("SELECT * FROM conversation WHERE id = " + SQLFormatter.formatUUID(conversationId));
        if (resultSet.next()) {
            UUID[] messageIds = (UUID[]) resultSet.getArray("message_ids").getArray();
            UUID[] updatedMessageIds = new UUID[messageIds.length + 1];
            for (int i = 0; i < messageIds.length; i++) {
                updatedMessageIds[i] = messageIds[i];
            }
            updatedMessageIds[updatedMessageIds.length - 1] = messageId;
            String sql = "UPDATE conversation SET message_ids = " + SQLFormatter.formatUUIDArray(updatedMessageIds) + " WHERE id = " + SQLFormatter.formatUUID(conversationId);
            statement.executeUpdate(sql);
        }
        connection.close();
    }

    public Message createMessage(Message message, UUID conversationId) throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        UUID id = UUID.randomUUID();
        message.setId(id);
        String sql = "INSERT INTO message (id, text, twitter_handle, timestamp) VALUES (" + SQLFormatter.formatUUID(id) + ", " + SQLFormatter.formatString(message.getText()) + ", " + SQLFormatter.formatString(message.getTwitterHandle()) + ", " + SQLFormatter.formatTimestamp(message.getTimestamp()) + ")";
        statement.executeUpdate(sql);
        connection.close();
        appendMessageToConversation(message.getId(), conversationId);
        return message;
    }

    public ConversationDTO createConversation(ConversationDTO conversation) throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        UUID id = UUID.randomUUID();
        conversation.setId(id);
        Message[] messages = conversation.getMessages();
        UUID[] messageIds = new UUID[messages.length];
        for (int i = 0; i < messageIds.length; i++) {
            messageIds[i] = messages[i].getId();
        }
        User[] users = conversation.getUsers();
        UUID[] userIds = new UUID[users.length];
        for (int i = 0; i < userIds.length; i++) {
            userIds[i] = users[i].getId();
        }
        String sql = "INSERT INTO conversation (id, message_ids, user_ids) VALUES (" + SQLFormatter.formatUUID(id) + ", " + SQLFormatter.formatUUIDArray(messageIds) + ", " + SQLFormatter.formatUUIDArray(userIds) + ")";
        statement.executeUpdate(sql);
        connection.close();
        return conversation;
    }

    public TweetDTO updateTweet(UUID id, TweetDTO tweet) throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        String sql = "UPDATE tweet SET message = " + SQLFormatter.formatString(tweet.getMessage()) + ", replying_to = " + SQLFormatter.formatUUID(tweet.getReplyingTo()) + ", timestamp = " + SQLFormatter.formatTimestamp(tweet.getTimestamp()) + ", user_id = " + SQLFormatter.formatUUID(tweet.getUserId()) + " WHERE id = " + SQLFormatter.formatUUID(id);
        statement.executeUpdate(sql);
        connection.close();
        return tweet;
    }

    public void deleteTweet(UUID id) throws SQLException {
        Connection connection = dataSource.getConnection();
        final var statement = connection.createStatement();
        String sql = "DELETE FROM tweet WHERE id = " + SQLFormatter.formatUUID(id);
        statement.executeUpdate(sql);
        connection.close();
    }
}
