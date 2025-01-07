import React, { useState, useEffect } from "react";
import emailjs from "emailjs-com";  // Import EmailJS

// Sample notification service (for illustration)
const notifications = [
  "You have a new message.",
  "Your reservation has been confirmed.",
  "New updates are available for your properties.",
];

interface Notification {
  id: string;
  message: string;
}

const NotificationDropdown: React.FC = () => {
  const [newNotifications, setNewNotifications] = useState<Notification[]>([]);

  // Function to generate random numeric IDs with exactly three digits
  const generateRandomId = (prefix: string): string => {
    const randomNumber = Math.floor(Math.random() * 900) + 100; // Generates a random number between 100 and 999
    return prefix + randomNumber.toString(); // Returns the ID with the prefix and 3-digit number
  };

  // Function to generate random seat numbers (between 100 and 999)
  const generateRandomSeatNumber = (min: number, max: number): number => {
    return Math.floor(Math.random() * (max - min + 1)) + min; // Random number between min and max
  };

  // Function to send email confirmation with dynamic template
  const sendEmailConfirmation = (
    email: string,
    subject: string,
    message: string,
    type: "payment" | "reservation",  // Added type to choose template
    name: string = "Samah",            // Default dynamic field: Name of the user
    reservationId: string = generateRandomId(""),         // Generate random reservation ID
    amount: string = "2500",           // Default dynamic field: Amount (only for payment)
    paymentId: string = generateRandomId(""),            // Generate random payment ID
    seatN: string = generateRandomSeatNumber(100, 999).toString(), // Generate random seat number (between 100 and 999)
  ) => {
    const emailPayload = {
      to_email: email,
      subject: subject,
      name: name,
      reservationId: reservationId,
      amount: amount,
      paymentId: paymentId,
      seatN: seatN,
    };

    const templateId = type === "payment" ? "template_6eul28a" : "template_uafnf59"; // Use different templates based on type

    emailjs
      .send("service1", templateId, emailPayload, "hEDZF3qH-Bauwht_q")  // Use actual template ID and User ID
      .then(
        (response) => {
          console.log("Email sent successfully", response);
        },
        (error) => {
          console.error("Error sending email", error);
        }
      );
  };

  // Simulate order placement and send email confirmations
  const handlePaymentConfirmation = () => {
    setNewNotifications([ 
      ...newNotifications, 
      { id: "1", message: "Your payment has been confirmed!" },
    ]);

    const userEmail = "samahmn98@gmail.com"; // Use dynamic user email
    sendEmailConfirmation(
      userEmail,
      "Payment Confirmation",
      "Your payment has been confirmed!",
      "payment"  // Specify payment type
    );
  };

  const handleReservationConfirmation = () => {
    setNewNotifications([ 
      ...newNotifications, 
      { id: "2", message: "Your reservation has been confirmed!" },
    ]);

    const userEmail = "samahmn98@gmail.com"; // Use dynamic user email
    sendEmailConfirmation(
      userEmail,
      "Reservation Confirmation",
      "Your reservation has been confirmed!",
      "reservation"  // Specify reservation type
    );
  };

  // Simulate an event trigger for the example
  useEffect(() => {
    setTimeout(handlePaymentConfirmation, 2000); // Trigger payment confirmation after 2 seconds for testing
    setTimeout(handleReservationConfirmation, 5000); // Trigger reservation confirmation after 5 seconds for testing
  }, []);

  return (
    <div className="absolute right-0 top-12 mt-2 w-64 bg-white shadow-lg rounded-md border border-neutral-200">
      <div className="flex flex-col p-2">
        {newNotifications.length === 0 ? (
          <p className="text-center text-gray-500">No new notifications</p>
        ) : (
          newNotifications.map((notification) => (
            <div key={notification.id} className="p-2 hover:bg-gray-100 rounded-md cursor-pointer">
              {notification.message}
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default NotificationDropdown;
