/* 
 * Name: Sonal Jain
 * Student number: 501150655 
 */
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.text.AbstractDocument.Content;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{

		if (content.getType().equals(Song.TYPENAME))
		{
			if (songs.contains(content))
			{
				errorMsg= "Song "+content.getTitle()+ " already downloaded";
				return false;
				//throw new AudioContentNotFoundException( "Song already downloaded");
			}
			songs.add((Song)content);
			return true;
		}
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			if (audiobooks.contains(content))
			{
				errorMsg= "Audiobook already downloaded";
					return false;
				//throw new AudioContentNotFoundException(  "AudioBook already downloaded");
				
			}
			audiobooks.add((AudioBook)content);
			return true;
		}
		return false;
		
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for(int i=0;i<audiobooks.size();i++)
		{
			int index=i+1;
			System.out.print(""+ index+ ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			
			String title= playlists.get(i).getTitle();
			System.out.print("" + index + ". " + title);
			System.out.println();	
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		ArrayList<String> myArtists= new ArrayList<>();
		String artist= "";
		for(int i=0;i<songs.size();i++)
		{
			artist=songs.get(i).getArtist();
			if(!isIn(myArtists, artist))
			myArtists.add(artist);

			
		}
		
		int index=0;
		for(int i=0;i<myArtists.size();i++)
		{
			System.out.println((index+1)+ ". " + myArtists.get(i));
			index++;
		}
		
	}

	public boolean isIn(ArrayList<String> artistArray, String artist)
	{
		for(int j=0;j<artistArray.size();j++)
			{
				if(artist==artistArray.get(j))
				{
					return true;
				}
			}
		return false;	
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		ArrayList<AudioContent> plContent;
		if(index>0 && index<=songs.size())
		{
			String songtitle= songs.get(index-1).getTitle();
			songs.remove(index-1);
		
			for(int i=0;i<playlists.size();i++)
			{
				plContent = playlists.get(i).getContent();
				for(int j=0;j<plContent.size();j++)
				{
					if(plContent.get(j).getTitle().equals(songtitle))
					{
						plContent.remove(j);
						
					}
				}
			}
		}
		throw new AudioContentNotFoundException("Song " + index + " not found");
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
	
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		@Override
		public int compare(Song a1, Song a2)
		{
			return a1.getYear()-a2.getYear();
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
	 Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a1, Song a2)
		{
			return a1.getLength()-a2.getLength();
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{
			throw new AudioContentNotFoundException("Song Not Found");
		}
		songs.get(index-1).play();
		
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		if(index>0 && index<=audiobooks.size())
		{
			AudioBook AudioBookatIndex=audiobooks.get(index-1);
			if(chapter>0 && chapter<=AudioBookatIndex.getNumberOfChapters())
			{
				AudioBookatIndex.selectChapter(chapter);
				
				AudioBookatIndex.play();
				
			}
			throw new AudioContentNotFoundException("Audiobook not found");
		}
		throw new AudioContentNotFoundException("Audiobook not found");
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		if(index>0 && index<=audiobooks.size())
		{
			audiobooks.get(index-1).printTOC();
			
		}
		throw new AudioContentNotFoundException("Audiobook does not exist in the library.");
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		for(int i=0;i<playlists.size();i++) 
		{
			if(playlists.get(i).getTitle().equals(title))
			{
				
				throw new AudioContentNotFoundException("Playlist " + title + " Already Exists");
			}
		}
		
		playlists.add(new Playlist(title));
	
		
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		for(int i=0;i<playlists.size();i++)
		{
			
			if(playlists.get(i).getTitle().equals(title))
			{
				
				playlists.get(i).printContents();
				
			}
		}
		throw new AudioContentNotFoundException("Playlist not found");
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		for(int i=0;i<playlists.size();i++)
		{
			if(playlists.get(i).getTitle().equals(playlistTitle))
			{
				playlists.get(i).playAll();
			}
		}
		throw new AudioContentNotFoundException("Playlist not found");
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		for(int i=0;i<playlists.size();i++)
		{
			if(playlists.get(i).getTitle().equals(playlistTitle))
			{
				playlists.get(i).play(indexInPL);
				
			}
		}
		throw new AudioContentNotFoundException("Playlist not found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		AudioContent content = null;
		
		if (type.equalsIgnoreCase("SONG"))
		{
			if (index < 1 || index > songs.size())
			{
				throw new AudioContentNotFoundException("Song Not Found");
			}
			content = songs.get(index-1);
		}
		else if (type.equalsIgnoreCase("AUDIOBOOK"))
		{
			if (index < 1 || index > audiobooks.size())
			{
				throw new AudioContentNotFoundException("AudioBook Not Found");
			}
			content = audiobooks.get(index-1);
		}
		if(content!=null){
			for(int i=0;i<playlists.size();i++)
				{
					if(playlistTitle.equals(playlists.get(i).getTitle()))
					{
						playlists.get(i).addContent(content);
					}
				}
				
			}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		int plIndex = playlists.indexOf(new Playlist(title));
		
		if (plIndex == -1)
		{
			throw new AudioContentNotFoundException("Playlist Not Found");
			
		}
		Playlist pl = playlists.get(plIndex);
		
		// Delete Content
		if (!pl.contains(index))
		{
			throw new AudioContentNotFoundException("Content Not In Playlist");
			
		}
		pl.deleteContent(index);
		
	}
	
	
}


public class AudioContentNotFoundException extends RuntimeException
{
	public AudioContentNotFoundException(){}
	public AudioContentNotFoundException(String message)
	{ 
		super(message);
	}


}
