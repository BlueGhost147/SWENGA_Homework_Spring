package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value = "session")
public class SongService {
	private List<SongModel> songs = new ArrayList<SongModel>();

	/**
	 * Add song to List
	 * 
	 * @param song
	 */
	public void addSong(SongModel song) {
		songs.add(song);
	}

	/**
	 * Verify if list contains song with same id
	 * 
	 * @param song
	 * @return
	 */
	public boolean contains(SongModel song) {
		return songs.contains(song);
	}

	/**
	 * convenient method: true if list is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return songs.isEmpty();
	}

	/**
	 * try to find SongModel with given Id return model, otherwise null
	 * 
	 * @param id
	 * @return
	 */
	public SongModel getSongById(int id) {
		for (SongModel SongModel : songs) {
			if (SongModel.getId() == id) {
				return SongModel;
			}
		}
		return null;
	}

	/**
	 * return size of list
	 * 
	 * @return
	 */
	public int getSize() {
		return songs.size();
	}

	/**
	 * return list with all songs
	 * 
	 * @return
	 */
	public List<SongModel> getAllSongs() {
		return songs;
	}

	/**
	 * return a new list with all songs which contain the search string in the title, artist or album
	 * search string
	 * 
	 * @param searchString
	 * @return
	 */
	public List<SongModel> getFilteredSongs(String searchString) {

		if (searchString == null || searchString.equals("")) {
			return songs;
		}

		// List for results
		List<SongModel> filteredList = new ArrayList<SongModel>();

		// check every song
		for (SongModel SongModel : songs) {

			if ((SongModel.getSongName() != null && SongModel.getSongName().toLowerCase().contains(searchString.toLowerCase()))
					|| (SongModel.getArtist() != null && SongModel.getArtist().toLowerCase().contains(searchString.toLowerCase()))
					|| (SongModel.getAlbum() != null && SongModel.getAlbum().toLowerCase().contains(searchString.toLowerCase()))
					) {
				filteredList.add(SongModel);
			}
		}
		return filteredList;
	}

	/**
	 * TRICKY: remove songs with the id
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(int id) {
		return songs.remove(new SongModel(id, null, null, null,null));
	}
}
