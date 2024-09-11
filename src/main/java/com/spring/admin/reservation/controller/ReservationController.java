package com.spring.admin.reservation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.client.reservation.domain.Reservation;
import com.spring.client.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("adminReservationController")
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class ReservationController {

	
	private final ReservationService reservationService;
	
	
	// 관리자 예약 목록 페이지
    @GetMapping("/reservationManager")
    public String getReservationManager(Model model) {
     List<Reservation> reservation = reservationService.reservationList();
     
     model.addAttribute("reservations", reservation);
        return "admin/respayManager/reservation";  // 해당 경로에 맞춰서 HTML 파일을 반환
    }
}
	

