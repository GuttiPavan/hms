package com.hms.hms.controller;

import com.hms.hms.entity.Bookings;
import com.hms.hms.entity.Property;
import com.hms.hms.entity.Rooms;
import com.hms.hms.repository.BookingsRepository;
import com.hms.hms.repository.PropertyRepository;
import com.hms.hms.repository.RoomsRepository;
import com.hms.hms.service.PdfService;
import com.hms.hms.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private PdfService pdfService;
    private PropertyRepository propertyRepository;
    private BookingsRepository bookingsRepository;
    private RoomsRepository roomsRepository;
    private SmsService smsService;


    public BookingController(PdfService pdfService, PropertyRepository propertyRepository, BookingsRepository bookingsRepository, RoomsRepository roomsRepository, SmsService smsService) {
        this.pdfService = pdfService;
        this.propertyRepository = propertyRepository;
        this.bookingsRepository = bookingsRepository;
        this.roomsRepository = roomsRepository;
        this.smsService = smsService;
    }

    @PostMapping("/create-booking")

    public ResponseEntity<?> createBookings(@RequestParam Long propertyId,
                                  @RequestBody Bookings bookings,
                                 @RequestParam String roomtype){
        Property property = propertyRepository.findById(propertyId).get();
        List<Rooms> room = roomsRepository.findRoomsByDateRange(bookings.getFromDate(),bookings.getToDate(),roomtype,property);
//        Rooms room = roomsRepository.findByPropertyIdAndType(propertyId,roomtype);

        for(Rooms rooms:room) {
            if (rooms.getCount() == 0) {
                return new ResponseEntity<>("No Rooms for:"+rooms.getDate(), HttpStatus.OK);
            }
        }

        Bookings savedBookings = bookingsRepository.save(bookings);


        if(savedBookings!=null){
        for(Rooms rooms:room){

            rooms.setCount(rooms.getCount()-1);
            roomsRepository.save(rooms);
        }}

        for(Rooms rooms:room){
            double totalPrice = rooms.getPerNight() *(room.size() - 1);
        }



     /* pdfService.generatePdf("D://hms_bookings\\conformation-order-"+savedBookings.getId()+".pdf",property);

       smsService.sendSms("+919515804424","Booking Conformed.booking id is:"+bookings.getId());


       smsService.sendWhatsAppMessage("whatsapp:+919515804424", "Booking Conformed.booking id is:" + bookings.getId());

      // smsService.sendEmail("pavangutti79@gmail.com", "Test email", "This is the body of the email.");*/

     //  return "Booking created successfully";

        return new ResponseEntity<>(room,HttpStatus.OK);

    }




}
