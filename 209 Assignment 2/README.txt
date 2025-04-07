My Audio Library Application

Overview: This project simulates a simple text‐based music application—similar in concept to streaming apps like Apple Music. It allows users to browse an online store for audio content (songs and audiobooks), download items into a personal library, and manage playback and playlists. The project demonstrates key object‐oriented programming concepts in Java, such as inheritance, polymorphism, interfaces, and exception handling.


Project Structure: 
• AudioContent.java – Abstract base class for all audio content providing common attributes and methods. 
• Song.java – Represents a song, extending AudioContent with additional fields for artist, composer, genre, and lyrics. 
• AudioBook.java – Represents an audiobook, extending AudioContent with attributes for author, narrator, chapter titles, and chapter content. 
• AudioContentStore.java – Simulates an online store by reading a data file (store.txt) to load available audio content; supports searching and downloading. • Library.java – Represents the user’s personal library for downloaded content. Provides methods for downloading, listing, deleting, and sorting content. 
• Playlist.java – Defines a playlist that holds various audio content objects and provides methods for managing and playing the playlist. 
• MyAudioUI.java – The main class that drives the text-based user interface for interacting with the application. 
• store.txt – A text file containing the audio content data (songs and audiobooks) for the store.



Some of the available commands include:

STORE : List all available audio content in the store.

SONGS : List all downloaded songs.

BOOKS : List all downloaded audiobooks.

PODCASTS : (Placeholder for future podcast functionality.)

ARTISTS : Display all unique artists from downloaded songs.

PLAYLISTS : List all created playlists.

DOWNLOAD : Download content from the store (specify a range by entering content numbers).

PLAYSONG : Play a song by entering its library index.

BOOKTOC : Display the table of contents for an audiobook.

PLAYBOOK : Play a specific chapter of an audiobook (enter the audiobook number and chapter).

DELSONG : Delete a song from your library (this also removes it from any playlist).

MAKEPL : Create a new playlist by providing a title.

PRINTPL : Print the contents of a specified playlist.

ADDTOPL : Add audio content to a playlist (by specifying content type and index).

DELFROMPL: Remove content from a playlist.

SORTBYYEAR, SORTBYNAME, SORTBYLENGTH: Sort songs in your library based on different criteria.

SEARCH, SEARCHA, SEARCHG: Search for content by title, artist, or genre.

DOWNLOADA, DOWNLOADG: Download content from the store by specifying an artist or genre.



Usage- How to Compile and Run:

Ensure you have the Java Development Kit (JDK) installed (Java 8 or higher is recommended).
Open a terminal (or command prompt) in the project directory.
Compile the project using the following command: javac *.java
Run the application with: java MyAudioUI



Author Information: • Name : Sonal Jain • Student Number: 501150655

Notes: This application simulates audio playback by printing information (e.g., lyrics, chapter text) to the console. Some features (like podcast management) are currently placeholders for future development. All audio content is loaded from the store.txt file; ensure that file is in the correct location when running the application.



Enjoy using the My Audio Library Application!