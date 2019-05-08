package at.fh.swenga.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.enums.ParentalRating;
import at.fh.swenga.model.SongModel;
import at.fh.swenga.model.SongService;

@Controller
public class SongManagerController {

	@Autowired
	private SongService songService;

	@RequestMapping(value = { "/", "listSongs" })
	public String showAllSongs(Model model) {
		model.addAttribute("songs", songService.getAllSongs());
		return "listSongs";
	}

	@RequestMapping("/fillSongList")
	public String fillSongList(Model model) {
		Date now = new Date();
		
		int idInc = 100;
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"Under Pressure","Queen","Hot Space",new Date(),1.99,ParentalRating.Everyone));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"Man On The Moon","R.E.M.","Automatic For The People",new Date(),1.99,ParentalRating.Everyone));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"King for a Day","Battle Beast","Bringer of Pain",new Date(),1.29,ParentalRating.Mature));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"Hammer High","Hammerfall","Built To Last",new Date(),1.59,ParentalRating.Everyone));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"F.T.L.","Keldian","Outbound",new Date(),1.29,ParentalRating.Everyone));

		List<SongModel> ajfa = songService.getAllSongs();
		model.addAttribute("songs", songService.getAllSongs());
		return "listSongs";
	}

	@GetMapping("/addSong")
	public String showAddSongForm(Model model) {
		return "editSong";
	}

	@PostMapping("/addSong")
	public String addSong(@Valid SongModel newSongModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			return "forward:/listSongs";
		}

		SongModel song = songService.getSongById(newSongModel.getId());

		if (song != null) {
			model.addAttribute("errorMessage", "Song already exists!<br>");
		} else {
			songService.addSong(newSongModel);
			model.addAttribute("message", "New song " + newSongModel.getId() + " added.");
		}

		return "forward:/listSongs";
	}

	@GetMapping("/deleteSong")
	public String delete(Model model, @RequestParam int id) {
		boolean isRemoved = songService.remove(id);

		if (isRemoved) {
			model.addAttribute("warningMessage", "Song " + id + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no song with the ID " + id);
		}

		return "forward:/listSongs";
		//return showAllSongs(model);
	}

	@PostMapping("/searchSongs")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("songs", songService.getFilteredSongs(searchString));
		return "listSongs";
	}

	@GetMapping("/editSong")
	public String showChangeSongForm(Model model, @RequestParam int id) {

		SongModel song = songService.getSongById(id);

		if (song != null) {
			model.addAttribute("song", song);
			return "editSong";
		} else {
			model.addAttribute("errorMessage", "Couldn't find song with the ID " + id);
			return "forward:/listSongs";
		}
	}

	@PostMapping("/editSong")
	public String editSong(@Valid SongModel changedSongModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listSongs";
		}

		SongModel song = songService.getSongById(changedSongModel.getId());

		if (song == null) {
			model.addAttribute("errorMessage", "Song doesn't exist!<br>");
		} else {
			song.setId(changedSongModel.getId());
			song.setSongName(changedSongModel.getSongName());
			song.setArtist(changedSongModel.getArtist());
			song.setAlbum(changedSongModel.getAlbum());
			song.setReleaseDate(changedSongModel.getReleaseDate());
			song.setPrice(changedSongModel.getPrice());
			song.setParentalRating(changedSongModel.getParentalRating());
			model.addAttribute("message", "Changed song with the ID " + changedSongModel.getId());
		}

		return "forward:/listSongs";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error";
	}
}
