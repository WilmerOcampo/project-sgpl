package com.wo.reservationservice.response;

public record ReservationResponse(Long id, Long userId, String userName, Long bookId, String bookTitle) {
    // ESTE MS TIENE QUE RECUPERAR NOMBRE DEL USUARIO SEGUN SU ID DEL MS AUTH-SERVICE
    // ESTE MS TIENE QUE RECUPERAR TITULO DEL LIBRO SEGUN SU ID DEL MS BOOK-SERVICE
}
