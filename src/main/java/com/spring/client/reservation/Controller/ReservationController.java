package com.spring.client.reservation.Controller;

import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.client.reservation.domain.Reservation;
import com.spring.client.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/reservation/*")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {
	
	 private final ReservationService reservationService;
	 								 
	
	  @PostMapping("/orders")
	    public String orderSave(Reservation reservation, RedirectAttributes redirectAttributes) {
	        // 예약 정보를 저장
	        Reservation savedReservation = reservationService.reservationInsert(reservation);

	        // resNo를 Base64로 인코딩
	        String encodedResNo = Base64.getEncoder().encodeToString(savedReservation.getResNo().toString().getBytes());

	        // 인코딩된 resNo를 URL 경로에 포함시켜 리다이렉트
	        return "redirect:/reservation/orders/payment/" + encodedResNo;
	    }

	    @GetMapping("/orders/payment/{resNo}")
	    public String paymentDetail(@PathVariable("resNo") String encodedResNo, Model model) {
	        // Base64로 인코딩된 resNo를 디코딩
	        Long resNo = Long.parseLong(new String(Base64.getDecoder().decode(encodedResNo)));

	        // 예약 번호를 이용해 예약 정보를 가져옴
	        Reservation reservation = reservationService.resDetail(resNo);

	        // 모델에 예약 정보를 추가
	        model.addAttribute("reservation", reservation);

	        // 결제 상세 페이지로 이동
	        return "client/reservation/order";
	    }
	
	
}