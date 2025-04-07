/* 
 * Name: Sonal Jain
 * Student number: 501150655 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

import Song.Genre;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in); 
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int firstIndex=0;
				int lastIndex=0;
				
				System.out.print("From Store Content #: ");
				if (scanner.hasNextInt())
				{
					firstIndex = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt())
				{
					lastIndex = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				ArrayList<AudioContent> contentList = store.getContentList(firstIndex,lastIndex);
				if (contentList == null)
					System.out.println("Content Not Found in Store");
				else
				{
					for(AudioContent content: contentList)
					{
						if(mylibrary.download(content)){
						System.out.println(content.getType()+ " "+ content.getTitle() +" added to library.");
						}
						else{
							System.out.println(mylibrary.getErrorMessage());
						}
						
					}				
				}	
									
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				// Print error message if the song doesn't exist in the library
				int index=0;
				System.out.print("Song Number: ");
				if(scanner.hasNextInt())
				{
					index=scanner.nextInt();
				
					mylibrary.playSong(index);
					
				}
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index=0;
				System.out.print("Audio book number: ");
				if(scanner.hasNextInt())
				{
					index=scanner.nextInt();
					mylibrary.printAudioBookTOC(index);
				}
			// Print error message if the book doesn't exist in the library
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index=0;
				int chapter=0;
				System.out.print("Audio book number: ");
				
				if(scanner.hasNextInt())
				{
					index=scanner.nextInt();
					System.out.print("Chapter: ");
					chapter=scanner.nextInt();
					mylibrary.playAudioBook(index, chapter);
				}
				
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				System.out.print("Playlist Title: ");
				String plName= scanner.next();
				mylibrary.playPlaylist(plName);
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				System.out.print("Playlist Title: ");
				String plTitle= scanner.next();
				System.out.print("Content Number: ");
				int contentNum= scanner.nextInt();
				mylibrary.playPlaylist(plTitle, contentNum);
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int index=0;
				System.out.print("Library song #: ");
				if(scanner.hasNextInt())
				{
					index = scanner.nextInt();
					mylibrary.deleteSong(index);
				}
				
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				System.out.print("Playlist Title: ");
				String playList= scanner.next();
				
				mylibrary.makePlaylist(playList);

			
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				System.out.print("Playlist Title: ");
				String pltitle= scanner.next();
				if(pltitle!=null){
				mylibrary.printPlaylist(pltitle);
				}
				else{System.out.println("playlist title is not valid");}
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				System.out.print("Playlist name: ");
				String plname= scanner.next();
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				String typename= scanner.next();
				System.out.print("Library Content #: ");
				int contentnum= scanner.nextInt();
				
				if(contentnum!=0 && typename!=null && plname!=null)
				{
					mylibrary.addContentToPlaylist(typename, contentnum, plname);
				}
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				System.out.print("Playlist title: ");
				String pltitle= scanner.next();
				System.out.print("Playlist content #: ");
				int contentnum= scanner.nextInt();
				mylibrary.delContentFromPlaylist(contentnum, pltitle);
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}
			else if(action.equalsIgnoreCase("SEARCH")) // search by title
			{
				System.out.print("Title: ");
				String title= scanner.nextLine();	
				store.search(title);
			}
			else if(action.equalsIgnoreCase("SEARCHA"))
			{
				System.out.print("Artist: ");
				String artist = scanner.nextLine();
				store.searchA(artist);
			}
			else if(action.equalsIgnoreCase("SEARCHG"))
			{
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				String genre = scanner.nextLine();
				Song.Genre g= Song.Genre.valueOf(genre);
				store.searchG(g);
			}
			else if(action.equalsIgnoreCase("DOWNLOADA"))
			{
				
				System.out.print("Artist Name: ");
				String artist= scanner.nextLine();
				
				ArrayList<AudioContent> contentList= store.downloadA(artist);
				if(contentList==null)
				{
					System.out.println("No content found in store");
				}
				else
				{
					for(AudioContent content: contentList)
					{
						if(mylibrary.download(content)){						
						System.out.println(content.getType()+ " "+ content.getTitle() +" added to library.");
						}
						else
						{
							System.out.println(mylibrary.getErrorMessage());
						}
					}
				}

			}
			else if(action.equalsIgnoreCase("DOWNLOADG"))
			{
				System.out.print("Genre: ");
				Song.Genre genre= Song.Genre.valueOf(scanner.nextLine());
				ArrayList<AudioContent> contentList= store.downloadG(genre);
				if(contentList==null)
				{
					System.out.println("No content found in store");
				}
				for(AudioContent content: contentList)
				{
					if(mylibrary.download(content)){					
					System.out.println("SONG "+ content.getTitle() +" added to library.");
					}
					else{
						System.out.println(mylibrary.getErrorMessage());
					}
				}
			}
		  }
		  catch (AudioContentNotFoundException e) {
			System.out.println(e);
		  }
		}
	}
}
