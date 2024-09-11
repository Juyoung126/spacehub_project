package com.spring.client.reservation.service;

import java.util.List;

import com.spring.client.reservation.domain.Reservation;

public interface ReservationService {

	public Reservation reservationInsert(Reservation reservation);

	public Reservation resDetail(Long resNo);

	public List<Reservation> getByMemberNo(Long memberNo);
	public boolean isReservationExists(Long resNo);

	public void cancelScheduledDeletion(Long resNo);

	public List<Reservation> reservationList();

	
}
