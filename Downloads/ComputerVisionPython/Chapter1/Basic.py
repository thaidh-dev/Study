import cv2
import mediapipe as mp
import time

# mở camera, sử dụng camera 0 của máy
cap = cv2.VideoCapture(0)
mpHands = mp.solutions.hands
# .Hands() có 4 tham số truyền vào -> xem giải thích ở link dưới
# https://google.github.io/mediapipe/solutions/hands.html#static_image_mode
hands = mpHands.Hands()
mpDraw = mp.solutions.drawing_utils
cTime = 0
pTime = 0

while cap.isOpened():
    # img: video frame
    success, img = cap.read()
    # rgb là sử dụng 3 màu sắc cơ bản R(red - đỏ), G(green - xanh lục) và B(blue - xanh lam) để biểu diễn tất cả các màu sắc.
    # OpenCV đảo 2 kênh R và B, trở thành BGR
    imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    # Processes an RGB image and returns the hand landmarks and handedness of each detected hand.
    results = hands.process(imgRGB)

    print(results.multi_hand_landmarks)
    if results.multi_hand_landmarks:
        # "multi_hand_landmarks" field that contains the hand landmarks on each detected hand
        for handLms in results.multi_hand_landmarks:
            # handLms: tương đương vs 1 tay
            # handLms.landmark: list các landmark trên 1 tay
            for id, lm in enumerate(handLms.landmark):
                # chiều dài, rộng, cao của video frame đc mở ra
                h, w, c = img.shape
                # tọa độ của 21 điểm landmark trên bàn tay
                cx, cy = int(lm.x * w), int(lm.y * h)
                if id == 4:
                    # vẽ điểm landmark(chỉnh màu, độ to, ...) ở đầu ngón cái
                    # 21 điểm Hand Landmark: https://google.github.io/mediapipe/solutions/hands.html#hand-landmark-model
                    cv2.circle(img, (cx, cy), 15, (255, 0, 255), cv2.FILLED)
                    print(id, cx, cy)

            # đánh dấu 21 điểm landmark trên bàn tay và nối các điểm lại vs nhau (mpHands.HAND_CONNECTIONS)
            mpDraw.draw_landmarks(img, handLms, mpHands.HAND_CONNECTIONS)

    # tính fps
    cTime = time.time()
    fps = 1 / (cTime - pTime)
    pTime = cTime
    # viết chữ lên video frame được mở ra (viết fps)
    cv2.putText(img, str(int(fps)), (10, 70), cv2.FONT_HERSHEY_PLAIN, 3, (255, 0, 255), 3)

    # show video frame vs tittle 'Image'
    cv2.imshow("Image", img)
    cv2.waitKey(1)
