package com.brothers.eslesmeapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brothers.eslesmeapp.model.Bildirim;
import com.brothers.eslesmeapp.model.CevapKaydet;
import com.brothers.eslesmeapp.model.Cozenler;
import com.brothers.eslesmeapp.model.Room;
import com.brothers.eslesmeapp.model.RoomKaydet;
import com.brothers.eslesmeapp.model.Sorular;
import com.brothers.eslesmeapp.model.Testler;
import com.brothers.eslesmeapp.model.User;
import com.brothers.eslesmeapp.repository.RoomRepository;
import com.brothers.eslesmeapp.repository.TestRepository;
import com.brothers.eslesmeapp.repository.UserRepository;
import com.brothers.eslesmeapp.tools.BildirimKodlari;
import com.brothers.eslesmeapp.tools.CreateDynamicLinkFirebase;
import com.brothers.eslesmeapp.tools.PuanHesapla;
import com.brothers.eslesmeapp.tools.PushNotificationServiceImpl;

@RestController
@RequestMapping("/room")
public class RoomController {
private RoomRepository roomRepository;
private UserRepository userRepository;
private TestRepository testRepository;

	
	public RoomController(RoomRepository roomRepository,UserRepository userRepository,TestRepository testRepository) {
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
		this.testRepository = testRepository;
	}
	
	@PostMapping("/id")
	public Optional<Room> getRoomById(@RequestParam String id) {
		return roomRepository.findById(id);
	}
	
	@PostMapping("/getTest")
	public Optional<Testler> getTestByRoomId(@RequestParam String roomId) {
		String roomTestId = roomRepository.findById(roomId).get().getTestId();
		Optional<Testler> test = testRepository.findById(roomTestId);
		return test;
	}
	
	@PostMapping("/cevapKaydet")
	public Double cevapKaydet(@RequestBody CevapKaydet cevapKaydet) {
		Room room = roomRepository.findById(cevapKaydet.getRoomId()).get();
		List<Integer> olusturanCevaplari = room.getOlusturanCevaplari();
		List<Integer> cozenCevaplari = cevapKaydet.getCevaplar();
		List<Sorular> sorular = testRepository.findById(room.getTestId()).get().getSorular();
		double puan = new PuanHesapla(olusturanCevaplari, cozenCevaplari, sorular).hesapla();
		
		Cozenler cozen = new Cozenler(cevapKaydet.getUid(),cevapKaydet.getName(),cevapKaydet.getCevaplar(),puan);
		room.getCozenler().add(cozen);
		this.roomRepository.save(room);
		User user = this.userRepository.findByUid(cevapKaydet.getUid()).get();
		user.getUsersRoom().getMySharedWithMeRooms().add(cevapKaydet.getRoomId());
		this.userRepository.save(user);
		
		User olusturanUser = this.userRepository.findByUid(room.getOlusturanUid()).get();
		olusturanUser.getBildirim().add(new Bildirim(BildirimKodlari.testCozuldu.getName(),user.getUid(),room.getTestId(),room.getId()));
		
		String olusturanToken = olusturanUser.getToken();
		new PushNotificationServiceImpl().sendPushNotification(olusturanToken,"Test Çözüldü",user.getName()+" arkadaşın gönderdiğin testi çözdü, sonucu görmek için hemen tıkla!");
		this.userRepository.save(olusturanUser);
		return puan;
	}
	@PostMapping("notificationSend")
	public void notificationSend(@RequestParam String token,@RequestParam String message) {
	
		new PushNotificationServiceImpl().sendPushNotification(token,"Baslik",message);

	}
	
/*	@GetMapping("createShortLink")
	public String createShortLink() {
	
		return new CreateDynamicLinkFirebase().createShortURL();

	} */
	
	@PostMapping("/cozduklerim")
	private List<Room> getRoomsByUid(@RequestParam String uid,@RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(
	    	    Order.desc("id")));
		List<String> myRooms = userRepository.findByUid(uid).get().getUsersRoom().getMyRooms();
		return roomRepository.findByIdIn(myRooms,pageable).getContent();
	}
	
	@PostMapping("/save")
	public String saveTest(@RequestBody RoomKaydet roomKaydet) {
		System.out.println(roomKaydet.toString());
		Room room = this.roomRepository.save(new Room(roomKaydet.getTestId(),roomKaydet.getTestAdi(),roomKaydet.getPaylasanUid(),roomKaydet.getPaylasanCevaplari()));
		String roomLink = new CreateDynamicLinkFirebase().createShortURL(room.getTestId(),room.getId());
		room.setRoomLink(roomLink);
		this.roomRepository.save(room);
		User user = userRepository.findByUid(roomKaydet.getPaylasanUid()).get();
		user.getUsersRoom().getMyRooms().add(room.getId());
		
		this.userRepository.save(user);
		
		return roomLink;
	}
	
	@PostMapping("/delete")
	private void deleteRoom(@RequestParam String roomId) {
		roomRepository.deleteById(roomId);
	}
	
	@PostMapping("/hide")
	private void hideRoom(@RequestParam String roomId,@RequestParam String uid,@RequestParam Boolean hide) {
		User user = userRepository.findByUid(uid).get();
		user.getUsersRoom().getMyRooms().remove(roomId);
		user.getUsersRoom().getMyHiddenRooms().add(roomId);
		this.userRepository.save(user);
	}
	
	@PostMapping("/gizlediklerim")
	private List<Room> getGizlediklerim(@RequestParam String uid,@RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(
	    	    Order.desc("id")));
		User user = userRepository.findByUid(uid).get();
		List<String> myRooms = user.getUsersRoom().getMyHiddenRooms();
		return roomRepository.findByIdIn(myRooms,pageable).getContent();	
	}
	
	@PostMapping("/sharedWithMe")
	private List<Room> getSharedWithMe(@RequestParam String uid,@RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(
	    	    Order.desc("id")));
		User user = userRepository.findByUid(uid).get();
		List<String> myRooms = user.getUsersRoom().getMySharedWithMeRooms();
		return roomRepository.findByIdIn(myRooms,pageable).getContent();	
	}
	
	@PostMapping("/sharedHiddenRooms")
	private List<Room> getMySharedHiddenRooms(@RequestParam String uid,@RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(
	    	    Order.desc("id")));
		User user = userRepository.findByUid(uid).get();
		List<String> myRooms = user.getUsersRoom().getMySharedHiddenRooms();
		return roomRepository.findByIdIn(myRooms,pageable).getContent();	
	}
	
	@PostMapping("/getTestResultByRoom")
	private List<Cozenler> getTestResultByRoom(@RequestParam String roomId) {
		Room room = roomRepository.findById(roomId).get();
		return room.getCozenler();
	}
}
