'use client';
import { useState, useEffect } from "react";
import axios from "axios";
import MessageBubble from "@/app/components/MessageBubble";
import MessageContainer from "@/app/components/MessageContainer";
import MessageText from "@/app/components/MessageTextArea";
import EmptyState from "@/app/components/EmptyState";
import { fetchChatCompletion } from "@/app/api/groqApi"; // Import fetchChatCompletion



interface ErrorStateProps {
  error: Error;
}


const ErrorState: React.FC<ErrorStateProps> = ({ error }) => {
  const [chatVisible, setChatVisible] = useState(false);
  const [messages, setMessages] = useState([
    { content: "Hi! How can I assist you with the bus app?", type: 1 } as const,
  ]);
  const [inputValue, setInputValue] = useState("");

  useEffect(() => {
    if (error) {
      console.error(error);
      setMessages((prev) => [
        ...prev,
        { content: "An error occurred. Please try again later.", type: 1 },
     ]);
    }
  }, [error]);

  const handleSendMessage = async (message: string) => {
    if (!message.trim()) return;

    // Add user message
    setMessages((prevMessages) => [
      ...prevMessages,
      { content: message, type: 0 }, // User message
    ]);

    try {
      const response = await fetchChatCompletion(message);  // Use fetchChatCompletion from Groq API

      // Add bot response
      setMessages((prev) => [
        ...prev,
        { content: response || "Sorry, I couldn't process that.", type: 1 },
      ]);

      setInputValue(""); // Clear input after sending
    } catch (error) {
      console.error("Error:", error);
      setMessages((prev) => [
        ...prev,
        { content: "Sorry, something went wrong.", type: 1 },
      ]);
    }
  };

  return (
    <>
      <div>
        <EmptyState title="Oops" subtitle="Something went wrong!" />
      </div>

      {/* Chat Bubble */}
      <div
        className="chat-bubble"
        onClick={() => setChatVisible((prev) => !prev)}
        style={{
          position: "fixed",
          bottom: "20px",
          right: "20px",
          width: "60px",
          height: "60px",
          backgroundColor: "#FF5A5F",
          borderRadius: "50%",
          color: "white",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          cursor: "pointer",
          boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
          zIndex: 1000,
        }}
      >
        <i className="fas fa-bus"></i>
      </div>

      {/* Chatbot Interface */}
      {chatVisible && (
        <div
          style={{
            position: "fixed",
            bottom: "100px",
            right: "20px",
            width: "300px",
            maxHeight: "400px",
            backgroundColor: "#fff",
            border: "1px solid #ccc",
            borderRadius: "10px",
            display: "flex",
            flexDirection: "column",
            boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
            zIndex: 1000,
          }}
        >
          <MessageContainer
            style={{
              padding: "20px",
              flex: 1,
              overflowY: "auto",
              display: "flex",
              flexDirection: "column",
            }}
          >
            {messages.map((msg, index) => (
              <MessageBubble
                key={index}
                text={msg.content}
                messageType={msg.type}
              />
            ))}
          </MessageContainer>
          <div
            style={{
              padding: "20px",
              borderTop: "1px solid #ccc",
              display: "flex",
              alignItems: "center",
            }}
          >
            <MessageText
              placeholder="Type a message..."
              value={inputValue}
              onChange={setInputValue}
              onPressEnter={(value) => {
                handleSendMessage(value);
                setInputValue("");
              }}
            />
          </div>
        </div>
      )}
    </>
  );
};

export default ErrorState;