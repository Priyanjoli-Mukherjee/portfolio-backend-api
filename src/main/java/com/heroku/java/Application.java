package com.heroku.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.UUID;
import java.sql.SQLException;

import com.heroku.java.kanban.Task;
import com.heroku.java.scrollr.ConversationDTO;
import com.heroku.java.scrollr.Message;
import com.heroku.java.scrollr.ScrollrService;
import com.heroku.java.scrollr.TweetDTO;
import com.heroku.java.scrollr.User;
import com.heroku.java.kanban.KanbanUser;
import com.heroku.java.concerto.Artist;
import com.heroku.java.concerto.City;
import com.heroku.java.concerto.ConcertoService;
import com.heroku.java.concerto.EventDTO;
import com.heroku.java.kanban.KanbanService;

@SpringBootApplication
@RestController
public class Application {
    private final KanbanService kanbanService;
    private final ConcertoService concertoService;
    private final ScrollrService scrollrService;

    @Autowired
    public Application(KanbanService kanbanService, ConcertoService concertoService, ScrollrService scrollrService) {
        this.kanbanService = kanbanService;
        this.concertoService = concertoService;
        this.scrollrService = scrollrService;
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<ArrayList<Task>> getTasks() throws SQLException {
        final ArrayList<Task> output = kanbanService.getTasks();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PostMapping(value = "/task", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws SQLException {
        final Task updatedTask = kanbanService.createTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PutMapping(value = "/task/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @RequestBody Task task) throws SQLException {
        final Task updatedTask = kanbanService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") UUID id) throws SQLException {
        kanbanService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/kanban-users", produces = "application/json")
    public ResponseEntity<ArrayList<KanbanUser>> getKanbanUsers() throws SQLException {
        final ArrayList<KanbanUser> users = kanbanService.getKanbanUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PostMapping(value = "/concerto-data", produces = "application/json")
    public ResponseEntity<Void> createConcertoData(@RequestBody String password) throws SQLException {
        concertoService.generateData(password);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/artists", produces = "application/json")
    public ResponseEntity<ArrayList<Artist>> getArtists() throws SQLException {
        final ArrayList<Artist> output = concertoService.getArtists();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/cities", produces = "application/json")
    public ResponseEntity<ArrayList<City>> getCities() throws SQLException {
        final ArrayList<City> output = concertoService.getCities();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/events", produces = "application/json")
    public ResponseEntity<ArrayList<EventDTO>> getEvents() throws SQLException {
        final ArrayList<EventDTO> output = concertoService.getEvents();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PostMapping(value = "/scrollr-data", produces = "application/json")
    public ResponseEntity<Void> createScrollrData(@RequestBody String password) throws SQLException {
        scrollrService.generateData(password);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/scrollr-users", produces = "application/json")
    public ResponseEntity<ArrayList<User>> getUsers() throws SQLException {
        final ArrayList<User> output = scrollrService.getUsers();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/current-user", produces = "application/json")
    public ResponseEntity<User> getCurrentUser() throws SQLException {
        final User output = scrollrService.getCurrentUser();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/tweets", produces = "application/json")
    public ResponseEntity<ArrayList<TweetDTO>> getTweets() throws SQLException {
        final ArrayList<TweetDTO> output = scrollrService.getTweets();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @GetMapping(value = "/conversations", produces = "application/json")
    public ResponseEntity<ArrayList<ConversationDTO>> getConversations() throws SQLException {
        final ArrayList<ConversationDTO> output = scrollrService.getConversations();
        return ResponseEntity.ok(output);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PostMapping(value = "/tweet", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetDTO tweet) throws SQLException {
        final TweetDTO updatedTweet = scrollrService.createTweet(tweet);
        return ResponseEntity.ok(updatedTweet);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PostMapping(value = "/message/{conversationId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Message> createMessage(@RequestBody Message message, @PathVariable("conversationId") UUID conversationId) throws SQLException {
        final Message updatedMessage = scrollrService.createMessage(message, conversationId);
        return ResponseEntity.ok(updatedMessage);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PostMapping(value = "/conversation", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ConversationDTO> createConversation(@RequestBody ConversationDTO conversation) throws SQLException {
        final ConversationDTO updatedConversation = scrollrService.createConversation(conversation);
        return ResponseEntity.ok(updatedConversation);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @PutMapping(value = "/tweet/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TweetDTO> updateTweet(@PathVariable("id") UUID id, @RequestBody TweetDTO tweet) throws SQLException {
        final TweetDTO updatedTweet = scrollrService.updateTweet(id, tweet);
        return ResponseEntity.ok(updatedTweet);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://priyanjoli-mukherjee.github.io"})
    @DeleteMapping(value = "/tweet/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") UUID id) throws SQLException {
        scrollrService.deleteTweet(id);
        return ResponseEntity.ok().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
