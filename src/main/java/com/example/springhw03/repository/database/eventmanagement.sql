CREATE DATABASE eventmanagement_db;

-- VENUE TBL
CREATE TABLE venues(
                       venue_id SERIAL PRIMARY KEY,
                       venue_name VARCHAR NOT NULL ,
                       location VARCHAR NOT NULL
);

-- VENUE DATA
INSERT INTO venues (venue_name, location) VALUES
                                              ('Grand Hall', 'New York'),
                                              ('Sunset Arena', 'Los Angeles'),
                                              ('Ocean View Conference Center', 'Miami');

SELECT * FROM venues;

-- EVENT TBL
CREATE TABLE events(
                       event_id SERIAL PRIMARY KEY ,
                       event_name VARCHAR NOT NULL ,
                       event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
                       venue_id INTEGER NOT NULL
                           CONSTRAINT fk_venues
                               REFERENCES venues
                               ON UPDATE CASCADE ON DELETE CASCADE
);

-- EVENT DATA
INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('Tech Conference 2025', '2025-06-15', 1),
                                                          ('Music Festival', '2025-07-20', 2),
                                                          ('Business Summit', '2025-08-10', 3);

SELECT * FROM events;

-- ATTENDEE TBL
CREATE TABLE attendees(
                          attendee_id SERIAL PRIMARY KEY ,
                          attendee_name VARCHAR(100),
                          email VARCHAR
);

-- ATTENDEE DATA
INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('John Doe', 'john.doe@example.com'),
                                                 ('Jane Smith', 'jane.smith@example.com'),
                                                 ('Alice Johnson', 'alice.johnson@example.com'),
                                                 ('Bob Williams', 'bob.williams@example.com');

SELECT * FROM attendees;


-- EVENT-ATTENDEE TBL
CREATE TABLE event_attendee(
                               id SERIAL PRIMARY KEY ,
                               event_id INTEGER NOT NULL
                                   CONSTRAINT fk_events
                                       REFERENCES events
                                       ON UPDATE CASCADE ON DELETE CASCADE ,
                               attendee_id INTEGER NOT NULL
                                   CONSTRAINT fk_attendees
                                       REFERENCES attendees
                                       ON UPDATE CASCADE ON DELETE CASCADE

);

-- EVENT-ATTENDEE DATA
INSERT INTO event_attendee (event_id, attendee_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 3),
                                                       (2, 4),
                                                       (3, 1),
                                                       (3, 3);

SELECT * FROM event_attendee;

-- TESTING FOR REPO
SELECT att.attendee_id, attendee_name, email FROM attendees att
                                                      INNER JOIN event_attendee ett ON att.attendee_id = ett.attendee_id
WHERE event_id = 15;

INSERT INTO events(event_name, venue_id)
VALUES ('FoundYouTouri', 1)
RETURNING *;

INSERT INTO event_attendee(event_id, attendee_id) VALUES (5, 1);
INSERT INTO event_attendee(event_id, attendee_id) VALUES (5, 2);

UPDATE events SET venue_id = 2 WHERE event_id = 15;

DELETE FROM events WHERE event_id = 15;