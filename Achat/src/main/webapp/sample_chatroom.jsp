<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="chat-container" id="chat-container"></div>

    <div class="input-container">
        <input type="text" id="message-input" placeholder="Type a message...">
        <button onclick="sendMessage()">Send</button>
    </div>

    <script>
        function displayMessage(message, sender) {
            const chatContainer = document.getElementById("chat-container");
            const messageElement = document.createElement("div");
            messageElement.textContent = message;
            messageElement.classList.add("message", sender === "You" ? "right" : "left");
            chatContainer.appendChild(messageElement);
        }

        function sendMessage() {
            const messageInput = document.getElementById("message-input");
            const message = messageInput.value.trim();

            if (message === "") {
                return;
            }

            // メッセージを表示（自分のメッセージ）
            displayMessage(message, "You");

            // Ajaxリクエストを作成してサーバーにメッセージを送信
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "MessageServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onload = function() {
                if (xhr.status === 200) {
                    // サーバーからの応答を取得して表示（相手のメッセージ）
                    displayMessage(xhr.responseText, "Friend");
                } else {
                    console.error("Failed to send message");
                }
            };
            xhr.send("chat=" + encodeURIComponent(message)); // "chat"としてリクエストパラメータを送信

            messageInput.value = ""; // 入力欄をクリア
        }

        // 初期メッセージを表示（例: サーバーから取得したりセッションから取得したりする）
        displayMessage("Hello, how can I help you?", "Friend");
    </script>
</body>
</html>
